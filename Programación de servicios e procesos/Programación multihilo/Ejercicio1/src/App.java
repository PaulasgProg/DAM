public class App {
    public static void main(String[] args) throws Exception {
        Hilo1 h1=new Hilo1(4);
        h1.start();

        Hilo2 h2=new Hilo2(4);
        h2.start();
    }
}
