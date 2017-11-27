/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.servicios;

import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ues.edu.sv.ingenieria.diseño.proyectox.beans.FmrSesion;

/**
 *
 * @author estuardo
 */
//Esta clase se usa para recibir las peicitiones de reidereccion y ver si hay una sesion activa
public class FiltroSesion implements Filter {

    String[] AdminListPages = {
        "/SistemaPrestamos/pages/adminUsuarios.jsf",
        "/SistemaPrestamos/pages/parametro.jsf",
        "/SistemaPrestamos/pages/adminFinanciera.jsf",
        "/SistemaPrestamos/pages/bitacora.jsf",
        "/SistemaPrestamos/index.jsf"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //NO se usa ni se como
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Obtener el atributo login de FmrSesion
        FmrSesion loginBean = (FmrSesion) ((HttpServletRequest) request).getSession().getAttribute("fmrSesion");

        // Para el primer request no hay usuario loggeado 
        // Para las siguientes peticiones se tiene que verificar si el usuario esta loggeado
        if (loginBean == null || !loginBean.isLogin()) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/index.jsf");
        } else {

         
                chain.doFilter(request, response);
           

        }
    }

    @Override
    public void destroy() {
        // tampoco se :v
    }

}
