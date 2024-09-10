import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {
        InetAddress destino = InetAddress.getLocalHost(); // ip a la que envío
        byte[] mensaje = new byte[1024];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una cadena");
        String cadena = scanner.nextLine();
        mensaje = cadena.getBytes();

        DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, 12345);
        DatagramSocket socket = new DatagramSocket(34567);

        socket.send(envio);

        DatagramPacket recibo = new DatagramPacket(mensaje, mensaje.length);
        socket.receive(recibo);

        String resultado = new String(recibo.getData());
        System.out.println(resultado);

        socket.close();

    }

}
