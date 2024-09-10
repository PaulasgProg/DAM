import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.crypto.AEADBadTagException;

public class Mayuscula {
    /* •	Cree un proceso hijo.
•	El proceso padre y el proceso hijo se comunicarán de forma bidireccional.
•	El proceso padre leerá líneas de su entrada estándar y las enviará a la entrada estándar del hijo.
•	El proceso hijo leerá el texto por su entrada estándar, lo transformará todo a letras mayúsculas y lo imprimirá por su salida estándar. 
•	El padre imprimirá en pantalla lo que recibe del hijo a través de la memoria. */

    public static void main(String[] args) throws Exception {

        //Creamos un buffer para recoger la entrada  y lo guardamos en "cadena"
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String cadena=br.readLine();
        //Pasamos esa cadena a mayusculas mediante el siguiente método
        String cadenaMayus=cadena.toUpperCase();
        //Escribimos por la salida estandar el resultado
        System.out.println(cadenaMayus);

    }
}
