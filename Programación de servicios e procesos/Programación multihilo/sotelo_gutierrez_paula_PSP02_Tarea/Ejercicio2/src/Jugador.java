public class Jugador extends Thread{

    private int idJugador;
    private Arbitro arbitro;

    Jugador(int id, Arbitro arbitro ){
        this.arbitro=arbitro;
        this.idJugador=id;
    }

    public long getId(){
        return idJugador;
    }

    @Override
    public void run() {
        while (arbitro.getJuegoTerminado()==false) {
            if (getId()==arbitro.getTurno()) {
                int numero=1+(int) (Math.random()*10);
                System.out.println("Jugador " +getId()+ " ha juegado un : "+numero);
                arbitro.jugada(this.idJugador, numero);
                
            }
            else {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
