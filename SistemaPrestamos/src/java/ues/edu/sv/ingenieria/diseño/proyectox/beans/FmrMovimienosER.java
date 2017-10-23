
package ues.edu.sv.ingenieria.diseño.proyectox.beans;


import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño.proyectox.controladores.ControladorMovimientosER;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.EstadoResultados;
import ues.edu.sv.ingenieria.diseño.proyectox.definiciones.MovimientosER;

/**
 *
 * @author erick
 */
@ManagedBean(name = "movimientos")
@ApplicationScoped
public class FmrMovimienosER implements Serializable {
    ControladorMovimientosER movimiento = new ControladorMovimientosER();
    List<MovimientosER> listaMovimientos ;
    private MovimientosER movimientosGastos = new MovimientosER();
    public FmrMovimienosER() {
    }
    
    @PostConstruct
    public void inicio(){
        
        
    }

    public void sumitGastos(){
        
        movimiento.insertarGastos(movimientosGastos);
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

    public MovimientosER getMovimientosGastos() {
        return movimientosGastos;
    }

    public void setMovimientosGastos(MovimientosER movimientosGastos) {
        this.movimientosGastos = movimientosGastos;
    }

 
    
    
}
