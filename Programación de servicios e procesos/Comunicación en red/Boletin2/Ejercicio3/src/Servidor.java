import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6000);
        Socket cliente = serverSocket.accept();

        System.out.println("Cliente conectado");

        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
        Numeros numeros = (Numeros) entrada.readObject();
        int numero = numeros.getNumero();
        while (numero >= 0) {
            long cuadrado = numero * numero;
            long cubo = numero * numero * numero;
            numeros.setCuadrado(cuadrado);
            numeros.setCubo(cubo);

            salida.writeObject(numeros);

            numeros = (Numeros) entrada.readObject();
            numero = numeros.getNumero();
        }
        entrada.close();
        salida.close();
        serverSocket.close();

    }
}
