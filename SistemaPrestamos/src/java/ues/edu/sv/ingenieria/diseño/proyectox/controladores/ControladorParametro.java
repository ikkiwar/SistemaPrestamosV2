/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.controladores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Parametro;


/**
 *
 * @author estuardo
 */
public class ControladorParametro {
    
    
    public List<Parametro> obtener() throws ErrorPrestamo { 
        
        List<Parametro> parametros = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;
        
        System.out.println("Estoy al Inicio del metodo obtener Parametro !");
        
        try {
            
            resultado = conexion.getValores("SELECT * FROM parametros");
            
            while (resultado.next()) {
                parametros.add(new Parametro(resultado.getInt("id"), 
                        resultado.getString("parametro"), 
                        resultado.getString("valor")));
               // System.out.println("Estoy en el While");
                
            }
            
        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorParametro.Obtener", "Error al Obtener los Parametros");
        }
        
        return parametros;
        
    }   
    
    public void actualizar(Parametro parametro) throws ErrorPrestamo{
        
        ResultSet resultado;
        System.out.println(parametro.getId_parametro());
        
        try {
            Conexion conexion = new Conexion();
            conexion.UID("UPDATE parametros SET parametro='" + parametro.getNombre()+ "', "
                    + "Valor='" + parametro.getValor() +"' "
                            + "WHERE id='" + parametro.getId_parametro()+ "'");
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Actualizar parametro", "ControladorParametro.actualizarParametro", "Error al actualizar el parametro");
        }
        
    }
    
    
    
}
