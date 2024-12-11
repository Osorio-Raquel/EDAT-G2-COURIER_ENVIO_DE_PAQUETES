package Modelo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import conexionBase.conexionBD;

public class verificacionCorreo {
	public ArrayList<String> correos = new ArrayList<>();
	public boolean verificador(String Correo, String contrasena ) {
		String password="";
		String id="";
		int estado;
		conexionBD conec = new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String consulta= "SELECT ID_empleado, estado from empleados WHERE usurario='"+Correo+"';";
		System.out.println("hasta la primera consulta");
		try {
			System.out.println("entra al try");
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			if(rs.next()) {
				id=rs.getString(1);
				estado=Integer.parseInt(rs.getString(2));
				String consulta2= "SELECT contrasenia from empleados where id_empleado="+id+";";
				ps=conn.prepareStatement(consulta2);
				rs=ps.executeQuery();
				if(rs.next()) {
					password=rs.getString(1);
					System.out.println(password);
					if(contrasena.equals(password) && estado!=0) {
						System.out.println(password+ "estado: "+ estado);
						return true;
					}else {
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta o la cuenta ya no es valida");
						return false;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Correo no encontrado!");
				return false;
			}
			
		}catch(Exception e) {
			return false;
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
		return false;
	}
	public boolean verificadorTipo(String correo) {
	    String consulta = "SELECT cargo FROM empleados WHERE usurario=?";
	    
	    try (Connection conn = conexionBD.conexion();
	         PreparedStatement ps = conn.prepareStatement(consulta)) {
	        
	        ps.setString(1, correo);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            String cargo = rs.getString("cargo");
	            if (cargo.equals("2")) {
	            	System.out.println(cargo);
	                return true;
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    
	    return false;
	}


	
	
}
