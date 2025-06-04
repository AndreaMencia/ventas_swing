package controladores;

import java.util.Map;
import modelo.Cliente;
import repositorios.RepoClientes;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class ControladorClientes {

    private static final String NO_EXISTE = "Cliente no encontrado";
    private static final String YA_EXISTE = "El cliente ya existe";
    private static final String NOMBRE_INVALIDO = "Nombre invalido. Vuelva a intentar";
    private final RepoClientes repositorioClientes = RepoClientes.getInstancia();

    /**
     * Metodo que crea un objeto Cliente recibiendo como parametro los atributos
     * del objeto y lo guarda en el repositorio
     *
     * @param nombre
     * @param ci
     * @param edad
     */
    public void crearClientes(String nombre, int ci, int edad) {
        Cliente nuevoCliente = new Cliente(nombre, ci, edad);

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(NOMBRE_INVALIDO);
        }
        if (repositorioClientes.getClientes().containsKey(ci)) {
            throw new IllegalArgumentException(YA_EXISTE);
        }
        repositorioClientes.guardarCliente(nuevoCliente);

    }

    /**
     * Metodo que verifica que la entrada del nombre del cliente no este vacia
     *
     * @param nombre
     */
    public void verificarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!nombre.matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+")) {
            throw new IllegalArgumentException("El nombre solo debe contener letras.");
        }
    }

    /**
     * Metodo que busca si existe un cliente especifico en el repositorio
     * recibiendo como parametro su numero de cedula y retorna su nombre
     *
     * @param ci
     * @return nombre del cliente
     */
    public String buscarCliente(int ci) {
        Cliente cliente = repositorioClientes.obtenerClientePorCI(ci);

        if (cliente == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }
        return cliente.getNombre();
    }

    /**
     * metodo que recibe como parametro un numero de cedula y retorna el cliente
     * asociado a ese numero
     *
     * @param cedula
     * @return
     */
    public Cliente obtenerClientePorCI(int cedula) {
        Cliente cliente = repositorioClientes.obtenerClientePorCI(cedula);

        if (cliente == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }

        return cliente;
    }

    /**
     * Metodo que edita el nombre de un cliente
     *
     * @param ci
     * @param nuevoNombre
     */
    public void editarNombre(int ci, String nuevoNombre) {
        Cliente cliente = repositorioClientes.obtenerClientePorCI(ci);
        if (cliente == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }
        cliente.setNombre(nuevoNombre);
    }

    public void editarEdad(int ci, int edad) {
        Cliente cliente = repositorioClientes.obtenerClientePorCI(ci);
        if (cliente == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }
        cliente.setEdad(edad);
    }

    /**
     * Metodo que borra un cliente del repositorio recibiendo como parametro su
     * numero de cedula
     *
     * @param ci
     */
    public void borrarCliente(int ci) {
        Cliente cliente = repositorioClientes.obtenerClientePorCI(ci);

        if (cliente == null) {
            throw new IllegalArgumentException(NO_EXISTE);
        }

        repositorioClientes.borrarClientePorCI(ci);
    }

    /**
     * Metodo que retorna el mapa que almacena a todos los clientes creados
     *
     * @return mapa de clave Integer (cedula de cliente) y valor Cliente
     * (cliente)
     */
    public Map<Integer, Cliente> getMapaClientes() {
        Map<Integer, Cliente> mapaClien = repositorioClientes.getClientes();
        return mapaClien;
    }
}
