import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Usando Sockets TCP realiza un programa cliente que introduzca cadenas por
         * teclado hasta
         * introducir un asterisco. Las cadenas se enviarán a un programa servidor. El
         * programa servidor
         * aceptará la conexión de un único cliente y por cada cadena recibida le
         * devolverá al cliente el
         * número de caracteres de la misma. El programa servidor finalizará cuando
         * reciba un asterisco
         * como cadena.
         */
        int numeroPuerto = 6000;// Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        String cad = "";

        System.out.println("Esperando conexion...");
        Socket cliente = servidor.accept();
        System.out.println("Cliente conectado...");

        // CREO FLUJO DE SALIDA AL SERVIDOR
        OutputStream salida = null;
        salida = cliente.getOutputStream();
        DataOutputStream fsalida = new DataOutputStream(salida);

        // CREO FLUJO DE ENTRADA DE SERVIDOR
        InputStream entrada = null;
        entrada = cliente.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);

        cad = flujoEntrada.readUTF();
        while (!cad.equals("*")) {
            Integer n = cad.length();
            System.out.println("Recibiendo: " + cad + " enviando: " + n);
            fsalida.writeInt(n);
            cad = flujoEntrada.readUTF();
        }
        // CERRAR STREAMS Y SOCKETS
        System.out.println("Cerrando conexion...");

        flujoEntrada.close();
        fsalida.close();
        cliente.close();
        servidor.close();

    }
}
