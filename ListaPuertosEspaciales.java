import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     * @param ocupacion
     */
    private int capacidad;
    private int ocupacion;
    public ListaPuertosEspaciales(int capacidad) {
        this.capacidad = capacidad;
        this.lista = new PuertoEspacial[capacidad];
        
		
    }
    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        return ocupacion;

    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        return capacidad == ocupacion;

    }
	// TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial
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
     * @param codigo
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
     * @param teclado
     * @param mensaje
     * @return
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
     * @param nombre
     * @return
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
     * @param fichero
     * @param capacidad
     * @return
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
