import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ProcesoPadre {
   /*  or otro lado, habrá un proceso padre que levantará el proceso escritor, esperará a que 
finalice. Después levantará el proceso vigilante y recogerá la información en caso de 
que la haya. Por cada línea que escriba el proceso hijo será un error detectado, así que 
nuestro proceso padre creará un contador de errores y al final escribirá el número de 
errores en un fichero llamado errors.txt. */
    public static void main(String[] args) {
        ProcessBuilder pb_Escritor=new ProcessBuilder("java","ProcesoEscritor");
        pb_Escritor.directory(new File(".\\"));
        ProcessBuilder pb_Vigilante=new ProcessBuilder("java","ProcesoVigilante");
        pb_Vigilante.directory(new File(".\\"));
        pb_Vigilante.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        try {
            Process procesoEscritor=pb_Escritor.start();
            int errores=0;

            procesoEscritor.waitFor();

            Process procesoVigilante=pb_Vigilante.start();

            FileWriter error=new FileWriter(new File(".\\errores.txt"));

            BufferedReader br=new BufferedReader(new InputStreamReader(procesoVigilante.getInputStream()));
            String line;
            while ((line=br.readLine())!=null) {
                errores++;
            }
            error.write(String.valueOf(errores)); //espera un string
            error.flush();
            error.close();
            procesoVigilante.waitFor();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
