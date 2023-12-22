import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 * ListaClientes es una clase que encapsula las variables
 * de la lisda de clientes,como es su capacidad y ocupación.
 * Esta clase se emplea para las funciones relacionadas
 * con el numero de clientes o los clientes de PlanetExpress,
 * como buscar o seleccionar un cliente, e insertar un nuevo.
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaClientes {
    /**
     * Lista con todos los clientes
     */
    private Cliente[] clientes;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad de la lista de clientes.
     * @param ocupacion numero de clientes en la lista de clientes.
     */

    /**
     * capacidad de la lista de clientes
     */
    private int capacidad;
    /**
     * Número de clientes en la lista
     */
    private int ocupacion = 0;
    /**
     * Constructor de la clase que incializa la lista a
     * una capacidad determinada
     */

    public ListaClientes(int capacidad) {
        this.capacidad = capacidad;
        this.clientes = new Cliente[capacidad];

    }

    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    /**
     * Getter del atributo ocupación
     * @return devuelve el número de clientes que hay en la lista
     */
    public int getOcupacion() {
        return ocupacion;

    }
    /**
     * Método que comprueba si la lista está llena
     * @return que está llena cuando la ocupación tiene el mismo valor
     * que la capacidad máxima de la lista
     */

    // TODO: ¿Está llena la lista de clientes?
    public boolean estaLlena() {
        return ocupacion == capacidad;

    }

    // TODO: Devuelve el cliente dada el indice
    /**
     * Getter del cliente que ocupa cierta posición
     * @param i posición que ocupa el cliente dentro de la lista
     * @return el cliente que ocupa la posición pasada por parámetro
     */
    public Cliente getCliente(int i) {
        Cliente cliente = null;
        if (i < ocupacion) {
            cliente = clientes[i];
        }
        return cliente;
    }

    // TODO: Inserta el cliente en la lista de clientes
    /**
     * Método que comprueba si se inserta un cliente en la lista
     * @param cliente que se quiere inserta en la lista
     * @return true si se inserta correctamente,false si la lista ya está llena
     */
    public boolean insertarCliente(Cliente cliente) {
        boolean correcto = false;
        if (!estaLlena()) {
            clientes[ocupacion] = cliente;
            ocupacion++;
            correcto = true;
        }
        return correcto;

    }

    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    /**
     * Método que busca un cliente a partid de su email
     * @param email del cliente que se quiere buscar
     * @return el cliente con ese email, null si no se ha encontrado
     */
    public Cliente buscarClienteEmail(String email) {
        Cliente cliente = null;
        boolean seCumple = false;
        int i = 0;
        while (i < ocupacion && !seCumple) {
            if (clientes[i].getEmail().equals(email)) {
                cliente = clientes[i];
                seCumple = true;
            }
            i++;
        }
        return cliente;

    }

    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * Método que selecciona a un cliente a partir del email introducido por el
     * usuario.
     * @param teclado lee el email introducido por el usuario.
     * @param mensaje solicita al usuario que introduzca el email.
     * @return devuelve el cliente encontrado a partir del email.
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        Cliente cliente = null;
        String email;
        do {
            System.out.print(mensaje);
            email = teclado.next();
            cliente = buscarClienteEmail(email);
            if (cliente == null) {
                System.out.println("Email no encontrado.");
            }
        } while (cliente == null);
        return cliente;
    }

    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     *Método que comprueba si se ha escrito en el fichero de clientes
     * @param fichero Nombre del fichero.
     * @return true si se ha escrito, false en caso contrario.
     */
    public boolean escribirClientesCsv(String fichero) {
        //falta añadir error de cierre de fichero
        PrintWriter salida = null;
        Cliente cliente;
        boolean escrito = true;
        try {
            salida = new PrintWriter(new FileWriter(fichero, true));
            for (int i = 0; i < ocupacion; i++) {
                cliente = clientes[i];
                salida.printf("%s;%s;%08d;%C;%s\n", cliente.getNombre(), cliente.getApellidos(), cliente.getEmail());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero de clientes no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de escritura en fichero de clientes.");
            escrito = false;
        } finally {
            if (salida != null) {
                salida.close();
                }
        }
        return escrito;
    }

    /**
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * Método que crea la lista de clientes a partir del fichero csv,
     * teniendo en cuneta su capacidad y los maximos envíos por cliente
     * @param fichero Nombre del fichero.
     * @param capacidad de la Lista de Clientes.
     * @param maxEnviosPorCliente máximo número de envíos por cliente.
     * @return lista de clientes.
     */
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {
        BufferedReader entrada = null;
        ListaClientes listaClientes = new ListaClientes(capacidad);
        try {
            entrada = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String [] dato = linea.split(";");
                Cliente cliente = new Cliente (dato[0], dato[1], dato[2], Integer.parseInt(dato[4]));
                listaClientes.insertarCliente(cliente);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero clientes no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de lectura de fichero clientes.");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException ex) {
                System.out.println("Error de cierre de fichero clientes.");
            }
        }
        return listaClientes;
    }
}