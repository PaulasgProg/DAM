public class App {
    public static void main(String[] args) throws Exception {
        Hilo1 h1=new Hilo1(3);
        new Thread(h1).start();
        Hilo2 h2=new Hilo2(3);
        new Thread(h2).start();
        
    }
}
