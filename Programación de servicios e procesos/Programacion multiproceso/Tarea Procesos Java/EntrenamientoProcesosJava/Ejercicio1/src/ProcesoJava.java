import java.util.Scanner;
import java.io.IOException;

public class ProcesoJava {
    public static void main(String[] args) throws IOException {

        Scanner sc= new Scanner(System.in);
    
        System.out.println("Introduce un nº");
        if (sc.hasNextInt()) {
            int numero = sc.nextInt();
            System.out.println("El resultado del proceso es: " + numero);
            // Devolver 0 si el proceso fue exitoso
            System.exit(0);
        } else {
            System.out.println("No se ha introducido un número.");
            // Devolver -1 si el proceso no fue exitoso
            System.exit(1);
        }
    }
}
