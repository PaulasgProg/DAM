import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcesoVigilante {

    /* Por otro lado, crea un proceso que vigile el archivo de texto log.txt. Cada 500 
milisegundos leerá el archivo, y si encuentra la palabra ERROR hará un print del error. El 
proceso finalizará cuando se lea la palabra END. */
    public static void main(String[] args) {
        try(BufferedReader reader=new BufferedReader(new FileReader(".\\log.txt"))) {

            String line;
            while ((line=reader.readLine())!=null) {
                //line=reader.readLine();
                if (line.contains("ERROR")) {
                    System.out.println("Se ha enconctado la palabra error");
                }else
                if(line.contains("END")){
                    reader.close();
                    break;
                }
                Thread.sleep(500); //espera 500 ms antes de volver a leer
            }

        } catch (IOException | InterruptedException | NullPointerException e) {
            System.err.println("cadena vacia");
        }
    }
}
