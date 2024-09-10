import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * 3. Realiza un programa servidor TCP que acepte 2 clientes. Mostrar para cada
         * cliente conectados
         * sus puertos local y remoto. Implementar también el programa cliente, donde se
         * muestren los
         * puertos locales y remotos a los que se encuentre conectado, asi como la IP de
         * la máquina
         * remota a la que se conecta.
         */

        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Servidor escucha en puerto " + serverSocket.getLocalPort());
        Socket clienteConectado = serverSocket.accept();

        System.out.println("Primer cliente atendido");
        System.out.println("Puerto del cliente 1 getLocalPort(): " + clienteConectado.getLocalPort());// su puerto
        System.out.println("Puerto del cliente 1 getPort(): " + clienteConectado.getPort()); // al que esta conectado

        Socket clienteConectado2 = serverSocket.accept();

        System.out.println("Segundo cliente atendido");
        System.out.println("Puerto del cliente 1 getLocalPort(): " + clienteConectado2.getLocalPort());// su puerto
        System.out.println("Puerto del cliente 1 getPort(): " + clienteConectado2.getPort()); // al que esta conectado

        clienteConectado.close();
        clienteConectado2.close();
        serverSocket.close();
    }
}
