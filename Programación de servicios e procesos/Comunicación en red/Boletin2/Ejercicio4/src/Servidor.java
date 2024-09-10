import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6000);

        byte[] recibidos = new byte[1024];
        DatagramPacket paqRecibidi = new DatagramPacket(recibidos, recibidos.length);
        socket.receive(paqRecibidi);

        ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        Persona persona = (Persona) objectInputStream.readObject();

        System.out.println("OBJETO RECIBIDO: ");
        System.out.println("nombre: " + persona.getNombre() + " edad " + persona.getEdad());

        persona.setEdad(40);
        persona.setNombre("Juana");

        // datos envio
        InetAddress destino = paqRecibidi.getAddress();
        int puerto = paqRecibidi.getPort();

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bs);
        objectOutputStream.writeObject(persona);

        byte[] envio = bs.toByteArray();
        DatagramPacket envioDatagramPacket = new DatagramPacket(envio, envio.length, destino, puerto);
        socket.send(envioDatagramPacket);
        System.out.println("OBJETO ENVIADO");
        System.out.println("nombre: " + persona.getNombre() + " edad " + persona.getEdad());

        bais.close();
        bs.close();
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();

    }
}
