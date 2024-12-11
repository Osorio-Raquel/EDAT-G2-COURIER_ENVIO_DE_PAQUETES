package Vista;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import Modelo.Envios;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class Pedidos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public String[] columnasNombres = {"ID Envío", "Cliente", "Empleado", "Vehículo", "Sucursal", "Estado", "Fecha Envío", "Fecha Entrega", "Costo"};
    public DefaultTableModel tablaEnvios = new DefaultTableModel(null, columnasNombres);

    public Pedidos() {
        setType(Type.UTILITY);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1111, 758);

        // Panel principal con fondo blanco suave
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));  // Fondo blanco suave
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());  // Usamos BorderLayout para manejar el espacio de manera eficiente
        setLocationRelativeTo(null);

        // Panel izquierdo (Sidebar) con azul suave
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 150, 243));  // Azul brillante
        panel.setPreferredSize(new Dimension(350, 0));  // Establecemos el tamaño fijo para el panel
        contentPane.add(panel, BorderLayout.WEST);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 

     // Botón para regresar
        JButton btnCerrarSesion = new JButton("Atrás");
        btnCerrarSesion.addActionListener(e -> {
            MenuGerente meng = new MenuGerente();
            meng.setVisible(true);
            dispose();
        });
        btnCerrarSesion.setBounds(100, panel.getHeight() - 100, 150, 40); // Ajusta la coordenada 'y'
 // Centramos el botón dentro del panel
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Roboto", Font.BOLD, 20));  // Fuente Roboto
        btnCerrarSesion.setBackground(new Color(0, 123, 255));  // Azul brillante
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(new LineBorder(new Color(0, 123, 255), 2, true)); // Bordes redondeados
        panel.add(btnCerrarSesion);

        // Barra de desplazamiento para la tabla
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);  // La tabla ocupará el centro de la ventana

        // Definir la tabla con bordes y estilo estético
        JTable tablaEnviosTable = new JTable(tablaEnvios);
        tablaEnviosTable.setRowHeight(45);  // Aumentamos la altura de las filas para mayor comodidad
        tablaEnviosTable.setFont(new Font("Roboto", Font.PLAIN, 16));  // Fuente más grande y moderna
        tablaEnviosTable.setForeground(Color.BLACK);  // Texto negro para mejor visibilidad
        tablaEnviosTable.setBackground(Color.WHITE);  // Fondo blanco de la tabla
        tablaEnviosTable.setSelectionBackground(new Color(33, 150, 243));  // Color de selección azul
        tablaEnviosTable.setSelectionForeground(Color.WHITE);  // Color de texto seleccionado en blanco
        tablaEnviosTable.setGridColor(new Color(230, 230, 230));  // Color gris claro para las líneas de la tabla

        // Configurar el encabezado de la tabla
        tablaEnviosTable.getTableHeader().setBackground(new Color(33, 150, 243));  // Fondo azul en el encabezado
        tablaEnviosTable.getTableHeader().setForeground(Color.WHITE);  // Texto blanco en el encabezado
        tablaEnviosTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));  // Fuente del encabezado en negrita

        scrollPane.setViewportView(tablaEnviosTable);

        // Insertar datos ficticios en la tabla
        insertarDatosFicticios(tablaEnviosTable);

        // Panel para el botón de cargar
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(255, 255, 255));  // Fondo blanco
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(panelBoton, BorderLayout.SOUTH);

        // Botón para cargar todos los envíos
        JButton btnCargarTodos = new JButton("Cargar Todos los Envíos");
        btnCargarTodos.addActionListener(e -> {
            // Crear el objeto de la clase Envios
            Envios envio = new Envios();

            // Llamar al método para obtener todos los envíos
            DefaultTableModel nuevoModelo = envio.obtenerEnvios(columnasNombres);

            // Establecer el modelo actualizado a la tabla
            tablaEnviosTable.setModel(nuevoModelo);

            // Ajustar el tamaño de las columnas
            ajustar(tablaEnviosTable, 0, 50); // Ajusta el ancho de las columnas según sea necesario
        });
        btnCargarTodos.setForeground(Color.WHITE);
        btnCargarTodos.setFont(new Font("Roboto", Font.BOLD, 18));  // Fuente en negrita
        btnCargarTodos.setFocusPainted(false);
        btnCargarTodos.setBorder(new LineBorder(new Color(7, 54, 127), 2));  // Bordes azules
        btnCargarTodos.setBackground(new Color(0, 123, 255));  // Azul brillante
        btnCargarTodos.setPreferredSize(new Dimension(250, 50)); // Tamaño grande y centrado
        panelBoton.add(btnCargarTodos);
    }

    // Método para ajustar el tamaño de las columnas de la tabla
    private void ajustar(JTable tabla, int columna, int ancho) {
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
    }

    // Método para insertar datos ficticios en la tabla
    private void insertarDatosFicticios(JTable tablaEnviosTable) {
        // Datos ficticios
    	Object[][] datos = {
                {"001", "Juan Pérez", "Carlos Gómez", "Camión A1", "Sucursal Centro", "En Proceso", "2024-12-01", "2024-12-03", "$100"},
                {"002", "Ana Martínez", "Sofía López", "Furgón B2", "Sucursal Norte", "Entregado", "2024-12-02", "2024-12-04", "$75"},
                {"003", "Pedro Sánchez", "Luis Fernández", "Camión C3", "Sucursal Este", "Pendiente", "2024-12-03", "2024-12-06", "$120"},
                {"004", "Lucía Rodríguez", "Marcos Díaz", "Furgón D4", "Sucursal Oeste", "En Proceso", "2024-12-04", "2024-12-07", "$90"},
                {"005", "Marta Gómez", "Daniel Ramírez", "Camión E5", "Sucursal Sur", "Entregado", "2024-12-05", "2024-12-08", "$110"},
                {"006", "Diego Herrera", "Natalia Ruiz", "Camión F6", "Sucursal Centro", "Pendiente", "2024-12-06", "2024-12-09", "$95"},
                {"007", "Isabel Morales", "Jorge Castillo", "Furgón G7", "Sucursal Norte", "En Proceso", "2024-12-07", "2024-12-10", "$85"},
                {"008", "Carlos Vega", "Valentina Cruz", "Camión H8", "Sucursal Este", "Entregado", "2024-12-08", "2024-12-11", "$130"},
                {"009", "Sofía Navarro", "Ricardo Paredes", "Furgón I9", "Sucursal Oeste", "Pendiente", "2024-12-09", "2024-12-12", "$105"},
                {"010", "Manuel Ortiz", "Andrea Molina", "Camión J10", "Sucursal Sur", "En Proceso", "2024-12-10", "2024-12-13", "$115"},
                {"011", "María Luisa", "Fernando Soto", "Camión K11", "Sucursal Centro", "Entregado", "2024-12-11", "2024-12-14", "$125"},
                {"012", "Jorge Díaz", "Paula Torres", "Furgón L12", "Sucursal Norte", "Pendiente", "2024-12-12", "2024-12-15", "$80"},
                {"013", "Laura Pérez", "Gabriel Castillo", "Camión M13", "Sucursal Este", "En Proceso", "2024-12-13", "2024-12-16", "$100"},
                {"014", "Fernando García", "Elena Ramírez", "Furgón N14", "Sucursal Oeste", "Entregado", "2024-12-14", "2024-12-17", "$90"},
                {"015", "Natalia Torres", "Héctor Morales", "Camión O15", "Sucursal Sur", "Pendiente", "2024-12-15", "2024-12-18", "$115"},
                {"016", "Daniela Ruiz", "Víctor Hernández", "Furgón P16", "Sucursal Centro", "En Proceso", "2024-12-16", "2024-12-19", "$85"},
                {"017", "Luis Fernández", "Claudia Sánchez", "Camión Q17", "Sucursal Norte", "Entregado", "2024-12-17", "2024-12-20", "$140"},
                {"018", "Paula Martínez", "Alejandro Gómez", "Furgón R18", "Sucursal Este", "Pendiente", "2024-12-18", "2024-12-21", "$95"},
                {"019", "Ricardo López", "Mónica Herrera", "Camión S19", "Sucursal Oeste", "En Proceso", "2024-12-19", "2024-12-22", "$110"},
                {"020", "Gabriela Navarro", "Sergio Díaz", "Furgón T20", "Sucursal Sur", "Entregado", "2024-12-20", "2024-12-23", "$120"},
                {"021", "Felipe Morales", "Camila Ortiz", "Camión U21", "Sucursal Centro", "Pendiente", "2024-12-21", "2024-12-24", "$105"},
                {"022", "Andrea Castillo", "Marco Rivas", "Furgón V22", "Sucursal Norte", "En Proceso", "2024-12-22", "2024-12-25", "$95"},
                {"023", "Sebastián Vega", "Valeria Cruz", "Camión W23", "Sucursal Este", "Entregado", "2024-12-23", "2024-12-26", "$130"},
                {"024", "Mariana Navarro", "Diego Herrera", "Furgón X24", "Sucursal Oeste", "Pendiente", "2024-12-24", "2024-12-27", "$100"},
                {"025", "Pablo Ortiz", "Lucía Rodríguez", "Camión Y25", "Sucursal Sur", "En Proceso", "2024-12-25", "2024-12-28", "$115"},
                {"026", "Isabel Gómez", "Fernando Soto", "Furgón Z26", "Sucursal Centro", "Entregado", "2024-12-26", "2024-12-29", "$125"},
                {"027", "Alejandro Morales", "Paula Torres", "Camión A27", "Sucursal Norte", "Pendiente", "2024-12-27", "2024-12-30", "$80"},
                {"028", "Marta Pérez", "Gabriel Castillo", "Furgón B28", "Sucursal Este", "En Proceso", "2024-12-28", "2024-12-31", "$100"},
                {"029", "Luis Ramírez", "Elena Ramírez", "Camión C29", "Sucursal Oeste", "Entregado", "2024-12-29", "2024-12-31", "$90"},
                {"030", "Sofía Torres", "Héctor Morales", "Furgón D30", "Sucursal Sur", "Pendiente", "2024-12-30", "2025-01-02", "$115"},
                {"031", "Daniela Morales", "Víctor Hernández", "Camión E31", "Sucursal Centro", "En Proceso", "2024-12-31", "2025-01-03", "$85"},
                {"032", "María Gómez", "Claudia Sánchez", "Furgón F32", "Sucursal Norte", "Entregado", "2025-01-01", "2025-01-04", "$140"},
                {"033", "Juan Hernández", "Alejandro Gómez", "Camión G33", "Sucursal Este", "Pendiente", "2025-01-02", "2025-01-05", "$95"},
                {"034", "Ana Ruiz", "Mónica Herrera", "Furgón H34", "Sucursal Oeste", "En Proceso", "2025-01-03", "2025-01-06", "$110"},
                {"035", "Pedro López", "Sergio Díaz", "Camión I35", "Sucursal Sur", "Entregado", "2025-01-04", "2025-01-07", "$120"},
                {"036", "Lucía Martínez", "Camila Ortiz", "Furgón J36", "Sucursal Centro", "Pendiente", "2025-01-05", "2025-01-08", "$105"},
                {"037", "Carlos Rodríguez", "Marco Rivas", "Camión K37", "Sucursal Norte", "En Proceso", "2025-01-06", "2025-01-09", "$95"},
                {"038", "Isabel Gómez", "Valeria Cruz", "Furgón L38", "Sucursal Este", "Entregado", "2025-01-07", "2025-01-10", "$130"},
                {"039", "Felipe Martínez", "Diego Herrera", "Camión M39", "Sucursal Oeste", "Pendiente", "2025-01-08", "2025-01-11", "$100"},
                {"040", "Andrea López", "Lucía Rodríguez", "Furgón N40", "Sucursal Sur", "En Proceso", "2025-01-09", "2025-01-12", "$115"},
                {"041", "Sebastián Ruiz", "Fernando Soto", "Camión O41", "Sucursal Centro", "Entregado", "2025-01-10", "2025-01-13", "$125"},
                {"042", "Mariana Fernández", "Paula Torres", "Furgón P42", "Sucursal Norte", "Pendiente", "2025-01-11", "2025-01-14", "$80"},
                {"043", "Pablo Gómez", "Gabriel Castillo", "Camión Q43", "Sucursal Este", "En Proceso", "2025-01-12", "2025-01-15", "$100"},
                {"044", "Isabel Torres", "Elena Ramírez", "Furgón R44", "Sucursal Oeste", "Entregado", "2025-01-13", "2025-01-16", "$90"},
                {"045", "Luis Morales", "Héctor Morales", "Camión S45", "Sucursal Sur", "Pendiente", "2025-01-14", "2025-01-17", "$115"},
                {"046", "Marta Ruiz", "Víctor Hernández", "Furgón T46", "Sucursal Centro", "En Proceso", "2025-01-15", "2025-01-18", "$85"},
                {"047", "Daniel Morales", "Claudia Sánchez", "Camión U47", "Sucursal Norte", "Entregado", "2025-01-16", "2025-01-19", "$140"},
                {"048", "María Herrera", "Alejandro Gómez", "Furgón V48", "Sucursal Este", "Pendiente", "2025-01-17", "2025-01-20", "$95"},
                {"049", "Juan López", "Mónica Herrera", "Camión W49", "Sucursal Oeste", "En Proceso", "2025-01-18", "2025-01-21", "$110"},
                {"050", "Ana Rodríguez", "Sergio Díaz", "Furgón X50", "Sucursal Sur", "Entregado", "2025-01-19", "2025-01-22", "$120"}
            };

        // Llenar la tabla con los datos ficticios
        for (Object[] fila : datos) {
            tablaEnvios.addRow(fila);
        }
    }
}
