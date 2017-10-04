/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Usuario;

/**
 *
 * @author estuardo
 */
public class ControladorUsuario {

    public boolean verificar(String nombreUsuario, String claveUsuario) throws ErrorPrestamo {
        boolean verificar = false;
        String nombre = null;
        String clave = null;
        ResultSet resultado;

        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT * FROM usuario WHERE nombre='" + nombreUsuario + "';");

            while (resultado.next()) {
                clave = resultado.getString("clave");
                System.out.println(clave);
                nombre = resultado.getString("nombre");
                System.out.println(nombre);
            }

        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorPrestamo.ObtenerActivos", "Error al obtener Prestamos Activos");

        }

        if (claveUsuario.equals(clave) && nombreUsuario.equals(nombre)) {
            verificar = true;
        }

        System.out.print(verificar);
        return verificar;
    }

    public void agregar(Usuario usuario) throws ErrorPrestamo {

        Conexion conexion = new Conexion();

        if (conexion != null) {
            conexion.UID("INSERT INTO usuario(id_usuario,login,nombre,apellidos,clave,"
                    + "rol) "
                    + "VALUES('" + usuario.getId_usuario() + "','" + usuario.getLogin()
                    + "','" + usuario.getNombres() + "', '" + usuario.getApellidos()
                    + "','" + usuario.getClave() + "','" + usuario.getRol() + "')");

        } else {
            throw new ErrorPrestamo("Error al Insertar Datos", "ControladorCliente.agregar", "Error al Agregar Cliente");
        }

    }

    public List<Usuario> obtener() throws ErrorPrestamo {
        List<Usuario> Usuario = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;

        try {

            resultado = conexion.getValores("SELECT * FROM usuario");

            while (resultado.next()) {
                Usuario.add(new Usuario(resultado.getInt("id_usuario"), resultado.getString("login"),
                        resultado.getString("nombre"), resultado.getString("apellidos"),
                        resultado.getString("clave"), resultado.getString("rol").charAt(0)));
                // System.out.println("Estoy en el While");

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorUsuario.obtener", "Error al Obtener los Usuarios");
        }

        return Usuario;
    }

    public void eliminar(Usuario usuario) throws ErrorPrestamo {

        Conexion conexion = new Conexion();

        if (conexion != null) {
            conexion.UID("DELETE FROM usuario WHERE id_usuario='" + usuario.getId_usuario() + "'");
        } else {

            throw new ErrorPrestamo("Error al Eliminar", "ControladorUsuario.eliminar", "Error al Eliminar Usuario");
        }

    }

    public void editar(Usuario Usuario) throws ErrorPrestamo {

        ResultSet resultado;

        try {
            Conexion conexion = new Conexion();
            conexion.UID("UPDATE usuario SET clave='" + Usuario.getClave()
                    + "',rol='" + Usuario.getRol() + "'WHERE id_usuario='" + Usuario.getId_usuario() + "'");

        } catch (Exception e) {
            throw new ErrorPrestamo("Error al ActualizarUsuario",
                    "ControladorUsuario.actualizar", "Error al actualiar el Usario");
        }

    }

    public List<Usuario> buscar(String filtro) throws ErrorPrestamo {
        List<Usuario> usuario = new ArrayList<>();
        Conexion conexion = new Conexion();
        ResultSet resultado;

        try {

            resultado = conexion.getValores("SELECT * FROM usuario  WHERE  nombre LIKE '"
                    + filtro + "%' OR apellidos LIKE '" + filtro + "%'");

            while (resultado.next()) {
                usuario.add(new Usuario(resultado.getInt("id_usuario"), resultado.getString("login"),
                        resultado.getString("nombre"), resultado.getString("apellidos"),
                        resultado.getString("clave"), resultado.getString("rol").charAt(0)));

            }

        } catch (SQLException ex) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorUsuario.obtener", "Error al Obtener los Usuarios por filtro");
        }

        return usuario;
    }

    public int obtenerMaxId() {
        int id = 0;

        ResultSet resultado;

        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT MAX(id_usuario) From usuario");
            while (resultado.next()) {
                id = resultado.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        id = id + 1;

        return id;
    }

}
