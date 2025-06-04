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
public class BorrarProducto {

    private final ControladorProductos control = new ControladorProductos();
    private final Ventana ventanaBorrarProducto;
    private JLabel titulo;
    private JLabel etiqueta1;
    private JTextField cajaTexto1;
    private JButton botonBorrar;
    private JButton botonVolver;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     */
    public BorrarProducto() {
        ventanaBorrarProducto = new Ventana(450, 350);
        iniciarComponentes();
        ventanaBorrarProducto.setVisible(true);
        asignarAcciones();

    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Borrar Producto");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);

        etiqueta1 = crearEtiqueta("Código del Producto:");
        cajaTexto1 = crearCajaDeTexto();
        botonBorrar = crearBoton("Borrar");
        botonVolver = crearBoton("Volver");

        agregarComponentes();

        ventanaBorrarProducto.revalidate();
        ventanaBorrarProducto.repaint();
    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaBorrarProducto.getPanelSuperior().add(titulo);
        agregarSeparador(25);
        agregarEtiqueta(etiqueta1);
        agregarSeparador(10);
        agregarCajaDeTexto(cajaTexto1);
        agregarSeparador(35);
        agregarBoton(botonBorrar);
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
        botonBorrar.addActionListener(e -> {
            try {
                String codigoIngresado = cajaTexto1.getText();
                if (codigoIngresado == null || codigoIngresado.isBlank()) {
                    return;
                }
                int codigo = Integer.parseInt(codigoIngresado);
                control.obtenerProducto(codigo);
                control.borrarProducto(codigo);
                JOptionPane.showMessageDialog(null, "Producto borrado correctamente");
                ventanaBorrarProducto.dispose();
                GestionProductos menu = new GestionProductos();

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
            }
        });
        botonVolver.addActionListener(e -> {
            ventanaBorrarProducto.dispose();
            GestionProductos menu = new GestionProductos();
        });
    }

    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaBorrarProducto.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     *
     * @param boton
     */
    private void agregarBoton(JButton boton) {
        ventanaBorrarProducto.getPanelCentral().add(boton);
    }

    /**
     * metodo que agrega la etiqueta
     *
     * @param etiqueta
     */
    private void agregarEtiqueta(JLabel etiqueta) {
        ventanaBorrarProducto.getPanelCentral().add(etiqueta);
    }

    /**
     * metodo que agrega la caja de texto
     *
     * @param cajaDeTexto
     */
    private void agregarCajaDeTexto(JTextField cajaDeTexto) {
        ventanaBorrarProducto.getPanelCentral().add(cajaDeTexto);
    }

}
