import java.io.*;
import java.io.FileReader;
import java.io.IOException;

public class ProcesoJava3 {

    public static void main(String[] args) throws IOException, InterruptedException{

        String archivo = "C:\\Users\\Paula\\OneDrive\\Escritorio\\TSDAM\\2ºDAM\\PSP - Programación de servicios y procesos\\UD1- Proframación multiproceso\\Ejercicios\\Tarea Procesos Java\\File.txt"; // Reemplaza con la ruta de tu archivo

        //preparamos proceso para que abra el archivo de manera gráfica
        ProcessBuilder pb= new ProcessBuilder("notepad.exe",archivo);

        //abrimos proceso con start
        Process p2=pb.start();

        //hay algun error?
        int exitVal;

        try {

            exitVal = p2.waitFor();
            System.out.println("Valor de Salida: " + exitVal);

        } catch (InterruptedException e) {
            e.printStackTrace();//no se que es
        }
       
    }
    
}
