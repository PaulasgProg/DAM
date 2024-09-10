import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 2000;

        try {

            // creamos cliente
            Socket socket = new Socket(host, port);
            // creamos flujo entrada al servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // creamos flujo salida al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // entrada estandar
            Scanner scanner = new Scanner(System.in);
            String serverResponse;
            while (true) {
                System.out.print("Introduce un numero entre 0 y 100: ");
                String userGuess = scanner.nextLine();
                out.println(userGuess);

                serverResponse = in.readLine();
                System.out.println("Respuesta del servidor: " + serverResponse);

                if ("¡Has acertado!".equals(serverResponse)) {
                    break;
                }
            }

            // cerramos
            out.close();
            in.close();
            scanner.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("No se conoce el host " + host);
        } catch (IOException er) {
            System.err.println("Error de E/S en la conexión con " + host);
        }
    }
}