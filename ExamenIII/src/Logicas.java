
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MONTIZ
 */
public class Logicas {
    
    String Fecha;
    String Tarea;
    String Encargado;
    ArrayList <Datos> Usuarios;


    Document Documentos;
    FileOutputStream Archivo;
    Paragraph Titulo;

    public Logicas(String Fecha, String Tarea,String Encargado, ArrayList<Datos> Usuarios) {
        this.Fecha = Fecha;
        this.Tarea = Tarea;
        this.Encargado = Encargado;
        this.Usuarios = Usuarios;
        
        Documentos = new Document();
        Titulo = new Paragraph("Reportes de Tareas Realizadas");
    }
    public void crearReporte(){
        
        try {
            
            Archivo = new FileOutputStream(Tarea + ".pdf");
            PdfWriter.getInstance(Documentos, Archivo);
            
            Documentos.open();
            Titulo.setAlignment(1);
            
            Documentos.add(Titulo);
            Documentos.add(Chunk.NEWLINE);
            Documentos.add(new Paragraph ("Su reporte de Tareas"));
            Documentos.add(Chunk.NEWLINE);
            
            PdfPTable Tabla = new PdfPTable(3);
            Tabla.setWidthPercentage(100);
            
            PdfPCell Fecha = new PdfPCell(new Phrase("Fecha de Tarea"));
            
            PdfPCell Tarea = new PdfPCell(new Phrase("Tarea"));
            
            PdfPCell Encargado = new PdfPCell(new Phrase("Encargado"));
            
            Tabla.addCell(Fecha);
            Tabla.addCell(Tarea);
            Tabla.addCell(Encargado);
            
            for (Datos Usuarios : Usuarios){
                Tabla.addCell(Usuarios.getFecha());
                Tabla.addCell(Usuarios.getTarea());
                Tabla.addCell(Usuarios.getEncargado());
            }
            
            Documentos.add(Tabla);
            
            Documentos.close();
            
            JOptionPane.showMessageDialog(null, "Se ha Realizado el Reporte");
            
        } catch (Exception e) {
        }
    }
}
