/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ControladorBitacora;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ErrorPrestamo;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Bitacora;

/**
 *
 * @author kevin
 */
@ManagedBean(name = "Bitacora")
@ApplicationScoped
public class FmrBitacora {
    
    private List<Bitacora> listBitacora; 
    private ControladorBitacora cBitacora = new ControladorBitacora();
    private String filtro;

  
    
    @PostConstruct
    public void inicioBitacora(){
    
        listBitacora= cBitacora.obtenerBitacora();
    
    }
    
    public void filtrar(){
    
        try {
              System.out.println(filtro);
            listBitacora=cBitacora.buscar(filtro);
          
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrBitacora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Bitacora> getListBitacora() {
        return listBitacora;
    }

    public void setListBitacora(List<Bitacora> listBitacora) {
        this.listBitacora = listBitacora;
    }
    
      public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    
}
