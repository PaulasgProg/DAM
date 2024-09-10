import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Implementa un programa que recoja de teclado una URL (con el formato
         * http://www.sitioweb.dom) y abra una conexión a dicho sitio Web, mostrando por
         * pantalla el
         * código HTML de su página inicial
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una URL");
        String urlStr = sc.nextLine();

        URL url = new URL(urlStr);

        BufferedReader in;
        try {
            InputStream inputStream = url.openStream();
            in = new BufferedReader(new InputStreamReader(inputStream));
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println(input);
            }
            in.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
