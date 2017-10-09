package ues.edu.sv.ingenieria.diseño1.proyectox.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ControladorResultado;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.EstadoResultados;

/**
 *
 * @author erick
 */
@ManagedBean(name = "cuentaResultados")
@ApplicationScoped
public class FmrResultados implements Serializable {

    ControladorResultado controlResultados = new ControladorResultado();
    List<EstadoResultados> listaResultados;

    public FmrResultados() {
    }

    @PostConstruct
    public void inicio() {
        try {
            listaResultados = controlResultados.obtener();
        } catch (Exception e) {
        }

    }

    public List<EstadoResultados> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<EstadoResultados> listaResultados) {
        this.listaResultados = listaResultados;
    }

    
    
    
}
