import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

    public static void main(String[] args) throws Exception {
        Persona persona = new Persona("Jose ", 30);
        DatagramSocket socket = new DatagramSocket();
        InetAddress destino = InetAddress.getLocalHost();
        int puerto = 6000;

        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bous);
        objectOutputStream.writeObject(persona);
        byte[] envio = bous.toByteArray();

        DatagramPacket paqenvio = new DatagramPacket(envio, envio.length, destino, puerto);
        socket.send(paqenvio);

        System.out.println("ENVIO A SERVIDOR");
        System.out.println("nombre: " + persona.getNombre() + " edad " + persona.getEdad());

        byte[] recibidos = new byte[1024];
        DatagramPacket paqrecibido = new DatagramPacket(recibidos, recibidos.length);
        socket.receive(paqrecibido);

        ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        Persona persona2 = (Persona) objectInputStream.readObject();

        System.out.println("RECIBO DE SERVIDOR");
        System.out.println("nombre: " + persona2.getNombre() + " edad " + persona2.getEdad());
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
        bais.close();
        bous.close();
    }

}
