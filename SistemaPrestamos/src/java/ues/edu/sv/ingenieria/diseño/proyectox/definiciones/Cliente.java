/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.definiciones;

import java.util.Date;

/**
 *
 * @author estuardo
 */
public class Cliente {

    private String dui;
    private String nit;
    private String nombres;
    private String apellidos;
    private char sexo;
    private String direccion;
    private String telefono;
    private Date fecha_nacimiento;
    private String observaciones;

    public Cliente() {

    }

    public Cliente(String dui, String nit, String nombres, String apellidos,
            Character sexo, String direccion, String telefono, Date fechaNacimiento,
            String observaciones) {
        this.dui = dui;
        this.nit = nit;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fechaNacimiento;
        this.observaciones = observaciones;
    }

    public boolean validar() {
        if (this == null) {
            return false;
        }

        if (this.dui == null) {
            return false;
        }
        if (this.nit == null) {
            return false;
        }

        if (this.nombres == null) {
            return false;
        }

        if (this.apellidos == null) {
            return false;
        }
        if (this.fecha_nacimiento == null) {
            return false;
        }
        return true;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
