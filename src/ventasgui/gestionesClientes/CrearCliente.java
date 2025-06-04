package ventasgui.gestionesClientes;

import controladores.ControladorClientes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ventasgui.Ventana;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class CrearCliente extends JFrame {

    private final ControladorClientes control = new ControladorClientes();
    private final Ventana ventanaCrearCliente;
    private JLabel titulo;
    private JLabel etiqueta1;
    private JLabel etiqueta2;
    private JLabel etiqueta3;
    private JTextField cajaTexto1;
    private JTextField cajaTexto2;
    private JTextField cajaTexto3;
    private JButton botonGuardar;
    private JButton botonVolver;

    /**
     * contructor de la clase que crea la ventana y agrega todos los
     * componentes y acciones
     */
    public CrearCliente() {
        ventanaCrearCliente = new Ventana(450, 500);
        iniciarComponentes();
        asignarAcciones();
        ventanaCrearCliente.setVisible(true);
    }

    /**
     * Inicializa y configura todos los componentes graficas de la ventana
     */
    private void iniciarComponentes() {
        //Configuracion del titulo
        titulo = new JLabel("Crear Cliente");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);
        //Etiquetas
        etiqueta1 = crearEtiqueta("Nombre y Apellido");
        etiqueta2 = crearEtiqueta("Numero de Cedula");
        etiqueta3 = crearEtiqueta("Edad");
        //Cajas de texto
        cajaTexto1 = crearCajaDeTexto();
        cajaTexto2 = crearCajaDeTexto();
        cajaTexto3 = crearCajaDeTexto();
        botonGuardar = crearBoton("Gurdar");
        botonVolver = crearBoton("Volver");
        agregarComponentes();
        ventanaCrearCliente.revalidate();
        ventanaCrearCliente.repaint();
    }
 
    /**
     * agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaCrearCliente.getPanelSuperior().add(titulo);
        agregarSeparador(30);
        agregarEtiqueta(etiqueta1);
        agregarSeparador(10);
        agregarCajaDeTexto(cajaTexto1);

        agregarSeparador(10);
        agregarEtiqueta(etiqueta2);
        agregarSeparador(10);
        agregarCajaDeTexto(cajaTexto2);

        agregarSeparador(10);
        agregarEtiqueta(etiqueta3);
        agregarSeparador(10);
        agregarCajaDeTexto(cajaTexto3);
        agregarSeparador(10);
        agregarBoton(botonGuardar);
        agregarSeparador(10);
        agregarBoton(botonVolver);
    }

    /**
     * crea un etiqueta personalizada
     * 
     * @param text
     * @return 
     */
    private JLabel crearEtiqueta(String text) {
        JLabel etiqueta = new JLabel(text);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 20));
        etiqueta.setForeground(Color.RED);
        Dimension size = new Dimension(300, 30);
        etiqueta.setPreferredSize(size);
        etiqueta.setMaximumSize(size);
        etiqueta.setMinimumSize(size);
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        return etiqueta;
    }

    /**
     * crea una caja de texto personalizada
     * 
     * @return 
     */
    private JTextField crearCajaDeTexto() {
        JTextField cajaDeTexto = new JTextField();
        Dimension size = new Dimension(400, 30);
        cajaDeTexto.setPreferredSize(size);
        cajaDeTexto.setMaximumSize(size);
        cajaDeTexto.setMinimumSize(size);
        cajaDeTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        return cajaDeTexto;
    }

    /**
     * crea un boton personalizado
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
     * asigna las acciones de los botones
     */
    private void asignarAcciones() {
        botonGuardar.addActionListener(e -> {
            try {
                String nombre = cajaTexto1.getText();
                int nroCedula = Integer.parseInt(cajaTexto2.getText());
                int edad = Integer.parseInt(cajaTexto3.getText());
                control.verificarNombre(nombre);
                control.crearClientes(nombre, nroCedula, edad);
                JOptionPane.showMessageDialog(null, "Cliente guardado correctamente");
                ventanaCrearCliente.dispose();
                GestionClientes ventanaGestCliente = new GestionClientes();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Datos mal ingresados.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        botonVolver.addActionListener(e -> {
            ventanaCrearCliente.dispose();
            GestionClientes ventanaGestCliente = new GestionClientes();
        });
    }

    /**
     * metodo que agrega una separacion entre componentes
     * @param alto 
     */
    private void agregarSeparador(int alto) {
        ventanaCrearCliente.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     * 
     * @param boton 
     */
    private void agregarBoton(JButton boton) {
        ventanaCrearCliente.getPanelCentral().add(boton);
    }

    /**
     * metodo que agrega la etiqueta
     * 
     * @param etiqueta 
     */
    private void agregarEtiqueta(JLabel etiqueta) {
        ventanaCrearCliente.getPanelCentral().add(etiqueta);
    }

    /**
     * metodo que agrega la caja de texto
     * 
     * @param cajaDeTexto 
     */
    private void agregarCajaDeTexto(JTextField cajaDeTexto) {
        ventanaCrearCliente.getPanelCentral().add(cajaDeTexto);
    }
}
