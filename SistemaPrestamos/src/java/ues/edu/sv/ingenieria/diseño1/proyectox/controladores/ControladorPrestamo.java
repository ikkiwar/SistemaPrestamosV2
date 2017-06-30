package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Cliente;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Prestamo;

/**
 *
 * @author estuardo
 */
public class ControladorPrestamo implements Serializable {

    public void agregar(Prestamo prestamo) throws ErrorPrestamo {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha1 = formato.format(prestamo.getFecha_inicio());
        String fecha2 = formato.format(prestamo.getFecha_fin());
        prestamo.setFecha_ultimo_pago(null);

        try {

            Conexion conexion = new Conexion();
            conexion.UID("INSERT INTO prestamo (id_prestamo,dui,monto,valor_cuota,tasa_interes,cantidad_cuotas,fecha_inicio,fecha_fin,fecha_ultimo_pago,fecha_ultimo_pago,saldo,estado,observaciones,capitalizacion) VALUES ('" + prestamo.getId_prestamo() + "','" + prestamo.getDui() + "','" + prestamo.getMonto() + "','" + prestamo.getValor_cuota() + "','" + prestamo.getTasa_interes() + "','" + prestamo.getCantidad_cuotas() + "','" + fecha1 + "','" + fecha2 + "',"+fecha1 + prestamo.getFecha_ultimo_pago() + ",'" + prestamo.getSaldo() + "','" + prestamo.getEstado() + "','" + prestamo.getObservaciones() + "','" + prestamo.getCapitalizacion() + "')");

        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Agregar", "ControladorPrestamo.Agregar", "Error al Agregar Prestamo");
        }

    }

//(ikki) en este metodo lleno la lista para llenar la tabla
    public List<Prestamo> obtenerActivos() throws ErrorPrestamo {
        List<Prestamo> activo = new ArrayList<>();
        ResultSet resultado;

        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("Select * from cliente C left join prestamo P on C.dui=P.dui where estado=1 ");
            while (resultado.next()) {
                System.out.println("Estoy en el While");
                activo.add(new Prestamo(resultado.getInt("id_prestamo"), resultado.getString("dui"), resultado.getString("nombres"), resultado.getString("apellidos"), resultado.getDouble("monto"), resultado.getDouble("valor_cuota"), resultado.getDouble("tasa_interes"), resultado.getInt("cantidad_cuotas"), resultado.getDate("fecha_inicio"), resultado.getDate("fecha_fin"), resultado.getDate("fecha_ultimo_pago"), resultado.getDouble("saldo"), resultado.getInt("estado"), resultado.getString("observaciones"), resultado.getString("capitalizacion")));

            }
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorPrestamo.ObtenerActivos", "Error al obtener Prestamos Activos");
        }
        return activo;
    }

    public int obtenerMaxId() throws ErrorPrestamo {
        int id = 0;
        ResultSet resultado;

        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT id_prestamo from prestamo ORDER BY id_prestamo DESC LIMIT 1");
            
            if(resultado.next()){
              id = resultado.getInt(1);
            }
           

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }

        id = id + 1;
        return id;
    }

    public List<Prestamo> obtenerHistorial() throws ErrorPrestamo {
        List<Prestamo> historial = new ArrayList<>();
        ResultSet resultado;

        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT * FROM prestamo p LEFT JOIN cliente c ON c.dui = p.dui");
            while (resultado.next()) {

                historial.add(new Prestamo(resultado.getInt("id_prestamo"), resultado.getString("dui"), resultado.getString("nombres"), resultado.getString("apellidos"), resultado.getDouble("monto"), resultado.getDouble("valor_cuota"), resultado.getDouble("tasa_interes"), resultado.getInt("cantidad_cuotas"), resultado.getDate("fecha_inicio"), resultado.getDate("fecha_fin"), resultado.getDate("fecha_ultimo_pago"), resultado.getDouble("saldo"), resultado.getInt("estado"), resultado.getString("observaciones"), resultado.getString("capitalizacion")));

            }
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorPrestamo.ObtenerActivos", "Error al obtener Prestamos Activos");
        }
        return historial;
    }

    public void modificar(Prestamo prestamo) throws ErrorPrestamo {

        try {

            Conexion conexion = new Conexion();
            conexion.UID("UPDATE prestamo SET dui='" + prestamo.getCliente().getDui() + "',monto='" + prestamo.getMonto() + "',valor_cuota='" + prestamo.getValor_cuota() + "',tasa_interes='" + prestamo.getTasa_interes() + "',cantidad_cuotas='" + prestamo.getCantidad_cuotas() + "',fecha_inicio='" + prestamo.getFecha_inicio() + "',fecha_fin='" + prestamo.getFecha_fin() + "',fecha_ultimo_pago='" + prestamo.getFecha_ultimo_pago() + "',saldo='" + prestamo.getSaldo() + "',estado='" + prestamo.getEstado() + "',"
                    + "observaciones='" + prestamo.getObservaciones() + "' WHERE id_prestamo='" + prestamo.getId_prestamo() + "'");

        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Modificar", "ControladorPrestamo.Modificar", "Error al Modificar el Prestamo");
        }

    }

    public void actualizar(double saldo, int id, Date date) throws ErrorPrestamo {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(date);

        try {
            Conexion conexion = new Conexion();
            conexion.UID("UPDATE prestamo SET saldo='" + saldo + "',fecha_ultimo_pago='" + fecha + "' WHERE id_prestamo='" + id + "'");
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al ActualizarSaldo", "ControladorPrestamo.atualizarSaldo", "Error al actualiar el saldo");
        }

    }

    public List<Prestamo> obtenerPorCliente(Cliente cliente) throws ErrorPrestamo {
        List<Prestamo> seleccionado = new ArrayList<>();
        ResultSet resultado;
        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT * FROM prestamo WHERE cliente='" + cliente.getDui() + "';");
            while (resultado.next()) {
                seleccionado.add(new Prestamo(resultado.getInt("id_prestamo"), resultado.getString("dui"), resultado.getString("nombres"), resultado.getString("apellidos"), resultado.getDouble("monto"), resultado.getDouble("valor_cuota"), resultado.getDouble("tasa_interes"), resultado.getInt("cantidad_cuotas"), resultado.getDate("fecha_inicio"), resultado.getDate("fecha_fin"), resultado.getDate("fecha_ultimo_pago"), resultado.getDouble("saldo"), resultado.getInt("estado"), resultado.getString("observaciones"), resultado.getString("capitalizacion")));
            }
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al obtener", "ControladorCliente.obtenerPorCliente", "Error al obtener por Cliente");
        }

        return seleccionado;
    }

    // metodo de buscar por filtrado 
    public List<Prestamo> buscar(String filtro) throws ErrorPrestamo {

        List<Prestamo> prestamo = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;
        try {

            resultado = conexion.getValores("SELECT * FROM prestamo p LEFT JOIN cliente c ON c.dui = p.dui where nombres LIKE '" + filtro + "%' OR apellidos LIKE '" + filtro + "%'");
            while (resultado.next()) {

                prestamo.add(new Prestamo(resultado.getInt("id_prestamo"), resultado.getString("dui"), resultado.getString("nombres"), resultado.getString("apellidos"), resultado.getDouble("monto"), resultado.getDouble("valor_cuota"), resultado.getDouble("tasa_interes"), resultado.getInt("cantidad_cuotas"), resultado.getDate("fecha_inicio"), resultado.getDate("fecha_fin"), resultado.getDate("fecha_ultimo_pago"), resultado.getDouble("saldo"), resultado.getInt("estado"), resultado.getString("observaciones"), resultado.getString("capitalizacion")));

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorCliente.Buscar", "Error al Obtener los clientesd");
        }

        return prestamo;

    }
}
