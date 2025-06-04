package ventasgui;

import ventasgui.gestionesClientes.GestionClientes;
import ventasgui.gestionesVentas.GestionVentas;
import ventasgui.gestionesProductos.GestionProductos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class MenuPrincipal {

    private final Ventana ventanaMenuPrincipal;
    private JLabel etiqueta;
    private JButton btnGestProductos;
    private JButton btnGestClientes;
    private JButton btnGestVentas;
    private JButton btnSalir;

    /**
     * Contructor que inicializa todos los elemntos de la clase
     */
    public MenuPrincipal() {
        ventanaMenuPrincipal = new Ventana(450, 450);
        iniciarComponentes();
        ventanaMenuPrincipal.setVisible(true);
    }

    /**
     * Metodo que inicializa los componentes de la ventana
     */
    private void iniciarComponentes() {
        etiqueta = new JLabel("Tienda Virtual");
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 36));
        etiqueta.setForeground(Color.BLACK);

        btnGestProductos = crearBoton("Gestión de Productos");
        btnGestClientes = crearBoton("Gestión de Clientes");
        btnGestVentas = crearBoton("Gestión de Ventas");
        btnSalir = crearBoton("Salir");

        asignarAcciones();
        agregarComponentes();

        ventanaMenuPrincipal.revalidate();
        ventanaMenuPrincipal.repaint();
    }

    /**
     * Metodo que crea un botón generico y recibe como parámetro el texto que
     * debe aparecer sobre el boton
     *
     * @param texto texto que debe aparecer sobre el boton
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
     * Metodo que establece las acciones que realizara cada boton al ser
     * presionado
     */
    private void asignarAcciones() {
        btnGestProductos.addActionListener(e -> {
            ventanaMenuPrincipal.dispose();
            GestionProductos ventanaGestProd = new GestionProductos();
        });

        btnGestClientes.addActionListener(e -> {
            ventanaMenuPrincipal.dispose();
            GestionClientes ventanaGestClien = new GestionClientes();
        });

        btnGestVentas.addActionListener(e -> {
            ventanaMenuPrincipal.dispose();
            GestionVentas ventanaGestVent = new GestionVentas();
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }

    /**
     * Metodo que agrega una separacion entre componentes y recibe como
     * parametro la cantidad de espacio que se quiere agregar
     *
     * @param alto tamaño del espacio entre componentes
     */
    private void agregarSeparador(int alto) {
        ventanaMenuPrincipal.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * Metodo que agrega un boton a la ventana
     *
     * @param boton boton que se agregara
     */
    private void agregarBoton(JButton boton) {
        ventanaMenuPrincipal.getPanelCentral().add(boton);
    }

    /**
     * Metodo que agrega todos los componentes a la ventana debidamente
     * separados
     */
    private void agregarComponentes() {
        ventanaMenuPrincipal.getPanelSuperior().add(etiqueta);
        agregarSeparador(30);
        agregarBoton(btnGestProductos);
        agregarSeparador(20);
        agregarBoton(btnGestClientes);
        agregarSeparador(20);
        agregarBoton(btnGestVentas);
        agregarSeparador(20);
        agregarBoton(btnSalir);
        agregarSeparador(20);
    }
}
