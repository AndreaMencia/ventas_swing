package repositorios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class RepoVentas {

    private static final String ARCHIVO_VENTAS = "ventas.txt";
    private static final String ARCHIVO_VENTAS_ANULADAS = "ventas_anuladas.txt";
    Map<Integer, Venta> ventas = new HashMap<>();
    private static final RepoVentas instancia = new RepoVentas();

    /**
     * Metodo que retorna el mapa ventas
     *
     * @return mapa de clave Integer (codigo de venta) y valor Venta (venta)
     */
    public Map<Integer, Venta> getVentas() {
        cargarDesdeArchivo(ARCHIVO_VENTAS);
        return ventas;
    }

    /**
     * contructor que recibe los datos guardados en el archivo
     */
    public RepoVentas() {
        cargarDesdeArchivo(ARCHIVO_VENTAS);
        cargarDesdeArchivo(ARCHIVO_VENTAS_ANULADAS);
    }

    /**
     * metodo que retorna una estancia de la clase
     *
     * @return instancia de clase
     */
    public static RepoVentas getInstancia() {
        return instancia;
    }

    /**
     * Metodo que guarda una venta dentro del mapa ventas
     *
     * @param venta
     */
    public void guardarVenta(Venta venta) {
        ventas.put(venta.getId(), venta);
        guardarEnArchivo(ARCHIVO_VENTAS);
    }

    /**
     * Verifica si una venta existe en el repositorio dado su ID.
     *
     * @param id El identificador de la venta.
     * @return true si la venta existe, false en caso contrario.
     */
    public boolean verificarExistencia(int id) {
        return ventas.containsKey(id);
    }

    /**
     * Metodo que busca y retorna una venta especifica del mapa recibiendo como
     * parametro la clave asociada a ella
     *
     * @param id
     * @return Venta
     */
    public Venta obtenerVentaPorId(int id) {
        return ventas.get(id);
    }

    /**
     * Metodo que borra una venta del mapa recibiendo como parametro la clave
     * asociada a ella
     *
     * @param id
     */
    public void borrarVentaPorId(int id) {
        if (ventas.containsKey(id)) {
            registrarVentaAnulada(ventas.get(id));
            ventas.remove(id);
            guardarEnArchivo(ARCHIVO_VENTAS);
        }
    }

    /**
     * Registra una venta anulada en el archivo "ventas_anuladas.txt".
     *
     * @param venta
     */
    private void registrarVentaAnulada(Venta venta) {
        try (
                PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_VENTAS_ANULADAS, true))) {
            // Se escribe una línea en el archivo con los datos de la venta anulada,
            pw.println(venta.getId() + ","
                    + venta.getCliente().getNumeroCI() + ","
                    + venta.getFechaDeCompra() + ","
                    + venta.getMonto());
        } catch (IOException e) {
            // Si ocurre un error al escribir en el archivo, se muestra un mensaje de error en consola.
            System.err.println("Error al registrar venta anulada: " + e.getMessage());
        }
    }

    /**
     * Lista todas las ventas anuladas que se encuentran registrados en el arch
     * "ventas_anuladas.txt".
     *
     * @param repoClientes
     * @return lista de ventas anuladas
     */
    public List<Venta> listarVentasAnuladas(RepoClientes repoClientes) {
        List<Venta> anuladas = new ArrayList<>();
        File archivo = new File(ARCHIVO_VENTAS_ANULADAS);
        //Si el archivo no existe, se retorna la lista vacia
        if (!archivo.exists()) {
            return anuladas;
        }
        //Se intenta abrir el archivo 
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            //Se lee linea por linea el contenido del archivo
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 4);
                if (datos.length != 4) {
                    continue;
                }
                //Se intenta convertir los campos a sus respetivos tipos
                int id = Integer.parseInt(datos[0]);
                int ciCliente = Integer.parseInt(datos[1]);
                LocalDate fecha = LocalDate.parse(datos[2]);
                int monto = Integer.parseInt(datos[3]);

                Cliente cliente = repoClientes.obtenerClientePorCI(ciCliente);
                if (cliente == null) {
                    continue;
                }

                Map<Producto, Integer> productos = new HashMap<>();
                Venta venta = new Venta(id, fecha, cliente, productos, monto);
                //Se agrega la ventana anulada a la lista
                anuladas.add(venta);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer ventas anuladas: " + e.getMessage());
        }
        return anuladas;
    }

    /**
     * metodo que guarda los datos en el archivo
     */
    private void guardarEnArchivo(String archivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Venta v : ventas.values()) {
                StringBuilder productosTexto = new StringBuilder();
                for (Map.Entry<Producto, Integer> entry : v.getProductos().entrySet()) {
                    productosTexto.append(entry.getKey().getNombre())
                            .append(":")
                            .append(entry.getValue())
                            .append(";");
                }
                if (productosTexto.length() > 0) {
                    productosTexto.setLength(productosTexto.length() - 1); // quitar último ;
                }

                pw.println(v.getId() + ","
                        + v.getCliente().getNumeroCI() + ","
                        + v.getFechaDeCompra() + ","
                        + v.getMonto() + ","
                        + productosTexto.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar ventas: " + e.getMessage());
        }
    }

    /**
     * metodo que carga los datos guardados en el arcgivo
     */
    private void cargarDesdeArchivo(String archivo) {
        ventas.clear();
        File arch = new File(archivo);
        if (!arch.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arch))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 5); // ahora esperamos 5 columnas
                if (datos.length < 4) {
                    continue;
                }

                int id = Integer.parseInt(datos[0]);
                int ciCliente = Integer.parseInt(datos[1]);
                LocalDate fecha = LocalDate.parse(datos[2]);
                int monto = Integer.parseInt(datos[3]);

                Cliente cliente = RepoClientes.getInstancia().obtenerClientePorCI(ciCliente);
                if (cliente == null) {
                    continue;
                }

                Map<Producto, Integer> productos = new HashMap<>();
                if (datos.length == 4) {
                    String productosTexto = datos[3];
                    String[] items = productosTexto.split(";");
                    for (String item : items) {
                        String[] partes = item.split(":");
                        if (partes.length != 2) {
                            continue;
                        }

                        String nombre = partes[0];
                        int cantidad = Integer.parseInt(partes[1]);

                        Producto producto = RepoProductos.getInstancia().obtenerProductoPorNombre(nombre);
                        if (producto != null) {
                            productos.put(producto, cantidad);
                        }
                    }
                }

                Venta venta = new Venta(id, fecha, cliente, productos, monto);
                ventas.put(id, venta);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar ventas: " + e.getMessage());
        }
    }

}
