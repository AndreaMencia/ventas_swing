package repositorios;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import modelo.Producto;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class RepoProductos {

    private static final String ARCHIVO = "productos.txt";
    Map<Integer, Producto> productos = new HashMap<>();
    private static final RepoProductos instancia = new RepoProductos();

    public RepoProductos() {
        cargarDesdeArchivo();
    }

    public static RepoProductos getInstancia() {
        return instancia;
    }

    /**
     * Metodo que retorna el mapa productos
     *
     * @return mapa de clave Integer y valor Producto
     */
    public Map<Integer, Producto> getProductos() {
        return productos;
    }

    /**
     * Metodo que guarda un producto dentro del mapa producto
     *
     * @param producto
     */
    public void guardarProducto(Producto producto) {
        productos.put(producto.getCodigo(), producto);
        guardarEnArchivo();
    }

    /**
     * Metodo que busca y retorna un producto especifico del mapa recibiendo
     * como parametro la clave asociada a el
     *
     * @param cod
     * @return Producto
     */
    public Producto obtenerProductoPorCodigo(int cod) {
        return productos.get(cod);
    }

    public Producto obtenerProductoPorNombre(String nombre) {
        for (Producto p : productos.values()) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo que borra un producto del mapa recibiendo como parametro la clave
     * asociada a el
     *
     * @param cod
     */
    public void borrarProductoPorCodigo(int cod) {
        if (productos.containsKey(cod)) {
            productos.remove(cod);
        }
        guardarEnArchivo();
    }

    private void guardarEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : productos.values()) {
                writer.println(p.getCodigo() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
        }
    }

    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    int precio = Integer.parseInt(partes[2]);
                    int stock = Integer.parseInt(partes[3]);

                    Producto producto = new Producto(nombre, codigo, precio, stock);
                    productos.put(codigo, producto);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
    }

}
