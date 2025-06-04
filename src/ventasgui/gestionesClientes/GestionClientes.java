package ventasgui.gestionesClientes;

import controladores.ControladorClientes;
import ventasgui.MenuPrincipal;
import ventasgui.Ventana;
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
public class GestionClientes {

    private final Ventana ventanaGestClientes;
    private final ControladorClientes control = new ControladorClientes();
    private JLabel etiqueta;
    private JButton botonCrearCliente;
    private JButton botonEditarCliente;
    private JButton botonBorrarCliente;
    private JButton botonListarClientes;
    private JButton botonVolver;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     */
    public GestionClientes() {
        ventanaGestClientes = new Ventana(450, 500);
        iniciarComponentes();
        asignarAcciones();
        ventanaGestClientes.setVisible(true);
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        etiqueta = new JLabel("Gestión de Clientes");
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 36));
        etiqueta.setForeground(Color.BLACK);

        botonCrearCliente = crearBoton("Crear Cliente");
        botonEditarCliente = crearBoton("Editar Cliente");
        botonBorrarCliente = crearBoton("Borrar Cliente");
        botonListarClientes = crearBoton("Listar Clientes");
        botonVolver = crearBoton("Volver");

        agregarComponentes();

        ventanaGestClientes.revalidate();
        ventanaGestClientes.repaint();

    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaGestClientes.getPanelSuperior().add(etiqueta);
        agregarSeparador(30);
        agregarBoton(botonCrearCliente);
        agregarSeparador(20);
        agregarBoton(botonEditarCliente);
        agregarSeparador(20);
        agregarBoton(botonBorrarCliente);
        agregarSeparador(20);
        agregarBoton(botonListarClientes);
        agregarSeparador(20);
        agregarBoton(botonVolver);
        agregarSeparador(20);
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

        Dimension size = new Dimension(200, 50);
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
        botonCrearCliente.addActionListener(e -> {
            ventanaGestClientes.dispose();
            CrearCliente ventanaCrearCliente = new CrearCliente();
        });

        botonEditarCliente.addActionListener(e -> {
            ventanaGestClientes.dispose();
            EditarCliente ventanaEditarCliente = new EditarCliente();
        });

        botonBorrarCliente.addActionListener(e -> {
            ventanaGestClientes.dispose();
            BorrarCliente ventanaBorrarCliente = new BorrarCliente();
        });

        botonListarClientes.addActionListener(e -> {
            ventanaGestClientes.dispose();
            ListarClientes ventanaLista = new ListarClientes(control);

        });

        botonVolver.addActionListener(e -> {
            ventanaGestClientes.dispose();
            MenuPrincipal menu = new MenuPrincipal();
        });
    }

    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaGestClientes.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     *
     * @param boton
     */
    private void agregarBoton(JButton boton) {
        ventanaGestClientes.getPanelCentral().add(boton);
    }

}
