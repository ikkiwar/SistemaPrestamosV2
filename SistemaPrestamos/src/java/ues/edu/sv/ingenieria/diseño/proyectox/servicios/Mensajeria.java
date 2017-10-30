
package ues.edu.sv.ingenieria.diseño.proyectox.servicios;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author erick
 */
public class Mensajeria {
    
   
    FacesContext contexto = FacesContext.getCurrentInstance();
    
    
     public void info(String mensaje1, String mensaje2) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje1, mensaje2));
    }
     
     public void bienvenida(String mensaje1, String mensaje2,String mensaje3) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje1, mensaje2));
    }
     
    public void warn(String mensaje1, String mensaje2) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  mensaje1, mensaje2));
    }
     
    public void error(String mensaje1, String mensaje2) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  mensaje1, mensaje2));
    }
     
    public void fatal(String mensaje1, String mensaje2) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,  mensaje1, mensaje2));
    }
    
    
}
