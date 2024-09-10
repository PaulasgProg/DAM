import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcesoJava2 {
public static void main(String[] args) {

        String archivo = "C:\\Users\\Paula\\OneDrive\\Escritorio\\TSDAM\\2ºDAM\\PSP - Programación de servicios y procesos\\UD1- Proframación multiproceso\\Ejercicios\\Tarea Procesos Java\\File.txt"; // Reemplaza con la ruta de tu archivo

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                System.out.println(linea);
            }
        } catch (IOException e) {

            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
