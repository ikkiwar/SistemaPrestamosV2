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
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.EstadoResultados;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.MovimientosER;
import ues.edu.sv.ingenieria.diseño.proyectox.servicios.Mensajeria;

/**
 *
 * @author erick
 */
public class ControladorMovimientosER {

    Mensajeria mensajes = new Mensajeria();
    String mensaje1;
    String mensaje2;

    //METODO PARA OBTENER LOS MOVIMIENTOS DE CADA CUENTA DEL ESTADO DE RESULTADOS
    public List<MovimientosER> obtener(EstadoResultados resultados) {
        List<MovimientosER> movimientos = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;

        if (conexion != null) {

            try {
                resultado = conexion.getValores("SELECT * FROM movimientos_ER WHERE id_cuenta='" + resultados.getId_cuenta() + "' ");

                while (resultado.next()) {

                    movimientos.add(new MovimientosER(resultado.getInt("id_cuenta"), resultado.getDate("fecha"), resultado.getDouble("monto")));

                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorMovimientosER.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return movimientos;
    }

    //METODO PARA TRABAJAR CON LOS MOVIMIENTOS DE LOS ESTADOS DE RESULTADOS EN INGRESOS POR MORA
    public void cuotaMovimientosIngresosPorMora(Cuota cuota) {
        java.util.Date fechaFormato = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(fechaFormato);
        System.out.println("Fecha Mora: " + fecha);
        Conexion conexion = new Conexion();

        int id_cuenta_Ingresos = 50;

        
            conexion.UID("INSERT INTO movimientos_ER(id_cuenta,fecha,monto) VALUES('" + id_cuenta_Ingresos + "','" + fecha + "','" + cuota.getMora() + "')");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorMovimientosER.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            calcularUtilidad();
        

    }

    //METODO PARA TRABAJAR CON LOS MOVIMIENTOS DE LOS ESTADOS DE RESULTADOS EN IGRESOS POR INTERES
    public void cuotaMovimientosIngresosPorInteres(Cuota cuota) {
        java.util.Date fechaFormato = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(fechaFormato);
        System.out.println("Fecha Interes: " + fecha);
        Conexion conexion = new Conexion();

        int id_cuenta_Ingresos = 50;
        
            conexion.UID("INSERT INTO movimientos_ER(id_cuenta,fecha,monto) VALUES('" + id_cuenta_Ingresos + "','" + fecha + "','" + cuota.getInteres() + "')");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorMovimientosER.class.getName()).log(Level.SEVERE, null, ex);
            }
            calcularUtilidad();
        

    }

    //METODO PARA CALCULAR LA UTILIDAD CADA VEZ QUE SE HAGA UN MOVIMIENTO DEL ER
    public void calcularUtilidad() {

        java.util.Date fechaFormato = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(fechaFormato);
        Conexion conexion = new Conexion();
        ResultSet resultado, resultado1;
        int id_cuenta_Ingresos = 50;
        int id_cuenta_Gastos = 60;
        int id_cuenta_utilidad = 70;
        double montoIngresos = 0;
        double montoGastos = 0;
        double utilidad = 0;

        //OBTENIENDO SUMA DE INGRESOS
        resultado = conexion.getValores("SELECT  SUM(monto) FROM movimientos_ER WHERE id_cuenta ='" + id_cuenta_Ingresos + "'");

        try {
            while (resultado.next()) {
                montoIngresos = resultado.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Monto Ingresos:" + montoIngresos);

        //OBTENIENDO SUMA DE GASTOS
        resultado1 = conexion.getValores("SELECT  SUM(monto) FROM movimientos_ER WHERE id_cuenta ='" + id_cuenta_Gastos + "'");

        try {
            while (resultado1.next()) {

                montoGastos = resultado1.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuota.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Monto Gastos:" + montoGastos);

        //CALCULANDO UTILIDAD
        utilidad = montoIngresos - montoGastos;

        //INSERTANDO UTILIDAD EN LA BASE DE DATOS
        try {
            conexion.UID("INSERT INTO movimientos_ER(id_cuenta,fecha,monto) VALUES('" + id_cuenta_utilidad + "','" + fecha + "','" + utilidad + "')");

        } catch (Exception e) {
            mensaje1 = "Error en la Transaccion!!";
            mensaje2 = "No se ha podido Registrar el Pago!";
            mensajes.fatal(mensaje1, mensaje2);
        }

    }

    //METODO PARA AGREGAR GASTOS EN LA BASE DE DATOS
    public void insertarGastos(MovimientosER movimientos) {

        Conexion conexion = new Conexion();
        int id_cuenta_gastos = 60;
        java.util.Date fechaFormato = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(fechaFormato);

        if (movimientos.validar()) {
            mensaje1 = "Transaccion Exitosa!!";
            mensaje2 = "Gasto Agregado Correctamente!!";
            try {

                conexion.UID("INSERT INTO movimientos_ER(id_cuenta,fecha,monto) VALUES('" + id_cuenta_gastos + "','" + fecha + "','" + movimientos.getMonto() + "')");
            } catch (Exception e) {
            }

            //calcularUtilidad();
            mensajes.info(mensaje1, mensaje2);
        } else {

            mensaje1 = "Error en la Transaccion!!";
            mensaje2 = "Debe ingresar Un monto valido!";
            mensajes.error(mensaje1, mensaje2);
        }

    }

}
