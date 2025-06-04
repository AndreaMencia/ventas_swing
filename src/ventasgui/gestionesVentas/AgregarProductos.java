package ventasgui.gestionesVentas;

import controladores.ControladorProductos;
import controladores.ControladorVentas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Producto;
import ventasgui.Ventana;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class AgregarProductos {

    private final ControladorVentas control = new ControladorVentas();
    private final ControladorProductos controlProductos = new ControladorProductos();
    private final Ventana ventanaAgregarProductos;
    private final CrearVenta padre;
    private JLabel titulo;
    private JLabel etiqueta1, etiqueta2;
    private JTextField cajaTexto1, cajaTexto2;
    private JButton botonAgregar, botonGuardar, botonVolver;
    private JTable tabla;
    private JScrollPane scrollPane;
    private JLabel etiquetaTotal;
    private DefaultTableModel modeloTabla;
    private final Map<Producto, Integer> productosAgregados;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     *
     * @param padre
     * @param productosAgregados
     */
    public AgregarProductos(CrearVenta padre, Map<Producto, Integer> productosAgregados) {
        this.padre = padre;
        this.productosAgregados = productosAgregados;
        ventanaAgregarProductos = new Ventana(500, 600);
        iniciarComponentes();
        asignarAcciones();
        ventanaAgregarProductos.setVisible(true);
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Agregar Productos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);

        etiqueta1 = crearEtiqueta("Código del Producto:");
        etiqueta2 = crearEtiqueta("Cantidad:");

        cajaTexto1 = crearCajaDeTexto();
        cajaTexto2 = crearCajaDeTexto();

        botonAgregar = crearBoton("Agregar");
        botonGuardar = crearBoton("Guardar");
        botonVolver = crearBoton("Volver");

        configurarTabla();

        etiquetaTotal = new JLabel("Total: 0 Gs.");
        etiquetaTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        etiquetaTotal.setForeground(Color.BLACK);
        etiquetaTotal.setPreferredSize(new Dimension(200, 30));
        etiquetaTotal.setAlignmentX(JLabel.RIGHT_ALIGNMENT);

        agregarComponentes();
    }

    /**
     * metodo que crea la tabla donde se mostraran los productos que se vayan
     * agregando
     */
    private void configurarTabla() {
        String[] columnas = {"Producto", "Precio", "Cantidad", "Subtotal"};
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
        scrollPane.setPreferredSize(new Dimension(400, 250));
    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaAgregarProductos.getPanelSuperior().add(titulo);
        agregarSeparador(20);
        agregarEtiqueta(etiqueta1);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto1);
        agregarSeparador(15);
        agregarEtiqueta(etiqueta2);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto2);
        agregarSeparador(20);
        agregarBoton(botonAgregar);
        agregarSeparador(15);
        ventanaAgregarProductos.getPanelCentral().add(scrollPane);
        agregarSeparador(10);
        ventanaAgregarProductos.getPanelCentral().add(etiquetaTotal);
        agregarSeparador(10);
        agregarBoton(botonGuardar);
        agregarSeparador(10);
        agregarBoton(botonVolver);
    }

    /**
     * metodo que crea una etiqueta personalizada
     *
     * @param texto
     * @return la etiqueta personalizada
     */
    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Monospaced", Font.BOLD, 18));
        etiqueta.setForeground(Color.RED);

        Dimension size = new Dimension(350, 25);
        etiqueta.setPreferredSize(size);
        etiqueta.setMaximumSize(size);
        etiqueta.setMinimumSize(size);
        etiqueta.setAlignmentX(JButton.CENTER_ALIGNMENT);

        return etiqueta;
    }

    /**
     * metodo que crea una caja de texto personalizada
     *
     * @return la caja de texto personalizada
     */
    private JTextField crearCajaDeTexto() {
        JTextField cajaDeTexto = new JTextField();
        Dimension size = new Dimension(350, 30);
        cajaDeTexto.setPreferredSize(size);
        cajaDeTexto.setMaximumSize(size);
        cajaDeTexto.setMinimumSize(size);
        cajaDeTexto.setAlignmentX(JButton.CENTER_ALIGNMENT);

        return cajaDeTexto;
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

        Dimension size = new Dimension(175, 30);
        boton.setPreferredSize(size);
        boton.setMaximumSize(size);
        boton.setMinimumSize(size);
        boton.setAlignmentX(JButton.LEFT_ALIGNMENT);

        return boton;
    }

    /**
     * metodo que asigna las acciones de cada boton
     */
    private void asignarAcciones() {
        botonAgregar.addActionListener(e -> {
            try {
                String codigoIngresado = cajaTexto1.getText();
                if (codigoIngresado == null || codigoIngresado.isBlank()) {
                    return;
                }
                int codigo = Integer.parseInt(codigoIngresado);

                String cantidadIngresada = cajaTexto2.getText();
                if (cantidadIngresada == null || cantidadIngresada.isBlank()) {
                    return;
                }
                int cantidad = Integer.parseInt(cantidadIngresada);

                control.verificarProducto(codigo);
                control.verificarCantidad(cantidad);

                Producto producto = controlProductos.obtenerProducto(codigo);
                productosAgregados.merge(producto, cantidad, Integer::sum);
                actualizarTabla();

                cajaTexto1.setText("");
                cajaTexto2.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Código o cantidad no válidos.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        botonGuardar.addActionListener(e -> {
            try {
                padre.setProductosSeleccionados(productosAgregados);
                ventanaAgregarProductos.dispose();
                padre.abrirVentana(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Código o cantidad no válidos.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        botonVolver.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Cancelar productos y volver?", "Confirmar cancelación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                ventanaAgregarProductos.dispose();
                GestionVentas gv = new GestionVentas();
            }
        });
    }

    /**
     * metodo que va actualizando la tabla de productos
     */
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        int total = 0;
        for (Map.Entry<Producto, Integer> entry : productosAgregados.entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            int subtotal = p.getPrecio() * cantidad;
            modeloTabla.addRow(new Object[]{p.getNombre(), p.getPrecio(), cantidad, subtotal});
            total += subtotal;
        }
        etiquetaTotal.setText("Total: " + total + " Gs.");
    }

    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaAgregarProductos.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     *
     * @param boton
     */
    private void agregarBoton(JButton boton) {
        ventanaAgregarProductos.getPanelCentral().add(boton);
    }

    /**
     * metodo que agrega la etiqueta
     *
     * @param etiqueta
     */
    private void agregarEtiqueta(JLabel etiqueta) {
        ventanaAgregarProductos.getPanelCentral().add(etiqueta);
    }

    /**
     * metodo que agrega la caja de texto
     *
     * @param cajaDeTexto
     */
    private void agregarCajaDeTexto(JTextField cajaDeTexto) {
        ventanaAgregarProductos.getPanelCentral().add(cajaDeTexto);
    }
}
