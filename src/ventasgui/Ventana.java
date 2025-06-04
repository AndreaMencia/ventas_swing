package ventasgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class Ventana extends JFrame {

    JPanel bordeSuperior;
    JPanel panelSuperior;
    JPanel panelCentral;

    /**
     * Constructor que recibe como parametros el ancho y alto de la ventana e
     * inicializa todos los elementos de la clase
     *
     * @param x ancho de la ventana
     * @param y alto de la ventana
     */
    public Ventana(int x, int y) {
        setTitle("Ventas GUI");
        this.setSize(x, y);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        iniciarPaneles();
    }

    /**
     * Metodo que inicializa los paneles en los que se agregaran los elementos o
     * componentes
     *
     */
    private void iniciarPaneles() {

        panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setPreferredSize(new Dimension(0, 80));
        panelSuperior.setBackground(new Color(255, 255, 199));
        panelSuperior.setBorder(BorderFactory.createMatteBorder(35, 0, 0, 0, new Color(153, 0, 0)));

        add(panelSuperior, BorderLayout.NORTH);

        panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(new Color(255, 255, 199));
        add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * Metodo que devuelve el panel superior de la ventana
     *
     * @return panel superior
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * Metodo que devuelve el panel central de la ventana
     *
     * @return panel central
     */
    public JPanel getPanelCentral() {
        return panelCentral;
    }

}
