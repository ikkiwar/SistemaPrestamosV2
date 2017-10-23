
package ues.edu.sv.ingenieria.diseño.proyectox.controladores;

/**
 *
 * @author estuardo
 */
public class ErrorPrestamo extends Exception {
 
     public ErrorPrestamo() {
    }

    public ErrorPrestamo(String titulo, String ubicacion, String mensaje) {
        super(titulo + "&" + ubicacion + "&" + mensaje);
       
        System.out.println("Informacion: "+ titulo+ "ubicacion: "+ubicacion+ "Mensaje: "+mensaje);
    }
    
    
}
