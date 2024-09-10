public class App {
    public static void main(String[] args) throws Exception {
        Arbitro ar=new Arbitro(3);

        Jugador j1=new Jugador(1, ar);
        Jugador j2=new Jugador(2, ar);
        Jugador j3=new Jugador(3, ar);

        j1.start();
        j2.start();
        j3.start();


    }
}
