
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            System.exit(0);
        } else {
            System.exit(1);
        }
    }
}