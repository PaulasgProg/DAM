import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));
            System.out.println(br.readLine());
            br.close();
        } catch (IOException e) {
            System.exit(1);
        } finally {
            System.out.println("Exiting the program");
        }

    }
}