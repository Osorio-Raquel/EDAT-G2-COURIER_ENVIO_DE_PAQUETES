package Modelo;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class FacturaEnPDF {

    public void GenerarReporte(double monto) {
        String dest = "C:/Documentos/Factura_Courier.pdf";  // Ruta del archivo PDF
        Document document = new Document();
        
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
            
            // Estilos para la factura con tonos rosas
            BaseColor colorPrincipal = new BaseColor(255, 182, 193);  // Rosa claro
            BaseColor colorSecundario = new BaseColor(255, 105, 180); // Rosa fuerte
            
            // Información de la empresa de Courier
            String empresaInfo = "Courier Express\nAv. Pando, La Paz - Bolivia\nTeléfono: 72036743\nCorreo: contacto@courierexpress.com";
            Font encabezado = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, colorSecundario);
            Paragraph paragraph = new Paragraph(empresaInfo, encabezado);
            document.add(paragraph);
            
            // Espaciado
            document.add(new Paragraph(" "));
            
            // Título de la factura con color rosa
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD, colorSecundario);
            Paragraph title = new Paragraph("FACTURA DE ENVÍO", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            // Espaciado
            document.add(new Paragraph(" "));
            
            // Información del cliente (predeterminada)
            Font clientInfoFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            String clienteInfo = "NIT: 123456789\nNombre: Juan Pérez\nDirección de Envío: Calle Ficticia 123, La Paz\nFecha y hora: " + obtenerFechaHora();
            Paragraph clienteData = new Paragraph(clienteInfo, clientInfoFont);
            document.add(clienteData);
            
            // Espaciado
            document.add(new Paragraph(" "));
            
            // Tabla de productos/paquetes enviados
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, colorSecundario);

            addTableHeader(table, headerFont, "Número");
            addTableHeader(table, headerFont, "Descripción");
            addTableHeader(table, headerFont, "Cantidad");
            addTableHeader(table, headerFont, "Precio Unitario");
            addTableHeader(table, headerFont, "Subtotal");

            // Paquetes enviados predeterminados
            addTableCell(table, "001");
            addTableCell(table, "Paquete 1");
            addTableCell(table, "1");
            addTableCell(table, "$10.00");
            addTableCell(table, "$10.00");
            
            addTableCell(table, "002");
            addTableCell(table, "Paquete 2");
            addTableCell(table, "2");
            addTableCell(table, "$5.00");
            addTableCell(table, "$10.00");
            
            // Total
            double total = 20.00;  // Total predeterminado
            BigDecimal bd = new BigDecimal(Double.toString(total));
            bd = bd.setScale(2, RoundingMode.FLOOR);
            
            addTableHeader(table, headerFont, "TOTAL");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableHeader(table, headerFont, "$" + bd.doubleValue());

            document.add(table);
            
            document.add(new Paragraph(" "));
            
            // Montos (Monto pagado y cambio)
            double cambio = monto - total;
            BigDecimal bdCambio = new BigDecimal(Double.toString(cambio));
            bdCambio = bdCambio.setScale(2, RoundingMode.FLOOR);
            
            Paragraph montos = new Paragraph("Monto pagado: $" + monto + "             Cambio: $" + bdCambio.doubleValue(), clientInfoFont);
            document.add(montos);
            
            // Espaciado adicional
            document.add(new Paragraph(" "));
            
            // Mensaje final sobre el servicio de courier
            String mensajeFinal = "Gracias por confiar en Courier Express. ¡Esperamos que tu paquete llegue seguro y rápido!";
            Paragraph finalMessage = new Paragraph(mensajeFinal, encabezado);
            finalMessage.setAlignment(Element.ALIGN_CENTER);
            document.add(finalMessage);
            
            document.close();

            System.out.println("¡Factura PDF generada exitosamente!");
            
            // Abrir el archivo PDF generado
            File pdfFile = new File(dest);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Apertura de archivos no soportada en este sistema.");
                }
            } else {
                System.out.println("El archivo PDF no fue encontrado.");
            }

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    
    private String obtenerFechaHora() {
        // Retorna la fecha y hora actual
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new java.util.Date());
    }
    
    // Método para agregar cabeceras a la tabla
    private void addTableHeader(PdfPTable table, Font font, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
    
    // Método para agregar celdas a la tabla
    private void addTableCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}