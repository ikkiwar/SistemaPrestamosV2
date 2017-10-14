
package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Cuota;

/**
 *
 * @author estuardo
 */

public class ControladorCuota {

    public void agregar(Cuota cuota) throws ErrorPrestamo {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(cuota.getFecha());

        Conexion conexion = new Conexion();

        cuotaBalance(cuota);
        
        if (conexion != null) {
            conexion.UID("INSERT INTO cuota (id_prestamo,num_cuota,valor,interes,fecha"
                    + ",capital,saldo_anterior,saldo_actualizado,mora) "
                    + "VALUES ('" + cuota.getId_prestamo() + "','" + cuota.getNum_cuota()
                    + "','" + cuota.getValor() + "','" + cuota.getInteres() + "','"
                    + fecha + "','" + cuota.getCapital() + "','" + cuota.getSaldo_anterior()
                    + "','" + cuota.getSaldo_actualizado() + "','" + cuota.getMora() + "')");
        } else {
            throw new ErrorPrestamo("Error al Insertar Datos", "ControladorCuota.agregar", "Error al Agregar Cuota");
        }

    }

    public void cuotaBalance(Cuota cuota){
        Conexion conexion = new Conexion();
        ResultSet resultado1,resultado2;
        double saldoEfectivo=0.0;
        double saldoCuentaPorCobrar=0.0;
        double efectivoNuevo;
        double cuentasPorCobrarNuevo;
        double capitalNuevo;
                  
        //OBTENIENDO LOS VALORES DE LAS CUENTAS DEL BALANCE GENERAL
        resultado1 = conexion.getValores("SELECT monto FROM balance WHERE id_cuenta=11");
        resultado2 = conexion.getValores("SELECT monto FROM balance WHERE id_cuenta=12");
        
        try {
              
            while (resultado1.next()) {
                saldoEfectivo= resultado1.getDouble("monto");

            }
            while (resultado2.next()) {
                saldoCuentaPorCobrar = resultado2.getDouble("monto");
            }

           

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("SALDO EFECTIVO " + saldoEfectivo);
        System.out.println("SALDO CUENTAS POR COBRAR " + saldoCuentaPorCobrar);
        
        efectivoNuevo= saldoEfectivo + cuota.getValor();
        cuentasPorCobrarNuevo = saldoCuentaPorCobrar - cuota.getValor();
        capitalNuevo = efectivoNuevo + cuentasPorCobrarNuevo;        
        
        //setiamos a la base los valores nuevos de las cuentas: efectivo, cuentas por cobrar y capital social
            conexion.UID("UPDATE balance SET monto='" + efectivoNuevo + "' WHERE id_cuenta=11");
            conexion.UID("UPDATE balance SET monto='" + cuentasPorCobrarNuevo + "' WHERE id_cuenta=12");
            conexion.UID("UPDATE balance SET monto='" + capitalNuevo + "' WHERE id_cuenta=31");
        
        
        
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
                // System.out.println("Estoy en el While");

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

        // System.out.println("Estoy al Inicio del metodo obtener Clientes !");
        try {

            resultado = conexion.getValores("SELECT * FROM cuota WHERE id_prestamo='" + id + "'");

            while (resultado.next()) {
                cuotas.add(new Cuota(resultado.getInt("id_prestamo"), resultado.getInt("num_cuota"),
                        resultado.getDouble("valor"), resultado.getDouble("interes"),
                        resultado.getDouble("capital"), resultado.getDate("fecha"),
                        resultado.getDouble("saldo_anterior"), resultado.getDouble("saldo_actualizado"),
                        resultado.getDouble("mora")));
                // System.out.println("Estoy en el While");

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

        // System.out.println("Estoy al Inicio del metodo obtener Clientes !");
        try {

            resultado = conexion.getValores("select MAX(fecha) FROM cuota WHERE id_prestamo='" + id + "'");

            while (resultado.next()) {
                reciente = resultado.getDate(1);              // System.out.println("Estoy en el While");

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
