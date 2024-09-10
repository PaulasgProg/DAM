import java.util.Scanner;

public class LeerNombre {

    public static void main(String[] args) {
        
    
        if (args.length > 0) {
            String nombre = args[0];
            System.out.println("Nombre recibido: " + nombre);
            System.exit(0); // Éxito
        } else {
            System.err.println("No se proporcionó un nombre.");
            System.exit(1); // Error
        }
    }
}