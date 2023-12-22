import java.util.Scanner;

/**
 * Description of the class
 * Utilidades es una clase que se puede emplear para diversas acciones
 * como leer un numero,caracter,así como una fecha, y comprobando que
 * los datos introducidos son correctos.
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * Método que solicita un número hasta que se introduzca uno entre
     * los límites pasados por parámetro.
     * @param teclado lee por teclado el número introducido por el usuario
     * @param mensaje solicita al usuario que introzuca un número
     * @param minimo valor mínimo entre el que puede estar comprendido el número
     * @param maximo valor máximo entre el que puede estar comprendido el número
     * @return int numero devuelve el núemero entero cuando sea válido
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;
        do {
            System.out.print(mensaje);
            numero = teclado.nextInt();
            if (numero < minimo)
                System.out.println("El número " + numero + " no es mayor que " + minimo);
            else if (numero > maximo)
                System.out.println("El número " + numero + " no es menor que " + maximo);
        } while (numero < minimo || numero > maximo);
        return numero;

    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * Método que solicita un número hasta que se introduzca uno entre
     * los límites pasados por parámetro.
     * @param teclado lee por teclado el número introducido por el usuario
     * @param mensaje solicita al usuario que introzuca un número
     * @param minimo valor mínimo entre el que puede estar comprendido el número
     * @param maximo valor máximo entre el que puede estar comprendido el número
     * @return long numero devuelve el núemero de tipo long cuando sea válido
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            if (teclado.hasNextLong()) {
                numero = teclado.nextLong();
                if (numero >= minimo && numero <= maximo) {
                    valido = true;
                }
            } else {
                teclado.next();
            }
        }
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * Método que solicita un número hasta que se introduzca uno entre
     * los límites pasados por parámetro.
     * @param teclado lee por teclado el número introducido por el usuario
     * @param mensaje solicita al usuario que introzuca un número
     * @param minimo valor mínimo entre el que puede estar comprendido el número
     * @param maximo valor máximo entre el que puede estar comprendido el número
     * @return double numero devuelve el núemero de tipo double cuando sea válido
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero;
        boolean valido = false;
        while (!valido){

        }
        do {
            System.out.print(mensaje);
            numero = teclado.nextInt();
            if (numero < minimo)
                System.out.println("El número " + numero + " no es mayor que " + minimo);
            else if (numero > maximo)
                System.out.println("El número " + numero + " no es menor que " + maximo);
        } while (numero < minimo || numero > maximo);
        return numero;
    }

    /**
     * TODO: TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * Método que solicita una letra hasta que se introduzca uno entre
     * los límites pasados por parámetro.
     * @param teclado lee por teclado la letra introducida por el usuario
     * @param mensaje solicita al usuario que introzuca una letra
     * @param minimo valor mínimo entre el que puede estar comprendida la letra
     * @param maximo valor máximo entre el que puede estar comprendida la letra
     * @return char letra devuelve la letra cuando sea válida
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String entrada = teclado.next();
            if (entrada.length() == 1) {
                letra = entrada.charAt(0);
                if (letra >= minimo && letra <= maximo) {
                    valido = true;
                }
            }
        }
        return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * Método que solicita una fecha hasta que se introduzca una correcta.
     * @param teclado lee por teclado la fecha introducida por el usuario
     * @param mensaje pide al usuario que introduzca una fecha
     * @return Fecha devuelve una fecha correcta con su dia,mes y año
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia=0;
        int mes=0;
        int anio=0;
        Fecha.comprobarFecha(dia, mes, anio);
        return new Fecha(dia, mes, anio);
    }


    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * Método que solicita una fecha y hora hasta que se introduzcan unas correctas.
     * @param teclado lee por teclado la fecha y hora introducida por el usuario
     * @param mensaje solicita al usuario que introzuca una fecha y hora
     * @return Fecha devuelve una fecha correcta con su dia,mes y año, y una hora
     * correcta con su hora,minutos y segundos.
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia=0;
        int mes=0;
        int anio=0;
        int hora=0;
        int minuto=0;
        int segundo=0;
        Fecha.comprobarFecha(dia, mes, anio);
        Fecha.comprobarHora(hora,minuto,segundo);
        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }

    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     * Método que imprime por pantalla el String que se pasa por parámetro
     * @param teclado lee por teclado el string
     * @param s el string pasado por parámetro
     * @return imprime el string del parámetro
     */
    public static String leerCadena(Scanner teclado, String s) {
        s = teclado.nextLine();
        System.out.print(s);
        return teclado.next();
    }
    /**
     * Método que devuelve una letra dado un número
     * @param numero que va a ser equivalente a la posición de la letra
     *  @return la letra de la lista
     */
    public static char numeroLetra(int numero) {
        char[] letra = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return letra[numero - 1];
    }/**
     * Método que devuelve un número dada una letra
     * @param letra que va a ser equivalente al número
     *  @return el número que ocupa la letra
     */
    public static int letraNumero(char letra) {
        int numero = letra - 'A' + 1;
        return numero;
    }
}
