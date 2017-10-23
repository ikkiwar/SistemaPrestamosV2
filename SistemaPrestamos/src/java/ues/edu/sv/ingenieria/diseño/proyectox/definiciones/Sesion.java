/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.definiciones;



/**
 *
 * @author estuardo
 */

public class Sesion {

    private int id;
    private String user;
    private String contraseña;
    private char rol;
    private boolean sesion;

    public Sesion() {

    }
    
    public Sesion(int id,String user,String contraseña,char rol){
            this.id=id;
            this.user=user;
            this.contraseña=contraseña;
            this.rol=rol;
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
