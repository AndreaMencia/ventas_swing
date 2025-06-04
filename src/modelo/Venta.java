package modelo;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class Venta {

    private int id;
    private LocalDate fechaDeCompra;
    private Cliente cliente;
    private Map<Producto, Integer> productos;
    private int monto;

    /**
     * Contructor de la clase que recibe como atributos un codigo identificador
     * de venta, una fecha, una instancia de la clase Cliente, un mapa de tipo
     * clave Producto y valor Integer y un monto
     *
     * @param id
     * @param fechaDeCompra
     * @param cliente
     * @param productos
     * @param monto
     */
    public Venta(int id, LocalDate fechaDeCompra, Cliente cliente, Map<Producto, Integer> productos, int monto) {
        this.id = id;
        this.fechaDeCompra = fechaDeCompra;
        this.cliente = cliente;
        this.productos = productos;
        this.monto = monto;
    }

    /**
     * Metodo que retorna el codigo identificador de la venta
     *
     * @return identificador de venta
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo que establece el codigo identificador de la venta
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo que retorna la fecha de la venta
     *
     * @return fecha de venta
     */
    public LocalDate getFechaDeCompra() {
        return fechaDeCompra;
    }

    /**
     * Metodo que establece la fecha de la venta
     *
     * @param fechaDeCompra
     */
    public void setFechaDeCompra(LocalDate fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    /**
     * Metodo que retorna el cliente que realiza la venta
     *
     * @return Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Metodo que establece el cliente que realiza la venta
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo que retorna un mapa de productos que se compraron en la venta
     *
     * @return mapa de valor Producto (producto) y clave Integer (cantidad de
     * producto)
     */
    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    /**
     * Metodo que establece el mapa de productos que se compraron
     *
     * @param productos
     */
    public void setProductos(Map<Producto, Integer> productos) {
        this.productos = productos;
    }

    /**
     * Metodo que retorna el valor total la venta
     *
     * @return monto
     */
    public int getMonto() {
        return monto;
    }

    /**
     * Metodo que establece el valor total la venta
     *
     * @param monto
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }

}
