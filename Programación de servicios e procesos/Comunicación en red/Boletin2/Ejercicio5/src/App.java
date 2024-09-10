import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class App {
    static Alumno arrayObjetos[] = new Alumno[5];

    public static void main(String[] args) throws Exception {
        // Creamos objetos en cada posicion

        Curso dam1 = new Curso("1", "Primero de CFGS DAM");
        Curso dam2 = new Curso("2", "Segundo de DAM");

        arrayObjetos[0] = new Alumno("20", "Fernando", dam1, 6);
        arrayObjetos[1] = new Alumno("32", "Epi", dam2, 4);
        arrayObjetos[2] = new Alumno("1", "Blas", dam2, 8);
        arrayObjetos[3] = new Alumno("25", "Manuela", dam1, 6);
        arrayObjetos[4] = new Alumno("4", "Alicia", dam2, 4);

        DatagramSocket servidor = new DatagramSocket(6000);

        byte[] recibido = new byte[1024];
        String id = "";
        while (!id.equals("*")) {
            DatagramPacket paqrecibido = new DatagramPacket(recibido, recibido.length);
            servidor.receive(paqrecibido);

            ByteArrayInputStream bais = new ByteArrayInputStream(recibido);
            ObjectInputStream in = new ObjectInputStream(bais);
            id = (String) in.readObject().toString();
            if (id.equals("*")) {
                break;
            }

            in.close();

            // obtenemos direccion
            InetAddress destino = paqrecibido.getAddress();
            int puerto = paqrecibido.getPort();

            // buscamos alumno
            Alumno alumno = DatosAlumno(id);

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bout);
            objectOutputStream.writeObject(alumno);

            byte[] envio = bout.toByteArray();
            DatagramPacket paqueteEnvio = new DatagramPacket(envio, envio.length, destino, puerto);
            servidor.send(paqueteEnvio);

            objectOutputStream.close();

        }

    }

    // devuelve el alumno de identificador i
    private static Alumno DatosAlumno(String identificador) {
        Curso noexiste = new Curso("*", "Sin datos");
        Alumno al = new Alumno(identificador, "No existe", noexiste, -1);
        for (int i = 0; i < arrayObjetos.length; i++) {
            if (arrayObjetos[i].getIdalumno().equals(identificador))
                al = arrayObjetos[i];
        }
        return al;
    }

}
