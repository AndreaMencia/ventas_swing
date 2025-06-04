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

    public InformarVentasAnuladas(ControladorVentas control) {
        ventInforVentaAnulada = new Ventana(450, 550);
        iniciarComponente();
        ventInforVentaAnulada.setVisible(true);
        
    }
    /**
     * 
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

    private void cargarDatosAnulados(DefaultTableModel modelo) {
    File archivo = new File("ventas_anuladas.txt");
    if (!archivo.exists()) return;

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",", 5); 
            if (datos.length != 5) continue;

            int id = Integer.parseInt(datos[0]);
            int cedula = Integer.parseInt(datos[1]);
            LocalDate fecha = LocalDate.parse(datos[2]);
            int monto = Integer.parseInt(datos[3]);
            String productosTexto = datos[4];

            // Parsear productos
            StringBuilder productosFormateados = new StringBuilder();
            String[] items = productosTexto.split(";");
            for (String item : items) {
                String[] partes = item.split(":");
                if (partes.length != 2) continue;
                String nombre = partes[0];
                String cantidad = partes[1];
                productosFormateados.append(nombre)
                                    .append(" (")
                                    .append(cantidad)
                                    .append("), ");
            }

            // Eliminar la coma final si existe
            if (productosFormateados.length() > 0) {
                productosFormateados.setLength(productosFormateados.length() - 2);
            }

            modelo.addRow(new Object[]{id, cedula, productosFormateados.toString(), fecha, monto});
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al leer archivo de anulaciones: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void agregarComponente() {
        ventInforVentaAnulada.getPanelSuperior().add(titulo);
        ventInforVentaAnulada.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 10)));
        ventInforVentaAnulada.getPanelCentral().add(scrollPane);
        ventInforVentaAnulada.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 15)));
        ventInforVentaAnulada.getPanelCentral().add(botonVolver);
    }

    private void asignarAccion() {
        botonVolver.addActionListener(e -> {
            ventInforVentaAnulada.dispose();
            GestionVentas ventGestVenta = new GestionVentas();
        });
    }
}