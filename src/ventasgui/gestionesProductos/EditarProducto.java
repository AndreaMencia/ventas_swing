package ventasgui.gestionesProductos;

import controladores.ControladorProductos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ventasgui.Ventana;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class EditarProducto {

    private final ControladorProductos control = new ControladorProductos();
    private final Ventana ventanaEditarProducto;
    private JLabel titulo;
    private JLabel etiqueta1;
    private JTextField cajaTexto1;
    private JButton botonEditarNombre;
    private JButton botonEditarPrecio;
    private JButton botonEditarStock;
    private JButton botonVolver;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     */
    public EditarProducto() {
        ventanaEditarProducto = new Ventana(450, 450);
        iniciarComponentes();
        asignarAcciones();
        ventanaEditarProducto.setVisible(true);
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Editar Producto");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);

        etiqueta1 = crearEtiqueta("Código del Producto:");
        cajaTexto1 = crearCajaDeTexto();

        botonEditarNombre = crearBoton("Editar Nombre");
        botonEditarPrecio = crearBoton("Editar Precio");
        botonEditarStock = crearBoton("Editar Stock");
        botonVolver = crearBoton("Volver");

        agregarComponentes();

        ventanaEditarProducto.revalidate();
        ventanaEditarProducto.repaint();

    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaEditarProducto.getPanelSuperior().add(titulo);
        agregarSeparador(15);
        agregarEtiqueta(etiqueta1);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto1);
        agregarSeparador(25);
        agregarBoton(botonEditarNombre);
        agregarSeparador(15);
        agregarBoton(botonEditarPrecio);
        agregarSeparador(15);
        agregarBoton(botonEditarStock);
        agregarSeparador(15);
        agregarBoton(botonVolver);
        agregarSeparador(15);
    }

    /**
     * metodo que crea una etiqueta personalizada
     *
     * @param text
     * @return la etiqueta personalizada
     */
    private JLabel crearEtiqueta(String text) {
        JLabel etiqueta = new JLabel(text);
        etiqueta.setFont(new Font("Monospaced", Font.BOLD, 18));
        etiqueta.setForeground(Color.RED);

        Dimension size = new Dimension(300, 25);
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
        Dimension size = new Dimension(300, 30);
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
        botonEditarNombre.addActionListener(e -> {
            try {
                String codigoIngresado = cajaTexto1.getText();
                if (codigoIngresado == null || codigoIngresado.isBlank()) {
                    return;
                }
                int codigo = Integer.parseInt(codigoIngresado);
                control.obtenerProducto(codigo);
                String input = JOptionPane.showInputDialog("Nuevo nombre: ", JOptionPane.QUESTION_MESSAGE);

                if (input == null || input.isBlank()) {
                    return;
                }
                String nuevoNombre = input.trim();
                control.editarNombre(codigo, nuevoNombre);
                JOptionPane.showMessageDialog(null, "Nombre editado correctamente");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
            }
        }
        );
        botonEditarPrecio.addActionListener(e -> {
            try {
                String codigoIngresado = cajaTexto1.getText();
                if (codigoIngresado == null || codigoIngresado.isBlank()) {
                    return;
                }
                int codigo = Integer.parseInt(codigoIngresado);
                control.obtenerProducto(codigo);
                String input = JOptionPane.showInputDialog("Nuevo precio: ", JOptionPane.QUESTION_MESSAGE);
                if (input == null || input.isBlank()) {
                    return;
                }
                int nuevoPrecio = Integer.parseInt(input);
                control.editarPrecio(codigo, nuevoPrecio);
                JOptionPane.showMessageDialog(null, "Precio editado correctamente");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
            }
        });
        botonEditarStock.addActionListener(e -> {
            try {
                String codigoIngresado = cajaTexto1.getText();
                if (codigoIngresado == null || codigoIngresado.isBlank()) {
                    return;
                }
                int codigo = Integer.parseInt(codigoIngresado);
                control.obtenerProducto(codigo);
                String input = JOptionPane.showInputDialog("Nuevo stock: ", JOptionPane.QUESTION_MESSAGE);
                if (input == null || input.isBlank()) {
                    return;
                }
                int nuevoStock = Integer.parseInt(input);
                control.editarStock(codigo, nuevoStock);
                JOptionPane.showMessageDialog(null, "Stock editado correctamente");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
            }
        });
        botonVolver.addActionListener(e -> {
            ventanaEditarProducto.dispose();
            GestionProductos menu = new GestionProductos();
        });
    }

    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaEditarProducto.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     *
     * @param boton
     */
    private void agregarBoton(JButton boton) {
        ventanaEditarProducto.getPanelCentral().add(boton);
    }

    /**
     * metodo que agrega la etiqueta
     *
     * @param etiqueta
     */
    private void agregarEtiqueta(JLabel etiqueta) {
        ventanaEditarProducto.getPanelCentral().add(etiqueta);
    }

    /**
     * metodo que agrega la caja de texto
     *
     * @param cajaDeTexto
     */
    private void agregarCajaDeTexto(JTextField cajaDeTexto) {
        ventanaEditarProducto.getPanelCentral().add(cajaDeTexto);
    }
}
