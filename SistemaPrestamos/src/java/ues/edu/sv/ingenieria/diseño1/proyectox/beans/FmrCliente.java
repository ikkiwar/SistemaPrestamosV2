/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorCliente;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorPrestamo;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ErrorPrestamo;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Cliente;

/**
 *
 * @author estuardo
 */
@ManagedBean(name = "clientes")
@ApplicationScoped
public class FmrCliente implements Serializable {

    private ControladorCliente Control = new ControladorCliente();
    private List<Cliente> lista;
    private Cliente Client = new Cliente();
    private Cliente SelectedClient;
    private String fecha;
    private String filtro;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FmrCliente() {

    }

    @PostConstruct
    public void inicio() {
        try {
            lista = Control.obtener();
            
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void filtrar(){
        //metodo encargado de mandar el valor de filtro que se obtiene desde el inputext y lo manda a el metodo buscar
        try {
            
            lista=Control.buscar(filtro);
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ver() {
        System.out.print(SelectedClient.getDui());

        try {
            Control.eliminar(SelectedClient);
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void submit() {
        System.out.print(Client.getDui());
        System.out.print(Client.getNit());
        System.out.print(Client.getNombres());
        System.out.print(Client.getApellidos());
        System.out.print(Client.getSexo());
        //System.out.print(Client.getFecha_nacimiento());
        fecha = simpleDateFormat.format(Client.getFecha_nacimiento());
        System.out.print(fecha);
        System.out.print(Client.getTelefono());
        System.out.print(Client.getDireccion());
        System.out.print(Client.getObservaciones());

        try {
            Control.agregar(Client);
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizar(){
        try {
            Control.actualizar(SelectedClient);
            //System.out.println("Actualizo");
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void seleccionado() {
        System.out.print(SelectedClient.getDui());
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public Cliente getClient() {
        return Client;
    }

    public void setClient(Cliente Client) {
        this.Client = Client;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getSelectedClient() {
        return SelectedClient;
    }

    public void setSelectedClient(Cliente SelectedClient) {
        this.SelectedClient = SelectedClient;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

}
