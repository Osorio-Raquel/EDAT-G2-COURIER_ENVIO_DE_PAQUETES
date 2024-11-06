import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegistroEnvioView extends JFrame {
    private JTextField origenField, destinoField, pesoField, contenidoField;
    private JTextField largoField, anchoField, altoField;
    private JButton registrarButton;
    private RegistroEnvioController controller;

    public RegistroEnvioView(RegistroEnvioController controller) {
        this.controller = controller;

        setTitle("Registro de Envío");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Agregar los campos de texto y etiquetas
        panel.add(new JLabel("Origen:"));
        origenField = new JTextField();
        panel.add(origenField);

        panel.add(new JLabel("Destino:"));
        destinoField = new JTextField();
        panel.add(destinoField);

        panel.add(new JLabel("Peso (kg):"));
        pesoField = new JTextField();
        panel.add(pesoField);

        panel.add(new JLabel("Contenido:"));
        contenidoField = new JTextField();
        panel.add(contenidoField);

        panel.add(new JLabel("Largo (cm):"));
        largoField = new JTextField();
        panel.add(largoField);

        panel.add(new JLabel("Ancho (cm):"));
        anchoField = new JTextField();
        panel.add(anchoField);

        panel.add(new JLabel("Alto (cm):"));
        altoField = new JTextField();
        panel.add(altoField);

        // Botón para registrar el envío
        registrarButton = new JButton("Registrar Envío");
        panel.add(registrarButton);

        // Asignar el ActionListener al botón
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que todos los campos obligatorios estén llenos
                if (origenField.getText().isEmpty() || destinoField.getText().isEmpty() || contenidoField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(RegistroEnvioView.this, "Todos los campos deben ser completados.");
                    return;  // Evitar que el código continúe si hay campos vacíos
                }

                // Validar que el peso sea un número válido
                double peso;
                try {
                    peso = Double.parseDouble(pesoField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegistroEnvioView.this, "El peso debe ser un número válido.");
                    return;  // Evitar que el código continúe si el peso no es un número válido
                }

                // Validar las dimensiones (largo, ancho, alto)
                double largo, ancho, alto;
                try {
                    largo = Double.parseDouble(largoField.getText());
                    ancho = Double.parseDouble(anchoField.getText());
                    alto = Double.parseDouble(altoField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegistroEnvioView.this, "Las dimensiones deben ser números válidos.");
                    return;  // Evitar que el código continúe si alguna dimensión no es un número válido
                }

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(RegistroEnvioView.this, "Envío registrado correctamente.");
            }
        });

        add(panel);
    }
}
