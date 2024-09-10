import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(34567);

        Numeros numeros = new Numeros();
        do {
            byte[] recibidos = new byte[1024];
            DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
            server.receive(paqRecibido);
            // transformamos
            ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
            ObjectInputStream in = new ObjectInputStream(bais);
            numeros = (Numeros) in.readObject();

            int numero = numeros.getNumero();
            long cuadrado = numero * numero;
            long cubo = numero * numero * numero;

            numeros.setCuadrado(cuadrado);
            numeros.setCubo(cubo);

            InetAddress destino = paqRecibido.getAddress();
            int puerto = paqRecibido.getPort();
            // Enviamos
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream salida = new ObjectOutputStream(bout);
            salida.writeObject(numeros);

            byte[] enviados = bout.toByteArray();

            DatagramPacket paqEnvio = new DatagramPacket(enviados, enviados.length, destino, puerto);
            server.send(paqEnvio);

        } while (numeros.getNumero() > 0);
    }

}
