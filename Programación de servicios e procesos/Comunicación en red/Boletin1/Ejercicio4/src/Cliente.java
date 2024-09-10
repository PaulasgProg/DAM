import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int puerto = 6000;
        Socket cliente = new Socket(host, puerto);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un numero entero");
        int entero = 0;
        try {
            entero = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("error");
            cliente.close();
            return;
        }

        DataOutputStream flujosalida = new DataOutputStream(cliente.getOutputStream());
        flujosalida.writeInt(entero);

        DataInputStream flujoentrada = new DataInputStream(cliente.getInputStream());
        int cuadrado = flujoentrada.readInt();

        System.out.println("El cuadrado de " + entero + " es " + cuadrado);

        flujoentrada.close();
        flujosalida.close();
        cliente.close();
        scanner.close();
    }
}