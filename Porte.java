import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
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
     * @param id
     * @param nave
     * @param origen
     * @param muelleOrigen
     * @param salida
     * @param destino
     * @param muelleDestino
     * @param llegada
     * @param precio
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
     * @param fila
     * @param columna
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {
        return listaEnvios.buscarEnvio(id, fila, columna);
    }


    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     *
     * @param envio
     * @return
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
     * @param localizador
     * @return
     */
    public boolean desocuparHueco(String localizador) {
        //LOCALIZAR ENVIO, IDENTIFICAR FILAS Y COLUMNAS, FALSE ESA POSICION
        Envio envio = buscarEnvio(localizador);
        int fila = envio.getFila();
        int columna = envio.getColumna();
        if (huecoOcupado(fila, columna)){
            return false;
        } else{
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
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
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
        for (int i=1; i<=filas; filas++) {
            char letra = (char)(64+i);
            System.out.println(" "+letra);
        }
        System.out.println();
        for (int i=1; i<=filas; columnas++){
            System.out.println(i);
            for (int j=1; j<=columnas; columnas++){
                char ocupado = ' ';
                if (huecoOcupado(i, j)){
                    ocupado = 'X';
                }
                if (i==1 || i<=10){
                    System.out.println("["+ocupado+"]");
                }
            }
        }
        System.out.println();
                }



    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     * @param fichero
     * @return
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter salida1 = null;
        try {
            salida1 = new PrintWriter(new PrintWriter(fichero));
            salida1.println("-------------------------------------------------------------------------");
            salida1.println("--------- Lista de envíos del porte "+id+" ---------");
            salida1.println("-------------------------------------------------------------------------");
            salida1.println("Hueco Cliente");
            //arreglar esto de clientes (for)
            for (int i=0; i<= huecos.length-1; i++ ) {
                for (int j = 0; j <= huecos.length-1; j++) {
                    salida1.printf("%s \t \t \t%s %s, %s", buscarEnvio(i, j).getHueco(), buscarEnvio(i, j).getCliente().getNombre(),
                            buscarEnvio(i, j).getCliente().getApellidos(), buscarEnvio(i, j).getCliente().getEmail());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error de escritura del fichero");
            return false;
        } finally {
            if (salida1!=null){
                return true;
            }
        }
        return true;
    }


    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        //REVISAR
            String ID = "PM";
            int[] numeros =rand.ints(4,0,9).toArray();
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
     * @param teclado
     * @param rand
     * @param puertosEspaciales
     * @param naves
     * @param portes
     * @return
     */
    //falta por terminar
    public static Porte altaPorte(Scanner teclado, Random rand,
                                  ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves,
                                  ListaPortes portes) {
        Porte porte = null;
        Porte origen, destino;
        Nave nave;
        String codigoOrigen,codigoDestino, matricula;
        int muelleOrigen, terminalDestino;
        double precio;
        do {System.out.println("Ingrese código de puerto Origen: ");
         codigoOrigen= teclado.nextLine();}
        while(codigoOrigen.equals(" "));
        do {System.out.println("Ingrese el muelle de Origen (1-4): ");
            codigoDestino= teclado.nextLine();}
        while();
        do {System.out.println("Ingrese código de puerto Destino: ");
            codigoDestino= teclado.nextLine();}
        while();
        do {System.out.println("Ingrese Terminal Destino (1-6): ");
            codigoDestino= teclado.nextLine();}
        while();
        //generar ID



        // do while (
        return null;
    }
}