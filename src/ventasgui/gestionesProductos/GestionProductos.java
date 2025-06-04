package ventasgui.gestionesProductos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import ventasgui.MenuPrincipal;
import ventasgui.Ventana;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class GestionProductos {

    private final Ventana ventanaGestProductos;
    private JLabel etiqueta;
    private JButton botonCrearProducto;
    private JButton botonEditarProducto;
    private JButton botonBorrarProducto;
    private JButton botonListarProductos;
    private JButton botonVolver;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     */
    public GestionProductos() {
        ventanaGestProductos = new Ventana(450, 500);
        iniciarComponentes();
        ventanaGestProductos.setVisible(true);
        asignarAcciones();
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        etiqueta = new JLabel("Gestión de Productos");
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 36));
        etiqueta.setForeground(Color.BLACK);

        botonCrearProducto = crearBoton("Crear Producto");
        botonEditarProducto = crearBoton("Editar Producto");
        botonBorrarProducto = crearBoton("Borrar Producto");
        botonListarProductos = crearBoton("Listar Productos");
        botonVolver = crearBoton("Volver");

        agregarComponentes();

        ventanaGestProductos.revalidate();
        ventanaGestProductos.repaint();

    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaGestProductos.getPanelSuperior().add(etiqueta);
        agregarSeparador(30);
        agregarBoton(botonCrearProducto);
        agregarSeparador(20);
        agregarBoton(botonEditarProducto);
        agregarSeparador(20);
        agregarBoton(botonBorrarProducto);
        agregarSeparador(20);
        agregarBoton(botonListarProductos);
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
        botonCrearProducto.addActionListener(e -> {
            ventanaGestProductos.dispose();
            CrearProducto cp = new CrearProducto();
        });

        botonEditarProducto.addActionListener(e -> {
            ventanaGestProductos.dispose();
            EditarProducto ep = new EditarProducto();
        });

        botonBorrarProducto.addActionListener(e -> {
            ventanaGestProductos.dispose();
            BorrarProducto bp = new BorrarProducto();
        });

        botonListarProductos.addActionListener(e -> {
            ventanaGestProductos.dispose();
            ListarProductos lp = new ListarProductos(); //
        });

        botonVolver.addActionListener(e -> {
            ventanaGestProductos.dispose();
            MenuPrincipal mp = new MenuPrincipal();
        });
    }

    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaGestProductos.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     *
     * @param boton
     */
    private void agregarBoton(JButton boton) {
        ventanaGestProductos.getPanelCentral().add(boton);
    }
}
