package ventasgui.gestionesVentas;

import controladores.ControladorVentas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ventasgui.Ventana;

/**
 * Clase que representa la ventana grafica para anular una venta.
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class AnularVenta {

    //Ventana base personalizado
    private final Ventana ventanaAnularVenta;
    private final ControladorVentas control = new ControladorVentas();
    //Componentes graficas
    private JLabel titulo;
    private JLabel etiqueta;
    private JTextField cajaTexto;
    private JButton btnBorrar;
    private JButton btnVolver;

    /**
     * Constructor de la clase. Inicializa la ventanas y sus componentes.
     */
    public AnularVenta() {
        ventanaAnularVenta = new Ventana(450, 500);
        iniciarComponente();
        ventanaAnularVenta.setVisible(true);
    }

    /**
     * Agrega todos los componentes visuales a la ventana.
     */
    private void iniciarComponente() {
        titulo = new JLabel("Anular Venta");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);
        etiqueta();
        cajaDeTexto();
        boton();

        agregarComponente();
        asignarAccion();

        ventanaAnularVenta.revalidate();
        ventanaAnularVenta.repaint();

    }

    /**
     * Crea la etiqueta que pide al usuario ingresar el ID de la venta.
     */
    private void etiqueta() {
        etiqueta = new JLabel("Ingrese el numero de ID de la venta:");
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 20));
        etiqueta.setForeground(Color.RED);
        Dimension size = new Dimension(400, 30);
        etiqueta.setPreferredSize(size);
        etiqueta.setMaximumSize(size);
        etiqueta.setMinimumSize(size);
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Crea la caja de texto donde permite al usuario ingresar el ID de la
     * venta.
     */
    private void cajaDeTexto() {
        cajaTexto = new JTextField();
        Dimension sizeCaja = new Dimension(400, 30);
        cajaTexto.setPreferredSize(sizeCaja);
        cajaTexto.setMaximumSize(sizeCaja);
        cajaTexto.setMinimumSize(sizeCaja);
        cajaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Crea los botones "Anular" y "Volver"
     */
    private void boton() {
        btnBorrar = new JButton("Anular");
        btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnBorrar.setBackground(new Color(255, 165, 110));
        btnBorrar.setForeground(Color.BLACK);
        btnBorrar.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));
        Dimension size = new Dimension(200, 50);
        btnBorrar.setPreferredSize(size);
        btnBorrar.setMaximumSize(size);
        btnBorrar.setMinimumSize(size);
        btnBorrar.setAlignmentX(JButton.LEFT_ALIGNMENT);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnVolver.setBackground(new Color(255, 165, 110));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));
        btnVolver.setPreferredSize(size);
        btnVolver.setMaximumSize(size);
        btnVolver.setMinimumSize(size);
        btnVolver.setAlignmentX(JButton.LEFT_ALIGNMENT);

    }

    /**
     * Agrega todos los componentes al panel
     */
    private void agregarComponente() {
        ventanaAnularVenta.getPanelSuperior().add(titulo);
        agregarSeparador(30);
        ventanaAnularVenta.getPanelCentral().add(etiqueta);
        agregarSeparador(20);
        ventanaAnularVenta.getPanelCentral().add(cajaTexto);
        agregarSeparador(30);
        ventanaAnularVenta.getPanelCentral().add(btnBorrar);
        agregarSeparador(30);
        ventanaAnularVenta.getPanelCentral().add(btnVolver);
    }

    /**
     * Agrega un espacio vertical entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaAnularVenta.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * Asigna las acciones a los botones de la interfaz. El boton "Anular"
     * intenta eliminar una venta valida por ID El boton "Volver" cierra la
     * ventan actual y vuelve al gestion de ventas
     */
    private void asignarAccion() {
        btnBorrar.addActionListener(e -> {
            try {
                String textoIngresado = cajaTexto.getText();
                int idVenta = Integer.parseInt(textoIngresado);
                control.borrarVenta(idVenta);
                JOptionPane.showMessageDialog(null, "La venta se ha anulado con éxito.");
                ventanaAnularVenta.dispose();
                GestionVentas ventGestVenta = new GestionVentas();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            ventanaAnularVenta.dispose();
            GestionVentas gv = new GestionVentas();
        });
    }

}
