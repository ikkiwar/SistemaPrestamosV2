/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorCliente;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorPrestamo;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ErrorPrestamo;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Cliente;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Documento;

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
    private Documento doc = new Documento();
    private UploadedFile img;
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

    public void filtrar() {
        //metodo encargado de mandar el valor de filtro que se obtiene desde el inputext y lo manda a el metodo buscar
        try {

            lista = Control.buscar(filtro);
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

    public void actualizar() {
        try {
            Control.actualizar(SelectedClient);
            //System.out.println("Actualizo");
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imagen(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        String nombre = file.getFileName();
        System.out.println(nombre);
        /*if (getImg() == null) {
            System.out.println("none");

        } else {
            System.out.println(getImg().getFileName());
        }
        System.out.println("lol");*/

    }

    public void handleFileUpload(FileUploadEvent event) throws IOException, ErrorPrestamo {
        int correlativo = doc.obtenerMaxId(Client.getDui());
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        Conexion conexion = new Conexion();

        try {
            conexion.UID("INSERT INTO documentos (dui,correlativo,nombre_archivo,archivo,descripcion) "
                    + "VALUES('" + Client.getDui() + "','" + correlativo + "','" + "fja" + "','"
                    + event.getFile().getInputstream() + "','" + "adga" + "')");
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al ActualizarSaldo",
                    "ControladorPrestamo.atualizarSaldo", "Error al actualiar el saldo");
        }

    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
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
