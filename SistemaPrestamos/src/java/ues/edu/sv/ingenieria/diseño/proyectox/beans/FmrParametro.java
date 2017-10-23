/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ControladorParametro;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ErrorPrestamo;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Parametro;
import ues.edu.sv.ingenieria.diseño.proyectox.servicios.EntradaBitacora;

/**
 *
 * @author estuardo
 */
@ManagedBean(name = "parametro")
@ApplicationScoped
public class FmrParametro implements Serializable {
    
    private ControladorParametro Control = new ControladorParametro();
    private List<Parametro> lista;
    private Parametro parametro = new Parametro();
    private Parametro selectedParametro;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
   private EntradaBitacora bitacora = new EntradaBitacora();
    public FmrParametro() {

    }

    @PostConstruct
    public void inicio() {
        try {
            lista = Control.obtener(); 
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void submit() {
       
        System.out.print(selectedParametro.getId_parametro());
        System.out.print(selectedParametro.getNombre());
        System.out.print(selectedParametro.getValor());

        try {
            Control.actualizar(selectedParametro);
            bitacora.agregar("se modifico el parametro: "+selectedParametro.getNombre());
            
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * @return the Control
     */
  /*  public ControladorParametro getControl() {
        return Control;
    }*/

    /**
     * @param Control the Control to set
     */
    public void setControl(ControladorParametro Control) {
        this.Control = Control;
    }

    /**
     * @return the lista
     */
    public List<Parametro> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Parametro> lista) {
        this.lista = lista;
    }

    /**
     * @return the parametro
     */
    public Parametro getParametro() {
        return parametro;
    }

    /**
     * @param parametro the parametro to set
     */
    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    /**
     * @return the selectedParametro
     */
    public Parametro getSelectedParametro() {                
        return selectedParametro;
    }

    /**
     * @param selectedParametro the selectedParametro to set
     */
    public void setSelectedParametro(Parametro selectedParametro) {
        this.selectedParametro = selectedParametro;
    }
    
    
}
