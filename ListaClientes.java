import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaClientes {
    private Cliente[] clientes;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     * @param ocupacion
     */
    private int capacidad;
    private int ocupacion = 0;

    public ListaClientes(int capacidad) {
        this.capacidad = capacidad;
        this.clientes = new Cliente[capacidad];

    }

    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    public int getOcupacion() {
        return ocupacion;

    }

    // TODO: ¿Está llena la lista de clientes?
    public boolean estaLlena() {
        return ocupacion == capacidad;

    }

    // TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        Cliente cliente = null;
        if (i < ocupacion) {
            cliente = clientes[i];
        }
        return cliente;
    }

    // TODO: Inserta el cliente en la lista de clientes
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
     *
     * @param teclado
     * @param mensaje
     * @return
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        Cliente cliente = null;
        String email;
        do {
            System.out.print(mensaje);
            email = teclado.next();
            cliente = buscarClienteEmail(email);
            if (cliente == null) {
                System.out.println("DNI no encontrado.");
            }
        } while (cliente == null);
        return cliente;
    }

    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     *
     * @param fichero
     * @return
     */
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter salida = null;
        Cliente cliente;
        boolean escrito = true;
        try {
            salida = new PrintWriter(new FileWriter(fichero, false));
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
     *
     * @param fichero
     * @param capacidad
     * @param maxEnviosPorCliente
     * @return lista de clientes
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