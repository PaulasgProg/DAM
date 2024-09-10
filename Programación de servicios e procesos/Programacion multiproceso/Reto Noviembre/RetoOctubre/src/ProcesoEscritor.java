import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

public class ProcesoEscritor {
    /* Crea un proceso llamado ProcesoEscritor que escriba cada 500 milisegundos en un 
archivo llamado log.txt. Puede escribir 3 mensajes. 
• 1: Todo está OK con un 60% de probabilidad
• 2: ERROR: se ha dectacto un problema con un 30% de probabilidad
• 3: END con un 10% de probabilidad
El proceso finaliza cuando escribe END
 */
    public static void main(String[] args) throws Exception {
       /*  File file=new File(".//log.txt"); //creamos el archivo donde se va a escribir
        FileWriter fw=new FileWriter(file); //pasamos el file por un filewriter para poder escribir */
        try (BufferedWriter writer=new BufferedWriter(new FileWriter("log.txt"))){
            Random random=new Random();

            while (true) {
                

                int probabilidad=random.nextInt(100); //numero aleatorio entre 0 y 100
                if (probabilidad<60) {
                    writer.write("1: Todo está OK\n");
                }else if(probabilidad<90){
                    writer.write("2: ERROR: se ha dectacto un problema\n");
                }else{
                    writer.write("3: END \n");
                    break;
                }
                Thread.sleep(500); //espera 500 ms hasta escribir otra frase
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
