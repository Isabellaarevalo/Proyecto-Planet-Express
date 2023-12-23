import java.util.Scanner;

/**
 * Description of the class
 * Cliente es una clase que encapsula las variables
 * que definen a un cliente,como su nombre,email y envíos del cliente.
 * Esta clase se emplea para las funciones relacionadas con un cliente,
 * como es añadir un envío,buscarlo, o sele ccionarlo, e incluso dar de alta a
 * un cliente.
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class Cliente {
    /**
     * lista de envíos de los clientes
     */
    private final ListaEnvios listaEnvios;
    /**
     * nombre del cliente
     */
    private final String nombre;
    /**
     * apellidos del cliente
     */
    private final String apellidos;
    /**
     *email del cliente con terminación en @planetexpress.com
     */
    private final String email;

    /**
     * Constructor of the class
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }
    /**
     * Getter del atributo nombre
     *  @return Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter del atributo apellidos
     * @return Apellidos del cliente
     */
    public String getApellidos() {
        return apellidos;
    }
    /**
     * Getter del atributo email
     * @return Email del cliente
     */
    public String getEmail() {
        return email;
    }
    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    /**
     * Retorna una cadena de texto con el nombre,apellidos y email del cliente.
     * @return Cadena de texto con los datos del cliente.
     */
    public String toString() {
        return nombre+" "+apellidos+","+email;
    }
    /**
     * Método que comprueba si se ha alcanzado el
     * máximo de envíos si la lista está llena
     *
     *  * @return true si esta llena, false en caso contrario
     */
    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        return listaEnvios.estaLlena();
    }


    // TODO: Devuelve un envío en función de su posición
    /**
     * Getter del envío
     * @param i posición del envío
     * @return el envío en la posición pasada por parámetro
     */
    public Envio getEnvio(int i) {
        return getEnvio(i);
    }
    /**
     * Getter del atributo listaEnvios
     * @return la lista de envíos
     */
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }

    // TODO: Añade un envío al cliente
    /**
     * Método que comprueba si se ha añadido
     * un envío en la lista
     * @param envio que se añade en la lista
     * @return true si añade el envío en la lista de envíos del cliente,false
     * en caso contrario
     */
    public boolean aniadirEnvio(Envio envio) {
        return listaEnvios.insertarEnvio(envio);

    }
    /**
     * Método que busca un envío mediante su localizador
     * @param localizador del envío que se busca
     * @return el envío con el localizador pasado por parámetro
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }
    /**
     * Método que comprueba si se ha eliminado del envío
     * del cliente.
     * @param localizador del envío a cancelar
     *
     * @return true si se ha eliminado el envío de la lista de envíos del cliente,
     * false en caso contrario
     */
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return getListaEnvios().eliminarEnvio(localizador);

    }
    /**
     * Método que lista los envíos de un cliente
     * @return la lista con los envíos
     */
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }
    /**
     * Método que selecciona un envío de la lista envíos
     * a partir del localizador introducido por el usuario.
     * @param mensaje solicita al usuario el localizador
     * @param teclado lee por teclado el localizador introducido por el usuario
     *
     * @return devueleve el envío con un localizador válido introducido por el usuario
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }


    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado Lee los datos introducidos por el usuario.
     * @param clientes Contiene la lista con los clientes ya existentes.
     * @param maxEnvios La cantidad máxima de envíos por cliente.
     * @return Cliente Crea a un nuevo cliente con nombre,apellidos y email únicos,y el máximo de envíos.
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        String nombre, apellidos, email;
        Cliente cliente = null;
        // do while
        System.out.print("Nombre:");
        do {
            nombre = teclado.nextLine();
        }while(nombre.equals(""));
        System.out.print("Apellidos:");
        apellidos = teclado.nextLine();
        do{
            System.out.print("Email:");
            email = teclado.nextLine();
            if (!correctoEmail(email)) {
                System.out.println("Email incorrecto.");
            } else if (cliente.getEmail() != null) {
                System.out.println("Email ya existe.");
            }
        } while (!correctoEmail(email) || cliente.getEmail() != null);
        do{
            cliente = new Cliente(nombre, apellidos, email, maxEnvios);
        } while (cliente.getEmail()!=null);
        System.out.println("Cliente con email "+cliente.getEmail()+" creado correctamente.");
        return cliente;
    }


    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     * Método que comprueba que el formato del email sea correcto,
     * es decir, que su terminación sea 'planetexpress.com'
     * @param email del usuario que hay que comrpobar.
     * @return Devuelve correcto si el email introducido por el usuario tiene el formato.
     */
    public static boolean correctoEmail(String email) {
        boolean correcto = false;
        String[] emailSeparado = email.split("@");
        if (emailSeparado.length > 1) {
            if (emailSeparado[1].equals("planetexpress.com") && emailSeparado[0].matches("^[a-zA-Z]+(?:[\\. a-zA-Z]+)?$")) {
                correcto = true;
            }
        }
        return correcto;
    }
    }
