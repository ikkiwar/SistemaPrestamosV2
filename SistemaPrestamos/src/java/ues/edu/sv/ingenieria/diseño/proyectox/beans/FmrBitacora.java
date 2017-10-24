/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ControladorBitacora;
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
    
    
    @PostConstruct
    public void inicioBitacora(){
    
        listBitacora= cBitacora.obtenerBitacora();
    
    }

    public List<Bitacora> getListBitacora() {
        return listBitacora;
    }

    public void setListBitacora(List<Bitacora> listBitacora) {
        this.listBitacora = listBitacora;
    }
    
    
}
