import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    /*
     * Crea un programa cliente que introduzca por teclado un número e inicialice un
     * objeto Numeros, el atributo numero debe contener el número introducido por
     * teclado. Debe
     * enviar ese objeto al programa servidor. El proceso se repetirá mientras el
     * número introducido
     * por teclado sea mayor que 0.
     */
    public static void main(String[] args) throws Exception {
        Socket cliente = new Socket("localhost", 6000);
        System.out.println("Introdcue un numero");
        Scanner sc = new Scanner(System.in);
        int numero = sc.nextInt();
        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
        while (numero >= 0) {
            Numeros numeros = new Numeros();
            numeros.setNumero(numero);

            salida.writeObject(numeros);

            numeros = (Numeros) entrada.readObject();

            System.out.println(numeros.getCuadrado());
            System.out.println(numeros.getCubo());
            System.out.println("Introdcue un numero");
            numero = sc.nextInt();
        }

        System.out.println("CEEERRANDO");
        sc.close();
        salida.close();
        entrada.close();
        cliente.close();
    }

}
