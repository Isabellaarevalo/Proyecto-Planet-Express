import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class Envio {

    private String localizador;
    private Porte porte;
    private Cliente cliente;
    private int fila;
    private int columna;
    private double precio;

    /**
     * Constructor of the class
     *
     * @param localizador único del envío, formado por el id del porte y 9 letras mayúsculas.
     * @param porte asociado a cada envío de tipo Porte.
     * @param cliente Es quíen contrata el envío, y es de tipo Cliente.
     * @param fila del hueco en el que se encuentra el envío en la matriz de Porte.
     * @param columna del hueco en el que se encuentra el envío en la matriz de Porte.
     * @param precio del envío.
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }
    public String getLocalizador() {
        return localizador;
    }
    public Porte getPorte() {
        return porte;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4

    //falta por hacer
    public String getHueco() {
        String hueco;
        fila = getFila();
        columna= getColumna();
         char letra = (char) (64 + columna);
         System.out.println("" + letra);
         hueco = ""+fila+letra;
        return hueco;
    }
    public double getPrecio() {
        return precio;
    }
    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {
       return "Envío "+localizador+" para Porte "+ porte.getID()+ " de "+porte.getOrigen().getCodigo() + porte.getMuelleOrigen()
               +"("+porte.getSalida()+")"+" a "+porte.getDestino().getCodigo()+porte.getMuelleDestino()+"("+porte.getLlegada()+")"
               +" en hueco "+ getHueco()+" por "+ porte.getPrecio()+"SSD";

    }
    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    public boolean cancelar() {
        boolean cancelado = cliente.cancelarEnvio(localizador) && porte.desocuparHueco(localizador);
        return cancelado;

    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero nombre del fichero en el que se guarda la factura del envío.
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     *     -----------------------------------------------------
     *     --------- Factura del envío PM1111AAAABBBBC ---------
     *     -----------------------------------------------------
     *     Porte: PM0066
     *     Origen: Gaia Galactic Terminal (GGT) M5
     *     Destino: Cidonia (CID) M1
     *     Salida: 01/01/2023 08:15:00
     *     Llegada: 01/01/2024 11:00:05
     *     Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     *     Hueco: 6C
     *     Precio: 13424,56 SSD
     */
    public boolean generarFactura(String fichero) {
        //revisar hueco
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new FileWriter(fichero));
            salida.println("-----------------------------------------------------");
            salida.println("--------- Factura del envío "+localizador+" ---------");
            salida.println("-----------------------------------------------------");
            salida.println("Porte: "+porte);
            salida.println("Origen: "+ porte.getOrigen()+" "+porte.getMuelleOrigen());
            salida.println("Destino: "+porte.getDestino()+" "+porte.getMuelleDestino());
            salida.println("Llegada: "+porte.getLlegada());
            salida.println("Cliente: "+cliente.getNombre()+" "+cliente.getApellidos()+", "+cliente.getEmail());
            salida.println("Hueco: "+getHueco());
            salida.println("Precio: "+precio+" SSD");
        } catch (FileNotFoundException e) {
            System.out.println("Fichero de factura no encontrado.");
        } catch(IOException ex) {
            System.out.println("Error de escritura en fichero de factura.");
        }finally {
            if(salida != null){
                salida.close();
            }
            return true;
        }
    }


    /**
     *	TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
	 *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand genera números aleatorios, utilizados para generar las 9 mayúsculas aleatorias.
     * @param idPorte identificador del porte de 6 caracteres.
     * @return devuelve el localizador formado al concatenar las mayúsculas generadas por rand, y el id del porte.
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String aleatorio = "";
        for (int i = 0; i < 4; i++) {
            char c = alphabet.charAt(rand.nextInt(alphabet.length()));
            aleatorio += c;
        }
        idPorte= idPorte + aleatorio;

        return localizador.toString();
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado lee los datos pro porcionados por el usuario.
     * @param rand para generar el localizador del envío.
     * @param porte para el cual se crea el envío.
     * @param cliente que contrata el envío.
     * @return Envio para el porte y cliente especificados
     */
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {
        Envio envio = null;
        int fila = 0, columna = 0;
        char letraColumna;
        double precio;
        boolean hueco = false;
        do {
            fila = Utilidades.leerNumero(teclado, "Fila del hueco:", 1, porte.getNave().getFilas());
            columna = Utilidades.leerNumero(teclado, "Columna del hueco:", 1, porte.getNave().getFilas());
            porte.imprimirMatrizHuecos();
        }while (porte.huecoOcupado(fila, porte.getNave().getColumnas())) ;
        precio = Utilidades.leerNumero(teclado, "Precio del envío:", 1, Double.MAX_VALUE);
        envio = new Envio(generarLocalizador(rand, porte.getID()), porte, cliente, fila, columna, precio);

        System.out.println("El envío"+envio.getLocalizador()+" se ha creado correctamente.");
        cliente.getListaEnvios().insertarEnvio(envio);
        porte.ocuparHueco(envio);
        return envio;
    }
    }