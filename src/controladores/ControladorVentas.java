package controladores;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import repositorios.RepoClientes;
import repositorios.RepoProductos;
import repositorios.RepoVentas;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class ControladorVentas {

    private static final String YA_EXISTE_VENTA = "Ya existe venta con ese código";
    private static final String NO_EXISTE_VENTA = "No existe venta con ese código";
    private static final String NO_EXISTE_PRODUCTO = "Producto no encontrado";
    private static final String NO_EXISTE_CLIENTE = "Cliente no encontrado";
    private static final String CODIGO_INVALIDO = "El codigo no puede ser cero ni negativo";
    private static final String CANTIDAD_INVALIDA = "La cantidad de productos no puede ser cero ni negativa";
    private static final String STOCK_INSUFICIENTE = "Cantidad en stock insuficiente para realizar la venta";
    private final RepoClientes repositorioClientes = RepoClientes.getInstancia();
    private final RepoProductos repositorioProductos = RepoProductos.getInstancia();
    private final RepoVentas repositorioVentas = RepoVentas.getInstancia();

    /**
     * Metodo que crea un objeto Venta recibiendo como parametro los atributos
     * del objeto y lo guarda en el repositorio
     *
     * @param id
     * @param cedula
     * @param productos
     * @param monto
     */
    public void crearVenta(int id, int cedula, Map<Producto, Integer> productos, int monto) {
        LocalDate fecha = LocalDate.now();
        Cliente cliente = repositorioClientes.obtenerClientePorCI(cedula);
        Venta nuevaVenta = new Venta(id, fecha, cliente, productos, monto);

        if (repositorioVentas.getVentas().containsKey(id)) {
            throw new IllegalArgumentException(YA_EXISTE_VENTA);
        }
        repositorioVentas.guardarVenta(nuevaVenta);
    }

    /**
     * Metodo que verifica la existencia de un cliente recibiendo como parametro
     * su numero de cedula
     *
     * @param ci
     */
    public void verificarCliente(int ci) {
        Cliente cliente = repositorioClientes.obtenerClientePorCI(ci);
        if (cliente == null) {
            throw new IllegalArgumentException(NO_EXISTE_CLIENTE);
        }
    }

    /**
     * metodo que verifica si un producto existe
     *
     * @param cod
     */
    public void verificarProducto(int cod) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(cod);
        if (producto == null) {
            throw new IllegalArgumentException(NO_EXISTE_PRODUCTO);
        }
    }

    public void verificarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(CANTIDAD_INVALIDA);
        }
    }

    /**
     * metodo que verifica que un codigo sea un numero positivo mayor a cero
     *
     * @param codigo
     */
    public void verificarCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException(CODIGO_INVALIDO);
        }
    }

    /**
     * metodo que verifica que la lista de productos seleccionados no este vacia
     *
     * @param productosSeleccionados
     */
    public void verificarProductos(Map<Producto, Integer> productosSeleccionados) {
        if (productosSeleccionados.isEmpty()) {
            throw new IllegalArgumentException("Debe agregar productos.");
        }
    }

    /**
     * Metodo que agrega productos a un mapa
     *
     * @param mapaVacio
     * @param idProd
     * @param cantidad
     */
    public void agregarProducto(Map<Producto, Integer> mapaVacio, int idProd, int cantidad) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(idProd);
        if (producto == null) {
            throw new IllegalArgumentException(NO_EXISTE_PRODUCTO);
        }
        mapaVacio.put(producto, cantidad);
    }

    /**
     * Metodo que borra una venta del repositorio recibiendo como parametro su
     * codigo identificador
     *
     * @param id
     */
    public void borrarVenta(int id) {
        Venta venta = repositorioVentas.obtenerVentaPorId(id);
        if (venta == null) {
            throw new IllegalArgumentException(NO_EXISTE_VENTA);
        }
        repositorioVentas.borrarVentaPorId(id);
    }

    /**
     * Metodo que retorna el mapa que almacena todas las ventas creadas
     *
     * @return mapa de clave Integer (codigo de venta) y valor Venta (venta)
     */
    public Map<Integer, Venta> getMapaVentas() {
        Map<Integer, Venta> lista = repositorioVentas.getVentas();
        return lista;
    }

    /**
     * Verifica si una venta existe por su ID.
     *
     * @param id ID de la venta.
     * @return true si la venta existe, false en caso contrario.
     */
    public boolean verificarVenta(int id) {
        return repositorioVentas.verificarExistencia(id);
    }

    public List<Venta> ventasAnuladas() {
        return repositorioVentas.listarVentasAnuladas(repositorioClientes);
    }

    /**
     * Metodo que recibe como parametros el identificador de un producto y la
     * cantidad que se vende de ese producto para verificar que la cantidad no
     * sea mayor a la disponible en stock, y si no lo es llama al metodo
     * restarStock()
     *
     * @param idProd
     * @param cantidad
     */
    public void verificarStock(int idProd, int cantidad) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(idProd);
        if (producto != null) {
            if (cantidad <= producto.getStock()) {
                restarStock(idProd, cantidad);
                return;
            }
            throw new IllegalArgumentException(STOCK_INSUFICIENTE);
        } else {
            throw new IllegalArgumentException(NO_EXISTE_PRODUCTO);
        }
    }

    /**
     * Metodo que recibe como parametros el identificador de un producto y la
     * cantidad que se vende de ese producto para restar esa cantidad de la
     * cantidad en stock del producto
     *
     * @param idProd
     * @param cantidad
     */
    private void restarStock(int idProd, int cantidad) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(idProd);
        int nuevoStock = producto.getStock() - cantidad;
        producto.setStock(nuevoStock);
    }

}
