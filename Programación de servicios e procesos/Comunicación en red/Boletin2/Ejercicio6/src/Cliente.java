import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws Exception {
        Socket cliente = new Socket("localhost", 12345);

        Scanner sc = new Scanner(System.in);
        String cadena = "";
        while (!cadena.equals("*")) {
            System.out.println("Introduce una cadena");
            cadena = sc.nextLine();

            DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream());
            dataOutputStream.writeUTF(cadena);

            if (!cadena.equals("*")) {
                DataInputStream dataInputStream = new DataInputStream(cliente.getInputStream());
                String cadenaMay = dataInputStream.readUTF();

                System.out.println(cadenaMay);
            }
        }
        sc.close();
        cliente.close();

    }

}
