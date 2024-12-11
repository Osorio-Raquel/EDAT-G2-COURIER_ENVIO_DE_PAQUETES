import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class GestionRutas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableRutas;
	private DefaultTableModel modeloT;
	//private Ruta ruta;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionRutas frame = new GestionRutas();
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
	public GestionRutas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitl = new JPanel();
		panTitl.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panTitl, BorderLayout.NORTH);
		
		JLabel lblTitl = new JLabel("GESTIÓN DE RUTAS");
		lblTitl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panTitl.add(lblTitl);
		
		JPanel panBtn = new JPanel();
		panBtn.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panBtn, BorderLayout.SOUTH);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(SystemColor.menu);
		panBtn.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(SystemColor.menu);
		panBtn.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(SystemColor.menu);
		panBtn.add(btnEliminar);
		
		JButton btnVisual = new JButton("Visualizar");
		btnVisual.setBackground(SystemColor.menu);
		panBtn.add(btnVisual);
		
		JScrollPane scrollTabla = new JScrollPane();
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		tableRutas = new JTable();
		tableRutas.setBackground(SystemColor.info);
		tableRutas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Origen", "Destino", "Distancia", "Tiempo estimado"
			}
		));
		modeloT = (DefaultTableModel) tableRutas.getModel();
		scrollTabla.setViewportView(tableRutas);
		
	}
	//métodos para las acciones de los botones, y el cargado de los datos de la Tabla

	
}
