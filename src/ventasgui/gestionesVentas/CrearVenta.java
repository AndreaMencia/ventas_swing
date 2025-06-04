package ventasgui.gestionesVentas;

import controladores.ControladorClientes;
import controladores.ControladorVentas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Producto;
import ventasgui.Ventana;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class CrearVenta {

    private final ControladorVentas control = new ControladorVentas();
    private final ControladorClientes controlClientes = new ControladorClientes();
    private final Ventana ventanaCrearVenta;
    private JLabel titulo;
    private JLabel etiqueta1;
    private JTextField cajaTexto1;
    private JLabel etiqueta2;
    private JTextField cajaTexto2;
    private JButton botonAgregar;
    private JButton botonGuardar;
    private JButton botonVolver;
    private Map<Producto, Integer> productosAgregados = new HashMap<>();

    /**
     * contructor de la clase que crea la ventana y agrega todos los
     * componentes y acciones
     */
    public CrearVenta() {
        ventanaCrearVenta = new Ventana(425, 450);
        iniciarComponentes();
        asignarAcciones();
        ventanaCrearVenta.setVisible(true);
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Crear Venta");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);

        etiqueta1 = crearEtiqueta("Código de Venta:");
        etiqueta2 = crearEtiqueta("CI del Cliente:");

        cajaTexto1 = crearCajaDeTexto();
        cajaTexto2 = crearCajaDeTexto();

        botonAgregar = crearBoton("Agregar Productos");
        botonGuardar = crearBoton("Guardar");
        botonVolver = crearBoton("Volver");

        agregarComponentes();
        ventanaCrearVenta.revalidate();
        ventanaCrearVenta.repaint();
    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaCrearVenta.getPanelSuperior().add(titulo);
        agregarSeparador(15);
        agregarEtiqueta(etiqueta1);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto1);
        agregarSeparador(15);
        agregarEtiqueta(etiqueta2);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto2);
        agregarSeparador(30);
        agregarBoton(botonAgregar);
        agregarSeparador(15);
        agregarBoton(botonGuardar);
        agregarSeparador(15);
        agregarBoton(botonVolver);
        agregarSeparador(15);
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
                int codigoVenta = Integer.parseInt(codigoIngresado);

                String cedulaIngresada = cajaTexto2.getText();
                if (cedulaIngresada == null || cedulaIngresada.isBlank()) {
                    return;
                }
                int cedula = Integer.parseInt(cedulaIngresada);

                control.verificarCliente(cedula);
                control.verificarCodigo(codigoVenta);

                ventanaCrearVenta.setVisible(false);
                AgregarProductos ap = new AgregarProductos(this, productosAgregados);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Códigos inválidos.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });

        botonGuardar.addActionListener(e -> {
            try {
                String codigoIngresado = cajaTexto1.getText();
                if (codigoIngresado == null || codigoIngresado.isBlank()) {
                    return;
                }
                int codigoVenta = Integer.parseInt(codigoIngresado);

                String cedulaIngresada = cajaTexto2.getText();
                if (cedulaIngresada == null || cedulaIngresada.isBlank()) {
                    return;
                }
                int cedula = Integer.parseInt(cedulaIngresada);

                control.verificarCliente(cedula);
                control.verificarCodigo(codigoVenta);
                control.verificarProductos(productosAgregados);
                setProductosSeleccionados(productosAgregados);

                int total = productosAgregados.entrySet().stream()
                        .mapToInt(e2 -> e2.getKey().getPrecio() * e2.getValue()).sum();

                control.crearVenta(codigoVenta, cedula, productosAgregados, total);
                JOptionPane.showMessageDialog(null, "Venta guardada correctamente.");

                ventanaCrearVenta.dispose();
                GestionVentas gv = new GestionVentas();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Códigos inválidos.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });

        botonVolver.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de que deseas cancelar la venta?", "Cancelar Venta",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                limpiarCampos(); // limpiar campos y productos
                ventanaCrearVenta.dispose();
                GestionVentas gv = new GestionVentas();
            }
        });
    }

    /*
    metodo que guarda los productos que se van agregando a la venta
    */
    public void setProductosSeleccionados(Map<Producto, Integer> productos) {
        control.verificarProductos(productos);
        this.productosAgregados = productos;
    }

    /**
     * metodo que lmpia los datos de la venta
     */
    private void limpiarCampos() {
        cajaTexto1.setText("");
        cajaTexto2.setText("");
        productosAgregados.clear();
    }

    /**
     * metodo que maneja la visibilidad de la ventana
     * 
     * @param abrir 
     */
    public void abrirVentana(boolean abrir) {
        ventanaCrearVenta.setVisible(abrir);
    }

    /**
     * metodo que agrega una separacion entre componentes
     * @param alto 
     */
    private void agregarSeparador(int alto) {
        ventanaCrearVenta.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     * 
     * @param boton 
     */
    private void agregarBoton(JButton boton) {
        ventanaCrearVenta.getPanelCentral().add(boton);
    }

    /**
     * metodo que agrega la etiqueta
     * 
     * @param etiqueta 
     */
    private void agregarEtiqueta(JLabel etiqueta) {
        ventanaCrearVenta.getPanelCentral().add(etiqueta);
    }

    /**
     * metodo que agrega la caja de texto
     * 
     * @param cajaDeTexto 
     */
    private void agregarCajaDeTexto(JTextField cajaDeTexto) {
        ventanaCrearVenta.getPanelCentral().add(cajaDeTexto);
    }
}
