/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.io.IOException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import ues.edu.sv.ingenieria.diseño1.proyectox.beans.FmrSesion;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Sesion;

/**
 *
 * @author estuardo
 */
@ManagedBean(name="direcciones")
@ApplicationScoped
public class Direccionamientos {
    private Sesion sesion = new Sesion();
    
    //metodos para redireccion
    public String reditectIndex(){
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String redirectHome(){
        return "/pages/home.jsf?faces-redirect=true";
    }
    
    public void verificarLog() throws IOException {
      
    if (!sesion.isSesion()) {
      
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath());
    }else{
          
    }
    
    
}
    

    
    
    
}
