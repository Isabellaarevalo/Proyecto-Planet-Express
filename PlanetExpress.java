import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class PlanetExpress {
    private final int maxPortes;
    private final int maxNaves;
    private final int maxClientes;
    private final int maxEnviosPorCliente;
    private ListaPuertosEspaciales listaPuertosEspaciales;
    private final int maxPuertosEspaciales;
    private ListaNaves listaNaves;
    private ListaClientes listaClientes;
    private ListaPortes listaPortes;


    /**
     * TODO: Rellene el constructor de la clase
     *
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente Máximo número de envíos por cliente.
     */
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales = maxPuertosEspaciales;
        this.maxNaves = maxNaves;
        this.maxPortes= maxPortes;
        this.maxClientes = maxClientes;
        this.maxEnviosPorCliente = maxEnviosPorCliente;
    }


    /**
     * TODO: Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     *  la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     * @param ficheroPuertos nombre del fichero de los puertos espaciales
     * @param ficheroNaves nombre del fichero de las naves
     * @param ficheroPortes nombre fichero de los portes
     * @param ficheroClientes nombre del fichero con los clientes
     * @param ficheroEnvios nombre del fichero con los envíos
     */
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos, maxPuertosEspaciales);
        listaNaves = ListaNaves.leerNavesCsv(ficheroNaves, maxNaves);
        listaPortes = ListaPortes.leerPortesCsv(ficheroPortes, maxPortes, listaPuertosEspaciales, listaNaves);
        listaClientes = ListaClientes.leerClientesCsv(ficheroClientes, maxClientes, maxEnviosPorCliente);
        ListaEnvios.leerEnviosCsv(ficheroEnvios, listaPortes, listaClientes);
    }


    /**
     * TODO: Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     * @param ficheroPuertos nombre del fichero con los puertos espaciales
     * @param ficheroNaves nombre del fichero con las naves
     * @param ficheroPortes nombre del fichero con los portes
     * @param ficheroClientes nombre del fichero con los clientes
     * @param ficheroEnvios nombre del fichero con los envíos
     */
    public void guardarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaPuertosEspaciales.escribirPuertosEspacialesCsv(ficheroPuertos);
        listaNaves.escribirNavesCsv(ficheroNaves);
        listaPortes.escribirPortesCsv(ficheroPortes);
        listaClientes.escribirClientesCsv(ficheroClientes);

        /*
        PrintWriter entrada= null;
        try{
            entrada= new PrintWriter((new FileWriter(ficheroEnvios, false)));
            entrada.print("");
        }catch(FileNotFoundException ex) {
            System.out.println("Fichero Envios no encontrado.");
        }catch (IOException ex) {
                System.out.println("Error de escritura en fichero de Envíos.");
        }finally{
            if(entrada!=null){
                entrada.close();
            }
        }
        */

    }
    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }
    public boolean insertarPorte (Porte porte) {
        return listaPortes.insertarPorte(porte);
    }
    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }
    public boolean insertarCliente (Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }

    /**
     * TODO: Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     *  Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     * @param teclado lee por teclado los datos introducidos por el usuario
     * @return devuelve una liste de los portes entre los puertos espaciales en una fecha
     * de salida determinada
     */
    public ListaPortes buscarPorte(Scanner teclado) {
        Fecha fecha;
        int fila, columna;
        double precio;
        String codigoOrigen = null, codigoDestino = null, email;
        char letra;
        Random rand = new Random();
        do {
            codigoOrigen = Utilidades.leerCadena(teclado, "Introduzca el Código Origen:");
        } while (listaPuertosEspaciales.buscarPuertoEspacial(codigoOrigen) == null);

        do {
            codigoDestino = Utilidades.leerCadena(teclado, "Introduzca el Código Destino:");
        } while (listaPuertosEspaciales.buscarPuertoEspacial(codigoDestino) == null);

        do {
            fecha = (Utilidades.leerFecha(teclado, "Fecha de salida:"));
            if (fecha == null) {
                System.out.println("Fecha introducida incorrecta.");
            }
        } while (fecha == null);
        return this.listaPortes.buscarPortes(codigoOrigen, codigoDestino, fecha);


       /* porte.seleccionarPorte(teclado,"Seleccione un porte:","CANCELAR");
        if (porte == null){
            System.out.println("Porte no encontrado.");
            do{
                porte.seleccionarPorte(teclado,"Seleccione un porte:","CANCELAR");
            } while (porte ==null);
        }

        do{
            System.out.println("¿Comprar envío para un nuevo cliente (n), o para uno ya existente (e)?");
            letra = teclado.next().charAt(0);
            if(letra != 'n'&& letra != 'e'){
                System.out.println("El valor de entrada debe ser 'n' o 'e'");
            }
        }while(letra != 'n'&& letra != 'e');
        do {
            System.out.println("Email del cliente: ");
            email = teclado.nextLine();
            if(listaClientes.buscarClienteEmail(email)==null){
                System.out.println("Email no encontrado");
            }
        }while (listaClientes.buscarClienteEmail(email)==null);

        Cliente cliente =listaClientes.buscarClienteEmail(email);

        do{System.out.println("Fila del hueco: ");
            fila = teclado.nextInt();
            System.out.println("Columna del hueco: ");
            columna = teclado.nextInt();
        }while(!porte.getPorte(i).huecoOcupado(fila,columna));

        System.out.println("Precio del envío: ");
        precio = teclado.nextInt();
        Envio.altaEnvio(teclado, rand, porte, cliente);
        System.out.println("Envío "porte."creado correctamente");

        return listaPortes.buscarPortes(codigoOrigen, codigoDestino, fecha);
    }
    */
    }

    /**
     * TODO: Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     *  especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  ejemplos de ejecución en el enunciado de la prática)
     * @param teclado lee por teclado los datos introducidos por el usuario
     * @param rand genera
     * @param porte devuelve el envío contratado por el usuario
     */
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) {
        char eleccion;
        Cliente cliente = null;
        Envio envio;
        if (porte != null) {
            eleccion = Utilidades.leerLetra(teclado, "¿Comprar envío para un nuevo cliente (n), o para uno ya existente (e)?", 'n', 'e');
            eleccion = teclado.next().charAt(0);
            if (eleccion != 'n' && eleccion != 'e') {
                System.out.println("El valor de entrada debe ser 'n' o 'e'");
                if (eleccion == 'n' || eleccion == 'e') {
                    if (eleccion == 'n' && !maxClientesAlcanzado()) {
                        teclado.nextLine();
                        cliente = Cliente.altaCliente(teclado, listaClientes, maxEnviosPorCliente);
                        insertarCliente(cliente);
                    } else if (eleccion == 'e') {
                        cliente = listaClientes.seleccionarCliente(teclado, "Ingrese email del pasajero:");
                    }
                }
            } else {
                cliente = listaClientes.seleccionarCliente(teclado, "Ingrese email del pasajero:");
            }
            if (cliente.maxEnviosAlcanzado()) {
                System.out.println("El Cliente seleccionado no puede adquirir más envíos.");
            } else {
                envio = Envio.altaEnvio(teclado, rand, porte, cliente);
                porte.ocuparHueco(envio);
                cliente.aniadirEnvio(envio);
                System.out.println("Envio " + envio.getLocalizador() + " comprado con éxito.");
            }
        }
    }


    /**
     * TODO Metodo statico con la interfaz del menú de entrada a la App.
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     * @param teclado lee por teclado los datos introducidos por el usuario
     * @return opción seleccionada
     */
    public static int menu(Scanner teclado) {
        int opcion = 0;
        System.out.println("1. Alta de Porte\n" + "2. Alta de Cliente\n" + "3. Buscar Porte\n" +
                "4. Mostrar envíos de un cliente\n" + "5. Generar lista de envíos\n" + "0. Salir");
        opcion = Utilidades.leerNumero(teclado, "Seleccione opción:", 0, 5);
        return opcion;
    }

    /**
     * TODO: Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     *  llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     *  guarda los datos de PlanetExpress en los mismos ficheros CSV.
     * @param args argumentos de la línea de comandos, recibe **10 argumentos** estrictamente en el siguiente orden:
     * 1. Número máximo de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * 2. Número máximo de naves que tendrá la lista de naves de PlanetExpress App.
     * 3. Número máximo de portes que tendrá la lita de portes de PlanetExpress App.
     * 4. Número máximo de clientes que tendrá la lista de clientes de PlanetExpress App.
     * 5. Número máximo de envíos por pasajero.
     * 6. Nombre del fichero CSV que contiene la lista de puertos espaciales de PlanetExpress App (tanto para lectura como escritura).
     * 7. Nombre del fichero CSV que contiene la lista de naves de PlanetExpress App (tanto para lectura como escritura).
     * 8. Nombre del fichero CSV que contiene la lista de portes de PlanetExpress App (tanto para lectura como escritura).
     * 9. Nombre del fichero CSV que contiene la lista de clientes de PlanetExpress App (tanto para lectura como escritura).
     * 10. Nombre del fichero CSV que contiene los envíos adquiridos en PlanetExpress App (tanto para lectura como escritura).
     * En el caso de que no se reciban exactamente estos argumentos, el programa mostrará el siguiente mensaje
     * y concluirá la ejecución del mismo: `Número de argumentos incorrecto`.
     */
    public static void main(String[] args) {
        Random rand = new Random();
        if (args.length != 10) {
            System.out.println("Número de argumentos incorrecto");
        }
        else{
            PlanetExpress planetExpress = new PlanetExpress(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            planetExpress.cargarDatos(args[5], args[6], args[7], args[8], args[9]);
            int opcion;
            Scanner teclado = new Scanner(System.in);
            do {
                opcion = menu(teclado);

                switch (opcion) {
                    case 1:     // TODO: Alta de Porte
                        Porte porte = Porte.altaPorte(teclado, rand, planetExpress.listaPuertosEspaciales, planetExpress.listaNaves,
                        planetExpress.listaPortes);
                        planetExpress.listaPortes.insertarPorte(porte);
                        break;
                    case 2:     // TODO: Alta de Cliente
                        Cliente cliente = Cliente.altaCliente(teclado, planetExpress.listaClientes, planetExpress.maxEnviosPorCliente);
                        planetExpress.listaClientes.insertarCliente(cliente);
                        break;
                    case 3:     // TODO: Buscar Porte

                        break;
                    case 4:     // TODO: Listado de envíos de un cliente

                        break;
                    case 5:     // TODO: Lista de envíos de un porte


                        break;
                }
            } while (opcion != 0);
        }


    }
}
