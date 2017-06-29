/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorCuota;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorPrestamo;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ErrorPrestamo;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Cliente;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Cuota;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Parametro;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Prestamo;

/**
 *
 * @author estuardo
 */
@ManagedBean(name = "prestamos")
@ApplicationScoped
public class FmrPrestamo implements Serializable {

    private ControladorPrestamo pControl = new ControladorPrestamo();
    private ControladorCuota cControl = new ControladorCuota();
    private List<Prestamo> listaP;
    private List<Prestamo> listaH;
    private List<Cuota> listaC;
    private Prestamo prestamo = new Prestamo();
    private Cuota cuota = new Cuota();
    private Prestamo selectPrestamo;
    private Parametro parametro = new Parametro();
    private String filtro;

    public FmrPrestamo() {

    }

    @PostConstruct
    public void inicio() {
        try {
            listaP = pControl.obtenerActivos();
            listaH = pControl.obtenerHistorial();

        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void detalle() {
        try {
            listaC = cControl.obtenerPorPrestamo(selectPrestamo.getId_prestamo());
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarhistorial() {
        try {
            listaC = cControl.obtenerPorPrestamo(selectPrestamo.getId_prestamo());
            System.out.println(selectPrestamo.getId_prestamo());

        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void filtrar() {
        try {
            System.out.println(filtro);
            listaH = pControl.buscar(filtro);
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void calcular() throws ErrorPrestamo {
        long diferencia;
        long diferenciadias;
        double tasa_mora = 0;
        double valormora = 0;
        double valor_cuota = 0;
        String mora=null;

       if (cuota.getValor() < selectPrestamo.getValor_cuota()) {
            cuota.setValor(selectPrestamo.getValor_cuota());
        }

        cuota.setId_prestamo(selectPrestamo.getId_prestamo());
        cuota.setSaldo_anterior(selectPrestamo.getSaldo());
        Date anterior = cControl.masreciente(selectPrestamo.getId_prestamo());
        Date ahora = cuota.getFecha();

        if (anterior == null) {
            cuota.setInteres(cuota.getValor() * selectPrestamo.getTasa_interes());

        } else {

            diferencia = ahora.getTime() - anterior.getTime();
            diferenciadias = TimeUnit.MILLISECONDS.toDays(diferencia);
             tasa_mora = parametro.obtenerTasas("Mora");
             System.out.print(tasa_mora);
            
            if (diferenciadias <= 30) {
                cuota.setInteres(0);
                System.out.println("mismo mes");
            } else {
                diferenciadias = diferenciadias - 30;
                if (!selectPrestamo.getCapitalizacion().equals("D")) {
                    valormora = (((cuota.getValor() * tasa_mora) / 30) * diferenciadias);
                } else {
                    valormora = ((cuota.getValor() * (tasa_mora / (30 * selectPrestamo.getCantidad_cuotas()))) * diferenciadias);
                }

                cuota.setInteres(cuota.getValor() * selectPrestamo.getTasa_interes());
            }

        }

        cuota.setCapital(cuota.getValor() - cuota.getInteres());
        cuota.setSaldo_actualizado(cuota.getSaldo_anterior() - cuota.getCapital());
        

    }

    public void submitCuota() throws ErrorPrestamo {

        cuota.setNum_cuota(cControl.obtenerMaxId(selectPrestamo.getId_prestamo()));
        System.out.println(cuota.getNum_cuota());

        if (cuota.getSaldo_actualizado() == 0) {
            System.out.println("VALIDAR");
        } else {

            cControl.agregar(cuota);
            pControl.actualizar(cuota.getSaldo_actualizado(), selectPrestamo.getId_prestamo(), cuota.getFecha());

        }

    }
    
    public void inciocalculo() throws ErrorPrestamo{
        cuota.setValor(selectPrestamo.getValor_cuota());
        System.out.println(selectPrestamo.getValor_cuota());
        calcular();
    }
    


    public void submit() {

        FacesContext context = FacesContext.getCurrentInstance();
        prestamo.setTasa_interes(prestamo.getTasa_interes() / 100);

        try {
            prestamo.setId_prestamo(pControl.obtenerMaxId());
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            pControl.agregar(prestamo);
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.addMessage(null, new FacesMessage("Successful", "Prestamo Agregado Correctamente "));

    }

    public void obtenerPorPrestamo(Cliente seleccionado) {
        System.out.print(seleccionado.getNombres());
        /*  try {
            listaC=pControl.obtenerPorCliente(seleccionado);
        } catch (ErrorPrestamo ex) {
            Logger.getLogger(FmrPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void pagar() {
        cuota.calcularCuota();

    }

    public List<Prestamo> getListaP() {
        return listaP;
    }

    public void setListaP(List<Prestamo> listaP) {
        this.listaP = listaP;
    }

    public List<Prestamo> getListaH() {
        return listaH;
    }

    public void setListaH(List<Prestamo> listaH) {
        this.listaH = listaH;
    }

    public List<Cuota> getListaC() {
        return listaC;
    }

    public void setListaC(List<Cuota> listaC) {
        this.listaC = listaC;
    }

    public Prestamo getSelectPrestamo() {
        return selectPrestamo;
    }

    public void setSelectPrestamo(Prestamo selectPrestamo) {
        this.selectPrestamo = selectPrestamo;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Cuota getCuota() {
        return cuota;
    }

    public void setCuota(Cuota cuota) {
        this.cuota = cuota;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

}
