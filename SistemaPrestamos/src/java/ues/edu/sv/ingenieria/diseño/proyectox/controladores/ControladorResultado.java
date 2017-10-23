package ues.edu.sv.ingenieria.diseño.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.EstadoResultados;


/**
 *
 * @author erick
 */
public class ControladorResultado {

    //METODO PARA OBTENER LAS CUENTAS DEL ESTADO DE RESULTADOS
    public List<EstadoResultados> obtener() {

        List<EstadoResultados> cuentasResultados = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;

        System.out.println("Estoy en el metodo Obtener Cuentas del Estado de Resultados!");

        try {

            resultado = conexion.getValores("SELECT * FROM estado_resultados");

            while (resultado.next()) {

                cuentasResultados.add(new EstadoResultados(resultado.getInt("id_cuenta"), resultado.getString("nombre_cuenta")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBalance.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cuentasResultados;
    }

    
    
    
    
}
