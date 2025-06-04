package modelo;

/**
 *
 * @author Andrea Mencia y Miori Chiba, Segundo Semestre Ing. Informatica.
 */
public class Cliente {

    private String nombre;
    private int numeroCI;
    private int edad;

    /**
     * Contructor de la clase que recibe como atributos un nombre y un numero de
     * cedula
     *
     * @param nombre
     * @param numeroCI
     * @param edad
     */
    public Cliente(String nombre, int numeroCI, int edad) {
        this.nombre = nombre;
        this.numeroCI = numeroCI;
        this.edad = edad;
    }

    /**
     * Metodo que retorna el nombre del cliente
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que establece un valor para el nombre del cliente
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retorna el numero de cedula del cliente
     *
     * @return numero de cedula
     */
    public int getNumeroCI() {
        return numeroCI;
    }

    /**
     * Metodo que establece un valor para el numero de cedula del cliente
     *
     * @param numeroCI
     */
    public void setNumeroCI(int numeroCI) {
        this.numeroCI = numeroCI;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
