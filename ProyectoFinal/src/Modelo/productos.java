package Modelo;

import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conexionBase.conexionBD;

public class productos {
	public boolean IngresoProducto(String nombre, double precioCompra, double precioVenta, double cantidad,
			double demanda,
			String fechaLimita, String proveedor, String telefono, int categoria,
			double mantenerInv, double ordenar, int tipo) {
		String sqlCallProcedure = "{CALL ingresarProducto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		conexionBD conec = new conexionBD();
		Connection conn = conec.conexion();
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall(sqlCallProcedure);
			cs.setString(1, nombre); // nombre del producto
			cs.setDouble(2, precioCompra); // precio de compra
			cs.setDouble(3, precioVenta); // precio de venta
			cs.setDouble(4, cantidad); // cantidad inicial
			cs.setDouble(5, demanda); // demanda estimada
			cs.setDate(6, java.sql.Date.valueOf(fechaLimita)); // fecha límite de vencimiento
			cs.setString(7, proveedor); // nombre del proveedor
			cs.setInt(8, Integer.parseInt(telefono)); // teléfono del proveedor
			cs.setInt(9, categoria); // categoría del producto
			cs.setDouble(10, mantenerInv); // cantidad mínima de inventario a mantener
			cs.setDouble(11, ordenar); // cantidad de reabastecimiento
			cs.setInt(12, tipo); // tipo del producto
			// Ejecutar el procedimiento almacenado
			int result = cs.executeUpdate();
			if (result > 0) {
				System.out.println("Producto ingresado exitosamente.");
				return true;
			} else {
				System.out.println("Error al ingresar el producto.");
				return false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar el producto: " + e.getMessage());
			return false;
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (conn != null)
					conn.close();
				System.out.println("Conexiones cerradas");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public DefaultTableModel tabla(int categoria, String[] columnas) {
		String sql = "SELECT id_producto, nombre FROM productos WHERE categoria=?";

		DefaultTableModel model = new DefaultTableModel(null, columnas);
		conexionBD conec = new conexionBD();
		Connection conn = conec.conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] datos = new String[2];
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, categoria);
			rs = ps.executeQuery();
			while (rs.next()) {
				datos[0] = rs.getString("id_producto");
				datos[1] = rs.getString("nombre");
				model.addRow(datos);
			}
			return model;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
				System.out.println("Conexiones cerradas");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return model;
	}

}
