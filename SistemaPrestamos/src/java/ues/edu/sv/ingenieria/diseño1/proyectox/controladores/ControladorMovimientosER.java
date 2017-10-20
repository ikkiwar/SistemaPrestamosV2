package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.EstadoResultados;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.MovimientosER;

/**
 *
 * @author erick
 */

public class ControladorMovimientosER {

    //METODO PARA OBTENER LOS MOVIMIENTOS DE CADA CUENTA DEL ESTADO DE RESULTADOS
    public List<MovimientosER> obtener(EstadoResultados resultados) {
        List<MovimientosER> movimientos = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;
        
        
        if (conexion != null) {
            
            try {
                resultado = conexion.getValores("SELECT * FROM movimientos_ER WHERE id_cuenta='" +resultados.getId_cuenta()+ "' ");
              
                
                
                while (resultado.next()) {
                    
                    movimientos.add(new MovimientosER(resultado.getInt("id_cuenta"), resultado.getDate("fecha"), resultado.getDouble("monto")));
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorMovimientosER.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return movimientos;
    }
    
}
