import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 * Porte es una clase que encapsula las variables
 * de un porte, como su identificador,la fecha de salida,el puerto
 * de origen,entre otros.
 * Esta clase se emplea para realizar funciones sobre el porte de un
 * envío, como desocupar o ocupar un hueco de los portes,así como
 * buscar un envío.
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class Porte {
    private boolean[][] huecos;
    private String id;
    private Nave nave;
    private PuertoEspacial origen;
    private int muelleOrigen;
    private Fecha salida;
    private PuertoEspacial destino;
    private int muelleDestino;
    private Fecha llegada;
    private double precio;
    private ListaEnvios listaEnvios;

    /**
     * TODO: Completa el constructo de la clase
     *
     * @param id identificador único del porte
     * @param nave que se emplea en un porte
     * @param origen puerto espacial de origen del porte
     * @param muelleOrigen muelle del puerto espacial del origen
     * @param salida fecha de salida del porte
     * @param destino puerto espacial del destino del porte
     * @param muelleDestino muelle del puerto del destino
     * @param llegada fecha de llegada del porte
     * @param precio del contenedor del porte
     */
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {
        this.id = id;
        this.nave = nave;
        this.origen = origen;
        this.muelleOrigen = muelleOrigen;
        this.salida = salida;
        this.destino = destino;
        this.muelleDestino = muelleDestino;
        this.llegada = llegada;
        this.precio = precio;
        huecos = new boolean[nave.getFilas()][nave.getColumnas()];
        listaEnvios = new ListaEnvios(nave.getFilas() * nave.getColumnas());
    }

    public String getID() {
        return id;
    }

    public Nave getNave() {
        return nave;
    }

    public PuertoEspacial getOrigen() {
        return origen;
    }

    public int getMuelleOrigen() {
        return muelleOrigen;
    }

    public Fecha getSalida() {
        return salida;
    }

    public PuertoEspacial getDestino() {
        return destino;
    }

    public int getMuelleDestino() {
        return muelleDestino;
    }

    public Fecha getLlegada() {
        return llegada;
    }

    public double getPrecio() {
        return precio;
    }

    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {
        int huecosLibres = 0;
        for (int i = 0; i <= nave.getFilas(); i++) {
            for (int j = 0; j < nave.getColumnas(); j++) {
                if (!huecos[i][j]) {
                    huecosLibres++;
                }
            }
        }
        return huecosLibres;
    }

    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {
        return listaEnvios.estaLlena();
    }

    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        return (huecos[fila - 1][columna - 1]);
    }

    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }


    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     *
     * @param fila que ocupa el envío
     * @param columna que ocupa el envío
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {
        return listaEnvios.buscarEnvio(id, fila, columna);
    }


    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     *
     * @param envio que va a ocupar el hueco
     * @return devuelve true si esta desocupado y lo ocupa, y false en caso contrario
     */
    public boolean ocuparHueco(Envio envio) {
        for (int i = 0; i <= nave.getFilas(); i++) {
            for (int j = 0; j < nave.getColumnas(); j++) {
                if (!huecos[i][j]) {
                    ocuparHueco(envio);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     *
     * @param localizador del envío
     * @return devuelve false si el hueco está ocupado, true en caso contrario
     */
    public boolean desocuparHueco(String localizador) {
        //LOCALIZAR ENVIO, IDENTIFICAR FILAS Y COLUMNAS, FALSE ESA POSICION
        Envio envio = buscarEnvio(localizador);
        int fila = envio.getFila();
        int columna = envio.getColumna();
        if (huecoOcupado(fila, columna)) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     *
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     * Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        //por verificar
        return "Porte " + id + " de " + origen.getNombre() + "(" + origen.getCodigo() + " " + muelleOrigen + " (" + salida + ") a"
                + destino.getNombre() + " (" + destino.getCodigo() + " " + muelleDestino + " " + "(" + llegada + " en" + nave + " por " + precio + " SSD, " +
                "huecos libres:" + numHuecosLibres();
    }


    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     *
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    //revisar campo de fecha
    public String toStringSimple() {
        return "Porte" + id + " de " + origen.getCodigo() + " " + muelleOrigen + "(" + salida + ") a " +
                "" + destino.getCodigo() + " " + muelleDestino + " (" + llegada;
    }


    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     *
     * @param codigoOrigen código del puerto de origen
     * @param codigoDestino código del puerto del destino
     * @param fecha fecha de salida del porte
     * @return devuelve true si el código de origen,destino y fecha coinciden con el porte
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        //confirmar lo de fecha
        if ((codigoOrigen == origen.getCodigo()) && (codigoDestino == destino.getCodigo() && (fecha == salida))) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *        A  B  C
     *      1[ ][ ][ ]
     *      2[X][X][X]
     *      3[ ][ ][ ]
     *      4[ ][X][ ]
     *     10[ ][ ][ ]
     */
    public void imprimirMatrizHuecos() {
        int columnas = nave.getFilas();
        int filas = nave.getColumnas();
        for (int i = 1; i <= filas; filas++) {
            char letra = (char) (64 + i);
            System.out.println(" " + letra);
        }
        System.out.println();
        for (int i = 1; i <= filas; columnas++) {
            System.out.println(i);
            for (int j = 1; j <= columnas; columnas++) {
                char ocupado = ' ';
                if (huecoOcupado(i, j)) {
                    ocupado = 'X';
                }
                if (i == 1 || i <= 10) {
                    System.out.println("[" + ocupado + "]");
                }
            }
        }
        System.out.println();
    }


    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     *
     * @param fichero nombre del fichero
     * @return devuelve true si se ha escrito en un fichero la lista de envíos
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter salida1 = null;
        try {
            salida1 = new PrintWriter(new PrintWriter(fichero));
            salida1.println("-------------------------------------------------------------------------");
            salida1.println("--------- Lista de envíos del porte " + id + " ---------");
            salida1.println("-------------------------------------------------------------------------");
            salida1.println("Hueco Cliente");
            //arreglar esto de clientes (for)
            for (int i = 0; i <= huecos.length - 1; i++) {
                for (int j = 0; j <= huecos.length - 1; j++) {
                    salida1.printf("%s \t \t \t%s %s, %s", buscarEnvio(i, j).getHueco(), buscarEnvio(i, j).getCliente().getNombre(),
                            buscarEnvio(i, j).getCliente().getApellidos(), buscarEnvio(i, j).getCliente().getEmail());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error de escritura del fichero");
            return false;
        } finally {
            if (salida1 != null) {
                return true;
            }
        }
        return true;
    }


    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     *
     * @param rand genera los números aleatorios
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        //REVISAR
        String ID = "PM";
        int[] numeros = rand.ints(4, 0, 9).toArray();
        for (int numero : numeros) {
            ID += String.valueOf(numero);
        }
        return ID;
    }

    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     *
     * @param teclado lee los datos que introduzca el usuario
     * @param rand genera el identificador con números aleatorios
     * @param puertosEspaciales del origen y dstino del porte
     * @param naves que selecciona el usuario para el porte
     * @param portes de la lista de portes
     * @return devuelve el porte con los datos seleccionados por el usuario
     */
    public static Porte altaPorte(Scanner teclado, Random rand,
                                  ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves,
                                  ListaPortes portes) {
        Porte porte = null;
        PuertoEspacial origen, destino;
        Nave nave;
        String codigoOrigen, codigoDestino, matricula, localizador;
        int muelleOrigen, terminalDestino;
        Fecha fechaSalida, fechaLlegada;
        double precio;
            //origen
            origen = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Origen:");
            muelleOrigen = Utilidades.leerNumero(teclado, "Ingrese Terminal Origen (1 - " + origen.getMuelles() + "):", 1, origen.getMuelles());
            //destino
            destino = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Destino:");
            terminalDestino = Utilidades.leerNumero(teclado, "Ingrese Terminal Destino (1 - " + destino.getMuelles() + "):", 1, destino.getMuelles());

            //nave
            nave = naves.seleccionarNave(teclado, "Ingrese matrícula de Avión:", origen.distancia(destino));

            do {
                fechaSalida = Utilidades.leerFechaHora(teclado, "Fecha de Salida:");
                fechaLlegada = Utilidades.leerFechaHora(teclado, "Fecha de Llegada:");
                if (!fechaSalida.anterior(fechaLlegada)) {
                    System.out.println("Llegada debe ser posterior a salida.");
                }
            } while (!fechaSalida.anterior(fechaLlegada));
            precio = Utilidades.leerNumero(teclado, "Ingrese precio del pasaje:", 0F, 999999999F);
            //generar ID
            do {
                localizador = generarID(rand);
            } while (porte.buscarEnvio(localizador) != null);
            porte = new Porte(localizador, nave, origen, muelleOrigen, fechaSalida, destino, terminalDestino, fechaLlegada, precio);
            return porte;
        }
    }