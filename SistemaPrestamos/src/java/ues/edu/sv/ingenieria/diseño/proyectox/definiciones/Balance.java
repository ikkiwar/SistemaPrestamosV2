package ues.edu.sv.ingenieria.diseño.proyectox.definiciones;

/**
 *
 * @author erick
 */

public class Balance {

    //DECLARACION DE VARIABLES 
    private int id_cuenta;
    private String nombre_cuenta;
    private double monto;

    //CONSTRUCTORES IMPLICITO Y EXPLICITO
    public Balance() {

    }

    public Balance(int id_cuenta, String nombre_cuenta, double monto) {
        this.id_cuenta = id_cuenta;
        this.nombre_cuenta = nombre_cuenta;
        this.monto = monto;
    }

    //GETTERS Y SETTERS
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

}
