/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.servicios;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author kevin
 */
public class Encriptador {

    public Encriptador() {
    }
// metodo encargago del encritamiento de la clave en MD5  el cual recibe por parametro y retorna la clave encriptada
    public String encriptar(String clave) {

        String claveEncriptada = DigestUtils.md5Hex(clave);
        System.out.println("Texto Encriptado con MD5 : " + claveEncriptada);

        return claveEncriptada;
    }

}
