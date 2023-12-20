import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaEnvios {
    private Envio[] envios;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad máxima de la lista
     * @param ocupacion
     */
    private int capacidad;
    private int ocupacion =0;
    public ListaEnvios(int capacidad) {
        this.capacidad = capacidad;
        this.envios = new Envio[capacidad];
		
    }

    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        return ocupacion;

    }
    // TODO: ¿Está llena la lista de envíos?
    public boolean estaLlena() {
        return ocupacion == capacidad;

    }
	//TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i) {
        Envio envio = null;
        if (i<=ocupacion){
            envio = envios[i];
    }
            return envio;
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     * @param envio
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
     * @param localizador
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
            }
            i++;
        return envio;
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte
     * @param fila
     * @param columna
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
     * @param localizador
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
     */
    public void listarEnvios() {
        for (Envio envio1 : envios) {
            if (envio1 != null) {
                System.out.println(envio1.toString());
            }
        }
    }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado
     * @param mensaje
     * @return
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
     * @param fichero
     * @return
     */
    public boolean aniadirEnviosCsv(String fichero) {
        //confirmar que no sobreescribe la información
        PrintWriter pw = null;
        boolean escrito = true;
        try {
            pw = new PrintWriter(new FileWriter(fichero, true));
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
     * @param ficheroEnvios
     * @param portes
     * @param clientes
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
