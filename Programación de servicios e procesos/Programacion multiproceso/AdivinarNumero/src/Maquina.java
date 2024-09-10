import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Maquina {
    public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String line = "";
        boolean salir = false;
        int numeroElegido = (int) (Math.random()*100+1);
        while(!salir){
            if((line = br.readLine()) != null){
                int numeroJugador = (Integer.parseInt(line));
                if(numeroJugador == -1){
                    salir = true;
                    System.out.println("Era el " + numeroElegido);
                }else if (numeroElegido > numeroJugador){
                    System.out.println("Mayor");
                }else if (numeroElegido < numeroJugador){
                    System.out.println("Menor");
                }else{
                    salir = true;
                    System.out.println(true);
                }
            }
        }

        System.exit(0);
    }
}
