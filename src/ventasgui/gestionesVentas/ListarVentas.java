package ventasgui.gestionesVentas;

import controladores.ControladorVentas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Venta;
import ventasgui.Ventana;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class ListarVentas {

    private final ControladorVentas controlador = new ControladorVentas();
    private final Ventana ventanaListarVentas;
    private JLabel titulo;
    private JTable tabla;
    private JScrollPane scrollPane;
    private JButton botonVolver;
    private DefaultTableModel modeloTabla;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     */
    public ListarVentas() {
        ventanaListarVentas = new Ventana(600, 500);
        iniciarComponentes();
        cargarDatos();
        asignarAcciones();
        ventanaListarVentas.setVisible(true);
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Lista de Ventas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(Color.BLACK);

        configurarTabla();

        botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonVolver.setBackground(new Color(255, 165, 110));
        botonVolver.setForeground(Color.BLACK);
        botonVolver.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonVolver.setPreferredSize(new Dimension(175, 35));
        botonVolver.setAlignmentX(JButton.CENTER_ALIGNMENT);

        ventanaListarVentas.getPanelSuperior().add(titulo);
        ventanaListarVentas.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 20)));
        ventanaListarVentas.getPanelCentral().add(scrollPane);
        ventanaListarVentas.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 20)));
        ventanaListarVentas.getPanelCentral().add(botonVolver);
    }

    /**
     * metodo que configura la tabla de listar
     */
    private void configurarTabla() {
        String[] columnas = {"Fecha", "Código de Venta", "Cliente", "Monto Total"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);

        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setBackground(new Color(255, 255, 199));
        tabla.setForeground(Color.BLACK);

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(153, 0, 0));
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(550, 300));
    }

    /**
     * metodo que carga los datos a mostrar en la tabla
     */
    private void cargarDatos() {
        Map<Integer, Venta> ventas = controlador.getMapaVentas();

        for (Venta venta : ventas.values()) {
            String fecha = venta.getFechaDeCompra().toString();
            int codigo = venta.getId();
            String cliente = venta.getCliente().getNombre();
            int monto = venta.getMonto();

            modeloTabla.addRow(new Object[]{fecha, codigo, cliente, monto});
        }
    }

    /**
     * metodo que asigna las acciones de cada boton
     */
    private void asignarAcciones() {
        botonVolver.addActionListener(e -> {
            ventanaListarVentas.dispose();
            GestionVentas gv = new GestionVentas();
        });
    }
}
