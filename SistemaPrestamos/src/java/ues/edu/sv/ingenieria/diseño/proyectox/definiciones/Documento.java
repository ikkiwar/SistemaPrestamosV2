/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.definiciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ControladorPrestamo;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ErrorPrestamo;

/**
 *
 * @author estuardo
 */
public class Documento {
    
    private String dui;
    private int correlativo;
    private String nombre;
    private UploadedFile file;
    private String descripcion;
    
    
    public Documento(){
        
        
    }

    public  void upload(String dui,int correlativo,String nombre,UploadedFile file,String descripcion){
        
        
        if(this.file !=null){
          //  Conexion conexion = new Conexion();
          // conexion.UID("INSERT INTO documentos (dui,correlativo,nombre_archivo,archivo,descripcion) VALUES ('"+dui+"','"+correlativo+"','"+nombre+"','"+file+"','"+descripcion+"')");
            System.out.print(file.getFileName());
          
        }
    }
    
    public void agregar(){
         
    }
    
    public int obtenerMaxId(String DUI) throws ErrorPrestamo {
        int id = 0;
        ResultSet resultado;

        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT MAX(correlativo) From documentos WHERE dui='" + DUI + "'");
            resultado.next();
            id = resultado.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        id = id + 1;
        return id;
    }
    
    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   


    
    
    
    
    
}
