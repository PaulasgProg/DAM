import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String line="";
        
        try {
            //Iniciamos el proceso hijo
            ProcessBuilder pbHijo=new ProcessBuilder("java","NumeroAleatorio");
            Process pHijo= pbHijo.start();

            //Iniciamos bucle que solo parará cuando line=fin
            while (!line.equals("fin")) {
                //Creamos un scanner para leer la entrada del teclado y guardamos en "line"
                Scanner sc= new Scanner(System.in);
                System.out.println("Introduce una cadena");
                line=sc.nextLine();

                //Creamos un buffer para leer las salidas del proceso hijo 
                BufferedReader reader=new BufferedReader(new InputStreamReader(pHijo.getInputStream()));

                //Creamos un print para escribir en el flujo de entrada del proceso hijo
                PrintStream ps=new PrintStream(pHijo.getOutputStream());
                ps.println(line);
                ps.flush();

                //Leemos la salida estandar del proceso hijo
                //Si la linea no es nula lo escribe por pantalla
                String hijoline="";
                while (((hijoline=reader.readLine())!=null)) {
                    System.out.println(hijoline);

                }
            }
            //VALOR DE SALIDA DEL PROCESO HIJO
            int exitVal;

            exitVal = pHijo.waitFor();
            System.out.println("Valor de Salida: " + exitVal);

            //Para visualizar los errores que sucedieron
            InputStream error = pHijo.getErrorStream();
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(error));
            String linea = null;
            while ((linea = bfReader.readLine()) != null)
                System.out.println("ERROR >" + linea);
        
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
