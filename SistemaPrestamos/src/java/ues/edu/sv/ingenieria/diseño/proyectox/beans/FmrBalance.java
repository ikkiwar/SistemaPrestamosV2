package ues.edu.sv.ingenieria.diseño.proyectox.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ControladorBalance;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.Balance;

/**
 *
 * @author erick
 */
@ManagedBean(name = "cuentas")
@ApplicationScoped
public class FmrBalance implements Serializable{

    ControladorBalance controlBalance = new ControladorBalance(); //INSTANCIA DE CONTROLADOR DE BALANCE PARA UTILIZAR SUS METODOS EN EL BEAN
    List<Balance> cuentasBalance;
    double efectivoDisponible;
    public FmrBalance() {
    }

    @PostConstruct
    public void inicio() {
        try {
            efectivoDisponible= controlBalance.obtenerEfectivo();
            cuentasBalance = controlBalance.obtener(); //SE LLENA LAS LISTA DE CUENTAS CON EL METODO OBTENER DEL CONTROLADOR DE BALANCE

        } catch (Exception e) {
        }

    }

    //GETTERS Y SETTERS PARA ACCEDER DESDE LA VISTA
    public List<Balance> getCuentasBalance() {
        return cuentasBalance;
    }

    public void setCuentasBalance(List<Balance> cuentasBalance) {
        this.cuentasBalance = cuentasBalance;
    }

    public double getEfectivoDisponible() {
        return efectivoDisponible;
    }

    public void setEfectivoDisponible(double efectivoDisponible) {
        this.efectivoDisponible = efectivoDisponible;
    }

    
    
}
