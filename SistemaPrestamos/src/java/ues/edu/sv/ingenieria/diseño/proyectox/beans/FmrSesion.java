/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.beans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ues.edu.sv.ingenieria.diseño.proyectox.servicios.Direccionamientos;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ControladorSesion;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ErrorPrestamo;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Sesion;
import ues.edu.sv.ingenieria.diseño.proyectox.servicios.EntradaBitacora;
import ues.edu.sv.ingenieria.diseño.proyectox.servicios.Mensajeria;

/**
 *
 * @author kevin
 */
@ManagedBean
@SessionScoped
public class FmrSesion implements Serializable {

    private Sesion sesion = new Sesion();
    private boolean login = false;
    private ControladorSesion sesionControlador = new ControladorSesion();
    private Direccionamientos direccionamiento = new Direccionamientos();
    private EntradaBitacora bitacora = new EntradaBitacora();
    Mensajeria mensajes = new Mensajeria();
    String mensaje1;
    String mensaje2;

    public String comprobarDatos() throws ErrorPrestamo {
        try {
            sesion.setSesion(sesionControlador.verificar(sesion));
            // sesionCheck=sesion.verificar(user, contraseña);
            //boolean autorizado= sesion.verificar(user, contraseña);

        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        // sesion true se procede a realizar la peticion de redireccion sesion false se devueve a login
        if (sesion.isSesion()) {

            login = sesion.isSesion();
            bitacora.agregar("Inicio de Sesion");
            return direccionamiento.redirectHome();

        } else {

            return direccionamiento.reditectIndex();

        }

    }

    public void bienvenida() {
        mensaje1="BIENVENIDO AL SISTEMA!";
        mensaje2=sesion.getUser();
        mensajes.info(mensaje1, mensaje2);
        
    }

    public String doLogout() throws ErrorPrestamo {
        // false indica que ya no esta loggeado
        login = false;

        // Mensaje de desloggeo
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        bitacora.agregar("Termino sesion");
        return direccionamiento.reditectIndex();
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

}
