package ues.edu.sv.ingenieria.diseño.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Balance;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Cuota;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Prestamo;

/**
 *
 * @author erick
 */
public class ControladorBalance {

    //METODO PARA OBTENER LAS CUENTAS DEL BALANCE 
    public List<Balance> obtener() {

        List<Balance> cuentasBalance = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;

        System.out.println("Estoy en el metodo Obtener Cuentas de Balance General!");

        try {

            resultado = conexion.getValores("SELECT * FROM balance");

            while (resultado.next()) {

                cuentasBalance.add(new Balance(resultado.getInt("id_cuenta"), resultado.getString("nombre_cuenta"), resultado.getDouble("monto")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBalance.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cuentasBalance;
    }

    //METODO QUE HACE LA COMPARACION ENTRE MONTO DE PRESTAMO Y EFECTIVO Y DISMINUYE EFECTIVO Y AUMENTA LAS CUENTAS POR COBRAR EN UN PRESTAMO, AUMENTA CAPITAL
    public boolean prestamoBalance(Prestamo prestamo) {
        boolean comprobar = false;
        Conexion conexion = new Conexion();
        ResultSet resultado, resultado1;

        double efectivoDisponible = 0;
        double cuentaPorCobrar = 0;
        double efectivoNuevo;
        double cuentaPorCobrarNuevo;
        double capitalNuevo;

        //TENGO QUE USAR DOS VARIABLES RESULSET PORQUE POR QUE SOLO QUIERO EN SI EL MONTO DE UNA CUENTA ESPECIFICA 
        resultado = conexion.getValores("SELECT monto FROM balance WHERE id_cuenta=11");
        resultado1 = conexion.getValores("SELECT monto FROM balance WHERE id_cuenta=12");

        try {

            while (resultado.next()) {
                efectivoDisponible = resultado.getDouble("monto");

            }
            while (resultado1.next()) {
                cuentaPorCobrar = resultado1.getDouble("monto");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("EFECTIVO DISPONIBLE " + efectivoDisponible);
        System.out.println("SALDO CUENTAS POR COBRAR " + cuentaPorCobrar);

        if (prestamo.getMonto() <= efectivoDisponible) {

            comprobar = true;

            System.out.println("Monto del prestamo : " + prestamo.getMonto());

            //disminuimos efectivo
            efectivoNuevo = efectivoDisponible - prestamo.getMonto();
            //aumentamos cuentas por cobrar
            cuentaPorCobrarNuevo = cuentaPorCobrar + prestamo.getMonto();
            //calculamos el capital nuevo el cual sera la suma de el nuevo efectivo y el nuevo saldo de cuentas por cobrar
            capitalNuevo = efectivoNuevo + cuentaPorCobrarNuevo;

            //setiamos a la base los valores nuevos de las cuentas: efectivo, cuentas por cobrar y capital social
            conexion.UID("UPDATE balance SET monto='" + efectivoNuevo + "' WHERE id_cuenta=11");
            conexion.UID("UPDATE balance SET monto='" + cuentaPorCobrarNuevo + "' WHERE id_cuenta=12");
            conexion.UID("UPDATE balance SET monto='" + capitalNuevo + "' WHERE id_cuenta=31");

        } else {
            System.out.println("No Puede Realizarse el prestamo Pues el Monto ha prestar es mayor que el efectivo Disponible");
        }

        return comprobar;
    }

    public void cuotaBalance(Cuota cuota) {
        Conexion conexion = new Conexion();
        ResultSet resultado1, resultado2;
        double saldoEfectivo = 0.0;
        double saldoCuentaPorCobrar = 0.0;
        double efectivoNuevo;
        double cuentasPorCobrarNuevo;
        double capitalNuevo;

        //OBTENIENDO LOS VALORES DE LAS CUENTAS DEL BALANCE GENERAL
        resultado1 = conexion.getValores("SELECT monto FROM balance WHERE id_cuenta=11");
        resultado2 = conexion.getValores("SELECT monto FROM balance WHERE id_cuenta=12");

        try {

            while (resultado1.next()) {
                saldoEfectivo = resultado1.getDouble("monto");

            }
            while (resultado2.next()) {
                saldoCuentaPorCobrar = resultado2.getDouble("monto");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("SALDO EFECTIVO " + saldoEfectivo);
        System.out.println("SALDO CUENTAS POR COBRAR " + saldoCuentaPorCobrar);

        efectivoNuevo = saldoEfectivo + cuota.getCapital();
        cuentasPorCobrarNuevo = saldoCuentaPorCobrar - cuota.getCapital();
        capitalNuevo = efectivoNuevo + cuentasPorCobrarNuevo;

        //setiamos a la base los valores nuevos de las cuentas: efectivo, cuentas por cobrar y capital social
        conexion.UID("UPDATE balance SET monto='" + efectivoNuevo + "' WHERE id_cuenta=11");
        conexion.UID("UPDATE balance SET monto='" + cuentasPorCobrarNuevo + "' WHERE id_cuenta=12");
        conexion.UID("UPDATE balance SET monto='" + capitalNuevo + "' WHERE id_cuenta=31");

    }

    //OBTENIENDO EFECTIVO DISPONIBLE PARA MOSTRARLO A LA HORA DE REALIZAR UN PRESTAMO!
    public Double obtenerEfectivo() {
        double efectivo = 0;
        Conexion conexion = new Conexion();
        ResultSet resultado;

        resultado = conexion.getValores("SELECT monto FROM balance WHERE id_cuenta=11");

        try {
            while (resultado.next()) {

                efectivo = resultado.getDouble("monto");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBalance.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Efectivo Disponible: " + efectivo);

        return efectivo;

    }

}
