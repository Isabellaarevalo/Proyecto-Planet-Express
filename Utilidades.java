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
        int num = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            if (teclado.hasNextInt()) {
                num = teclado.nextInt();
                if (num >= minimo && num <= maximo) {
                    valido = true;
                }
            } else {
                teclado.next();
            }
        }
        return num;
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
        long numLg = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            if (teclado.hasNextLong()) {
                numLg = teclado.nextLong();
                if (numLg >= minimo && numLg <= maximo) {
                    valido = true;
                }
            } else {
                teclado.next();
            }
        }
        return numLg;
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
        double numDou=-1.1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            if (teclado.hasNextDouble()) {
                numDou = Double.parseDouble(teclado.next().replace(',','.'));;
                if (numDou >= minimo && numDou <= maximo) {
                    valido = true;
                }
            } else {
                teclado.next();
            }
        }
        return numDou;
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
     * @param teclado lee por teclado la fecha introducida por el usuario
     * @param mensaje pide al usuario que introduzca una fecha
     * @return Fecha devuelve una fecha correcta con su dia,mes y año
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        System.out.println(mensaje);
        int dia, mes, anio;
        boolean fechaCorrecta = false;
        do {
            dia = leerNumero(teclado, "Ingrese día:", 1, Fecha.DIAS_MES);
            mes = leerNumero(teclado, "Ingrese mes:", 1, Fecha.MESES_ANIO);
            anio = leerNumero(teclado, "Ingrese año:", Fecha.PRIMER_ANIO, Fecha.ULTIMO_ANIO);
            fechaCorrecta = Fecha.comprobarFecha(dia, mes, anio);
            if (!fechaCorrecta) {
                System.out.println("Fecha introducida incorrecta.");
            }
        } while (!fechaCorrecta);
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
        int hora, minuto, segundo = 0, dia, mes, anio;
        System.out.println(mensaje);
        boolean horaCorrecta = false, fechaCorrecta = false;
        do {
            dia = leerNumero(teclado, "Ingrese día:", 1, Fecha.DIAS_MES);
            mes = leerNumero(teclado, "Ingrese mes:", 1, Fecha.MESES_ANIO);
            anio = leerNumero(teclado, "Ingrese año:", Fecha.PRIMER_ANIO, Fecha.ULTIMO_ANIO);
            hora = leerNumero(teclado, "Ingrese hora:", 1, 23);
            minuto = leerNumero(teclado, "Ingrese minuto:", 0, 59);
            segundo = leerNumero(teclado, "Ingrese segundo:", 0, 59);
            horaCorrecta = Fecha.comprobarHora(hora, minuto, segundo);
            fechaCorrecta = Fecha.comprobarFecha(dia, mes, anio);
            if (!(fechaCorrecta && horaCorrecta)) {
                System.out.println("Fecha u hora introducida incorrecta.");
            }
        } while (!(fechaCorrecta && horaCorrecta));
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
