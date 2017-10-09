package ues.edu.sv.ingenieria.diseño1.proyectox.definiciones;

/**
 *
 * @author erick
 */
public class EstadoResultados {

    private int id_cuenta;
    private String nombre_cuenta;

    public EstadoResultados() {

    }

    public EstadoResultados(int id_cuenta, String nombre_cuenta) {
        this.id_cuenta = id_cuenta;
        this.nombre_cuenta = nombre_cuenta;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

}
