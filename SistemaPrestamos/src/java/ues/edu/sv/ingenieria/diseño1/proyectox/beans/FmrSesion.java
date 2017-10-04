/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.beans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.rpc.encoding.Serializer;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorSesion;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ErrorPrestamo;

/**
 *
 * @author kevin
 */
@ManagedBean(name="sesion")
@ApplicationScoped
public class FmrSesion implements Serializable {
    
    private String user;
    private String contraseña;
    private ControladorSesion sesion = new ControladorSesion();

    public void comprobarDatos() {
        try {
            boolean autorizado= sesion.verificar(user, contraseña);
            
          
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    
}
