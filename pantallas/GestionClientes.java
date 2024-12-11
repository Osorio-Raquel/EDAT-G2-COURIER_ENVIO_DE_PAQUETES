import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import java.awt.Font;

public class GestionClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCli;
	private DefaultTableModel modeloT;
	private JList<String> listHistorial;
	private DefaultListModel<String> modeloL;
	private Cliente cliente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionClientes frame = new GestionClientes();
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
	public GestionClientes() {
		this.cliente = new Cliente();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 378);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitl = new JPanel();
		panTitl.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panTitl, BorderLayout.NORTH);
		
		JLabel lblTitl = new JLabel("GESTION DE CLIENTES");
		lblTitl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panTitl.add(lblTitl);
		
		JScrollPane scrollTabla = new JScrollPane();
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		//tabla de informacion de los clientes
		tableCli = new JTable();
		tableCli.setBackground(SystemColor.info);
		tableCli.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"ID", "Nombre", "Email", "Telefono", "Direccion", "Fecha Registro"
			}
		));
		modeloT = (DefaultTableModel) tableCli.getModel();
		scrollTabla.setViewportView(tableCli);
		
		modeloL = new DefaultListModel<String>();
		listHistorial = new JList<>(modeloL);
		listHistorial.setBackground(SystemColor.control);
		JScrollPane scrollHistorial = new JScrollPane(listHistorial);
		scrollHistorial.setBorder(BorderFactory.createTitledBorder("Historial de Envíos "));
		contentPane.add(scrollHistorial,BorderLayout.EAST);
		
		JPanel panbtn = new JPanel();
		panbtn.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panbtn, BorderLayout.SOUTH);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(SystemColor.menu);
		panbtn.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(SystemColor.menu);
		panbtn.add(btnEditar);
		
		JButton btnHistorial = new JButton("Historial");
		btnHistorial.setBackground(SystemColor.menu);
		panbtn.add(btnHistorial);
		
		cargarTabla();
		
		btnAgregar.addActionListener(this::agregar);
		btnEditar.addActionListener(this::editar);
		btnHistorial.addActionListener(this::historial);
	}
	private void cargarTabla() {
		modeloT.setRowCount(0); // Limpiar 

        // Llamada al método selectClientes() para obtener los datos
        ArrayList<Cliente> clientes = cliente.selectClientes();
        for (Cliente cliente : clientes) {
        	modeloT.addRow(new Object[]{
                    cliente.getClienteId(),
                    cliente.getNombre(),
                    cliente.getEmail(),
                    cliente.getTelefono(),
                    cliente.getDireccion().getCalle(), 
                    cliente.getFechaRegistro()
            });
        }
	}
	private void agregar(ActionEvent e) {
	    // Crear los componentes para el formulario
	    JTextField txtNombre = new JTextField();
	    JTextField txtEmail = new JTextField();
	    JTextField txtTelefono = new JTextField();
	    JTextField txtDireccion = new JTextField();

	    // Crear el panel que contiene los campos
	    JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
	    panelFormulario.add(new JLabel("Nombre:"));
	    panelFormulario.add(txtNombre);
	    panelFormulario.add(new JLabel("Email:"));
	    panelFormulario.add(txtEmail);
	    panelFormulario.add(new JLabel("Teléfono:"));
	    panelFormulario.add(txtTelefono);
	    panelFormulario.add(new JLabel("Dirección:"));
	    panelFormulario.add(txtDireccion);

	    // Mostrar el formulario en un JOptionPane
	    int resultado = JOptionPane.showConfirmDialog(
	        this,
	        panelFormulario,
	        "Agregar Cliente",
	        JOptionPane.OK_CANCEL_OPTION,
	        JOptionPane.PLAIN_MESSAGE
	    );

	    // Si el usuario presiona OK, procesar los datos
	    if (resultado == JOptionPane.OK_OPTION) {
	        String nombre = txtNombre.getText();
	        String email = txtEmail.getText();
	        String telefono = txtTelefono.getText();
	        String direccion = txtDireccion.getText();

	        // Validar que los campos no estén vacíos
	        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios ", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        java.util.Date utilDate = new java.util.Date();
	        // Crear y guardar el nuevo cliente
	        Cliente nuevoCliente = new Cliente();
	        nuevoCliente.setNombre(nombre);
	        nuevoCliente.setEmail(email);
	        nuevoCliente.setTelefono(telefono);
	        nuevoCliente.setDireccion(new Direccion(0, null, direccion, "", "", "")); 
	        nuevoCliente.setFechaRegistro(new java.sql.Date(utilDate.getTime()));
	        
	        // Insertar en la base de datos
	        nuevoCliente.insertCliente();
	        // Recargar la tabla
	        cargarTabla();
	    }
	}

	private void editar(ActionEvent e) {
        int filaSeleccionada = tableCli.getSelectedRow();
	    if (filaSeleccionada != -1) {
            int clienteId = (int) modeloT.getValueAt(filaSeleccionada, 0);
            Cliente seleccionado = cliente.selectClienteById(clienteId); // Llama a selectClienteById

            JTextField txtNombre = new JTextField();
            JTextField txtEmail = new JTextField();
            JTextField txtTelefono = new JTextField();
	    	JTextField txtDireccion = new JTextField();
	    	
	    	// Crear el panel que contiene los campos
	    	JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
	    	panelFormulario.add(new JLabel("Nombre:"));
	    	panelFormulario.add(txtNombre);
	    	panelFormulario.add(new JLabel("Email:"));
	    	panelFormulario.add(txtEmail);
	    	panelFormulario.add(new JLabel("Teléfono:"));
	    	panelFormulario.add(txtTelefono);
	    	panelFormulario.add(new JLabel("Dirección:"));
	    	panelFormulario.add(txtDireccion);

	    	// Cargar datos en los campos de texto
            txtNombre.setText(seleccionado.getNombre());
            txtEmail.setText(seleccionado.getEmail());
            txtTelefono.setText(seleccionado.getTelefono());
            txtDireccion.setText(seleccionado.getDireccion().getCalle());
	    	// Mostrar el formulario en un JOptionPane
	    	int resultado = JOptionPane.showConfirmDialog(
	    			this,
	    			panelFormulario,
	    			"Editar Cliente",
	    			JOptionPane.OK_CANCEL_OPTION,
	    			JOptionPane.PLAIN_MESSAGE
	    	);
            
            if (resultado == JOptionPane.OK_OPTION) {
            	String nombre = txtNombre.getText();
            	String email = txtEmail.getText();
            	String telefono = txtTelefono.getText();
            	String direccion = txtDireccion.getText();

            	// Validar que los campos no estén vacíos
            	if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            		JOptionPane.showMessageDialog(this, "No puede dejar vacios", "Error", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            	seleccionado.setNombre(nombre);
            	seleccionado.setEmail(email);
            	seleccionado.setTelefono(telefono);
            	seleccionado.getDireccion().setCalle(direccion);
            	// Actualizar cliente seleccionado
            	seleccionado.updateCliente();
            	// Recargar la tabla
            	cargarTabla();
        
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para editar.");
        }
	    
	}
	
	private void historial(ActionEvent e) {
		modeloL.clear();
		int filaSeleccionada = tableCli.getSelectedRow();
		if (filaSeleccionada != -1) {
			int clienteId = (int) modeloT.getValueAt(filaSeleccionada, 0);
			// invocar metodo para ver el historial de envios de un cliente
			//modeloL.clear();
			//simulacion de envio con estado, fecha de envio y de entrega y su costo
			modeloL.addElement("1 - Entregado - 21/04/2024 - 23/04/2024 - 45.50");
						
		}else {
			JOptionPane.showMessageDialog(this, "Seleccione un cliente para ver su historial");
		}
	}
}
