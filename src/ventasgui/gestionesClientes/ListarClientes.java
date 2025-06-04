package ventasgui.gestionesClientes;
import controladores.ControladorClientes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Cliente;
import ventasgui.Ventana;

/**
 *Vetana grafica que permite vizualizar todos los clientes registrados en forma de tabla.
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class ListarClientes extends JFrame {
    //instancia de ventana personalizada
    private final Ventana ventanaLista;
    private final ControladorClientes control;
    //Columnas de la tabla que muestra la informacion
    private final String[] columnas = {"Nro Cedula", "Nombre", "Edad"};
    //Componentes graficos
    private JLabel titulo;
    private JTable tabla;
    private JScrollPane scrollPane;
    private JButton btnVolver;
    private JComboBox<String> comboOrden;
    private TableRowSorter<TableModel> sorter; 
   /**
    * Inicializa los componentes graficos y muestra la ventana
    * @param control 
    */
    public ListarClientes(ControladorClientes control) {
        this.control = control;
        ventanaLista = new Ventana(450, 550); 
        iniciarComponente();
        ventanaLista.setVisible(true);
    }
    /**
     * Crea y configura cada componente de la interfaz grafica 
     */
    private void iniciarComponente() {
        titulo = new JLabel("Lista de Clientes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);
        btnVolver = crearBoton("Volver");
        comboOrden = new JComboBox<>(new String[]{"Ordenar por nombre", "Ordenar por edad"});
        comboOrden.setMaximumSize(new Dimension(200, 30));
        comboOrden.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboOrden.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        tabla();
        agregarComponente();
        asignarAccion();
    }
    /**
     * Define el modelo de la tabla y llena con datos del controlador
     * */
    private void tabla() {
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    Map<Integer, Cliente> mapaClientes = control.getMapaClientes();

    // Convertir a lista y ordenar con Comparator (tipado explícito)
    List<Map.Entry<Integer, Cliente>> listaClientes = new ArrayList<>(mapaClientes.entrySet());
    listaClientes.sort(Comparator.comparing((Map.Entry<Integer, Cliente> entry) -> entry.getValue().getNombre()));

    // Agregar al modelo
    for (Map.Entry<Integer, Cliente> entry : listaClientes) {
        Cliente cliente = entry.getValue();
        Object[] fila = {entry.getKey(), cliente.getNombre(), cliente.getEdad()};
        modelo.addRow(fila);
    }

    tabla = new JTable(modelo);

    // Agregar sorter e integrarlo con la tabla
    sorter = new TableRowSorter<>(modelo);
    tabla.setRowSorter(sorter);

    tabla.setFillsViewportHeight(true);
    tabla.setRowHeight(30);
    tabla.setFont(new Font("Monospaced", Font.PLAIN, 14));
    tabla.setBackground(new Color(255, 255, 199));
    tabla.setForeground(Color.BLACK);
    tabla.setGridColor(Color.LIGHT_GRAY);

    JTableHeader encabezado = tabla.getTableHeader();
    encabezado.setBackground(new Color(153, 0, 0));
    encabezado.setForeground(Color.WHITE);
    encabezado.setFont(new Font("Monospaced", Font.BOLD, 16));
    ((DefaultTableCellRenderer) encabezado.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    scrollPane = new JScrollPane(tabla);
}
     /**
    * Crea y devuelve un botón estilizado con el texto proporcionado.
    * @param texto Texto a mostrar en el botón.
    * @return JButton con fuente personalizada.
    */
    private JButton crearBoton(String texto) { 
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(new Color(255, 165, 110));
        boton.setForeground(Color.BLACK);
        boton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));

        Dimension size = new Dimension(175, 40);
        boton.setPreferredSize(size);
        boton.setMaximumSize(size);
        boton.setMinimumSize(size);
        boton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        return boton;
    }
    /**
     * Agrega los componentes visuales al panel
     */
    private void agregarComponente() {
        ventanaLista.getPanelSuperior().add(titulo);
        ventanaLista.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 15)));
        ventanaLista.getPanelCentral().add(comboOrden);
        ventanaLista.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 10)));
        ventanaLista.getPanelCentral().add(scrollPane);
        ventanaLista.getPanelCentral().add(Box.createRigidArea(new Dimension(0, 15)));
        ventanaLista.getPanelCentral().add(btnVolver);
    }
    /**
     * Define acciones para el boton
     */
    private void asignarAccion() {
        btnVolver.addActionListener(e -> {
            ventanaLista.dispose();
            GestionClientes gc = new GestionClientes();
        });

        comboOrden.addActionListener(e -> {
            String seleccion = (String) comboOrden.getSelectedItem();
            if ("Ordenar por nombre".equals(seleccion)) {
                sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
            } else if ("Ordenar por edad".equals(seleccion)) {
                sorter.setSortKeys(List.of(new RowSorter.SortKey(2, SortOrder.ASCENDING))); 
            }
        });
    }

}