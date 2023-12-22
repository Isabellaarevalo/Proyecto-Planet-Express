/**
 * Description of the class
 *PuertoEspacial es una clase que encapsula las variables
 * usadas para definir un Puerto Espacial como es el nombre
 * y el código de un puerto.Esta clase se puede utilizar para
 * calcular distancias entre puertos, y al necesitar datos sobre
 * los puertos espaciales.
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class PuertoEspacial {
    /**
     * nombre del puerto espacial
     */
    private String nombre;
    /**
     * código del puerto espacial
     */
    private String codigo;
    /**
     * radio distancia de un puerto al Sol
     */
    private double radio;
    /**
     * el ángulo que forma el puerto desde el
     * eje positivo x hasta la proyección del punto en el plano xy,
     * siendo el Sol el eje de coordenadas
     */
    private double azimut;
    /**
     * el ángulo desde el eje positivo z hasta el puerto
     */
    private double polar;
    /**
     *número de muelles de carga del puerto espacial
     */
    private int numMuelles;

    /**
     * Constructor of the class
     * Inicializa los atributos de la clase
     *
     * @param nombre nombre del puerto espacial
     * @param codigo del puerto espacial
     * @param radio distancia de un puerto al Sol
     * @param azimut , el ángulo que forma el puerto desde el
     * eje positivo x hasta la proyección del punto en el plano xy,siendo el Sol el eje de coordenadas
     * @param polar  el ángulo desde el eje positivo z hasta el puerto
     * @param numMuelles número de muelles de carga del puerto espacial
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }
    /**
     * Getter del atributo nombre
     *  @return Nombre del puerto
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter del atributo código
     *  @return código del puerto
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Getter del atributo radio
     *  @return radio del puerto
     */
    public double getRadio() {
        return radio;
    }
    /**
     * Getter del atributo azimut
     *  @return azimut del puerto
     */
    public double getAzimut() {
        return azimut;
    }
    /**
     * Getter del atributo polar
     *  @return polar del puerto
     */
    public double getPolar() {
        return polar;
    }
    /**
     * Getter del atributo número de muelles
     *  @return número de muelles del puerto
     */
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     *
     *  Método que calcula la distancia entre dos puertos espaciales, calculando
     *  las coordenadas cartesianas, y posteriormente calculando la distancia
     *  euclídea.
     * @param destino puerto espacial del destino
     * @return la distancia entre dos puertos espaciales
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        double x1, x2=0;
        double y1, y2=0;
        double z1, z2=0;
        double d=0;
        x1 = this.radio*Math.sin(this.azimut)*Math.cos(this.polar);
        y1 = this.radio*Math.sin(this.azimut)*Math.sin(this.polar);
        z1 = this.radio*Math.cos(this.azimut);
        x2 = destino.getRadio()*Math.sin(destino.getAzimut())*Math.cos(destino.getPolar());
        y2 = destino.getRadio()*Math.sin(destino.getAzimut()*Math.sin(destino.getPolar()));
        z2 = destino.getRadio()*Math.cos(this.azimut);
        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        d = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)+Math.pow(z2-z1,2));
        return d;
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * Método que crea una cadena de texto con los datos de un puerto espacial
     * con el formato especificado
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        return nombre+"("+codigo+"), en ("+radio+" "+azimut+" "+polar+"), con "+numMuelles+" muelles" ;
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * Método que crea una cadena de texto con los datos de un puerto espacial
     * con el formato especificado
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {
        return nombre+"("+codigo+")";
    }
}
