import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * . Crea un programa servidor que pueda atender hasta 3 clientes. Debe enviar a
         * cada cliente un
         * mensaje indicando el número de cliente que es. Este número será 1, 2 o 3. El
         * cliente mostrará
         * el mensaje recibido. Cambia el programa para que lo haga para N clientes,
         * siendo N un
         * parámetro que tendrás que definir en el programa.
         */
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cuantos clientes aceptamos?");
        int num;
        try {
            num = scanner.nextInt();
            ServerSocket serverSocket = new ServerSocket(6000);

            Socket[] clientes = new Socket[num];
            DataOutputStream outputStream = null;
            int i = 1;
            for (Socket socket : clientes) {
                socket = serverSocket.accept();
                System.out.println(i + " cliente");
                outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeInt(i);
                i++;

            }

            outputStream.close();
            for (Socket socket : clientes) {
                socket.close();
            }
            scanner.close();
            serverSocket.close();

        } catch (Exception e) {
            System.err.println("No es un numero");
        }

    }
}
