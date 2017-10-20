
package ues.edu.sv.ingenieria.diseño1.proyectox.beans;


import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorMovimientosER;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.EstadoResultados;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.MovimientosER;

/**
 *
 * @author erick
 */
@ManagedBean(name = "movimientos")
@ApplicationScoped
public class FmrMovimienosER implements Serializable {
    ControladorMovimientosER movimiento = new ControladorMovimientosER();
    List<MovimientosER> listaMovimientos ;
    
    public FmrMovimienosER() {
    }
    
    @PostConstruct
    public void inicio(){
        
        
    }

    public void llenar(EstadoResultados cuentaSelecciona){
        System.out.println("Entre al metodo llenar");
        
        listaMovimientos = movimiento.obtener(cuentaSelecciona);
    }
        
    
    
    public List<MovimientosER> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<MovimientosER> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public ControladorMovimientosER getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(ControladorMovimientosER movimiento) {
        this.movimiento = movimiento;
    }

 
    
    
}
