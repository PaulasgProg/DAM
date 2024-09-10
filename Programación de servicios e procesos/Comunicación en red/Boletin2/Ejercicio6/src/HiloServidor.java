import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class HiloServidor extends Thread {
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    HiloServidor(Socket s) {
        this.socket = s;

    }

    @Override
    public void run() {
        System.out.println("Informacion  cliente conectado");
        System.out.println(socket.getPort());
        System.out.println(socket.getInetAddress());

        try {
            String cadena = "";
            while (!cadena.equals("*")) {
                dataInputStream = new DataInputStream(socket.getInputStream());
                cadena = dataInputStream.readUTF();
                if (cadena.equals("*")) {
                    break;
                }
                String mayus = cadena.toUpperCase();

                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(mayus);

            }
            System.out.println("Cliente desconectado");

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
