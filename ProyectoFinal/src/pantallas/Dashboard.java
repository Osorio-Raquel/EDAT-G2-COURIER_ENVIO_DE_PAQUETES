package pantallas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private DashAct accion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		//this.accion = accion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 422);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitl = new JPanel();
		panTitl.setBackground(SystemColor.activeCaption);
		contentPane.add(panTitl, BorderLayout.NORTH);
		
		JLabel lblTitl = new JLabel("PANEL PRINCIPAL");
		lblTitl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panTitl.add(lblTitl);
		
		JPanel panConslt = new JPanel();
		panConslt.setBackground(SystemColor.activeCaption);
		contentPane.add(panConslt, BorderLayout.CENTER);
		panConslt.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panEnvios = new JPanel();
		panConslt.add(panEnvios);
		panEnvios.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitle1 = new JPanel();
		panEnvios.add(panTitle1, BorderLayout.NORTH);
		
		JLabel lblTi1 = new JLabel("Total envios por Estado");
		panTitle1.add(lblTi1);
		
		JPanel panDat1 = new JPanel();
		panEnvios.add(panDat1, BorderLayout.CENTER);
		
		JLabel lblEnvio = new JLabel("");
		lblEnvio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnvio.setBounds(102, 40, 64, 40);
		panDat1.add(lblEnvio);
		//Llenamos el combo box con una lista de los estados
		String[] estados ={"Pendientes", "En Tránsito", "Entregados"};
		JComboBox<String> cmbEst = new JComboBox<>(estados);
		cmbEst.setBackground(SystemColor.menu);
		cmbEst.setBounds(52, 5, 93, 22);
		//cuando se selecciona una opcion se muestra su determinado monto en la etiqueta
		cmbEst.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selec = (String)cmbEst.getSelectedItem();
				int totalEnvios = 6;//accion.getEnviosPorEstado(selec); // Método del DachAct
	            lblEnvio.setText(String.valueOf(totalEnvios));
			}
		});
		panDat1.setLayout(null);
		panDat1.add(cmbEst);
		
		ImageIcon originalIcon1 = new ImageIcon("C:\\Users\\HP\\Documents\\iconos\\envio.png");
		Image originalImage1 = originalIcon1.getImage();
		// Redimensionar la imagen para ajustarla al tamaño del JLabel 
		Image scaledImage1 = originalImage1.getScaledInstance(71, 58, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
		JLabel lblIconEnvio = new JLabel("");
		lblIconEnvio.setIcon(scaledIcon1);
		lblIconEnvio.setBounds(19, 35, 71, 58);
		panDat1.add(lblIconEnvio);
		
		
		JPanel panVehiculos = new JPanel();
		panConslt.add(panVehiculos);
		panVehiculos.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitle2 = new JPanel();
		panVehiculos.add(panTitle2, BorderLayout.NORTH);
		
		JLabel lblTi2 = new JLabel("Disponibilidad de Vehiculos");
		panTitle2.add(lblTi2);
		
		JPanel panDat2 = new JPanel();
		panVehiculos.add(panDat2, BorderLayout.CENTER);
		
		JLabel lblVehiculo = new JLabel("");
		lblVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVehiculo.setBounds(98, 40, 66, 42);
		panDat2.add(lblVehiculo);
		//Llenamos el combo box con una lista de los estados del vehiculo.
		String[] disponibilidad ={"Disponibles", "Ocupados", "En Mantenimiento"};
		JComboBox<String> cmbDisp = new JComboBox<>(disponibilidad);
		cmbDisp.setBackground(SystemColor.menu);
		cmbDisp.setBounds(34, 5, 130, 22);
		//cuando se selecciona una opcion se muestra su determinado monto
		cmbDisp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selec = (String)cmbEst.getSelectedItem();
				int totalVehiculos = 9;//accion.getVehiculosPorEstado(selec); // Método del DachAct
	            lblVehiculo.setText(String.valueOf(totalVehiculos));
			}
		});
		panDat2.setLayout(null);
		panDat2.add(cmbDisp);
		
		ImageIcon originalIcon2 = new ImageIcon("C:\\Users\\HP\\Documents\\iconos\\vehiculo.png");
		Image originalImage2 = originalIcon2.getImage();
		// Redimensionar la imagen para ajustarla al tamaño del JLabel 
		Image scaledImage2 = originalImage2.getScaledInstance(66, 59, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
		JLabel lblIconCar = new JLabel("");
		lblIconCar.setIcon(scaledIcon2);
		
		lblIconCar.setBounds(20, 34, 66, 59);
		panDat2.add(lblIconCar);
		
		JPanel panClientes = new JPanel();
		panConslt.add(panClientes);
		panClientes.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitle3 = new JPanel();
		panClientes.add(panTitle3, BorderLayout.NORTH);
		
		JLabel lblTi3 = new JLabel("Total clientes registrados");
		panTitle3.add(lblTi3);
		
		JPanel panDat3 = new JPanel();
		panClientes.add(panDat3, BorderLayout.CENTER);
		panDat3.setLayout(null);
		
		JLabel lblCli = new JLabel("");
		lblCli.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCli.setBounds(116, 13, 62, 42);
		panDat3.add(lblCli);
		
		JButton btnActCli = new JButton("Actualizar");
		btnActCli.setBackground(SystemColor.menu);
		btnActCli.setBounds(58, 68, 87, 25);
		panDat3.add(btnActCli);
		
		ImageIcon originalIcon3 = new ImageIcon("C:\\Users\\HP\\Documents\\iconos\\clientes.jpg");
		Image originalImage3 = originalIcon3.getImage();
		// Redimensionar la imagen para ajustarla al tamaño del JLabel 
		Image scaledImage3 = originalImage3.getScaledInstance(76, 50, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
		JLabel lblIconCli = new JLabel("");
		lblIconCli.setIcon(scaledIcon3);
		lblIconCli.setBounds(22, 13, 76, 50);
		panDat3.add(lblIconCli);
		
		//mostrar cantidad de clientes
		btnActCli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int totalClientes = 3;//accion.getTotalClientes(); // Método del DachAct
	            lblCli.setText(String.valueOf(totalClientes));
			}
		});
		
		JPanel panIngresos = new JPanel();
		panConslt.add(panIngresos);
		panIngresos.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitle4 = new JPanel();
		panIngresos.add(panTitle4, BorderLayout.NORTH);
		
		JLabel lblTi4 = new JLabel("Facturacion acumulada");
		panTitle4.add(lblTi4);
		
		JPanel panDat4 = new JPanel();
		panIngresos.add(panDat4, BorderLayout.CENTER);
		panDat4.setLayout(null);
		
		JLabel lblIngreso = new JLabel("");
		lblIngreso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngreso.setBounds(116, 13, 60, 42);
		panDat4.add(lblIngreso);
		
		JButton btnActFact = new JButton("Actualizar");
		btnActFact.setBackground(SystemColor.menu);
		btnActFact.setBounds(58, 68, 87, 25);
		panDat4.add(btnActFact);
		
		ImageIcon originalIcon4 = new ImageIcon("C:\\Users\\HP\\Documents\\iconos\\dinero.png");
		Image originalImage4 = originalIcon4.getImage();
		// Redimensionar la imagen para ajustarla al tamaño del JLabel 
		Image scaledImage4 = originalImage4.getScaledInstance(76, 50, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon4 = new ImageIcon(scaledImage4);
		JLabel lblIconCash = new JLabel("");
		lblIconCash.setIcon(scaledIcon4);
		lblIconCash.setBounds(28, 13, 76, 50);
		panDat4.add(lblIconCash);
		//actualizar los ingresos generados
		btnActFact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ingresos = 157;//accion.getTotalIngresos(); // Método del DashAct
	            lblIngreso.setText(String.valueOf(ingresos));
			}
		});
		
		JPanel panOtros = new JPanel();
		panOtros.setBackground(SystemColor.activeCaption);
		contentPane.add(panOtros, BorderLayout.SOUTH);
		
		//Contenido del JComboBox
		//Cada opcion corresponde con una accion que redirige a la pantalla correspondiente
		HashMap <String, Runnable> opciones = new HashMap<>();
		opciones.put("Gestión de Rutas", () -> openGestRutas());
		opciones.put("Gestión de Clientes", () -> openGestClientes());
		opciones.put("Gestión de Vehículos", () -> openGestVehiculos());
		//opciones.put("Gestion de Envios", () -> openGestEnvios());
		opciones.put("Gestion de Facturas", () -> openGestFacturas());
		//opciones.put("Gestion de inventario", () -> openGestFacturas());
		opciones.put("Gestion de Empleados", () -> openGestEmpleados());
		opciones.put("Gestion de Sucursales", () -> openGestSucursales());
		
		//pasa las opciones de forma directa convirtiendolas en array
		JComboBox<String> cmbGest = new JComboBox<>(opciones.keySet().toArray(new String[0]));
		cmbGest.setBackground(SystemColor.menu);
		cmbGest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String opcionSelec = (String) cmbGest.getSelectedItem();
				if (opcionSelec != null && opciones.containsKey(opcionSelec)) {
                    opciones.get(opcionSelec).run();  
                } else {
                	JOptionPane.showMessageDialog(Dashboard.this, "No se seleccionó");
                   
                }
			}
		});
		panOtros.add(cmbGest);
		
		JButton btnConfi = new JButton("Configuración");
		btnConfi.setBackground(SystemColor.menu);
		panOtros.add(btnConfi);
		
		JButton btnReport = new JButton("Reportes");
		btnReport.setBackground(SystemColor.menu);
		panOtros.add(btnReport);
		
		btnConfi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Configuracion configuracion = new Configuracion();
				configuracion.setVisible(true);
			}
		});
		
		btnReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Reportes reportes = new Reportes();
				reportes.setVisible(true);
			}
		});
		setVisible(true);
	}
	private static void openGestClientes() {
		GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.setVisible(true);
    }
	private static void openGestEnvios() {
		GestionEnvios gestionEnvios = new GestionEnvios();
        gestionEnvios.setVisible(true);
    }
	private static void openGestRutas() {
		GestionRutas gestionRutas = new GestionRutas();
        gestionRutas.setVisible(true);
    }
	private static void openGestVehiculos() {
		GestionVehiculos gestionVehiculos = new GestionVehiculos();
        gestionVehiculos.setVisible(true);
    }
	private static void openGestEmpleados() {
		GestionEmpleados gestionEmpleados = new GestionEmpleados();
        gestionEmpleados.setVisible(true);
    }
	private static void openGestSucursales() {
		GestionSucursales gestionSucursales = new GestionSucursales();
        gestionSucursales.setVisible(true);
    }
	private static void openGestFacturas() {
		GestionFacturas gestionFacturas = new GestionFacturas();
        gestionFacturas.setVisible(true);
    }
}
