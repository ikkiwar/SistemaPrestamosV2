package ues.edu.sv.ingenieria.diseño1.proyectox.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorBalance;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Balance;

/**
 *
 * @author erick
 */
@ManagedBean(name = "cuentas")
@ApplicationScoped
public class FmrBalance implements Serializable{

    ControladorBalance controlBalance = new ControladorBalance(); //INSTANCIA DE CONTROLADOR DE BALANCE PARA UTILIZAR SUS METODOS EN EL BEAN
    List<Balance> cuentasBalance;

    public FmrBalance() {
    }

    @PostConstruct
    public void inicio() {
        try {

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

    
    
}
