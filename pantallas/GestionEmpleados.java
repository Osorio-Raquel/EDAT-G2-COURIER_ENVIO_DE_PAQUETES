import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

public class GestionEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableEmpl;
	private DefaultTableModel modeloT;
	private Empleado empleado;
	private Administrador administrador;
	private Sucursal sucursal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEmpleados frame = new GestionEmpleados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionEmpleados() {
		this.empleado = new Empleado();
		this.administrador = new Administrador();
		this.sucursal = new Sucursal();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 476, 313);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitl = new JPanel();
		panTitl.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panTitl, BorderLayout.NORTH);
		
		JLabel lblTitl = new JLabel("GESTION DE EMPLEADOS");
		lblTitl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panTitl.add(lblTitl);
		
		JScrollPane scrollTabla = new JScrollPane();
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		tableEmpl = new JTable();
		tableEmpl.setBackground(SystemColor.info);
		tableEmpl.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Nombre", "Email", "Puesto", "Sucursal"
			}
		));
		modeloT = (DefaultTableModel) tableEmpl.getModel();
		scrollTabla.setViewportView(tableEmpl);
		
		JPanel panbtn = new JPanel();
		panbtn.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panbtn, BorderLayout.SOUTH);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(SystemColor.menu);
		panbtn.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(SystemColor.menu);
		panbtn.add(btnEditar);
		
		JLabel lblMostrar = new JLabel("Mostrar");
		panbtn.add(lblMostrar);
		
		JComboBox<String> cmbPuestos = new JComboBox<>();
		cmbPuestos.setBackground(SystemColor.menu);
		// Cargar puestos de empleados en el JComboBox
        ArrayList<String> puestos = obtenerPuestos();
        cmbPuestos.setModel(new DefaultComboBoxModel<>(puestos.toArray(new String[0])));
		cmbPuestos.addItemListener(null); //incluir método para Filtrar
		cmbPuestos.setToolTipText("Filtrar por puestos de empleados");
		panbtn.add(cmbPuestos);
		
		JComboBox<String> cmbSucursal = new JComboBox<>();
		cmbSucursal.setBackground(SystemColor.menu);
		// Cargar nombres de las sucursales en el JComboBox
        ArrayList<String> sucursales = obtenerSucursales();
        cmbSucursal.setModel(new DefaultComboBoxModel<>(sucursales.toArray(new String[0])));
		cmbSucursal.addItemListener(null); //incluir método para Filtrar
		cmbSucursal.setToolTipText("Filtrar por sucursales");
		panbtn.add(cmbSucursal);
		
		JButton btnTodo = new JButton("Todo");
		btnTodo.setBackground(SystemColor.menu);
		panbtn.add(btnTodo);
		btnTodo.addActionListener(this::cargarTabla);
		
	}

	/*
	 * Se carga la Tabla con los datos de los empleados
	 * @param e es el evento que causa la llamada de este método
	 */
	private void cargarTabla(ActionEvent e) {
		modeloT.setRowCount(0); // Limpiar 

        // Llamada al método selectEmpleados() para obtener los datos
        ArrayList<Empleado> empleados = empleado.selectEmpleados();
        for (Empleado empleado : empleados) {
        	modeloT.addRow(new Object[]{
                    empleado.getNombre(),
                    empleado.getEmail(),
                    empleado.getPuesto(),
                    sucursal.getAdministrador().getNombre()//Esta mal, saca el nombre del admin
        	});
        }
	}
	/*
	 * Obtiene los puestos de los empleados desde la base de datos
	 * @return lista de los puestos
	 */
    private ArrayList<String> obtenerPuestos() {
        ArrayList<String> puestos = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try {
            String query = "SELECT DISTINCT puesto from empleados";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                puestos.add(rs.getString("puesto"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puestos;
    }
  /*
   * Obtiene los nombres de las sucursales desde la base de datos
   * @return Lista de las sucursales
   */
    private ArrayList<String> obtenerSucursales() {
        ArrayList<String> sucursales = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try {
            String query = "SELECT nombre from sucursales";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                sucursales.add(rs.getString("puesto"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursales;
    }
}
