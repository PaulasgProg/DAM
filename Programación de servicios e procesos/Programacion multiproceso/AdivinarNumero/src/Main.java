import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "Maquina");
        pb.directory(new File(".\\"));
        Process p = pb.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        PrintStream ps = new PrintStream(p.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        int numero;
        int intentos = 1;
        String line = "";

        while(p.isAlive() && Boolean.parseBoolean(line) != true && intentos <=5){
            System.out.println("¿Que numero crees que es? " + intentos + "º intento");
            numero = scanner.nextInt();
            scanner.nextLine();

            ps.println(numero);
            ps.flush();

            if((line = br.readLine()) != null){
                System.out.println(line);
            }
            intentos++;

        }

        if(intentos >= 6 && Boolean.parseBoolean(line) != true){
            ps.println(-1);
            ps.flush();
            System.out.println("Perdiste!");

            if((line = br.readLine()) != null){
                System.out.println(line);
            }

        }else if(Boolean.parseBoolean(line) == true){
            System.out.println("Ganaste!");
        }


        System.out.println("El proceso Maquina a finalizado con el code " + p.waitFor());
    }
  
}
