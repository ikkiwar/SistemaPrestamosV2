
package ues.edu.sv.ingenieria.diseño1.proyectox.definiciones;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ues.edu.sv.ingenieria.diseño1.proyectox.controladores.ErrorPrestamo;

/**
 *
 * @author estuardo
 */
public class Parametro {

    private int id_parametro;
    private String nombre;
    private String valor;

    public Parametro() {

    }

    public Parametro(int id_parametro, String nombre, String valor) {
        this.id_parametro = id_parametro;
        this.nombre = nombre;
        this.valor = valor;

    }

    public Parametro obtener(int id) throws ErrorPrestamo {
        Parametro parametro = null;

        try {

            Conexion conexion = new Conexion();
            ResultSet resultado = conexion.getValores("SELECT * FROM parametros WHERE id_parametro='" + id + "'");
            while (resultado.next()) {

                parametro = new Parametro(id, resultado.getString("parametro"), resultado.getString("valor"));
                // this.nombre=resultado.getString("nombre");
                //this.valor=resultado.getString("valor");

            }
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "Parametros obtener", "Error al obtener Paramtros");
        }

        return parametro;
    }

    public List<Parametro> obtener() throws ErrorPrestamo {
        List<Parametro> lista = new ArrayList<>();
        Conexion conexion = new Conexion();

        try {

            ResultSet resultado = conexion.getValores("SELECT * FROM parametros");
            while (resultado.next()) {
                lista.add(new Parametro(resultado.getInt("id"), resultado.getString("parametro"),
                        resultado.getString("valor")));
            }
        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "Obtener Lista parametro", "Error al obtener paramtros");
        }

        return lista;
    }

    public void modificar(Parametro parametro) throws ErrorPrestamo {
        Conexion conexion = new Conexion();

        try {

            conexion.UID("UPDATE parametros SET parametro='" + parametro.nombre + "',"
                    + "valor='" + parametro.valor + "' "
                    + "WHERE id='" + parametro.id_parametro + "'");

        } catch (Exception e) {
            throw new ErrorPrestamo("Error al modificar", "modificar parametros", "Error al modificar parametros");
        }

    }

    public void agregar() {

    }

    public double obtenerTasas(String nombreTasa) throws ErrorPrestamo {
        double tasa = 0;
        //String tasa=null;
        Conexion conexion = new Conexion();
        ResultSet resultado;

        try {

            resultado = conexion.getValores("SELECT valor FROM parametros WHERE parametro='" + nombreTasa + "'");
            if (resultado.next()) {
                tasa = Double.parseDouble(resultado.getString(1));
            }

        } catch (Exception e) {
            throw new ErrorPrestamo("Error al Obtener", "Parametros tasas", "Error al obtener parametros");
        }

        return tasa;
    }

    public int getId_parametro() {
        return id_parametro;
    }

    public void setId_parametro(int id_parametro) {
        this.id_parametro = id_parametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
