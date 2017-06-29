/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.definiciones;

import org.primefaces.model.UploadedFile;

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
