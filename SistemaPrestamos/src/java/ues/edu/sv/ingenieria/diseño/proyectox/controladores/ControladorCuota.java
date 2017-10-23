package ues.edu.sv.ingenieria.diseño.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Cuota;
import ues.edu.sv.ingenieria.diseño.proyectox.servicios.Mensajeria;

/**
 *
 * @author estuardo
 */
public class ControladorCuota {

    ControladorBalance controladorBalance = new ControladorBalance();
    ControladorMovimientosER controladorMovimientos = new ControladorMovimientosER();

    Mensajeria mensajes = new Mensajeria();
    String mensaje1;
    String mensaje2;

    public void agregar(Cuota cuota) throws ErrorPrestamo {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(cuota.getFecha());

        Conexion conexion = new Conexion();

        
            controladorBalance.cuotaBalance(cuota);
            controladorMovimientos.cuotaMovimientosIngresosPorInteres(cuota);
            mensaje1 = "Transaccion Exitosa!!";
            mensaje2 = "Pago Agregado Correctamente!!";
            try {

                conexion.UID("INSERT INTO cuota (id_prestamo,num_cuota,valor,interes,fecha"
                        + ",capital,saldo_anterior,saldo_actualizado,mora) "
                        + "VALUES ('" + cuota.getId_prestamo() + "','" + cuota.getNum_cuota()
                        + "','" + cuota.getValor() + "','" + cuota.getInteres() + "','"
                        + fecha + "','" + cuota.getCapital() + "','" + cuota.getSaldo_anterior()
                        + "','" + cuota.getSaldo_actualizado() + "','" + cuota.getMora() + "')");

                controladorMovimientos.cuotaMovimientosIngresosPorMora(cuota);

                mensajes.info(mensaje1, mensaje2);
            } catch (Exception e) {
                throw new ErrorPrestamo("Error al Insertar Datos", "ControladorCuota.agregar", "Error al Agregar Cuota");

            }
        
    }

    public List<Cuota> obtener() throws ErrorPrestamo {

        List<Cuota> cuotas = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;

        System.out.println("Estoy al Inicio del metodo obtener Clientes !");

        try {

            resultado = conexion.getValores("SELECT * FROM cuota");

            while (resultado.next()) {
                cuotas.add(new Cuota(resultado.getInt("id_prestamo"), resultado.getInt("num_cuota"),
                        resultado.getDouble("valor"), resultado.getDouble("interes"),
                        resultado.getDouble("capital"), resultado.getDate("fecha"),
                        resultado.getDouble("saldo_anterior"), resultado.getDouble("saldo_actualizado"),
                        resultado.getDouble("mora")));

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorCuota.Obtener", "Error al Obtener los Clientes");
        }

        return cuotas;

    }

    public List<Cuota> obtenerPorPrestamo(int id) throws ErrorPrestamo {

        List<Cuota> cuotas = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;

        try {

            resultado = conexion.getValores("SELECT * FROM cuota WHERE id_prestamo='" + id + "'");

            while (resultado.next()) {
                cuotas.add(new Cuota(resultado.getInt("id_prestamo"), resultado.getInt("num_cuota"),
                        resultado.getDouble("valor"), resultado.getDouble("interes"),
                        resultado.getDouble("capital"), resultado.getDate("fecha"),
                        resultado.getDouble("saldo_anterior"), resultado.getDouble("saldo_actualizado"),
                        resultado.getDouble("mora")));

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorCuota.Obtener", "Error al Obtener los Clientes");
        }

        return cuotas;

    }

    public Date masreciente(int id) throws ErrorPrestamo {

        Date reciente = null;
        Conexion conexion = new Conexion();
        ResultSet resultado;

        try {

            resultado = conexion.getValores("select MAX(fecha) FROM cuota WHERE id_prestamo='" + id + "'");

            while (resultado.next()) {
                reciente = resultado.getDate(1);

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorCuota.Obtener", "Error al Obtener los Clientes");
        }

        return reciente;

    }

    public int obtenerMaxId(int idprestamo) throws ErrorPrestamo {
        int id = 0;
        ResultSet resultado;

        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT MAX(num_cuota) From cuota "
                    + "WHERE id_prestamo='" + idprestamo + "'");
            while (resultado.next()) {
                id = resultado.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        id = id + 1;
        return id;
    }

    public int cantidadCuotas(int idprestamo) {
        int cuotasPagadas = 0;

        ResultSet resultado;
        System.out.println("id del prestamo suministrado: " + idprestamo);
        System.out.println("Estoy en cantidadCuotas del Controlador");

        try {
            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT COUNT(num_cuota) "
                    + "FROM cuota where id_prestamo='" + idprestamo + "'");

            resultado.next();
            cuotasPagadas = resultado.getInt(1);

            System.out.println("El total de Cuotas pagadas son: " + cuotasPagadas);

        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);

        }

        return cuotasPagadas;

    }

}
