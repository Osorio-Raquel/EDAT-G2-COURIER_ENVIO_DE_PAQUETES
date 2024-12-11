package Vista;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import javax.swing.JLabel;

import java.awt.Font;

public class Panel1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable personal;

    /**
     * Create the panel.
     */
    public Panel1() {
        setBackground(new Color(13, 71, 170));  // Color de fondo azul oscuro
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        // Titulo
        JLabel lblListaDePersonal = new JLabel("Personal");
        lblListaDePersonal.setForeground(Color.WHITE);
        lblListaDePersonal.setFont(new Font("Roboto Black", Font.BOLD, 30));
        lblListaDePersonal.setBounds(24, 10, 517, 29);
        add(lblListaDePersonal);

        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 49, 559, 400);
        add(scrollPane);

        // Datos de la tabla
        String[] datos = {"ID", "Nombre", "Apellido", "CI", "Correo", "Cargo", "Salario"};
        DefaultTableModel empleados = desplegarEmpleados(datos);

        // Tabla para mostrar los datos
        personal = new JTable(empleados);
        ajustar(personal, 0, 30);
        ajustar(personal, 1, 60);
        ajustar(personal, 2, 60);
        ajustar(personal, 3, 65);
        ajustar(personal, 5, 80);  // Ajustado el tamaño de la columna "Cargo"
        ajustar(personal, 6, 60);

        // Bordes de la tabla
        personal.setBorder(new LineBorder(Color.BLACK));
        personal.setGridColor(Color.BLACK);
        personal.setFillsViewportHeight(true);
        personal.setRowHeight(40); // Altura de las filas

        // Cabecera de la tabla
        JTableHeader header = personal.getTableHeader();
        header.setBackground(new Color(21, 101, 192));  // Fondo azul claro
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(16f));
        header.setBorder(new LineBorder(Color.BLACK, 1));

        // Renderizado de las celdas
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((DefaultTableCellRenderer) component).setBorder(new LineBorder(Color.BLACK)); // Borde para celdas
                return component;
            }
        };

        // Aplicar el renderizador a todas las celdas de la tabla
        for (int i = 0; i < personal.getColumnCount(); i++) {
            personal.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Efecto de hover en las filas
        personal.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Cambiar color de fondo cuando el mouse pasa por encima
                if (!isSelected) {
                    if (row % 2 == 0) {
                        component.setBackground(new Color(245, 245, 245)); // Gris suave para filas pares
                    } else {
                        component.setBackground(Color.WHITE); // Blanco para filas impares
                    }
                }
                return component;
            }
        });

        scrollPane.setViewportView(personal);
    }

    // Método para cargar los empleados con datos predefinidos, incluyendo conductores
    public DefaultTableModel desplegarEmpleados(String[] nombresColumnas) {
        DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);
        String[] datos = new String[7];

        // Datos predefinidos de empleados
        // Empleado 1 - Gerente
        datos[0] = "001"; datos[1] = "Juan"; datos[2] = "Perez"; datos[3] = "12345678"; datos[4] = "juan.perez@empresa.com"; datos[5] = "Gerente"; datos[6] = "$3000";
        modelo.addRow(datos);

        // Empleado 2 - Asistente
        datos[0] = "002"; datos[1] = "Maria"; datos[2] = "Lopez"; datos[3] = "87654321"; datos[4] = "maria.lopez@empresa.com"; datos[5] = "Asistente"; datos[6] = "$2000";
        modelo.addRow(datos);

        // Empleado 3 - Jefe de Ventas
        datos[0] = "003"; datos[1] = "Carlos"; datos[2] = "Gonzalez"; datos[3] = "11223344"; datos[4] = "carlos.gonzalez@empresa.com"; datos[5] = "Jefe de Ventas"; datos[6] = "$2500";
        modelo.addRow(datos);

        // Empleado 4 - Ejecutivo de ventas
        datos[0] = "004"; datos[1] = "Ana"; datos[2] = "Martinez"; datos[3] = "44332211"; datos[4] = "ana.martinez@empresa.com"; datos[5] = "Ejecutiva de ventas"; datos[6] = "$2200";
        modelo.addRow(datos);

        // Empleado 5 - Conductor
        datos[0] = "005"; datos[1] = "Luis"; datos[2] = "Fernandez"; datos[3] = "98765432"; datos[4] = "luis.fernandez@empresa.com"; datos[5] = "Conductor"; datos[6] = "$1800";
        modelo.addRow(datos);

        // Empleado 6 - Conductor
        datos[0] = "006"; datos[1] = "Pedro"; datos[2] = "Sanchez"; datos[3] = "12349876"; datos[4] = "pedro.sanchez@empresa.com"; datos[5] = "Conductor"; datos[6] = "$1700";
        modelo.addRow(datos);

        return modelo;
    }

    // Ajustar el tamaño de las columnas de la tabla
    public static void ajustar(JTable table, int colIndex, int ancho) {
        TableColumn column = table.getColumnModel().getColumn(colIndex);
        column.setPreferredWidth(ancho);
        column.setMinWidth(ancho);
        column.setMaxWidth(ancho);
    }
}
