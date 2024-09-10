import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class NumeroAleatorio {

    public static void main(String[] args) throws Exception {
        //Creamos la clase random para después generar un numero
        Random random=new Random();
        //creamos un buffer para leer la entrada y lo guardamoms en "cadena"
        BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
        String cadena=bReader.readLine();

        //si el valor de cadena es diferente a "fin" se genera un numero aleatorio entre 0 y 10 y se escribe por 
        //el flujo de salida estandar
        if (!cadena.equals("fin")) {
            int numAleatorio=random.nextInt(10);
            System.out.println(numAleatorio);
        }


    }
}
