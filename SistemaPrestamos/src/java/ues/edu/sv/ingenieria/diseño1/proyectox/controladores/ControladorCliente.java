package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Cliente;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;

/**
 *
 * @author estuardo
 */

//Este es un comentario puuu
public class ControladorCliente {

    public void agregar(Cliente cliente) throws ErrorPrestamo {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(cliente.getFecha_nacimiento());

        Conexion conexion = new Conexion();

        if (conexion != null) {
            conexion.UID("INSERT INTO cliente(dui,nit,nombres,apellidos,sexo,"
                    + "direccion,telefonos,fecha_nacimiento,observaciones) "
                    + "VALUES('" + cliente.getDui() + "','" + cliente.getNit()
                    + "','" + cliente.getNombres() + "', '" + cliente.getApellidos()
                    + "','" + cliente.getSexo() + "','" + cliente.getDireccion() + "','"
                    + cliente.getTelefono() + "','" + fecha + "','"
                    + cliente.getObservaciones() + "')");

        }
        else {
            throw new ErrorPrestamo("Error al Insertar Datos", "ControladorCliente.agregar", "Error al Agregar Cliente");
        }

    }

    public void actualizar(Cliente cliente) throws ErrorPrestamo {

        ResultSet resultado;
        System.out.println(cliente.getNombres());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formato.format(cliente.getFecha_nacimiento());

        try {
            Conexion conexion = new Conexion();
            conexion.UID("UPDATE cliente SET nit='" + cliente.getNit() 
                    +"',nombres='" + cliente.getNombres() + "',apellidos='" 
                    +cliente.getApellidos() + "',sexo='" + cliente.getSexo() 
                    +"',direccion='" + cliente.getDireccion() + "',telefonos='" 
                    +cliente.getTelefono() + "',fecha_nacimiento='" + fecha 
                    + "',observaciones='" + cliente.getObservaciones() 
                    +"' WHERE dui='" + cliente.getDui() + "'");
            
        } 
        catch (Exception e) {
            throw new ErrorPrestamo("Error al ActualizarSaldo", 
                    "ControladorPrestamo.atualizarSaldo", "Error al actualiar el saldo");
        }

    }

    public List<Cliente> obtener() throws ErrorPrestamo {

        List<Cliente> clientes = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;

        System.out.println("Estoy al Inicio del metodo obtener Clientes !");

        try {

            resultado = conexion.getValores("SELECT * FROM cliente");

            while (resultado.next()) {
                clientes.add(new Cliente(resultado.getString("dui"), resultado.getString("nit"),
                        resultado.getString("nombres"), resultado.getString("apellidos"),
                        resultado.getString("sexo").charAt(0), resultado.getString("direccion"),
                        resultado.getString("telefonos"), resultado.getDate("fecha_nacimiento"), 
                        resultado.getString("observaciones")));
                // System.out.println("Estoy en el While");

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorCliente.Obtener", "Error al Obtener los Clientes");
        }

        return clientes;

    }

    public List<Cliente> buscar(String filtro) throws ErrorPrestamo {

        List<Cliente> clientes = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;
        try {

            resultado = conexion.getValores("SELECT * FROM cliente  WHERE  nombres LIKE '" 
                    + filtro + "%' OR apellidos LIKE '" + filtro + "%'");
            
            while (resultado.next()) {
                clientes.add(new Cliente(resultado.getString("dui"), resultado.getString("nit"),
                        resultado.getString("nombres"), resultado.getString("apellidos"),
                        resultado.getString("sexo").charAt(0), resultado.getString("direccion"),
                        resultado.getString("telefonos"), resultado.getDate("fecha_nacimiento"),
                        resultado.getString("observaciones")));

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorCliente.Buscar", "Error al Obtener los clientesd");
        }

        return clientes;

    }

    public void eliminar(Cliente cliente) throws ErrorPrestamo {

        Conexion conexion = new Conexion();

        if (conexion != null) {
            conexion.UID("DELETE FROM cliente WHERE dui='" + cliente.getDui() + "'");
        } else {

            throw new ErrorPrestamo("Error al Eliminar", "ControladorCliente.eliminar", "Error al Eliminar Cliente");
        }

    }

}
