import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {
    public static void main(String[] args) throws Exception {
        int port = 2000;
        Random rand = new Random();
        int secretNumber = rand.nextInt(101);

        try {
            // CREAMOS SERVIDOR
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor escuchando en el puerto " + serverSocket.getLocalPort());
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            try {
                // CREAMOS FLUJO ENTRADACLIENTE
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // CREAMOS FLUJO DE SALIDA AL CLIENTE
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    int guess = Integer.parseInt(inputLine);
                    System.out.println("Cliente introduce: " + guess);

                    if (guess < secretNumber) {
                        out.println("El número secreto es mayor.");
                    } else if (guess > secretNumber) {
                        out.println("El número secreto es menor.");
                    } else {
                        out.println("¡Has acertado!");
                        break;
                    }
                }
                // CERRAMOS STREAMS Y SOCKETS
                out.close();
                ;
                in.close();
                clientSocket.close();
                serverSocket.close();

            } catch (IOException e) {
                System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
