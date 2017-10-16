/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.controladores;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import servicios.Direccionamientos;
import servicios.Encriptador;
import servicios.EntradaBitacora;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Conexion;
import ues.edu.sv.ingenieria.diseño1.proyectox.definiciones.Sesion;

/**
 *
 * @author estuardo
 */
public class ControladorSesion {

    Direccionamientos direccionamineto = new Direccionamientos();
    ControladorBitacora bitacora = new ControladorBitacora();
    
   

    // metodo para comprar contraseñas la que se esta ingresando y la almacenda en la base de datos
    public boolean verificar(Sesion sesion) throws ErrorPrestamo {
        boolean verificar = false;
       
        ArrayList<Sesion> datos = datosBD(sesion);

        // encripta la contraseña ingresada en login para comparar con la contraseña ya encriptada en la bd
        Encriptador encrip = new Encriptador();

        String claveComprobador = encrip.encriptar(sesion.getContraseña());

        if (!datos.isEmpty()) {

            String clave = datos.get(0).getContraseña();
            System.out.println(claveComprobador + " comparando con" + clave);
            // compara que las contraseñas sean iguales y pone la flag en true si es asi de lo contrario permance en false
            if (claveComprobador.equals(clave)) {
                verificar = true;
               
              /*  sesionverificada.setId(datos.get(0).getId());
                sesionverificada.setUser(datos.get(0).getUser());
                sesionverificada.setRol(datos.get(0).getRol());*/
                EntradaBitacora sesionverificada = new EntradaBitacora(datos.get(0).getId(),datos.get(0).getUser(),
                datos.get(0).getContraseña(),datos.get(0).getRol());
                System.out.println(sesionverificada.getUser());
                bitacora.agregar(sesionverificada.getId(), "Inicio sesion");
            }

        }

        
        return verificar;
    }

    // metodo que devuelve la contraseña en bd
    public ArrayList datosBD(Sesion sesion) throws ErrorPrestamo {
        ArrayList<Sesion> datos = new ArrayList();
        ResultSet resultado;
        try {

            Conexion conexion = new Conexion();
            resultado = conexion.getValores("SELECT * FROM usuario WHERE login='" + sesion.getUser() + "';");

            while (resultado.next()) {
                datos.add(new Sesion(resultado.getInt("id_usuario"),resultado.getString("login"),
                        resultado.getString("clave"),resultado.getString("rol").charAt(0)));
                
                

            }

        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "ControladorSesion.verificar", "Error al obtener clave ");

        }

        

        return datos;
    }

}
