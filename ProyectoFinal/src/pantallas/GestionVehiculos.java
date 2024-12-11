import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;

public class GestionVehiculos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableVehiculos;
	private DefaultTableModel modeloT;
	private JComboBox<String> cmbEstado;
	private Vehiculo vehiculo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionVehiculos frame = new GestionVehiculos();
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
	public GestionVehiculos() {
		this.vehiculo = new Vehiculo();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 176);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitl = new JPanel();
		panTitl.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panTitl, BorderLayout.NORTH);
		
		JLabel lblTitl = new JLabel("GESTION DE VEHICULOS");
		lblTitl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panTitl.add(lblTitl);
		
		JScrollPane scrollTabla = new JScrollPane();
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		//tabla de informacion de los vehículos
		tableVehiculos = new JTable();
		tableVehiculos.setBackground(SystemColor.info);
		tableVehiculos.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"ID", "Tipo", "Modelo", "Matricula", "Capacidad (Kg)", "Estado"
			}
		));
		modeloT = (DefaultTableModel) tableVehiculos.getModel();
		scrollTabla.setViewportView(tableVehiculos);
		
		JPanel panBtn = new JPanel();
		panBtn.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panBtn, BorderLayout.SOUTH);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(SystemColor.menu);
		panBtn.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(SystemColor.menu);
		panBtn.add(btnEditar);
		
		JLabel lblFiltro = new JLabel("Mostrar: ");
		panBtn.add(lblFiltro);
		
		cmbEstado = new JComboBox<String>(new String[]{"Todos", "Disponible", "En Mantenimiento", "Ocupado"});
		cmbEstado.setBackground(SystemColor.menu);
		cmbEstado.addItemListener(this::filtrar);	//cambia según el evento, gracias al método filtrar()
		panBtn.add(cmbEstado);
		//cargar Tabla de datos
		cargarTabla();
		
		btnAgregar.addActionListener(this::agregar);
		btnEditar.addActionListener(this::editar);
	}

	private void cargarTabla() {
        cargarTablaFiltro("Todos");
    }
	
	private void cargarTablaFiltro(String filtro) {
		modeloT.setRowCount(0); // Limpiar 

        // Llamada al método selectVehiculos() para obtener los datos
        ArrayList<Vehiculo> vehiculos = vehiculo.selectVehiculos();
        for (Vehiculo vehiculo : vehiculos) {
        	if (filtro.equals("Todos") || vehiculo.getEstadoVehiculo().getNombreEstado().equals(filtro)) {
                modeloT.addRow(new Object[]{
                    vehiculo.getVehiculoId(),
                    vehiculo.getTipo(),
                    vehiculo.getModelo(),
                    vehiculo.getMatricula(),
                    vehiculo.getCapacidadKg(),
                    vehiculo.getEstadoVehiculo().getNombreEstado()
                });
        	}
        	
        }
	}
	private void filtrar(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String filtroSelec = (String) cmbEstado.getSelectedItem();
            cargarTablaFiltro(filtroSelec);
        }
    }
	private void agregar(ActionEvent e) {
	    // Crear los componentes para el formulario
	    JTextField txtTipo = new JTextField();
	    JTextField txtModelo = new JTextField();
	    JTextField txtMatricula = new JTextField();
	    JTextField txtCapacidad = new JTextField();
	    JComboBox<String> comboEstado = new JComboBox<>(new String[]{"Disponible", "En Mantenimiento", "Ocupado"});

	    // Crear el panel que contiene los campos
	    JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 15, 15));
	    panelFormulario.add(new JLabel("Tipo:"));
	    panelFormulario.add(txtTipo);
	    panelFormulario.add(new JLabel("Modelo:"));
	    panelFormulario.add(txtModelo);
	    panelFormulario.add(new JLabel("Matricula:"));
	    panelFormulario.add(txtMatricula);
	    panelFormulario.add(new JLabel("Capacidad:"));
	    panelFormulario.add(txtCapacidad);
	    panelFormulario.add(new JLabel("Estado:"));
	    panelFormulario.add(comboEstado);

	    // Mostrar el formulario en un JOptionPane
	    int resultado = JOptionPane.showConfirmDialog(
	        this,
	        panelFormulario,
	        "Agregar Vehiculo",
	        JOptionPane.OK_CANCEL_OPTION,
	        JOptionPane.PLAIN_MESSAGE
	    );

	    // Si el usuario presiona OK, procesar los datos
	    if (resultado == JOptionPane.OK_OPTION) {
	        String tipo = txtTipo.getText();
	        String modelo = txtModelo.getText();
	        String matricula = txtMatricula.getText();
	        String capacidad = txtCapacidad.getText();
	        String estado = (String) comboEstado.getSelectedItem();

	        // Validar que los campos no estén vacíos
	        if (tipo.isEmpty() || modelo.isEmpty() || matricula.isEmpty() || capacidad.isEmpty() || estado.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        // Crear y guardar el nuevo vehiculo
	        Vehiculo nuevoVehic = new Vehiculo();
	        nuevoVehic.setTipo(tipo);
	        nuevoVehic.setModelo(modelo);
	        nuevoVehic.setMatricula(matricula);
	        nuevoVehic.setCapacidadKg(Double.parseDouble(capacidad));
	        nuevoVehic.setEstadoVehiculo(new EstadoVehiculo(0, estado));
	        // Insertar en la base de datos
	        nuevoVehic.insertVehiculo();
	        // Recargar la tabla
	        cargarTabla();
	    }
	}

	private void editar(ActionEvent e) {
        int filaSeleccionada = tableVehiculos.getSelectedRow();
	    if (filaSeleccionada != -1) {
            int vehiculoId = (int) modeloT.getValueAt(filaSeleccionada, 0);
            Vehiculo seleccionado = vehiculo.selectVehiculoById(vehiculoId); // Llama a selectVehiculoById

            JTextField txtTipo = new JTextField(seleccionado.getTipo());
            JTextField txtModelo = new JTextField(seleccionado.getModelo());
            JTextField txtMatricula = new JTextField(seleccionado.getMatricula());
            JTextField txtCapacidad = new JTextField(String.valueOf(seleccionado.getCapacidadKg()));
            JComboBox<String> comboEstado = new JComboBox<>(new String[]{"Disponible", "En Mantenimiento", "Ocupado"});
            comboEstado.setSelectedItem(seleccionado.getEstadoVehiculo().getNombreEstado()); 
            
	    	
	    	// Crear el panel que contiene los campos
	    	JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 15, 15));
		    panelFormulario.add(new JLabel("Tipo:"));
		    panelFormulario.add(txtTipo);
		    panelFormulario.add(new JLabel("Modelo:"));
		    panelFormulario.add(txtModelo);
		    panelFormulario.add(new JLabel("Matricula:"));
		    panelFormulario.add(txtMatricula);
		    panelFormulario.add(new JLabel("Capacidad:"));
		    panelFormulario.add(txtCapacidad);
		    panelFormulario.add(new JLabel("Estado:"));
		    panelFormulario.add(comboEstado);

	    	// Mostrar el formulario en un JOptionPane
	    	int resultado = JOptionPane.showConfirmDialog(
	    			this,
	    			panelFormulario,
	    			"Editar Vehiculo",
	    			JOptionPane.OK_CANCEL_OPTION,
	    			JOptionPane.PLAIN_MESSAGE
	    	);
            
            if (resultado == JOptionPane.OK_OPTION) {
            	String tipo = txtTipo.getText();
    	        String modelo = txtModelo.getText();
    	        String matricula = txtMatricula.getText();
    	        String capacidad = txtCapacidad.getText();
    	        String estado = (String) comboEstado.getSelectedItem();

    	        // Validar que los campos no estén vacíos
    	        if (tipo.isEmpty() || modelo.isEmpty() || matricula.isEmpty() || capacidad.isEmpty() || estado.isEmpty()) {
    	            JOptionPane.showMessageDialog(this, "No se puede dejar vacios", "Error", JOptionPane.ERROR_MESSAGE);
    	            return;
    	        }
    	        // Actualizar vehículo
                seleccionado.setTipo(tipo);
                seleccionado.setModelo(modelo);
                seleccionado.setMatricula(matricula);
                seleccionado.setCapacidadKg(Double.parseDouble(txtCapacidad.getText()));
                seleccionado.setEstadoVehiculo(new EstadoVehiculo(0, estado));
                seleccionado.updateVehiculo(); // Método para actualizar en la base de datos
            	cargarTabla();	// Recargar la tabla
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un vehiculo para editar.");
        }
	    
	}
}
