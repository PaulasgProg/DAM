import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class HiloServidor extends Thread {
    /*
     * Utilizando sockets TCP, implementar un programa servidor que inicialice un
     * array de 5 objetos
     * de tipo Profesor. El servidor aceptará conexiones de clientes en un bucle
     * infinito. Cada vez
     * que se conecte un cliente, el servidor le asignará un id, que empezará en 1 e
     * irá
     * incrementándose cada vez que se conecta un nuevo cliente. El servidor
     * recibirá del cliente un
     * idProfesor y le devolverá el objeto Profesor asociado.
     */

    Socket socket = null;
    Profesor arrayProfesot[];
    DataInputStream fentrada;
    ObjectOutputStream fsalida;
    int idCliente;

    public HiloServidor(Socket s, int idCliente, Profesor[] array) {

        this.socket = s;
        this.arrayProfesot = array;
        this.idCliente = idCliente;

        try {
            fsalida = new ObjectOutputStream(socket.getOutputStream());
            fentrada = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public void run() {
        String cadena = "";
        try {
            fsalida.writeObject(idCliente);
        } catch (IOException e2) {
            System.out.println("ERROR AL ENVIAR IDCLIENTE CON CLIENTE " + idCliente);
            e2.printStackTrace();
        }

        while (!cadena.trim().equals("*")) {
            Profesor profesor;
            try {
                try {
                    cadena = fentrada.readUTF();
                } catch (SocketException dd) {
                    System.out.println("\tERROR AL LEER EL CLIENTE: " + idCliente);
                    break;
                } catch (EOFException EO) {
                    System.out.println("EL CLIENTE " + idCliente + " HA FINALIZADO ");
                    break;
                }
                System.out.println("\tConsultando id: " + cadena + ", solicitado por cliente: " + idCliente);

                int id = Integer.parseInt(cadena);
                profesor = DatosProfesor(id);

                fsalida.reset();
                fsalida.writeObject(profesor);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } // fin while

        System.out.println("FIN CON: " + socket.toString() + " DEL CLIENTE:  " + idCliente);
        try {
            fsalida.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            fentrada.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // devuelve el profesor de identificador
    private Profesor DatosProfesor(int identificador) {
        Especialidad noexiste = new Especialidad(0, "sin datos");

        Profesor profesor = new Profesor(identificador, "No existe", null, noexiste);

        for (int i = 0; i < arrayProfesot.length; i++) {
            if (arrayProfesot[i].getIdProfesor() == identificador)
                profesor = arrayProfesot[i];
        }
        return profesor;
    }

}
