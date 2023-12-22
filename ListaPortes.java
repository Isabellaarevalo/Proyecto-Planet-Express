import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaPortes {
    private Porte[] portes;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad máxima de la lista.
     * @param ocupación número de portes.
     */
    private int capacidad;
    private int ocupacion=0;
    public ListaPortes(int capacidad) {
        this.capacidad = capacidad;
        this.portes = new Porte[capacidad];
        
		
		
    }
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        return capacidad==ocupacion;

    }

	//TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte a insertar
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        boolean seCumple = false;
        if (!estaLlena()) {
            portes[ocupacion] = porte;
            ocupacion++;
            seCumple = true;
        }
        return seCumple;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id identificador del porte que buscamos
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        Porte aeropuerto = null;
        boolean seCumple = false;
        int i = 0;
        while (i < ocupacion && !seCumple) {
            if (portes[i].getID().equals(id)) {
                aeropuerto=getPorte(i);
                seCumple = true;
            }
            i++;
        }
        return aeropuerto;

    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen código de el puerto de el que sale el Porte
     * @param codigoDestino código de el puerto al que llega el Porte.
     * @param fecha en la que sale el porte.
     * @return los portes encontrados con dichos parámetros.
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaPortes listaPortes = null;
        boolean seCumple = false;
        int i = 0;
        while (i < ocupacion && !seCumple) {
            if (portes[i].coincide(codigoOrigen, codigoDestino, fecha)){
                listaPortes.insertarPorte(portes[i]);
                seCumple = true;
            }
            i++;
        }
        return listaPortes;
        }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        // confirm
        for (Porte porte : Arrays.asList(portes)) {
            if (porte != null) {
                System.out.println(porte.toString());
            }
        }
    }


    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado lee por teclado el id introducido por el usuario
     * @param mensaje solicita al usuarii que introduzca el id
     * @param cancelar palabra con la que se sale del bucle
     * @return devuelve el porte buscado
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        Porte porteAct = null;
        String id;
        do {
            System.out.print(mensaje);
            id = teclado.next();
            porteAct = buscarPorte(id);
            if (porteAct == null) {
                System.out.println("Porte no encontrado.");
            }
            if(id.equals(cancelar)){
               porteAct = null;

            }
        } while (porteAct == null);
        return porteAct;
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero nombre del fichero
     * @return devuelve si se ha escrito en la lista de portes
     */
    public boolean escribirPortesCsv(String fichero) {
        PrintWriter salida = null;
        Porte porte;
        boolean escrito = true;
        try {
            salida = new PrintWriter(new FileWriter(fichero, true));
            for (int i = 0; i < ocupacion; i++) {
                porte = portes[i];
                salida.printf("%s;%s;%08d;%C;%s\n", porte.getID(), porte.getNave().getMatricula(), porte.getOrigen(),
                        porte.getMuelleOrigen(), porte.getSalida(), porte.getDestino(), porte.getMuelleDestino(),
                        porte.getLlegada(), porte.getPrecio());
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
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero nombre del fichero
     * @param capacidad de la lista de portes
     * @param puertosEspaciales de salida y llegada de los portes
     * @param naves que transportan los portes
     * @return devuelve una lista con los portes
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        BufferedReader entrada = null;
        ListaPortes listaPortes = new ListaPortes(capacidad);
        try {
            entrada = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = entrada.readLine())!=null){
                String[] dato = linea.split(";");
                Porte porte = new Porte (dato[0], naves.buscarNave(dato[1]), puertosEspaciales.buscarPuertoEspacial(dato[2]),
                        Integer.parseInt(dato[3]), Fecha.fromString(dato[4]), puertosEspaciales.buscarPuertoEspacial(dato[5]),
                        Integer.parseInt(dato[6]), Fecha.fromString(dato[5]), Double.parseDouble(dato[8]));
                listaPortes.insertarPorte(porte);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero portes no encontrado.");
        } catch(IOException ex){
            System.out.println("Error de lectura de fichero portes.");
        } finally {
            try{
                if(entrada !=null) {
                    entrada.close();
                }
        } catch(IOException ex){
             System.out.println("Error de cierre de fichero portes.");
                }
            }
        return listaPortes;
    }
    }
