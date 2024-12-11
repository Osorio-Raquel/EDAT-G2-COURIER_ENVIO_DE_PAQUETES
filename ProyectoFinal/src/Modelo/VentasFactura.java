package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conexionBase.conexionBD;

public class VentasFactura {
	public ArrayList<Double>Cantidad;
	public ArrayList<String>productos;
	public VentasFactura(ArrayList<Double>Cantidad, ArrayList<String>productos) {
		this.Cantidad=Cantidad;
		this.productos=productos;
	}
	

	public int RealizarVenta(String fechaa) {
		String consulta= "INSERT INTO factura (metodo_pago, id_persona, total, fecha) values (1, 1, 0.0," + fechaa + ")";


		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;

		int facturaID = 0;
		try {
			ps=conn.prepareStatement(consulta);
			ps.executeUpdate();
			String obtenerIDF = "SELECT id_factura from factura where total = 0.0";
			PreparedStatement ps1= null;
			ps1 = conn.prepareStatement(obtenerIDF);
			rs=ps1.executeQuery();
			while(rs.next()) {
				facturaID = rs.getInt("id_factura");
			}
			double totalEsteSi = 0.0;
			
			for(int i = 0; i < productos.size(); i++) {
				String p = productos.get(i);
				String idP = "SELECT ID_producto, precio_venta from productos where ID_producto = '" + p + "';";
				PreparedStatement ps2= null;
				ps2 = conn.prepareStatement(idP);
				rs=ps2.executeQuery();
				int productoid = 0;
				double precio = 0.0;
				while(rs.next()) {
					productoid = rs.getInt("ID_producto");
					precio = rs.getDouble("precio_venta");
				}
				double cant = Cantidad.get(i);
				double subtotal = cant * precio;
				totalEsteSi += subtotal;
				String compras = "INSERT INTO producto_factura (ID_producto, id_factura, cantidad, Subtotal) "
						+ "values(" + productoid + "," + facturaID + "," + cant + "," + subtotal + ");";
				PreparedStatement ps3= null;
				ps3 = conn.prepareStatement(compras);
				ps3.executeUpdate();
			}
			
			String actualizarFactura = "UPDATE factura SET total = " + totalEsteSi + "WHERE id_factura = "+facturaID+";";
			PreparedStatement ps4= null;
			ps4 = conn.prepareStatement(actualizarFactura);
			ps4.executeUpdate();
			
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
		
		return facturaID;
	}
	public DefaultTableModel carritoFactura(String[] columnas, int id_factura) {
	    DefaultTableModel model = new DefaultTableModel(null, columnas);

	    if (Cantidad.size() != productos.size()) {
	        JOptionPane.showMessageDialog(null, "Las listas de productos y cantidades no coinciden en tamaño.");
	        return model;
	    }
	    System.out.println("factura: " + id_factura);

	    String consulta = "SELECT productos.nombre, producto_factura.cantidad, productos.precio_venta, producto_factura.Subtotal " +
	                      "FROM productos, producto_factura " +
	                      "WHERE id_factura = " + id_factura + " AND producto_factura.id_producto = productos.ID_producto;";

	    
	    conexionBD conec = new conexionBD();
	    Connection conn = conec.conexion();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        ps = conn.prepareStatement(consulta);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String[] tabla = new String[4];
	            tabla[0] = rs.getString("productos.nombre");
	            tabla[1] = rs.getString("producto_factura.cantidad");
	            tabla[2] = rs.getString("productos.precio_venta");
	            tabla[3] = rs.getString("producto_factura.subtotal");

	            model.addRow(tabla);
	            
	            System.out.println("Aniade fila");
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error cerrando recursos: " + e.getMessage());
	        }
	    }

	    return model;
	}


}
