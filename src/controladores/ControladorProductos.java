package controladores;

import java.util.Map;
import modelo.Producto;
import repositorios.RepoProductos;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class ControladorProductos {

    private static final String NO_EXISTE = "Producto no encontrado.";
    private static final String YA_EXISTE = "Ya existe un producto con ese código.";
    private static final String NOMBRE_INVALIDO = "Nombre invalido. Vuelva a intentar";
    private static final String CODIGO_INVALIDO = "El código no puede ser un número negativo ni cero. Vuelva a intentar";
    private static final String PRECIO_INVALIDO = "El precio no puede ser un número negativo ni cero. Vuelva a intentar";
    private static final String STOCK_INVALIDO = "El stock no puede ser un número negativo. Vuelva a intentar";
    private final RepoProductos repositorioProductos = RepoProductos.getInstancia();

    /**
     * Metodo que crea un objeto Producto recibiendo como parametro los
     * atributos del objeto y lo guarda en el repositorio
     *
     * @param nombre
     * @param codigo
     * @param precio
     * @param stock
     */
    public void crearProducto(String nombre, int codigo, int precio, int stock) {
        Producto nuevoProducto = new Producto(nombre, codigo, precio, stock);

        if (repositorioProductos.getProductos().containsKey(codigo)) {
            throw new IllegalArgumentException(YA_EXISTE);
        }
        if (codigo <= 0) {
            throw new IllegalArgumentException(CODIGO_INVALIDO);
        }
        if (precio <= 0) {
            throw new IllegalArgumentException(PRECIO_INVALIDO);
        }
        if (stock < 0) {
            throw new IllegalArgumentException(STOCK_INVALIDO);
        }

        repositorioProductos.guardarProducto(nuevoProducto);
    }

    /**
     * Metodo que verifica que la entrada del nombre del producto no este vacia
     *
     * @param nombre
     */
    public void verificarNombre(String nombre) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException(NOMBRE_INVALIDO);
        } else if (nombre.matches("\\d+")) {
            throw new NumberFormatException();
        }

    }

    /**
     * Metodo que busca si existe un producto especifico en el repositorio
     * recibiendo como parametro su codigo identificador y retornando el
     * producto si lo encuentra
     *
     * @param cod
     * @return String de Producto
     */
    public Producto obtenerProducto(int cod) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(cod);

        if (producto == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }
        return producto;
    }

    
    
    /**
     * Metodo que edita el nombre de un producto
     *
     * @param cod
     * @param nuevoNombre
     */
    public void editarNombre(int cod, String nuevoNombre) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(cod);
        if (producto == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }
        if (nuevoNombre.matches("\\d+")) {
            throw new IllegalArgumentException(NOMBRE_INVALIDO);
        }
        producto.setNombre(nuevoNombre);
    }

    /**
     * Metodo que edita el precio de compra de un producto
     *
     * @param cod
     * @param nuevoPrecio
     */
    public void editarPrecio(int cod, int nuevoPrecio) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(cod);
        if (producto == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }
        if (nuevoPrecio <= 0) {
            throw new IllegalArgumentException(PRECIO_INVALIDO);
        }
        producto.setPrecio(nuevoPrecio);
    }

    /**
     * Metodo que edita la cantidad en stock de un producto
     *
     * @param cod
     * @param nuevoStock
     */
    public void editarStock(int cod, int nuevoStock) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(cod);
        if (producto == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }
        if (nuevoStock < 0) {
            throw new IllegalArgumentException(STOCK_INVALIDO);
        }
        producto.setStock(nuevoStock);
    }

    /**
     * Metodo que borra un producto del repositorio recibiendo como parametro su
     * codigo identificador
     *
     * @param cod
     */
    public void borrarProducto(int cod) {
        Producto producto = repositorioProductos.obtenerProductoPorCodigo(cod);

        if (producto == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }

        repositorioProductos.borrarProductoPorCodigo(cod);
    }

    /**
     * Metodo que retorna el mapa que almacena todos los productos creados
     *
     * @return mapa de clave Integer (codigo de producto) y valor Producto
     * (producto)
     */
    public Map<Integer, Producto> getMapaProductos() {
        Map<Integer, Producto> mapa = repositorioProductos.getProductos();
        return mapa;
    }

}
