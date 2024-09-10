import java.io.IOException;
import java.util.Scanner;

public class EjecutarLeerNombre {
    
    public static void main(String[] args) throws IOException, InterruptedException{
        Process proceso=Runtime.getRuntime().exec("java LeerNombre John");
        int exitcode=proceso.waitFor();

        try {
            
        if(exitcode==0){

            System.out.println("El proceso LeerNombre terminó correctamente.");

        }
        if(exitcode==1){

            System.out.println("El proceso LeerNombre no terminó correctamente.");
        }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }



        
    }
    
}
