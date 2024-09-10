import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class App {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(12345);
        byte[] mensaje = new byte[1024];
        DatagramPacket recibo = new DatagramPacket(mensaje, mensaje.length);
        socket.receive(recibo);

        int bytes = recibo.getLength();
        String paquete = new String(recibo.getData());

        String cadena = paquete.toUpperCase();
        mensaje = new byte[1024];
        mensaje = cadena.getBytes();
        InetAddress destino = InetAddress.getLocalHost(); // ip a la que envío
        DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, 34567);
        socket.send(envio);
        socket.close();
    }
}
