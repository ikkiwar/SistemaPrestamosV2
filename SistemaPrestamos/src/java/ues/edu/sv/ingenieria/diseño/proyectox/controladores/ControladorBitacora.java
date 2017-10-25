/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Bitacora;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Sesion;

/**
 *
 * @author estuardo
 */
public class ControladorBitacora {
    
    public void agregar(int id, String accion) throws ErrorPrestamo{
        
       // SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date fecha= new Date();
        Calendar cal= Calendar.getInstance();
        Timestamp fechaSave = new Timestamp(cal.getTimeInMillis());
        //String fechaSave= formato.format(fecha);
        Conexion conexion = new Conexion();

        if (conexion != null) {
            conexion.UID("INSERT INTO bitacora(id_bitacora,id_usuario,fecha,accion)"
                    + "VALUES('"+obtenerMaxIdBitacora() +"','" +id + "','" + fechaSave+"','"+ accion + "')");

        } else {
            throw new ErrorPrestamo("Error al Insertar Datos", "ControladorBitacora.agregar", "Error al Agregar Nuevo Registro");
        }

    }
        
        
    public int obtenerMaxIdBitacora() {
        int id = 0;

        ResultSet resultado;

        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT MAX(id_bitacora) From bitacora");
            while (resultado.next()) {
                id = resultado.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        id = id + 1;

        return id;
    }
    
    
    public void mostrar(){
        
        List<Sesion> sesion = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;
        
        
    }
    
    // esta es el metodo que trae todos los datos en la BD para la tabla bitacora y los guarda en una lista del tipo bitacora
      public List<Bitacora> obtenerBitacora() {
        List<Bitacora> listAcciones = new ArrayList<>(); 
           
           Conexion conexion = new Conexion();
        ResultSet resultado;

        System.out.println("Estoy al Inicio del metodo obtener Bitacora !");

        

            resultado = conexion.getValores("SELECT * FROM bitacora ORDER BY fecha DESC");

        try {
            while (resultado.next()) {
                
                listAcciones.add(new Bitacora(resultado.getDate("fecha"),resultado.getTime("fecha"),
                        resultado.getInt("id_usuario"),resultado.getString("accion")));
         
                // System.out.println("Estoy en el While");

                
            }  
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBitacora.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAcciones;
    }
    
    // este metodo es el encargado de hace la consulta filtro y guarda los valores en una list para luego setiar en la tabla  
      public List<Bitacora> buscar(String filtro) throws ErrorPrestamo {

        List<Bitacora> lBitacora = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;
        try {

            resultado = conexion.getValores("SELECT * FROM bitacora  WHERE  fecha LIKE '"
                    + filtro + "%' OR id_usuario LIKE '" + filtro + "%' OR accion LIKE '" + filtro + "%'");

            while (resultado.next()) {
                lBitacora.add(new Bitacora(resultado.getDate("fecha"),resultado.getTime("fecha"),
                resultado.getInt("id_usuario"),resultado.getString("accion")));

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorBitacora.Buscar", "Error al obtener bitacora");
        }

        return lBitacora;

    }
    
}
