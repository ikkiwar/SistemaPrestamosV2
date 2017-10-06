/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
       ArrayList<String> datos= datosBD(sesion);
        String clave = datos.get(0);
        // encripta la contraseña ingresada en login para comparar con la contraseña ya encriptada en la bd
        Encriptador encrip = new Encriptador();
        
        String claveComprobador = encrip.encriptar(sesion.getContraseña());

        
        System.out.println(claveComprobador + " comparando con" + clave);
        // compara que las contraseñas sean iguales y pone la flag en true si es asi de lo contrario permance en false
        if (claveComprobador.equals(clave)) {
            verificar = true;
        }
        
        System.out.print(verificar+"----"+sesion.getRol());
        return verificar;
    }

    // metodo que devuelve la contraseña en bd
    public ArrayList datosBD(Sesion sesion) throws ErrorPrestamo {
      ArrayList<String> datos = new ArrayList();
        ResultSet resultado;
        try {
            
            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT * FROM usuario WHERE login='" + sesion.getUser() + "';");
            
            while (resultado.next()) {
                datos.add(0,resultado.getString("clave")) ;    
                datos.add(1,resultado.getString("nombre"));
                datos.add(2,resultado.getNString("rol"));
            
            }   
            
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorSesion.verificar", "Error al obtener clave ");
            
        }
        
        sesion.setRol(datos.get(2));
       
        return datos;
    }
    
}
