import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws Exception {
        Socket cliente = new Socket("localhost", 6000);

        // Flujo salida
        DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

        // Flujo entrada
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

        Scanner sc = new Scanner(System.in);

        int idCliente = (int) entrada.readObject();
        System.out.println("SOY EL CLIENTE: " + idCliente);

        String cadena = "";
        Profesor profe;
        while (true) {
            System.out.println("=====================================================");
            System.out.print("Introduce identificador a consultar: ");
            cadena = sc.nextLine();

            if (cadena.trim().equals("*"))
                break;

            try {
                Integer.parseInt(cadena);
            } catch (NumberFormatException nex) {
                System.out.println("\tIdentificador incorrecto: ");
                continue;
            }

            // ENVIANDO AL SERVIDOR
            try {
                salida.writeUTF(cadena);
            } catch (SocketException se) {
                System.out.println("ERROR AL ENVIAR DATOS AL SERVIDOR (el proceo finalizar )...");
                break;
            }

            // RECIBIENDO DEL SERVIDOR
            profe = (Profesor) entrada.readObject();

            // visualizo datos
            System.out.printf("\tNombre: %s, Especialidad: %d - %s %n", profe.getNombre(),
                    profe.getEspecialidad().getId(),
                    profe.getEspecialidad().getNombreEsp());

            Asignatura[] asig = profe.getAsignaturas();
            try {
                for (int j = 0; j < asig.length; j++) {
                    System.out.printf("\t\tAsignatura: %d - %s %n", asig[j].getId(), asig[j].getNombreAsig());
                }
            } catch (java.lang.NullPointerException m) {
            }

        } // WHILE TRUE

        // CERRAR STREAMS Y SOCKETS
        entrada.close();
        salida.close();
        cliente.close();

        System.out.print("Fin de cliente... ");

    }

}
