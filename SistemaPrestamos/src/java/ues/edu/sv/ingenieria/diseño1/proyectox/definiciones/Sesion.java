/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.definiciones;

import java.util.ArrayList;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorSesion;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ErrorPrestamo;

/**
 *
 * @author estuardo
 */
public class Sesion {

    private String user;
    private String contraseña;
    private String rol;
    private boolean sesion;

    public Sesion() {

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isSesion() {
        return sesion;
    }

    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }

}
