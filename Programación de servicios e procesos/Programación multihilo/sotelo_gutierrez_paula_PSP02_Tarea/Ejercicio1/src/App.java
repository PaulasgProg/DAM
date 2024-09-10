public class App {
    public static void main(String[] args) throws Exception {
        Recipiente reci = new Recipiente();
        
        Productor produ1 = new Productor(reci,1);
        Consumidor consu1 = new Consumidor(reci,1);
        
        produ1.start();
        consu1.start();
        produ1.join();
        consu1.join();
    }
}
