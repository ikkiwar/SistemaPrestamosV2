/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.sql.ResultSet;
import servicios.Encriptador;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Sesion;

/**
 *
 * @author estuardo
 */
public class ControladorSesion {
    
  // metodo para comprar contraseñas la que se esta ingresando y la almacenda en la base de datos
    public boolean verificar(Sesion sesion) throws ErrorPrestamo {
        boolean verificar = false;
        String nombre = null;
        String clave = null;
        // encripta la contraseña ingresada en login para comparar con la contraseña ya encriptada en la bd
        Encriptador encrip = new Encriptador();
        ResultSet resultado;
        String claveComprobador = encrip.encriptar(sesion.getContraseña());
// trae de la bd la contraseña encriptada almacenada 
        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT * FROM usuario WHERE login='" + sesion.getUser() + "';");

            while (resultado.next()) {
                clave = resultado.getString("clave");
                System.out.println(clave);
                nombre = resultado.getString("nombre");
                System.out.println(nombre);
            }

        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorSesion.verificar", "Error al obtener clave ");

        }
        System.out.println(claveComprobador+" comparando con"+clave);
        // compara que las contraseñas sean iguales y pone la flag en true si es asi de lo contrario permance en false
        if (claveComprobador.equals(clave) ) {
            verificar = true;
        }

        System.out.print(verificar);
        return verificar;
    }  
    
    
    
}
