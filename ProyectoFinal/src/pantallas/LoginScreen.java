package pantallas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfUser;
	private JPasswordField pwPssw;
	
	// Constructor de la pantalla de login
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// Panel superior
		JPanel panTitl = new JPanel();
		contentPane.add(panTitl, BorderLayout.NORTH);
		JLabel lblTitl = new JLabel("Inicio de Sesión");
		panTitl.add(lblTitl);
		
		// Panel de los campos de usuario y contraseña
		JPanel panFields = new JPanel();
		contentPane.add(panFields, BorderLayout.CENTER);
		panFields.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblUser = new JLabel("Usuario: ");
		panFields.add(lblUser);
		txfUser = new JTextField();
		panFields.add(txfUser);
		txfUser.setColumns(10);
		
		JLabel lblPssw = new JLabel("Contraseña: ");
		panFields.add(lblPssw);
		pwPssw = new JPasswordField();
		panFields.add(pwPssw);
		
		// Panel para el botón de login
		JPanel panBtt = new JPanel();
		contentPane.add(panBtt, BorderLayout.SOUTH);
		JButton btnLog = new JButton("Iniciar sesión");
		panBtt.add(btnLog);
		
		// Acción del botón
		btnLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = txfUser.getText();
				String contra = String.valueOf(pwPssw.getPassword());
				// Verificar si el usuario y contraseña son correctos
				boolean autenticado = autentificar(usuario, contra);
				
				// Validación de que los campos estén completos
				if (txfUser.getText().isBlank() || String.valueOf(pwPssw.getPassword()).isBlank()) {
					JOptionPane.showMessageDialog(LoginScreen.this, "Por favor, rellene todos los campos");
					txfUser.setText("");
					pwPssw.setText("");
				} else {
					if (autenticado) {
						JOptionPane.showMessageDialog(LoginScreen.this, "Inicio de sesión exitoso");
						// Mostrar el Dashboard y cerrar la pantalla de login
						Dashboard dashboard = new Dashboard();
						dashboard.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(LoginScreen.this, "Usuario o contraseña incorrectos");
						txfUser.setText("");
						pwPssw.setText("");
					}
				}
			}
		});
	}
	
	// Método para autenticar al usuario
	private static boolean autentificar(String user, String passw) {
		String usuario = "layla";
		String contra = "24365hola";
		if (user.equals(usuario) && passw.equals(contra)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Método main para ejecutar la aplicación
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Crear y mostrar la ventana de login
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

