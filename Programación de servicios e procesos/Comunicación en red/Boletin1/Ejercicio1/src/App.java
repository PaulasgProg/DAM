import java.net.InetAddress;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Realiza un programa Java que admita desde consola nombres de máquinas o
        // direcciones IP y
        // vaya mostrando por pantalla información sobre ellas, haciendo uso de la clase
        // InetAddress.
        Scanner sc = new Scanner(System.in);
        InetAddress dir = null;
        String maquina = "";
        while (!maquina.equals("*")) {
            System.out.println("Introduce el nombre de la maquina");
            maquina = sc.nextLine();

            try {
                dir = InetAddress.getByName(maquina);
            } catch (Exception e) {
                System.out.println("HOST DESCONOCIDO");
                System.out.println(e.getMessage());
                System.exit(0);
            }
            // Array de tipo InetAddress con todas las direcciones IP asignadas //

            try {
                InetAddress[] dirs = InetAddress.getAllByName(dir.getHostName());
                for (InetAddress inetAddress : dirs) {
                    System.out.println(inetAddress.toString());
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }

    public static void pruebaMetodos(InetAddress dir) {
        System.out.println("metodo getbyname: " + dir);
        InetAddress dir2 = null;
        try {
            dir.getLocalHost();
            System.out.println("GetLocalhost: " + dir2);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(dir.getHostName());
        System.out.println(dir.getCanonicalHostName());
        System.out.println(dir.getHostAddress());
        System.out.println(dir.toString());
    }
}
