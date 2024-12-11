import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;

public class GestionFacturas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableFactura;
	private DefaultTableModel modeloT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFacturas frame = new GestionFacturas();
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
	public GestionFacturas() {
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
		
		JLabel lblTitl = new JLabel("GESTION DE FACTURAS");
		lblTitl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panTitl.add(lblTitl);
		
		JPanel panBtn = new JPanel();
		panBtn.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panBtn, BorderLayout.SOUTH);
		
		JButton btnNueva = new JButton("Factura Nueva");
		btnNueva.setBackground(SystemColor.menu);
		panBtn.add(btnNueva);
		
		JScrollPane scrollFacturas = new JScrollPane();
		contentPane.add(scrollFacturas, BorderLayout.CENTER);
		
		tableFactura = new JTable();
		tableFactura.setBackground(SystemColor.info);
		tableFactura.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cliente", "Fecha", "Total", "Método de pago", "Estado"
			}
		));
		modeloT = (DefaultTableModel) tableFactura.getModel();
		scrollFacturas.setViewportView(tableFactura);
	}

}
