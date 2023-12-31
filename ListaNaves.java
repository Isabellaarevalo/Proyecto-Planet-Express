import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 * ListaNaves es una clase que encapsula las variables de la
 * lista de las naves como su capacidad y ocupación.
 * Esta clase se emplea para realizar métodos relacionados con
 * las naves, como seleccionar una nave,buscarla,mostrarlas,
 * o insertar una nueva dentro de la lista de naves.
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaNaves {
    /**
     * Lista de naves
     */
    private Nave[] naves;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad máxima de la lista de naves.
     * @param ocupacion numero de naves.
     */

    /**
     *Capacidad máxima de la lista de naves
     */
    private int capacidad;
    /**
     * Número de naves en la lista
     */
    private int ocupacion;
    /**
     * Constructor que inicializa la lista en una capacidad determinada
     */
    public ListaNaves(int capacidad) {
        this.capacidad = capacidad;
        this.naves = new Nave[capacidad];
		
    }

    // TODO: Devuelve el número de naves que hay en la lista
    /**
     * Getter del atributo ocupación
     */
    public int getOcupacion() {
        return ocupacion;

    }
    // TODO: ¿Está llena la lista de naves?
    /**
     * Método que comprueba si la lista está llena
     * @return true, que está llena cuando la ocupación tiene el mismo valor
     * que la capacidad máxima de la lista,false en caso contrario
     */
    public boolean estaLlena() {
        return capacidad == ocupacion;

    }
	// TODO: Devuelve nave dado un indice
    /**
     * Getter de la nave que ocupa cierta posición
     * @param posicion que ocupa el envío dentro de la lista
     * @return la nave que ocupa la posición pasada por parámetro en la lista
     */
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * Método que inserta una nueva nave en la lista
     * @param nave que se inserta en la lista de naves.
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {
        boolean seCumple = false;
        if (!estaLlena()) {
            naves[ocupacion] = nave;
            ocupacion++;
            seCumple = true;
        }
        return seCumple;
    }
    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * Método que busca una nave a partir de su matrícula si la encuentra
     * @param matricula asociada con la nave.
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        Nave naveAct = null;
        boolean seCumple = false;
        int i = 0;
        while (i < ocupacion && !seCumple) {
            if (naves[i].getMatricula().equals(matricula)) {
                naveAct = naves[i];
                seCumple = true;
            }
            i++;
        }
        return naveAct;
    }
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    /**
     * Método que muestra todas las naves de la lista
     */
    public void mostrarNaves() {
        for (Nave nave : naves) {
            if (nave != null) {
                System.out.println(nave.toString());
            }
        }
    }


    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     *
     * Función que selecciona una nave a partir de su matrícula y comprueba si su alcance
     * es suficiente,comparándolo con el alcance pasado por parámetro.
     * @param teclado lee la matrícula introducida por el usuario.
     * @param mensaje solicita al usuario la matrícula de la nave.
     * @param alcance necesario de la nave.
     * @return la nave con alcance suficiente.
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        //confirm el equals si lo de dentro de paréntesis está correcto
        Nave nave = null;
        String matricula;
        do {
            System.out.print(mensaje);
            matricula = teclado.next();
            nave = buscarNave(matricula);
            if (nave == null) {
                System.out.println("Matrícula de nave no encontrada.");
            }
            if(alcance<buscarNave(matricula).getAlcance()) {
                System.out.println("Alcance de la nave insuficiente.");
            }

        } while (nave == null);
        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * Método que compruebe si se ha escrito en el fichero naves
     * @param nombre del fichero naves
     * @return true si se ha escrito en el fichero, null en caso contrario
     */
    public boolean escribirNavesCsv(String nombre) {
        // falta añadir error de cierre de fichero
        PrintWriter pw = null;
        Nave nave;
        boolean escrito = true;
        try {
            pw = new PrintWriter(new FileWriter("naves.csv", true));
            for (int i = 0; i < ocupacion; i++) {
                nave = naves[i];
                pw.printf("%s;%s;%08d;%C;%s\n", nave.getMarca(), nave.getModelo(), nave.getMatricula(),
                        nave.getFilas(), nave.getColumnas(), nave.getAlcance());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero de naves no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de escritura en fichero de naves.");
            escrito = false;
        } finally {
            if (pw != null) {
                pw.close();
            }
            return escrito;
        }
    }



    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * Función que genera una lista con las naves,teniendo en cuenta la capacidad pasada
     * por parámetro
     * @param fichero nombre del fichero
     * @param capacidad máxima de la lista.
     * @return fichero con la lista de naves.
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String [] dato = linea.split(";");
                Nave nave = new Nave (dato[0], dato[1], dato[2], Integer.parseInt(dato[3]),
                        Integer.parseInt(dato[4]), Double.parseDouble(dato[5]));
                listaNaves.insertarNave(nave);
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
        return listaNaves;
    }
}
