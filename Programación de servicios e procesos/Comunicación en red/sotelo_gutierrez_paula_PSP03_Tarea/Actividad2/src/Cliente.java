import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1500;

        try (Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedInputStream fileIn = new BufferedInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)) {

            System.out.print("Introduce el nombre del fichero a solicitar: ");
            String fileName = scanner.nextLine();
            out.println(fileName);

            String serverResponse = in.readLine();
            if ("OK".equals(serverResponse)) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] byteChunk = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileIn.read(byteChunk)) != -1) {
                    buffer.write(byteChunk, 0, bytesRead);
                }

                System.out.println("Contenido del fichero:");
                System.out.println(buffer.toString());
            } else {
                System.out.println(serverResponse);
            }

        } catch (UnknownHostException e) {
            System.err.println("No se conoce el host " + host);
        } catch (IOException e) {
            System.err.println("Error de E/S en la conexión con " + host);
        }
    }
}