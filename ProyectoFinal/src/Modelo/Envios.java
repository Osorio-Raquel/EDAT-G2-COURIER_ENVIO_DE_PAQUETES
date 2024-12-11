package Modelo;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import conexionBase.conexionBD;

public class Envios {

    // Método para ingresar un nuevo envío en la base de datos
    public boolean IngresoEnvio(int clienteId, int empleadoId, int vehiculoId, int rutaId, 
                                int estadoId, double costo) {
        String sqlCallProcedure = "{CALL ingresarEnvio(?, ?, ?, ?, ?, ?, ?, ?)}";
        conexionBD conec = new conexionBD();
        Connection conn = conec.conexion();
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall(sqlCallProcedure);
            cs.setInt(1, clienteId); // ID del cliente
            cs.setInt(2, empleadoId); // ID del empleado
            cs.setInt(3, vehiculoId); // ID del vehículo
            cs.setInt(4, rutaId); // ID de la ruta
            cs.setInt(5, estadoId); // ID del estado
            cs.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // Fecha de envío actual
            cs.setNull(7, Types.TIMESTAMP); // Fecha de entrega, inicialmente nula
            cs.setDouble(8, costo); // Costo del envío

            // Ejecutar el procedimiento almacenado
            int result = cs.executeUpdate();
            if (result > 0) {
                System.out.println("Envío ingresado exitosamente.");
                return true;
            } else {
                System.out.println("Error al ingresar el envío.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar el envío: " + e.getMessage());
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

    // Método para obtener los envíos filtrados por sucursal (ruta)
    public DefaultTableModel obtenerEnvios(String[] columnas) {
        String sql = "SELECT envio_id, cliente_id, empleado_id, vehiculo_id, ruta_id, estado_id, " +
                     "fecha_envio, fecha_entrega, costo FROM envios";

        DefaultTableModel model = new DefaultTableModel(null, columnas);
        conexionBD conec = new conexionBD();
        Connection conn = conec.conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[] datos = new String[9];
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getString("envio_id");
                datos[1] = rs.getString("cliente_id");
                datos[2] = rs.getString("empleado_id");
                datos[3] = rs.getString("vehiculo_id");
                datos[4] = rs.getString("ruta_id");
                datos[5] = rs.getString("estado_id");
                datos[6] = rs.getString("fecha_envio");
                datos[7] = rs.getString("fecha_entrega");
                datos[8] = rs.getString("costo");
                model.addRow(datos);
            }
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
                System.out.println("Conexiones cerradas");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return model;
    }

    
    // Método para actualizar la fecha de entrega de un envío
    public boolean actualizarFechaEntrega(int envioId, Timestamp fechaEntrega) {
        String sql = "UPDATE envios SET fecha_entrega = ? WHERE envio_id = ?";
        conexionBD conec = new conexionBD();
        Connection conn = conec.conexion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, fechaEntrega); // Fecha de entrega
            ps.setInt(2, envioId); // ID del envío
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Fecha de entrega actualizada exitosamente.");
                return true;
            } else {
                System.out.println("Error al actualizar la fecha de entrega.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la fecha de entrega: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
                System.out.println("Conexiones cerradas");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
