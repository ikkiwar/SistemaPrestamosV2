/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorUsuario;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ErrorPrestamo;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Usuario;

/**
 *
 * @author estuardo
 */
@ManagedBean(name = "usuario")
@ApplicationScoped
public class FmrUsuario implements Serializable {

    private ControladorUsuario controlUsuario = new ControladorUsuario();
    private List<Usuario> lista;
    private String repetirContraseña;
    private Usuario usuario = new Usuario();
    private String filtro;

    @PostConstruct
    public void inicio() {
        try {
            lista = controlUsuario.obtener();

        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void filtrar() {
        //metodo encargado de mandar el valor de filtro que se obtiene desde el inputext y lo manda a el metodo buscar
        try {

            lista = controlUsuario.buscar(filtro);
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregar() {
        usuario.setId_usuario(controlUsuario.obtenerMaxId());
        System.out.println(usuario.getId_usuario());
        
       try {
            controlUsuario.agregar(usuario);

        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRepetirContraseña() {
        return repetirContraseña;
    }

    public void setRepetirContraseña(String repetirContraseña) {
        this.repetirContraseña = repetirContraseña;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

}
