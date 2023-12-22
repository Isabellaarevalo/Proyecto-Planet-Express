import java.util.Scanner;

/**
 * Description of the class
 *Utilidades es una clase que se puede emplear para diversas acciones
 * como leer un numero,caracter,así como una fecha, y comprobando que
 * los datos introducidos son correctos.
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
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
     * @param teclado lee por teclado el número introducido por el usuario
     * @param mensaje solicita al usuario que introzuca un número
     * @param minimo valor mínimo entre el que puede estar comprendido el número
     * @param maximo valor máximo entre el que puede estar comprendido el número
     * @return long numero devuelve el núemero de tipo long cuando sea válido
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero;
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
     * @param teclado lee por teclado el número introducido por el usuario
     * @param mensaje solicita al usuario que introzuca un número
     * @param minimo valor mínimo entre el que puede estar comprendido el número
     * @param maximo valor máximo entre el que puede estar comprendido el número
     * @return double numero devuelve el núemero de tipo double cuando sea válido
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero;
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
     * @param teclado lee por teclado la letra introducida por el usuario
     * @param mensaje solicita al usuario que introzuca una letra
     * @param minimo valor mínimo entre el que puede estar comprendida la letra
     * @param maximo valor máximo entre el que puede estar comprendida la letra
     * @return char letra devuelve la letra cuando sea válida
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra;
        do {
            System.out.print(mensaje);
            letra = teclado.next().charAt(0);
            if (letra < minimo)
                System.out.println("El número " + letra + " no es mayor que " + minimo);
            else if (letra > maximo)
                System.out.println("El número " + letra + " no es menor que " + maximo);
        } while (letra < minimo || letra > maximo);
        return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
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
     * @param teclado lee por teclado el string
     * @param s el string pasado por parámetro
     * @return imprime el string del parámetro
     */
    public static String leerCadena(Scanner teclado, String s) {
        s = teclado.nextLine();
        System.out.print(s);
        return teclado.next();
    }
}
