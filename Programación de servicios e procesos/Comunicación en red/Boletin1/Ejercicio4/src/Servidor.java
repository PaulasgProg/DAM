import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        /*
         * 4. Crea un programa cliente que introduzca por teclado un número entero y se
         * lo envíe al
         * servidor. El servidor le devolverá el cuadrado del número.
         */
        ServerSocket serverSocket = new ServerSocket(6000);
        Socket clienteConectado = serverSocket.accept();

        System.out.println("Cliente conectado");
        DataOutputStream outputStream = new DataOutputStream(clienteConectado.getOutputStream());
        DataInputStream inputStream = new DataInputStream(clienteConectado.getInputStream());

        // recibe numero del cliente
        int numero = inputStream.readInt();

        int resultado = numero * numero;

        // escribe numero a cliente
        outputStream.writeInt(resultado);
        outputStream.close();
        inputStream.close();
        serverSocket.close();
        clienteConectado.close();
    }
}
