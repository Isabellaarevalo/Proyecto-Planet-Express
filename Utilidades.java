import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Isabella Arévalo
 * @author Sara Galinova
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return int numero
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
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return long numero
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
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return double numero
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
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return char letra
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
     * @param teclado
     * @param mensaje
     * @return Fecha
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
     * @param teclado
     * @param mensaje
     * @return Fecha
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
     * @param teclado
     * @param s
     * @return
     */
    public static String leerCadena(Scanner teclado, String s) {
        s = teclado.nextLine();
        System.out.print(s);
        return teclado.next();
    }

    public static char numeroLetra(int numero) {
        char[] letra = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return letra[numero - 1];
    }
    public static int letraNumero(char letra) {
        int numero = letra - 'A' + 1;
        return numero;
    }
}
