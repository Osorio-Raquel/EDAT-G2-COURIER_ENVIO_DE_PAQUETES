package Modelo;import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarEstadoView extends JFrame {
    private JTextField trackingNumberField;
    private JComboBox<String> estadoComboBox;
    private JButton actualizarButton;
    private ActualizarEstadoController controller;

    public ActualizarEstadoView(ActualizarEstadoController controller) {
        this.controller = controller;
        
        setTitle("Actualizar Estado de Envío");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Número de Seguimiento:"));
        trackingNumberField = new JTextField();
        panel.add(trackingNumberField);

        panel.add(new JLabel("Nuevo Estado:"));
        estadoComboBox = new JComboBox<>(new String[] {"Pendiente", "En tránsito", "Entregado"});
        panel.add(estadoComboBox);

        actualizarButton = new JButton("Actualizar Estado");
        panel.add(actualizarButton);

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.actualizarEstado(
                    trackingNumberField.getText(),
                    (String) estadoComboBox.getSelectedItem()
                );
            }
        });

        add(panel);
    }
}
