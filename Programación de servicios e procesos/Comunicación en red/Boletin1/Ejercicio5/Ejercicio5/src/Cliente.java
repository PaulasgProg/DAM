import java.io.DataInputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int puerto = 6000;
        Socket cliente = new Socket(host, puerto);

        DataInputStream dataInputStream = new DataInputStream(cliente.getInputStream());
        int num = dataInputStream.readInt();
        System.out.println("Soy el cliente numero " + num);
        dataInputStream.close();
        cliente.close();
    }

}
