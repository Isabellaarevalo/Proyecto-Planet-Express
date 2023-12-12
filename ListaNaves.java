import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class ListaNaves {
    private Nave[] naves;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     * @param ocupacion
     */
    private int capacidad;
    private int ocupacion;
    public ListaNaves(int capacidad) {
        this.capacidad = capacidad;
        this.naves = new Nave[capacidad];
		
    }

    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        return ocupacion;

    }
    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {
        return capacidad == ocupacion;

    }
	// TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave
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
     * @param matricula
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
     * @param teclado
     * @param mensaje
     * @param alcance
     * @return
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        //confirm el equals si lo de dentro de paréntesis está correcto
        Nave nave = null;
        String matricula;
        do {
            System.out.print(mensaje);
            matricula = teclado.next();
            nave = buscarNave(matricula);
            if (nave == null && !matricula.equals(matricula)) {
                System.out.println("Matrícula de nave no encontrada.");
            }

        } while (nave == null && !matricula.equals(matricula));
        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre
     * @return
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("naves.csv",true));
            mostrarNaves();


            return true;
        } catch (FileNotFoundException e) {
            pw.println("Fichero naves.csv no encontrado.");
            return false;
        }catch(IOException e){
            pw.println("Error de escritura en fichero naves.csv.");
        }catch (IOException e){
            pw.println("Error de cierre de fichero naves.csv.");
        }
        finally {
            if(pw != null){
                
            }


        }
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        Scanner sc = null;
        try {

        } catch (Exception e) {
            return null;
        } finally {

        }
        return listaNaves;
    }
}
