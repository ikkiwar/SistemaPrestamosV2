/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.definiciones;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author kevin
 */
public class Bitacora {

    private Date fecha;
    private Time hora;
    private String usuario;
    private String accion;

    public Bitacora(Date fecha,Time hora, String usuario, String accion) {
        this.fecha = fecha;
        this.hora = hora;
        this.usuario = usuario;
        this.accion = accion;
    }
    
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
}
