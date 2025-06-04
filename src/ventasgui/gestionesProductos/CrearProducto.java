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
public class CrearProducto {

    private final ControladorProductos control = new ControladorProductos();
    private final Ventana ventanaCrearProducto;
    private JLabel titulo;
    private JLabel etiqueta1;
    private JTextField cajaTexto1;
    private JLabel etiqueta2;
    private JTextField cajaTexto2;
    private JLabel etiqueta3;
    private JTextField cajaTexto3;
    private JLabel etiqueta4;
    private JTextField cajaTexto4;
    private JButton botonGuardar;
    private JButton botonVolver;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     */
    public CrearProducto() {
        ventanaCrearProducto = new Ventana(450, 550);
        iniciarComponentes();
        asignarAcciones();
        ventanaCrearProducto.setVisible(true);
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Crear Producto");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);

        etiqueta1 = crearEtiqueta("Código del Producto:");
        etiqueta2 = crearEtiqueta("Nombre del Producto:");
        etiqueta3 = crearEtiqueta("Precio:");
        etiqueta4 = crearEtiqueta("Cantidad:");

        cajaTexto1 = crearCajaDeTexto();
        cajaTexto2 = crearCajaDeTexto();
        cajaTexto3 = crearCajaDeTexto();
        cajaTexto4 = crearCajaDeTexto();

        botonGuardar = crearBoton("Guardar");
        botonVolver = crearBoton("Volver");

        agregarComponentes();

        ventanaCrearProducto.revalidate();
        ventanaCrearProducto.repaint();
    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaCrearProducto.getPanelSuperior().add(titulo);
        agregarSeparador(15);
        agregarEtiqueta(etiqueta1);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto1);
        agregarSeparador(15);
        agregarEtiqueta(etiqueta2);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto2);
        agregarSeparador(15);
        agregarEtiqueta(etiqueta3);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto3);
        agregarSeparador(15);
        agregarEtiqueta(etiqueta4);
        agregarSeparador(5);
        agregarCajaDeTexto(cajaTexto4);
        agregarSeparador(20);
        agregarBoton(botonGuardar);
        agregarSeparador(15);
        agregarBoton(botonVolver);

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

        Dimension size = new Dimension(150, 30);
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
        botonGuardar.addActionListener(e -> {
            try {
                String nombre = cajaTexto2.getText();

                int codigo = Integer.parseInt(cajaTexto1.getText());
                int precio = Integer.parseInt(cajaTexto3.getText());
                int stock = Integer.parseInt(cajaTexto4.getText());
                control.verificarNombre(nombre);
                control.crearProducto(nombre, codigo, precio, stock);
                JOptionPane.showMessageDialog(null, "Producto guardado correctamente");
                ventanaCrearProducto.dispose();
                GestionProductos menu = new GestionProductos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Vuelva a intentar");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        botonVolver.addActionListener(e -> {
            ventanaCrearProducto.dispose();
            GestionProductos menu = new GestionProductos();
        });
    }

    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaCrearProducto.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     *
     * @param boton
     */
    private void agregarBoton(JButton boton) {
        ventanaCrearProducto.getPanelCentral().add(boton);
    }

    /**
     * metodo que agrega la etiqueta
     *
     * @param etiqueta
     */
    private void agregarEtiqueta(JLabel etiqueta) {
        ventanaCrearProducto.getPanelCentral().add(etiqueta);
    }

    /**
     * metodo que agrega la caja de texto
     *
     * @param cajaDeTexto
     */
    private void agregarCajaDeTexto(JTextField cajaDeTexto) {
        ventanaCrearProducto.getPanelCentral().add(cajaDeTexto);
    }
}
