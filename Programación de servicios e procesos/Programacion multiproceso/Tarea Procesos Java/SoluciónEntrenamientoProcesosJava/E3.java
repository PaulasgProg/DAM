package org.example;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        //Preparamos el proceso
        ProcessBuilder pb = new ProcessBuilder("notepad.exe", "file.txt");
        //Creamos el proceso con start()
        Process p2 = pb.start();
        
        //¿Se ha ejecutado bien el proceso?
        int exitVal;
        try {
            exitVal = p2.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}