import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {

        /*
         * Implementar también el programa cliente, donde se muestren los
         * puertos locales y remotos a los que se encuentre conectado, asi como la IP de
         * la máquina
         * remota a la que se conecta.
         */

        String nombreMaquina = "localhost";
        int puertoServidor = 6000;
        Socket cliente = null;
        try {
            cliente = new Socket(nombreMaquina, puertoServidor);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InetAddress inetAddress = cliente.getInetAddress();
        System.out.println("Puerto local " + cliente.getLocalPort());
        System.out.println("Puerto al que se conecta " + cliente.getPort());
        System.out.println("Nombre Host/Ip " + cliente.getInetAddress()); // direccion a la que está conectado
        System.out.println("Host remoto " + inetAddress.getHostName().toString());
        System.out.println("IP host remoto " + inetAddress.getHostAddress().toString());

        try {
            cliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
