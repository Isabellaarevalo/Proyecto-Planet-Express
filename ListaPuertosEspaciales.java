import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 * ListaPuertosEspaciales es una clase que encapsula las variables de la
 * lista de los puertos como su capacidad y ocupación.
 * Esta clase se emplea para realizar métodos relacionados con la lista de
 * todos los puertos, como,buscar,seleccionar o
 * insertar un puerto en la lista.
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    /**
     * Lista con todos los puertos espaciales
     */
    private PuertoEspacial[] lista;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad máxima de la lista
     * @param ocupacion número de Puertos Espaciales
     */

    /**
     *Capacidad máxima de la lista
     */
    private int capacidad;
    /**
     * Número de puertos en la lista
     */

    private int ocupacion;
    /**
     * Constructor que inicializa la lista en una capacidad determinada
     */
    public ListaPuertosEspaciales(int capacidad) {
        this.capacidad = capacidad;
        this.lista = new PuertoEspacial[capacidad];
        
		
    }
    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    /**
     * Getter del atributo ocupación
     */
    public int getOcupacion() {
        return ocupacion;

    }
    // TODO: ¿Está llena la lista?
    /**
     * Método que comprueba si la lista está llena
     * @return true, que está llena cuando la ocupación tiene el mismo valor
     * que la capacidad máxima de la lista,false en caso contrario
     */
    public boolean estaLlena() {
        return capacidad == ocupacion;

    }
	// TODO: Devuelve un puerto espacial dado un indice
    /**
     * Getter del puerto que ocupa cierta posición
     * @param i que ocupa el puerto dentro de la lista
     * @return el puerto que ocupa la posición i pasada por parámetro en la lista
     */
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * Método que inserta un Puerto espacial nuevo en la lista
     * @param puertoEspacial que se inserta en la lista
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean correcto = false;
        if (!estaLlena()) {
            lista[ocupacion] = puertoEspacial;
            correcto = true;
            ocupacion++;
        }
        return correcto;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     * Método que busca un Puerto espacial a partir del codigo pasado como parámetro
     * @param codigo del Puerto Espacial a buscar
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial puertoEspacial = null;
        boolean seCumple = false;
        int i = 0;
        while (i < ocupacion && !seCumple) {
            if (lista[i].getCodigo().equals(codigo)) {
                puertoEspacial= getPuertoEspacial(i);
                seCumple = true;
            }
            i++;
        }
        return puertoEspacial;
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     *
     *  Método selecciona un puerto espacial a partir de un código válido
     * @param teclado lee  por teclado el código introducido por el usuario.
     * @param mensaje solicita al usuario que introduzca el código del puerto.
     * @return devuelve el Puerto Espacial con un código válido.
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial = null;
        String codigo;
        do {
            System.out.print(mensaje);
            codigo = teclado.next();
            puertoEspacial = buscarPuertoEspacial(codigo);
            if (puertoEspacial == null) {
                System.out.println("Código de puerto espacial no encontrado.");
            }
        } while (puertoEspacial == null);
        return puertoEspacial;
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * Método que genera un fichero CSV con la lista de puertos espaciales
     * @param nombre del fichero
     * @return devuelve un fichero con los puertos espaciales
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        PuertoEspacial puertoEspacial;
        boolean escrito = true;
        try {
            pw = new PrintWriter(new FileWriter(nombre, false));
            for (int i = 0; i < ocupacion; i++) {
                puertoEspacial = lista[i];
                pw.printf("%s;%s;%08d;%C;%s\n", puertoEspacial.getNombre(), puertoEspacial.getCodigo(),
                        puertoEspacial.getRadio(), puertoEspacial.getAzimut(), puertoEspacial.getPolar(),
                        puertoEspacial.getMuelles());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero puertosEspaciales no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de escritura en fichero puertosEspaciales.");
            escrito = false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return escrito;
    }


    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     *
     *  Método que genera una lista de PuertosEspaciales a partir del fichero CSV,
     *  teniendo en cuenta su capacidad máxima
     * @param fichero nombre del fichero
     * @param capacidad máxima de la lista
     * @return devuelve una lista de Puertos Espaciales
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] dato = linea.split(";");
                PuertoEspacial puertoEspacial = new PuertoEspacial(dato[0], dato[1], Double.parseDouble(dato[2]),
                        Double.parseDouble(dato[3]), Double.parseDouble(dato[4]), Integer.parseInt(dato[5]));
                listaPuertosEspaciales.insertarPuertoEspacial(puertoEspacial);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero puertosEspaciales no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de lectura de fichero puertosEspaciales.");
        } finally {
            try{
                if(entrada !=null) {
                    entrada.close();
                }
            } catch(IOException ex){
                System.out.println("Error de cierre de fichero puertosEspaciales.");
            }
        }
        return listaPuertosEspaciales;
    }
}
