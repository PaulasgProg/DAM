import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
/* •	Cree un proceso hijo.
•	El proceso padre y el proceso hijo se comunicarán de forma bidireccional.
•	El proceso padre leerá líneas de su entrada estándar y las enviará a la entrada estándar del hijo.
•	El proceso hijo leerá el texto por su entrada estándar, lo transformará todo a letras mayúsculas y lo imprimirá por su salida estándar. 
•	El padre imprimirá en pantalla lo que recibe del hijo a través de la memoria. */
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //INICIAMOS EL PROCESO HIJO
        ProcessBuilder pbHijo=new ProcessBuilder("java","Mayuscula");
        try {
            Process pHijo=pbHijo.start();

            //Creamos un Scanner para obtener la entrada estándar y lo guardamos lo obtenido en "cadena"
            System.out.println("Introduce una cadena en minusculas");
            Scanner sc=new Scanner(System.in);
            String cadena=sc.nextLine();

            //Creamos conexión con proceso hijo para escribir en su flujo de entrada y escribimos "cadena"
            BufferedWriter bWriter= new BufferedWriter(new OutputStreamWriter(pHijo.getOutputStream())); 
            bWriter.write(cadena);
            bWriter.flush();
            bWriter.close();

            //Creamos conexión con proceso hijo para leer su flujo de salida estándar
            BufferedReader bReader=new BufferedReader(new InputStreamReader(pHijo.getInputStream()));
            String line="";
            while ((line=bReader.readLine())!=null) {
                System.out.println(line);
            }
            sc.close();// cerramos Scanner

            //Recogemos la salida del proceso Hijo
            int exitval=pHijo.waitFor();
            System.out.println("Valor de salida de proceso hijo "+exitval);

            //Para visualizar errores
            InputStream error=pHijo.getErrorStream();
            BufferedReader bferror=new BufferedReader(new InputStreamReader(error));

            String linea = null;
            while ((linea = bferror.readLine()) != null)
                System.out.println("ERROR >" + linea);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
