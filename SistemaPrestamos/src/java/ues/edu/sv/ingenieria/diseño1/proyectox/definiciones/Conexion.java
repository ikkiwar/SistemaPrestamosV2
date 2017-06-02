
package ues.edu.sv.ingenieria.diseño1.proyectox.definiciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author estuardo
 */
public class Conexion {
    
    private String url = "jdbc:mysql://localhost:3306/prestamos";
    //private String url = "jdbc:mysql://localhost:3306/baseDeDatos"; algunas veces es necesario cambiar localhost por 127.0.0.1
    private String login = "root"; //Administrador de MySQL
    private String password = "12345";
    private Connection cnx = null;
    private Statement sttm = null;
    private ResultSet rst = null;

    //método para Update, Insert, Delete
    public void UID(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, login, password);
            sttm = cnx.createStatement();
            // cnx.
            sttm.executeUpdate(sql); //statement
        } catch (ClassNotFoundException c) {
            System.out.print(c);
            System.exit(1); //salir de aplicación
        } catch (SQLException e) {
            System.out.print(e);
            System.exit(1);
        }
    }

    //Método para Consultar
    public ResultSet getValores(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, login, password);
            sttm = cnx.createStatement();
            rst = sttm.executeQuery(sql);  //resultset
        } catch (ClassNotFoundException c) {
            
            System.exit(1);
        } catch (SQLException e) {
            
            System.exit(1);
        } finally {
            return rst;
        }
    }
    
}
