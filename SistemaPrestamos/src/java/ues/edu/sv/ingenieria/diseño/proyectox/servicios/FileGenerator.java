
package ues.edu.sv.ingenieria.diseño.proyectox.servicios;

import java.io.File;
import java.sql.Connection;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author frank
 */
public class FileGenerator {
    private File jasper;
    private Map<String, Object> parametros;
    private Connection conexion;
    
    
     public FileGenerator() {
         
     }
     /**
     * Método genérico para generar reportes. Este método se tiene que invocar
     * después de haber creado un File (jasper) y haber agregado los parámetros
     * necesarios a la variable parametros para generar el reporte requerido.
     *
     * @param ver Indica si el archivo se verá en el navegador (true), de caso
     * contrario se descargará
     * @param templateName Nombre del archivo plantilla .jasper del reporte, el
     * archivo debe de estar dentro de /WEB-INF/fileTemplates/
     * @param parametros Mapa con los parametros necesarios para generar el
     * reporte
     */
    public void generar(boolean ver, String templateName, Map<String, Object> parametros) {
        this.parametros = parametros;
        jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/fileTemplates/" + templateName));
        if (ver) {
            verPDF();
        } else {
            exportarPDF();
        }
    }

    /**
     * Genera un archivo PDF para que el usuario seleccione la ubicación en
     * donde se guardará.
     */
    private void exportarPDF() {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }

            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! exportarPDF");
        }
    }

    /**
     * Genera un archivo PDF para que el usuario lo pueda ver en el navegador.
     */
    private void verPDF() {
        try {
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, conexion);

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            try (ServletOutputStream outStream = response.getOutputStream()) {
                outStream.write(bytes, 0, bytes.length);
                outStream.flush();
            }

            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! verPDF");
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

}
     

