/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.beans;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ues.edu.sv.ingenieria.diseño.proyectox.servicios.FileGenerator;

/**
 *
 * @author frank
 */
@Named(value = "fmrContrato")
@ViewScoped

public class FmrContrato extends FileGenerator implements Serializable {

    public FmrContrato() {
    }

    @PostConstruct
    public void inicializar() {
        try {
            setConexion(DriverManager.getConnection("jdbc:mysql://localhost:3306/prestamos", "root", "12345"));
        } catch (SQLException ex) {
            Logger.getLogger(FmrContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarContrato(boolean ver, Integer idPrestamo) {
        if (idPrestamo != null && idPrestamo.compareTo(0) > 0) {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID_PRESTAMO", idPrestamo);
            generar(ver, "contrato.jasper", parametros);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ID de préstamo está vacío!", ""));
        }
    }

}

