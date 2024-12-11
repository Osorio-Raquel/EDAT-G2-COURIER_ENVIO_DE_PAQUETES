import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;

public class Reportes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox <String> cmbTipoR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportes frame = new Reportes();
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
	public Reportes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 199);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitl = new JPanel();
		panTitl.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panTitl, BorderLayout.NORTH);
		
		JLabel lblTitl = new JLabel("REPORTES ");
		lblTitl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panTitl.add(lblTitl);
		
		JPanel panReport = new JPanel();
		contentPane.add(panReport, BorderLayout.CENTER);
		panReport.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panTipo = new JPanel();
		panTipo.setBackground(SystemColor.inactiveCaption);
		panReport.add(panTipo);
		
		JLabel lblTipo = new JLabel("Tipo de reporte: ");
		panTipo.add(lblTipo);
		
		cmbTipoR = new JComboBox<>(new String[] {"Clientes", "Vehículos", "Sucursales"});
		cmbTipoR.setBackground(SystemColor.menu);
		panTipo.add(cmbTipoR);
		
		JPanel panBtn = new JPanel();
		panBtn.setBackground(SystemColor.inactiveCaption);
		panReport.add(panBtn);
		
		JButton btnReporte = new JButton("Generar Reporte");
		btnReporte.setBackground(SystemColor.menu);
		panBtn.add(btnReporte);
		
		JButton btnExportar = new JButton("Exportar a PDF");
		btnExportar.setBackground(SystemColor.menu);
		panBtn.add(btnExportar);
	}

}
