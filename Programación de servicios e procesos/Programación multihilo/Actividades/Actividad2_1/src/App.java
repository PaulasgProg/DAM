public class App {
    public static void main(String[] args) throws Exception {
        Hijo1 hilo1=new Hijo1(3);
        Hijo2 hilo2=new Hijo2(3);

        hilo1.start();
        hilo2.start();
    }
}
