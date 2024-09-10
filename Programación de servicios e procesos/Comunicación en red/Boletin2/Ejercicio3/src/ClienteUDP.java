import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(12345);
        InetAddress destino = InetAddress.getLocalHost();
        Scanner sc = new Scanner(System.in);
        int numero = 0;

        do {
            System.out.println("Introduce un numero");
            numero = sc.nextInt();
            Numeros numeros = new Numeros();
            numeros.setNumero(numero);

            // pasar el objeto a bytes
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream salida = new ObjectOutputStream(bs);
            // enviamos objeto
            salida.writeObject(numeros);
            byte[] bytes = bs.toByteArray();
            DatagramPacket paquete = new DatagramPacket(bytes, bytes.length, destino, 34567);
            datagramSocket.send(paquete);

            byte[] recibidos = new byte[1024];
            DatagramPacket parRecibido = new DatagramPacket(recibidos, recibidos.length);
            datagramSocket.receive(parRecibido);
            // CONVERTIMOS BYTES A OBJETO
            ByteArrayInputStream bi = new ByteArrayInputStream(recibidos);
            ObjectInputStream in = new ObjectInputStream(bi);
            Numeros numeros2 = (Numeros) in.readObject();

            System.out.println(numeros2.getCuadrado());
            System.out.println(numeros2.getCubo());
        } while (numero > 0);

    }

}
