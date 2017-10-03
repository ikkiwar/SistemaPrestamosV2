/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño1.proyectox.definiciones;

/**
 *
 * @author estuardo
 */
public class Usuario {
    
    private int id_usuario;
    private String login;
    private String nombres;
    private String apellidos;
    private String clave;
    private char rol;
    
    
    
    public Usuario(){
    }
    
    public Usuario(int id_usuario,String login,String nombres,String apellidos,String clave,char rol){
        this.id_usuario =id_usuario;
        this.login=login;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.rol=rol;
        this.clave=clave;
      
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public char getRol() {
        return rol;
    }

    public void setRol(char rol) {
        this.rol = rol;
    }
    
    
    
    
    
    
    
}
