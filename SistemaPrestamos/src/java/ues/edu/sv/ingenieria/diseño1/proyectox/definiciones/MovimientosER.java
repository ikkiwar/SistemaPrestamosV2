package ues.edu.sv.ingenieria.diseño1.proyectox.definiciones;

import java.util.Date;

/**
 *
 * @author erick
 */

public class MovimientosER {

    private int id_cuenta;
    private Date fecha;
    private double monto;

    public MovimientosER() {

    }

    public MovimientosER(int id_cuenta, Date fecha, double monto) {
        this.id_cuenta = id_cuenta;
        this.fecha = fecha;
        this.monto = monto;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

}
