package ventasgui.gestionesVentas;

import controladores.ControladorVentas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import ventasgui.Ventana;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class InformarVentasAnuladas {

    //Ventanas personalizadas
    private final Ventana ventInforVentaAnulada;
    private final String[] columnas = {"ID", "Cédula Cliente", "Fecha", "Monto"};
    //Componentes graficas
    private JLabel titulo;
    private JTable tabla;
    private JScrollPane scrollPane;
    private JButton botonVolver;

    /**
     * Constructor de la clase Informar Ventas Anuladas. Inicializa las ventanas
     * y sus componentes
     *
     * @param control
     */
    public InformarVentasAnuladas(ControladorVentas control) {
        ventInforVentaAnulada = new Ventana(450, 550);
        iniciarComponente();
        ventInforVentaAnulada.setVisible(true);

    }

    /**
     * Agrega todos los componentes visuales a las ventanas
     */
    private void iniciarComponente() {
        titulo = new JLabel("Informe de Ventas Anuladas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.BLACK);

        botonVolver = new JButton("Volver");
        Dimension size = new Dimension(200, 50);
        botonVolver.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonVolver.setBackground(new Color(255, 165, 110));
        botonVolver.setForeground(Color.BLACK);
        botonVolver.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonVolver.setPreferredSize(size);
        botonVolver.setAlignmentX(JButton.CENTER_ALIGNMENT);

        tabla();
        agregarComponente();
        asignarAccion();
    }

    /**
     * Crea una tabla donde se mostraran todos las ventas anuladas
     */
    private void tabla() {
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);

        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setBackground(new Color(255, 255, 199));
        tabla.setForeground(Color.BLACK);
        tabla.setGridColor(Color.LIGHT_GRAY);

        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setBackground(new Color(153, 0, 0));
        encabezado.setForeground(Color.WHITE);
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        ((DefaultTableCellRenderer) encabezado.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        scrollPane = new JScrollPane(tabla);

        cargarDatosAnulados(modelo);
    }

    /**
     * Se encarga de leer las ventas anuladas cargados en el archivo
     * "ventas_anuladas.txt" y muestra los datos en la tabla
     *
     * @param modelo
     */
    private void cargarDatosAnulados(DefaultTableModel modelo) {
        File archivo = new File("ventas_anuladas.txt");
        //Si el archivo esta vacio termina el programa
        if (!archivo.exists()) {
            return;
        }

        try (
                //Se intenta abrir el archivo para lectura
                BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            //Lee linea por linea los contenidos del archivo
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 5);
                if (datos.length < 4) {
                    continue;
                }
                //Se convierte cada campo a un entero que representa ID, cedula, fecha y monto
                int id = Integer.parseInt(datos[0]);
                int cedula = Integer.parseInt(datos[1]);
                LocalDate fecha = LocalDate.parse(datos[2]);
                int monto = Integer.parseInt(datos[3]);
                //Se agrega una nueva fila al modelo de la tabla con los datos leidos
                modelo.addRow(new Object[]{id, cedula, fecha, monto});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo de anulaciones: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Agrega todos los componentes en la ventana
     */
    private void agregarComponente() {
        ventInforVentaAnulada.getPanelSuperior().add(titulo);
        ventInforVentaAnulada.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 10)));
        ventInforVentaAnulada.getPanelCentral().add(scrollPane);
        ventInforVentaAnulada.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 15)));
        ventInforVentaAnulada.getPanelCentral().add(botonVolver);
    }

    /**
     * Metodo que aigna acciones a cada boton
     */
    private void asignarAccion() {
        botonVolver.addActionListener(e -> {
            ventInforVentaAnulada.dispose();
            GestionVentas ventGestVenta = new GestionVentas();
        });
    }
}
