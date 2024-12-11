package Modelo;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Paint;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import conexionBase.conexionBD;

public class ReporteVentas extends ReportePapa{
	public ReporteVentas() {
		super();
	}
	
	@Override
	public void addTableCell(PdfPTable table, String text) {
		// TODO Auto-generated method stub
		super.addTableCell(table, text);
	}
	
	@Override
	public void addTableHeader(PdfPTable table, Font font, String text) {
		// TODO Auto-generated method stub
		super.addTableHeader(table, font, text);
	}
	
	@Override
	public String Fechita() {
		// TODO Auto-generated method stub
		return super.Fechita();
	}
	
	@Override
	public String horita() {
		// TODO Auto-generated method stub
		return super.horita();
	}
	
	public ArrayList<DatosVentas> obtenerDatos (LocalDate inicio, LocalDate fin){
		ArrayList<DatosVentas> inv = new ArrayList<>();
		String consulta = "select pr.nombre, pr.categoria, sum(pd.subtotal) as summ\r\n"
				+ "from productos as pr, producto_factura as pd, factura as fa\r\n"
				+ "where pr.ID_Producto = pd.ID_producto\r\n"
				+ "and fa.id_factura = pd.id_factura\r\n"
				+ "and fa.fecha >= ?\r\n"
				+ "and fa.fecha <= ?\r\n"
				+ "group by pr.ID_producto\r\n"
				+ "order by pr.categoria;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			
			ps=conn.prepareStatement(consulta);
			ps.setString(1, inicio.format(DateTimeFormatter.ISO_LOCAL_DATE));
			ps.setString(2, fin.format(DateTimeFormatter.ISO_LOCAL_DATE));
			rs=ps.executeQuery();
			int num = 1;
			while(rs.next()) {
				String nombre = rs.getString("pr.nombre");
				String[] cat = {"","Frutas","Verduras","Carnes","Lacteos","Cereales","Dulces","Limpieza","Aseo Personal"};
                int categoria = rs.getInt("pr.categoria");
                String cate = cat[categoria];
                double total = rs.getDouble("summ");
                BigDecimal bd = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
                double totalRedondeado = bd.doubleValue();
                DatosVentas ped = new DatosVentas(num, nombre, cate, totalRedondeado);
                inv.add(ped);
				num++;
			}
			System.out.println("Funciona sql");
		}catch(Exception e) {
			 e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	            System.out.println("conexiones cerradas");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		return inv;
	}
	
	public void GenerarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        String dest = "ReporteVentas.pdf";
        
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(dest));

            document.open();
            
            String imagePath = "C:\\Documentos\\imag\\logo330x200.png";
            Image imagen = Image.getInstance(imagePath);
            imagen.scaleToFit(100, 100);
            imagen.setAbsolutePosition(10, document.getPageSize().getHeight() - imagen.getScaledHeight() - 10);
            document.add(imagen);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Paragraph title = new Paragraph("Reporte de Ventas", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            Paragraph espaciador = new Paragraph(" ");
            espaciador.setSpacingBefore(10);
            document.add(espaciador);
            
            Font fechayHora = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
            Paragraph fechaReporte = new Paragraph("Fecha de Reporte: " + Fechita() + "            Hora de Reporte:" + horita(), fechayHora);
            document.add(fechaReporte);
            
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaActualStrI = fechaInicio.format(formato);
            String fechaActualStrF = fechaFin.format(formato);
            
            
            document.add(espaciador);
            
            Paragraph intervaloInforme = new Paragraph("Reporte del: " + fechaActualStrI + " al " + fechaActualStrF, fechayHora);
            document.add(intervaloInforme);
            
            document.add(espaciador);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            addTableHeader(table, headerFont, "Número");
            addTableHeader(table, headerFont, "Categoria");
            addTableHeader(table, headerFont, "Nombre");
            addTableHeader(table, headerFont, "Total");
            
            ArrayList<DatosVentas> inv = new ArrayList<>();
            inv = obtenerDatos(fechaInicio, fechaFin);
            
            for(DatosVentas d : inv) {
            	addTableCell(table, "" + d.getNumero());
            	addTableCell(table, "" + d.getTipo());
                addTableCell(table, "" + d.getNombre());
                addTableCell(table, "" + d.getGanancia());
            }
             
            double suma = SumaTotal(inv);
            
            BigDecimal bd = new BigDecimal(suma).setScale(2, RoundingMode.HALF_UP);
            double totalRedondeado = bd.doubleValue();
            
            addTableCell(table, "TOTAL");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "" + totalRedondeado);

            document.add(table);
            
            document.add(espaciador);
            document.add(espaciador);
            
            Map<String, Double> totalPorCategoria = new HashMap<>();

            for (DatosVentas d : inv) {
                totalPorCategoria.put(
                    d.getTipo(),
                    totalPorCategoria.getOrDefault(d.getTipo(), 0.0) + d.getGanancia()
                );
            }

            // Llenar el dataset con las ganancias sumadas por categoría
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (Map.Entry<String, Double> entry : totalPorCategoria.entrySet()) {
                dataset.setValue(entry.getKey(), entry.getValue());
            }
            
            JFreeChart chart = ChartFactory.createPieChart("Ventas por Categoría (Bs)", dataset, true, true, false);
            
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setSectionPaint("Frutas", new Color(76, 175, 80));         // Verde
            plot.setSectionPaint("Verduras", new Color(139, 195, 74));      // Verde claro
            plot.setSectionPaint("Carnes", new Color(244, 67, 54));         // Rojo
            plot.setSectionPaint("Lacteos", new Color(33, 150, 243));       // Azul
            plot.setSectionPaint("Cereales", new Color(255, 193, 7));       // Amarillo
            plot.setSectionPaint("Dulces", new Color(255, 87, 34));         // Naranja
            plot.setSectionPaint("Limpieza", new Color(121, 85, 72));       // Marrón
            plot.setSectionPaint("Aseo Personal", new Color(156, 39, 176)); // Morado
            
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
            	    "{0}: {1} ({2})", // "{0}" es el nombre, "{1}" es el valor, "{2}" es el porcentaje
            	    NumberFormat.getNumberInstance(), // Formato para el valor
            	    new DecimalFormat("0.00%")       // Formato para el porcentaje
            	));
            
            File chartFile = new File("chart.png");
            ChartUtils.saveChartAsPNG(chartFile, chart, 500, 400);

            // Agregar el gráfico al PDF
            Image chartImage = Image.getInstance(chartFile.getAbsolutePath());
            chartImage.scaleToFit(500, 400);
            chartImage.setAlignment(Element.ALIGN_CENTER);
            document.add(chartImage);

            document.close();
            
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
            

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static double SumaTotal (ArrayList<DatosVentas> ped) {
		double suma = 0.0;
		for(DatosVentas p : ped) {
			suma += p.getGanancia();
		}
		return suma;
	}
}
