import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket envio;
        InetAddress destino = InetAddress.getLocalHost();
        int puerto = 6000;

        String cadena = "";
        int idAlumno = 0;

        while (!cadena.equals("*")) {
            System.out.println("Introduce un id: ");
            cadena = sc.nextLine();
            try {
                idAlumno = Integer.parseInt(cadena);

            } catch (NumberFormatException e) {
                continue;
            }
            if (cadena.trim().equals("*")) {
                break;
            }
            ByteArrayOutputStream baiArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(baiArrayOutputStream);
            objectOutputStream.writeObject(idAlumno);

            byte[] bytesenvio = baiArrayOutputStream.toByteArray();

            envio = new DatagramPacket(bytesenvio, bytesenvio.length, destino, puerto);
            socket.send(envio);
            objectOutputStream.close();

            try {
                byte[] recibido = new byte[1024];
                DatagramPacket paqueterecibido = new DatagramPacket(recibido, recibido.length);
                socket.setSoTimeout(5000);
                socket.receive(paqueterecibido);
                ByteArrayInputStream bais = new ByteArrayInputStream(recibido);
                ObjectInputStream objectinputStream = new ObjectInputStream(bais);

                Alumno alumno = (Alumno) objectinputStream.readObject();
                objectinputStream.close();

                // visualizo datos
                System.out.printf("\tNombre: %s, Curso: %s - %s, Nota: %d %n",
                        alumno.getNombre(), alumno.getCurso().getId(),
                        alumno.getCurso().getDescripcion(),
                        alumno.getNota());

            } catch (InterruptedIOException e) {
                System.out.println("Paquete perdido");
            }

        }

    }

}
