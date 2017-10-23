package ues.edu.sv.ingenieria.diseño.proyectox.definiciones;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author estuardo
 */
public class Prestamo {

    private int id_prestamo;
    private Cliente cliente;
    private double monto;
    private double valor_cuota;
    private double tasa_interes;
    private int cantidad_cuotas;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Date fecha_ultimo_pago;
    private double saldo;
    private int estado;
    private String observaciones;
    private List<Cuota> cuota;
    private String dui;
    private String nombres;
    private String apellidos;
    private String capitalizacion;
    private double interesCapitalizado = 0.0;

    public Prestamo() {
        fecha_inicio = Calendar.getInstance().getTime();
        this.cantidad_cuotas = 12;
    }

    public Prestamo(Integer id_prestamo, String dui, String nombres,
            String apellidos, double monto, double valor_cuota,
            double tasa_interes, int cantidad_cuotas, Date fecha_inicio,
            Date fecha_fin, Date fecha_ultimo_pago, double saldo, int estado,
            String observaciones, String capitalizacion) {
        this.id_prestamo = id_prestamo;
        this.monto = monto;
        this.valor_cuota = valor_cuota;
        this.tasa_interes = tasa_interes;
        this.cantidad_cuotas = cantidad_cuotas;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_ultimo_pago = fecha_ultimo_pago;
        this.saldo = saldo;
        this.estado = estado;
        this.observaciones = observaciones;
        this.capitalizacion = capitalizacion;
        this.dui = dui;
        this.nombres = nombres;
        this.apellidos = apellidos;

    }

    public double calcularCuota() {
        
        double cuota = 0.0;
        this.saldo = this.monto;

        if (this.monto > 0 && this.cantidad_cuotas > 0) {

            calcularInteresCapitalizacion();

            cuota = this.monto * (this.interesCapitalizado 
                    / (1 - Math.pow(1 + this.interesCapitalizado, -1 * this.cantidad_cuotas)));
        }
        System.out.println("valor de la Cuota" + cuota);

        Calendar fin = Calendar.getInstance();
        fin.setTime(this.fecha_inicio);
        fin.add(Calendar.MONTH, this.cantidad_cuotas);
        this.fecha_fin = fin.getTime();

        this.estado = 1;
        this.valor_cuota = Math.round(cuota * 100.0) / 100.0;

        return cuota;

    }

    public void calcularInteresCapitalizacion() {
        double tasaInteres=0; 
        System.out.println("LA CAPITALIZACION ES:" + this.capitalizacion);
        System.out.println("TSA INTERES"+this.tasa_interes);
        tasaInteres = this.tasa_interes / 100;
        if (this.capitalizacion != null && this.tasa_interes >= 0) {

            if (this.capitalizacion.equals("M")) {
                System.out.println("Estoy en Capitalizacion Mensual");
                interesCapitalizado = tasaInteres;

            } else if (this.capitalizacion.equals("D")) {

                interesCapitalizado = tasaInteres / (30 * this.cantidad_cuotas);

                System.out.println("Estoy en Capitalizacion Diaria");
            }

        }
        
        
       
        

    }

    public double calcularInteres() {
        if (this.saldo > 0 && this.tasa_interes > 0) {
            int diff = 1;
            Calendar lastFecha = Calendar.getInstance();
            if (this.getCuota().size() > 0) {
                lastFecha.setTime(this.fecha_ultimo_pago);
            } else {
                lastFecha.setTime(this.fecha_inicio);
            }

            diff = Calendar.getInstance().get(Calendar.MONTH) - lastFecha.get(Calendar.MONTH);
            return this.saldo * diff * this.tasa_interes;
        }

        return 0;

    }

    public void calcularMora() {

    }

    public boolean validar() {
        if (this == null) {
            return false;
        } else if (this.id_prestamo == 0) {
            return false;
        } else if (this.monto <= 0) {
            return false;
        } else if (this.valor_cuota <= 0) {
            return false;
        } else if (this.tasa_interes <= 0) {
            return false;
        } else if (this.cantidad_cuotas <= 0) {
            return false;
        } else if (this.fecha_inicio == null) {
            return false;
        } else if (this.saldo <= 0) {
            return false;
        } else if (this.estado <= 0 || this.estado > 2) {
            return false;
        } else if (this.valor_cuota != calcularCuota()) {
            return false;
        } else if (this.capitalizacion == null) {
            return false;
        }

        if (this.fecha_fin != null) {
            if (this.fecha_fin.before(this.fecha_inicio)) {
                return false;
            }
        }

        return true;
    }

    public void agregarCuota(Cuota cuota) {

        if (cuota != null) {
            this.cuota.add(cuota);

            this.setSaldo(cuota.getSaldo_actualizado());
            Calendar fecha = Calendar.getInstance();
            fecha.set(Calendar.DAY_OF_MONTH, fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
            System.out.println("max day: " + fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
            this.setFecha_ultimo_pago(fecha.getTime());

        }

    }

    public void pagar() {

    }

    public Cuota crearCuota() {
        Cuota cuota = new Cuota();
        cuota.setPrestamo(this);
        cuota.setFecha(Calendar.getInstance().getTime());
        cuota.setInteres(this.calcularInteres());

        return cuota;

    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getValor_cuota() {
        return valor_cuota;
    }

    public void setValor_cuota(double valor_cuota) {
        this.valor_cuota = valor_cuota;
    }

    public double getTasa_interes() {
        return tasa_interes;
    }

    public void setTasa_interes(double tasa_interes) {
        this.tasa_interes = tasa_interes;
    }

    public int getCantidad_cuotas() {
        return cantidad_cuotas;
    }

    public void setCantidad_cuotas(int cantidad_cuotas) {
        this.cantidad_cuotas = cantidad_cuotas;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getFecha_ultimo_pago() {
        return fecha_ultimo_pago;
    }

    public void setFecha_ultimo_pago(Date fecha_ultimo_pago) {
        this.fecha_ultimo_pago = fecha_ultimo_pago;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Cuota> getCuota() {
        return cuota;
    }

    public void setCuota(List<Cuota> cuota) {
        this.cuota = cuota;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCapitalizacion() {
        return capitalizacion;
    }

    public void setCapitalizacion(String capitalizacion) {
        this.capitalizacion = capitalizacion;
    }

    public double getInteresCapitalizado() {
        return interesCapitalizado;
    }

    public void setInteresCapitalizado(double interesCapitalizado) {
        this.interesCapitalizado = interesCapitalizado;
    }

}
