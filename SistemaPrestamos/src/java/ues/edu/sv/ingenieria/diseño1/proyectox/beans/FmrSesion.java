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
import servicios.Direccionamientos;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorSesion;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ErrorPrestamo;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Sesion;

/**
 *
 * @author kevin
 */
@ManagedBean(name="sesion")
@ApplicationScoped
public class FmrSesion implements Serializable {
    
    private Sesion sesion = new Sesion();
    private ControladorSesion sesionControlador = new ControladorSesion();
    private Direccionamientos direccionamiento = new Direccionamientos();

    public void comprobarDatos() {
        try {
            sesion.setSesion(sesionControlador.verificar(sesion));
           // sesionCheck=sesion.verificar(user, contraseña);
            //boolean autorizado= sesion.verificar(user, contraseña);
            
          
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(sesion.isSesion()){
            direccionamiento.redirectHome();
        }else{
           
        }
        
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    
    
    
    
}
