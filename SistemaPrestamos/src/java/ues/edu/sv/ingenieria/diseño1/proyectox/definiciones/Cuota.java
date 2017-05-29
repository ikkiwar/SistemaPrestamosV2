/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.definiciones;

import java.util.Date;

/**
 *
 * @author estuardo
 */
public class Cuota {

    private Prestamo prestamo;
    private int id_prestamo;
    private int num_cuota;
    private double valor;
    private double interes;
    private double capital;
    private Date fecha;
    private double saldo_anterior;
    private double saldo_actualizado;

    public Cuota() {
        this.fecha = new Date();
        
    }

    public Cuota(int id_prestamo, int num_cuota, double valor, double interes, double capital, Date fecha, double saldo_anterior, double saldo_actualizado) {
        this.id_prestamo = id_prestamo;
        this.num_cuota = num_cuota;
        this.valor = valor;
        this.interes = interes;
        this.capital = capital;
        this.fecha = fecha;
        this.saldo_anterior = saldo_anterior;
        this.saldo_actualizado = saldo_actualizado;
    }

    public boolean validar() {
        if (this == null) {
            return false;
        } else if (this.num_cuota < 0) {

            return false;
        } else if (this.getPrestamo().getId_prestamo() <= 0) {

            return false;
        } else if (this.getNum_cuota() <= 0 || this.getNum_cuota() > this.getPrestamo().getCantidad_cuotas()) {

            return false;
        } else if (this.valor <= 0) {

            return false;
        } else if (this.interes < 0) {

            return false;
        } else if (this.fecha == null || this.fecha.before(this.getPrestamo().getFecha_inicio()) || this.fecha.after(this.getPrestamo().getFecha_fin())) {

            return false;
        } else if (this.capital < 0) {

            return false;
        } else if (this.saldo_anterior <= 0) {

            return false;
        } else if (this.saldo_actualizado < 0) {

            return false;
        }

        return true;
    }

    public double calcularCuota() {

        this.setCapital(this.valor - this.interes);
        this.setSaldo_anterior(this.getPrestamo().getSaldo());
        this.setSaldo_actualizado(this.getSaldo_anterior() - this.capital);

        return this.getValor();
    }

    public int getNum_cuota() {
        return num_cuota;
    }

    public void setNum_cuota(int num_cuota) {
        this.num_cuota = num_cuota;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSaldo_anterior() {
        return saldo_anterior;
    }

    public void setSaldo_anterior(double saldo_anterior) {
        this.saldo_anterior = saldo_anterior;
    }

    public double getSaldo_actualizado() {
        return saldo_actualizado;
    }

    public void setSaldo_actualizado(double saldo_actualizado) {
        this.saldo_actualizado = saldo_actualizado;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

}
