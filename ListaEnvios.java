import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *ListaEnvios es una clase que encapsula las variables de la
 * lista de envíos como su capacidad y ocupación.
 * Esta clase se emplea para realizar métodos relacionados con
 * todos los envíos, como listar los envíos,buscar e incluso
 * eliminar un envío dentro de la lista.
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaEnvios {
    /**
     * Lista de envíos
     */
    private Envio[] envios;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad máxima de la lista
     * @param ocupacion número de envíos
     */
    /**
     * Capacidad máxima de la lista de envíos
     */
    private int capacidad;
    /**
     * Número de envío es la lista
     */
    private int ocupacion =0;
    /**
     * Constructor de la clase que incializa la lista a una capacidad
     * determinada
     */
    public ListaEnvios(int capacidad) {
        this.capacidad = capacidad;
        this.envios = new Envio[capacidad];
		
    }

    // TODO: Devuelve el número de envíos que hay en la lista
    /**
     * Getter del atributo ocupación.
     * @return el número de envíos en la lista
     */
    public int getOcupacion() {
        return ocupacion;

    }
    // TODO: ¿Está llena la lista de envíos?
    /**
     * Método que comprueba si la lista está llena
     * @return que está llena cuando la ocupación tiene el mismo valor
     * que la capacidad máxima de la lista
     */
    public boolean estaLlena() {
        return ocupacion == capacidad;

    }
	//TODO: Devuelve el envio dado un indice
    /**
     * Getter del envío que ocupa cierta posición
     * @param i posición que ocupa el envío dentro de la lista
     * @return el envío que ocupa la posición pasada por parámetro
     */
    public Envio getEnvio(int i) {
        Envio envio = null;
        if (i<=ocupacion){
            envio = envios[i];
    }
            return envio;
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     * Metodo que inserta un nuevo envío en la lista si no esta llena.
     * @param envio Envío que hay que insertar en la lista de envíos
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        boolean seCumple = false;
        if (!estaLlena()) {
            envios[ocupacion] = envio;
            ocupacion++;
            seCumple = true;
        }
        return seCumple;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     * Método que busca el envío a partir de su localizador
     * @param localizador secuencia de letras y números mediante la cual buscamos el envío
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        Envio envio = null;
        boolean seCumple = false;
        int i = 0;
        while (i < ocupacion && !seCumple) {
            if (envios[i].getLocalizador().equals(localizador)) {
                envio = envios[i];
                seCumple = true;
                }
            i++;
            }
        return envio;
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * Método que busca un envío a partir del id del porte y la fila y columna que ocupa.
     * @param idPorte identificador del porte
     * @param fila del hueco en el que se busca el envío.
     * @param columna del hueco en el que se busca el envío.
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        Envio envio = null;
        boolean seCumple = false;
        int i = 0;
        while (i < ocupacion && !seCumple) {
            if (envios[i].getPorte().getID().equals(idPorte) && envios[i].getFila() == fila && envios[i].getColumna() == columna) {
                envio = envios[i];
                seCumple = true;
            }
            i++;
        }
        return envio;
    }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     * Método que elimina un envio a partir de su localizador
     * @param localizador único del envío que lo localiza.
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio (String localizador) {
        int nOcupacion = 0;
        boolean eliminado = false;
        for (int i = 0; i < ocupacion; i++) {
            if (!envios[i].getLocalizador().equals(localizador)) {
                envios[nOcupacion] = envios[i];
                nOcupacion++;
            } else {
                eliminado = true;
            }
        }
        ocupacion = nOcupacion;
        return eliminado;
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     * Método que lista todos los envíos por pantalla
     */
    public void listarEnvios() {
        for (int i=0; i<ocupacion; i++) {
                System.out.println(envios[i].toString());
            }
        }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     *
     *  Método que selecciona un envio a partir del localizador que introduca el usuario si este
     *  es correctp.
     * @param teclado lee el localizador intoducido por el usuario.
     * @param mensaje solicita al usuario que introduzca el localizador.
     * @return devuelve el envío al que le pertenece el localizzador cuando es correcto.
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        Envio envio = null;
        String localizador;
        do {
            System.out.print(mensaje);
            localizador = teclado.next();
            envio = buscarEnvio(localizador);
            if (envio == null) {
                System.out.println("Localizador no encontrado.");
            }

        } while (envio == null);
        return envio;
    }


    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * Método que añade los envíos en un fichero
     * @param fichero nombre del fichero.
     * @return escribe en el fichero los envíos.
     */
    public boolean aniadirEnviosCsv(String fichero) {
        //confirmar que no sobreescribe la información
        PrintWriter pw = null;
        boolean escrito = true;
        try {
            pw = new PrintWriter(new FileWriter(fichero));
            for (int i = 0; i < ocupacion; i++) {
                pw.printf("%s;%s;%s;%S;%d;%d;%.1f\n",envios[i].getLocalizador(),envios[i].getPorte().getID(),
                        envios[i].getCliente().getEmail(), envios[i].getFila(), envios[i].getColumna(), envios[i].getPrecio());
            }
        }catch(FileNotFoundException e){
            System.out.println("Fichero de envíos no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de escritura en fichero de envíos.");
            escrito = false;
        } finally {
            if (pw != null) pw.close();
        }
        return escrito;
    }

    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * Función que lee los envios del anterior fichero y los añade a su lista de portes y
     * clientes.
     * @param ficheroEnvios nombre del fichero de envíos.
     * @param portes asociados a su respectivo envío de la lista de portes.
     * @param clientes asociados a su respectivo envío, de la lista de clientes.
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        BufferedReader entrada = null;
        //comprobar que el portes.getOcupacion() está bien ponerlo así
        ListaEnvios listaEnvios = new ListaEnvios(portes.getOcupacion());
        try {
                entrada = new BufferedReader(new FileReader(ficheroEnvios));
                String linea;
                while ((linea = entrada.readLine())!=null) {
                    String [] dato = linea.split(";");
                    Envio envio = new Envio (dato[0], portes.buscarPorte(dato[1]),
                            clientes.buscarClienteEmail(dato[2]), Integer.parseInt(dato[3]),
                            Integer.parseInt(dato[4]), Double.parseDouble(dato[5]));
                    listaEnvios.insertarEnvio(envio);
                    clientes.buscarClienteEmail(dato[2]).aniadirEnvio(envio);
                }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero envíos no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de lectura de fichero envíos.");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException ex) {
                System.out.println("Error de cierre de fichero envíos.");
            }
        }
    }
}
