public class Arbitro {

    private int numeroJugadores;
    private int numeroAdiv;
    private int turno;
    private Boolean juegoTerminado;

    Arbitro(int nJugadores){
        this.numeroAdiv=1+(int) (Math.random()*10);
        System.out.println("Numero a adivinar: "+numeroAdiv);
        this.numeroJugadores=nJugadores;
        this.juegoTerminado=false;
        this.turno=1;

    }

    public void setJuegoTerminado(Boolean terminado){
        this.juegoTerminado=terminado;
    }
    public Boolean getJuegoTerminado(){
        return juegoTerminado;
    }

    public void Sigturno(){
        turno++;
        if (turno>numeroJugadores) {
            turno=1;
        }
        System.out.println("         Le toca a Jugador "+getTurno());
    }
    public int getTurno(){
        return turno;
    }

    public synchronized void jugada(int id, int numero){
        if (id==getTurno()) {
            if (numeroAdiv==numero) {
                System.out.println("    ACERTASTE!! Se ha acabado el juego");
                setJuegoTerminado(true);
            } else {
                System.out.println("    Ese no era el numero");
                Sigturno();
            }
        } 
    }


}
