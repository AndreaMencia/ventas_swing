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
 * Clase que proporciona una interfaz grafica para editar la informacion de un
 * cliente
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class EditarCliente {

    private final ControladorClientes control = new ControladorClientes();
    private final Ventana ventanaEditarCliente;

    private JLabel titulo;
    private JLabel etiqueta;
    private JTextField cajaTexto;
    private JButton botonNombre;
    private JButton botonEdad;
    private JButton botonVolver;

    /**
     * Constructor que inicializa la ventana, configura los componentes graficas
     * y asigna las acciones a los botones
     */
    public EditarCliente() {
        ventanaEditarCliente = new Ventana(450, 500);
        iniciarComponentes();
        asignarAcciones();
        ventanaEditarCliente.setVisible(true);
    }

    /**
     * Inicializa todos los componentes graficas
     */
    private void iniciarComponentes() {
        titulo = new JLabel("Editar Cliente");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);

        botonNombre = crearBoton("Nombre");
        botonEdad = crearBoton("Edad");
        botonVolver = crearBoton("Volver");

        crearEtiqueta();
        crearCajaDeTexto();

        agregarComponentes();

        ventanaEditarCliente.revalidate();
        ventanaEditarCliente.repaint();

    }

    /**
     * Crea y configuara la etiqueta
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
     * Crea y configura la caja de texto para ingresar el numero de cedula.
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
     * Agrega los componentes creados al pael central de la ventana.
     */
    private void agregarComponentes() {
        ventanaEditarCliente.getPanelSuperior().add(titulo);
        agregarSeparador(20);
        ventanaEditarCliente.getPanelCentral().add(etiqueta);
        agregarSeparador(10);
        ventanaEditarCliente.getPanelCentral().add(cajaTexto);
        agregarSeparador(20);
        agregarBoton(botonNombre);
        agregarSeparador(20);
        agregarBoton(botonEdad);
        agregarSeparador(20);
        agregarBoton(botonVolver);

    }

    /**
     * Crea un boton.
     *
     * @param texto
     * @return
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
     * Asigna las acciones que debe realizar cada boton.
     */
    private void asignarAcciones() {
        // Cambiar nombre
        botonNombre.addActionListener(e -> {
            try {
                String textoIngresado = cajaTexto.getText();
                if (textoIngresado == null || textoIngresado.isBlank()) {
                    return;
                }

                int numeroDeCedula = Integer.parseInt(textoIngresado);
                control.buscarCliente(numeroDeCedula);

                String input = JOptionPane.showInputDialog(null, "Nuevo nombre:");
                if (input == null || input.isBlank()) {
                    return;
                }

                String nuevoNombre = input.trim();
                control.editarNombre(numeroDeCedula, nuevoNombre);
                JOptionPane.showMessageDialog(null, "Nombre actualizado correctamente.");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cambiar edad
        botonEdad.addActionListener(e -> {
            try {
                String textoIngresado = cajaTexto.getText();
                if (textoIngresado == null || textoIngresado.isBlank()) {
                    return;
                }

                int numeroDeCedula = Integer.parseInt(textoIngresado);
                control.buscarCliente(numeroDeCedula);

                String input = JOptionPane.showInputDialog(null, "Nueva edad:");
                if (input == null || input.isBlank()) {
                    return;
                }

                int nuevaEdad = Integer.parseInt(input.trim());
                control.editarEdad(numeroDeCedula, nuevaEdad);
                JOptionPane.showMessageDialog(null, "Edad actualizada correctamente.");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        //Volver al menu anterior
        botonVolver.addActionListener(e -> {
            ventanaEditarCliente.dispose();
            GestionClientes ventanaGestCliente = new GestionClientes();
        });
    }

    /**
     * Agrega un espacio vertical entre componentes.
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaEditarCliente.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * Agrega un boton al panel central
     *
     * @param boton
     */
    private void agregarBoton(JButton boton) {
        ventanaEditarCliente.getPanelCentral().add(boton);
    }

}
