package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Balance;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;

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

}
