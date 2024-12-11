import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GestionSucursales extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableSuc;
	private DefaultTableModel modeloT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionSucursales frame = new GestionSucursales();
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
	public GestionSucursales() {
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
		
		JLabel lblNewLabel = new JLabel("GESTION DE SUCURSALES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panTitl.add(lblNewLabel);
		
		JPanel panBtn = new JPanel();
		panBtn.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panBtn, BorderLayout.SOUTH);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(SystemColor.menu);
		panBtn.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(SystemColor.menu);
		panBtn.add(btnEditar);
		
		JScrollPane scrollSuc = new JScrollPane();
		contentPane.add(scrollSuc, BorderLayout.CENTER);
		
		tableSuc = new JTable();
		tableSuc.setBackground(SystemColor.info);
		tableSuc.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Direccion", "Administrador", "Inventario"
			}
		));
		// se muestra la cantidad de inventario en la columna Inventario
		modeloT = (DefaultTableModel) tableSuc.getModel();
		scrollSuc.setViewportView(tableSuc);
	}

}
