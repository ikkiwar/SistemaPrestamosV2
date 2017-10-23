/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.servicios;

import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ControladorBitacora;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ErrorPrestamo;

/**
 *
 * @author estuardo
 */
public class EntradaBitacora {
    
    private static int id;
    private static String user;
    private static String contraseña;
    private static char rol;
    private static boolean sesion;
    
    public EntradaBitacora(){
        
    }
    
     public EntradaBitacora(int id,String user,String contraseña,char rol){
            this.id=id;
            this.user=user;
            this.contraseña=contraseña;
            this.rol=rol;
    }
     
    public void agregar(String accion) throws ErrorPrestamo{
       ControladorBitacora bitacora= new ControladorBitacora();
       System.out.println(this.id);
       bitacora.agregar(this.id, accion);
       }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public char getRol() {
        return rol;
    }

    public void setRol(char rol) {
        this.rol = rol;
    }

    public boolean isSesion() {
        return sesion;
    }

    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }
     
     
     
     
    
    
    
}
