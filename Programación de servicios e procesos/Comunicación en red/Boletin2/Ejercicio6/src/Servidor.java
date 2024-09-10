import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor iniciado");
        Socket clienteConectado = null;
        while (true) {
            try {
                clienteConectado = serverSocket.accept();
            } catch (SocketException e) {
                break;
            }
            HiloServidor hiloServidor = new HiloServidor(clienteConectado);
            hiloServidor.start();
        }
        serverSocket.close();
        clienteConectado.close();
        System.out.println("Servidor finalizado...");

    }
}
