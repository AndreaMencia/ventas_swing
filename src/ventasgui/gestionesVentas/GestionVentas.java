package ventasgui.gestionesVentas;

import controladores.ControladorVentas;
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
public class GestionVentas {

    private final Ventana ventanaGestVentas;
    private final ControladorVentas control = new ControladorVentas();
    private JLabel etiqueta;
    private JButton botonCrearVenta;
    private JButton botonAnularVenta;
    private JButton botonListarVentas;
    private JButton botonVentasAnuladas;
    private JButton botonVolver;

    /**
     * contructor de la clase que crea la ventana y agrega todos los componentes
     * y acciones
     */
    public GestionVentas() {
        ventanaGestVentas = new Ventana(450, 500);
        iniciarComponentes();
        ventanaGestVentas.setVisible(true);
    }

    /**
     * metodo que inicializa los componentes
     */
    private void iniciarComponentes() {
        etiqueta = new JLabel("Gestión de Ventas");
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 36));
        etiqueta.setForeground(Color.BLACK);

        botonCrearVenta = crearBoton("Crear Venta");
        botonAnularVenta = crearBoton("Anular Venta");
        botonListarVentas = crearBoton("Listar ventas");
        botonVentasAnuladas = crearBoton("Listar ventas anuladas");
        botonVolver = crearBoton("Volver");

        agregarComponentes();
        asignarAcciones();

        ventanaGestVentas.revalidate();
        ventanaGestVentas.repaint();
    }

    /**
     * metodo que ordena y agrega los componentes a la ventana
     */
    private void agregarComponentes() {
        ventanaGestVentas.getPanelSuperior().add(etiqueta);
        agregarSeparador(30);
        agregarBoton(botonCrearVenta);
        agregarSeparador(20);
        agregarBoton(botonAnularVenta);
        agregarSeparador(20);
        agregarBoton(botonListarVentas);
        agregarSeparador(20);
        agregarBoton(botonVentasAnuladas);
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
        botonCrearVenta.addActionListener(e -> {
            ventanaGestVentas.dispose();
            CrearVenta cv = new CrearVenta();
        });

        botonAnularVenta.addActionListener(e -> {
            ventanaGestVentas.dispose();
            AnularVenta ventAnularVenta = new AnularVenta();
        });

        botonListarVentas.addActionListener(e -> {
            ventanaGestVentas.dispose();
            ListarVentas lv = new ListarVentas();
        });

        botonVentasAnuladas.addActionListener(e -> {
            ventanaGestVentas.dispose();
            InformarVentasAnuladas ventInforVentAnuladas = new InformarVentasAnuladas(control);
        });

        botonVolver.addActionListener(e -> {
            ventanaGestVentas.dispose();
            MenuPrincipal menu = new MenuPrincipal();
        });
    }

    /**
     * metodo que agrega una separacion entre componentes
     *
     * @param alto
     */
    private void agregarSeparador(int alto) {
        ventanaGestVentas.getPanelCentral().add(Box.createRigidArea(new Dimension(0, alto)));
    }

    /**
     * metodo que agrega el boton
     *
     * @param boton
     */
    private void agregarBoton(JButton boton) {
        ventanaGestVentas.getPanelCentral().add(boton);
    }
}
