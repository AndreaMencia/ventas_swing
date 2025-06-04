package modelo;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class Producto {

    private String nombre;
    private int codigo;
    private int precio;
    private int stock;

    /**
     * Contructor de la clase que recibe como atributos un nombre, un codigo
     * identificador , un precio y una cantidad en stock
     *
     * @param nombre
     * @param codigo
     * @param precio
     * @param stock
     */
    public Producto(String nombre, int codigo, int precio, int stock) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Metodo que retorna el nombre del Producto
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que establece un valor para el nombre del producto
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retorna el codigo identificador del producto
     *
     * @return codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Metodo que establece el codigo identificador del producto
     *
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo que retorna el precio del producto
     *
     * @return precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Metodo que establece el precio del producto
     *
     * @param precio
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Metodo que retorna la cantidad en stock del producto
     *
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Metodo que establece la cantidad en stock del producto
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
