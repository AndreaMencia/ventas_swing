package ventasgui.gestionesProductos;

import controladores.ControladorProductos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Producto;
import ventasgui.Ventana;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class ListarProductos {

    ControladorProductos controlador = new ControladorProductos();
    private final Ventana ventanaListarProductos;
    private JLabel titulo;
    private JTable tabla;
    JScrollPane scrollPane;
    private JButton botonVolver;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     */
    public ListarProductos() {
        ventanaListarProductos = new Ventana(600, 500);
        iniciarComponentes();
        ventanaListarProductos.setVisible(true);
        asignarAcciones();
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Lista de Productos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        ventanaListarProductos.getPanelSuperior().add(titulo);

        botonVolver = crearBoton("Volver");

        tabla();
        agregarComponentes();
        ventanaListarProductos.revalidate();
        ventanaListarProductos.repaint();
    }

    /**
     * metodo que crea la tabla donde se listan los productos creados
     */
    private void tabla() {
        String[] columnas = {"Código", "Nombre", "Precio", "Stock"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        Map<Integer, Producto> productos = controlador.getMapaProductos();

        for (Producto p : productos.values()) {
            Object[] fila = {
                p.getCodigo(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock()
            };
            modelo.addRow(fila);
        }

        tabla = new JTable(modelo);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setBackground(new Color(255, 255, 199));
        tabla.setForeground(Color.BLACK);
        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setBackground(new Color(153, 0, 0));
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        encabezado.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(550, 300));
    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaListarProductos.getPanelSuperior().add(titulo);
        agregarSeparador(10);
        ventanaListarProductos.getPanelCentral().add(scrollPane);
        agregarSeparador(10);
        ventanaListarProductos.getPanelCentral().add(botonVolver);
        agregarSeparador(10);
    }

    /**
     * metodo que crea un boton personalizado
     *
     * @param texto
     * @return el boton personalizado
     */
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(new Color(255, 165, 110));
        boton.setForeground(Color.BLACK);
        boton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));

        Dimension size = new Dimension(175, 40);
        boton.setPreferredSize(size);
        boton.setMaximumSize(size);
        boton.setMinimumSize(size);
        boton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        return boton;
    }

    /**
     * metodo que asigna las acciones de cada boton
     */
    private void asignarAcciones() {
        botonVolver.addActionListener(e -> {
            ventanaListarProductos.dispose();
            GestionProductos gp = new GestionProductos(); // Volver al menú de productos
        });
    }
    
    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaListarProductos.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }
}
