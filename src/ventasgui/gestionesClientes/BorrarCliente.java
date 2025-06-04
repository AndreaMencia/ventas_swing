package ventasgui.gestionesClientes;

import controladores.ControladorClientes;
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
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class BorrarCliente {

    private final Ventana ventanaBorrarCliente;
    private final ControladorClientes control = new ControladorClientes();

    private JLabel titulo;
    private JLabel etiqueta;
    private JTextField cajaTexto;
    private JButton botonBorrar;
    private JButton botonVolver;

    BorrarCliente() {
        ventanaBorrarCliente = new Ventana(450, 500);
        iniciarComponentes();
        asignarAcciones();
        ventanaBorrarCliente.setVisible(true);
    }

    /**
     * Inicializa los componentes
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Borrar Cliente");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);
        crearEtiqueta();
        crearCajaDeTexto();
        botonBorrar = crearBoton("Borrar");
        botonVolver = crearBoton("Volver");
        agregarComponentes();
        ventanaBorrarCliente.revalidate();
        ventanaBorrarCliente.repaint();
    }

    /**
     * Configura la etiqueta que solicita al usuario el numero de cedula.
     */
    private void crearEtiqueta() {
        etiqueta = new JLabel("Ingrese el numero de cedula:");
        etiqueta.setFont(new Font("Monospaced", Font.BOLD, 20));
        etiqueta.setForeground(Color.RED);
        Dimension size = new Dimension(400, 30);
        etiqueta.setPreferredSize(size);
        etiqueta.setMaximumSize(size);
        etiqueta.setMinimumSize(size);
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Configura la caja de texto para ingresar la cedula
     */
    private void crearCajaDeTexto() {
        cajaTexto = new JTextField();
        Dimension sizeCaja = new Dimension(400, 30);
        cajaTexto.setPreferredSize(sizeCaja);
        cajaTexto.setMaximumSize(sizeCaja);
        cajaTexto.setMinimumSize(sizeCaja);
        cajaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Crea un boton personalizado con estilo
     *
     * @param texto
     * @return el boton configurado
     */
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(new Color(255, 165, 110));
        boton.setForeground(Color.BLACK);
        boton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));
        Dimension size = new Dimension(200, 50);
        boton.setPreferredSize(size);
        boton.setMaximumSize(size);
        boton.setMinimumSize(size);
        boton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        return boton;

    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaBorrarCliente.getPanelSuperior().add(titulo);
        agregarSeparador(30);
        ventanaBorrarCliente.getPanelCentral().add(etiqueta);
        agregarSeparador(20);
        ventanaBorrarCliente.getPanelCentral().add(cajaTexto);
        agregarSeparador(30);
        ventanaBorrarCliente.getPanelCentral().add(botonBorrar);
        agregarSeparador(30);
        ventanaBorrarCliente.getPanelCentral().add(botonVolver);
    }

    /**
     * metodo que asigna las acciones de cada boton
     */
    private void asignarAcciones() {
        botonBorrar.addActionListener(e -> {
            try {
                String textoIngresado = cajaTexto.getText();
                int nroDeCedula = Integer.parseInt(textoIngresado);
                control.borrarCliente(nroDeCedula);
                JOptionPane.showMessageDialog(null, "Se ha borrado con exito.");
                ventanaBorrarCliente.dispose();
                GestionClientes ventanaGestCliente = new GestionClientes();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        botonVolver.addActionListener(e -> {
            ventanaBorrarCliente.dispose();
            GestionClientes ventGestCliente = new GestionClientes();
        });
    }

    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaBorrarCliente.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

}
