package repositorios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import modelo.Cliente;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class RepoClientes {

    private static final String ARCHIVO_CLIENTES = "clientes.txt";
    private final Map<Integer, Cliente> clientes = new HashMap<>();
    private static final RepoClientes instancia = new RepoClientes();

    /**
     * contructor que recibe los datos guardados en el archivo
     */
    public RepoClientes() {
        cargarDesdeArchivo();
    }

    /**
     * Metodo que retorna el mapa clientes
     *
     * @return mapa de clave Integer y valor Cliente
     */
    public Map<Integer, Cliente> getClientes() {
        return clientes;
    }

    /**
     * metodo que retorna una estancia de la clase
     *
     * @return instancia de clase
     */
    public static RepoClientes getInstancia() {
        return instancia;
    }

    /**
     * Metodo que guarda un cliente dentro del mapa clientes
     *
     * @param cliente
     */
    public void guardarCliente(Cliente cliente) {
        this.clientes.put(cliente.getNumeroCI(), cliente);
        guardarEnArchivo();
    }

    /**
     * Metodo que busca y retorna un cliente especifico del mapa recibiendo como
     * parametro la clave asociada a el
     *
     * @param numCI
     * @return valor Cliente asociado a la clave numCI
     */
    public Cliente obtenerClientePorCI(int numCI) {
        return clientes.get(numCI);
    }

    /**
     * Metodo que borra un cliente del mapa recibiendo como parametro la clave
     * asociada a el
     *
     * @param numCI
     */
    public void borrarClientePorCI(int numCI) {
        if (clientes.containsKey(numCI)) {
            clientes.remove(numCI);
            guardarEnArchivo();
        }
    }

    /**
     * metodo que guarda los datos en el archivo
     */
    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_CLIENTES))) {
            for (Cliente c : clientes.values()) {
                pw.println(c.getNumeroCI() + "," + c.getNombre() + "," + c.getEdad());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar clientes: " + e.getMessage());
        }
    }

    /**
     * metodo que carga los datos guardados en el arcgivo
     */
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO_CLIENTES);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 3) {
                    continue;
                }
                int ci = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                int edad = Integer.parseInt(datos[2]);
                clientes.put(ci, new Cliente(nombre, ci, edad));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar clientes: " + e.getMessage());
        }
    }

}
