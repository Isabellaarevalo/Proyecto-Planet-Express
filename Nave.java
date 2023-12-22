/**
 * Description of the class
 * Nave es una clase que encapsula las variables propias
 * de un nave, como su marca,matrícula, alcance,entre otras.
 * Esta clase se puede emplear al necesitar datos sobre
 * las naves que transportan los envíos.
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class Nave {
    /**
     * marca de la nave
     */
    private String marca;
    /**
     * modelo de la nave
     */
    private String modelo;
    /**
     * matrícula de la nave
     */
    private String matricula;
    /**
     * columna de la bodega de carga de la nave para almacenar contenedores
     */
    private int columnas;
    /**
     * fila de la bodega de carga de la nave para almacenar contenedores
     */
    private int filas;
    /**
     * distancia máxima que puede recorrer la nave
     */

    private double alcance;


    /**
     * Constructor of the class
     * Incializa los atributos
     *
     * @param marca de la nave
     * @param modelo de la nave
     * @param matricula de la nave
     * @param columnas de la bodega de carga de la nave para almacenar contenedores
     * @param filas de la bodega de carga de la nave para almacenar contenedores
     * @param alcance distancia máxima que puede recorrer la nave
     */
    public Nave(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }
    /**
     * Getter del atributo marca
     *  @return Marca de la nave
     */
    public String getMarca() {
        return marca;
    }
    /**
     * Getter del atributo modelo
     *  @return Modelo de la nave
     */
    public String getModelo() {
        return modelo;
    }
    /**
     * Getter del atributo matrícuña
     *  @return Matrícula de la nave
     */
    public String getMatricula() {
        return matricula;
    }
    /**
     * Getter del atributo columnas
     *  @return columna que ocupa la nave
     */
    public int getColumnas() {
        return columnas;
    }
    /**
     * Getter del atributo filas
     *  @return filas que ocupa la nave
     */
    public int getFilas() {
        return filas;
    }
    /**
     * Getter del atributo alcance
     *  @return alcance de la nave
     */
    public double getAlcance() {
        return alcance;
    }

    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * Devuelve una cadena de texto con los datos de la nave y el formato
     * del enunciado
     * @return ejemplo del formato -> "Planet Express One (EP-245732X): 40 contenedores, hasta 1.57 UA"
     */
    public String toString() {
        return modelo+" ("+matricula+"): "+filas*columnas+" contenedores, hasta" +alcance+" UA";
    }

    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * Devuelve una cadena de texto con los datos de la nave y el formato
     * del enunciado
     * @return ejemplo del formato -> "Planet Express One (EP-245732X)"
     */
    public String toStringSimple() {
        return modelo+"("+matricula+")";
    }
}
