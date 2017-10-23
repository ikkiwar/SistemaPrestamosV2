/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.ingenieria.diseño.proyectox.servicios;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

/**
 *
 * @author estuardo
 */
public class Ticket {
    
    
public void comprobantePago(String[] Lista){
    
    PrinterOptions p = new PrinterOptions();

        p.resetAll();
        p.initialize();
        p.feedBack((byte) 2);
        p.chooseFont(1);
        p.color(0);
        p.alignLeft();
        p.setText("Prestamos ABC");
        p.newLine();
        p.setText("Comprobante de pago");
        p.newLine();
        p.addLineSeperator();
        p.newLine();

        p.alignLeft();
        p.setText("Id Prestamo:"+Lista[0]);
        p.newLine();
        p.setText(Lista[1]);
        p.newLine();
        p.setText(Lista[2]);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.setText("Cuota: $"+Lista[3]);
        p.newLine();

        p.setText("Monto: $" + Lista[4]);

        p.newLine();
        p.setText("Saldo Anterior: $" + Lista[5]);
        p.newLine();
        p.setText("Mora: $"+ Lista[6]);
        p.newLine();
        p.setText("Interes: $"+Lista[7]);
        p.newLine();
        p.setText("Capital: $"+Lista[8]);
        p.newLine();
        p.setText("Saldo Actualizado: $"+Lista[9]);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.alignLeft();
        p.setText(" - Detalles - ");
        p.newLine();
        p.alignLeft();
        p.addLineSeperator();

        p.newLine();

        p.setText("Fecha de pago: "+Lista[10]);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.setText("Esperanza x Guirola FOREVER :3");
        p.newLine();
        p.addLineSeperator();
        p.feed((byte) 3);
        p.finit();

        feedPrinter(p.finalCommandSet().getBytes());
 
    
    
    
}    



    private static boolean feedPrinter(byte[] b) {
    try {       
        AttributeSet attrSet = new HashPrintServiceAttributeSet(new PrinterName("3nstar", null)); //EPSON TM-U220 ReceiptE4

        DocPrintJob job = PrintServiceLookup.lookupPrintServices(null, attrSet)[0].createPrintJob();       
        //PrintServiceLookup.lookupDefaultPrintService().createPrintJob();  

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(b, flavor, null);
        
        

        job.print(doc, null);
       
        
    } catch (javax.print.PrintException pex) {
        System.out.println("Printer Error " + pex.getMessage());
        return false;
    } catch(Exception e) {
        e.printStackTrace();
        return false;
    }
    return true;
}
    
    
    

    
    
    
}
