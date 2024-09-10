public class Consumidor extends Thread {
    private Recipiente reci;
    private int numero;
    
    //constructor
   public Consumidor(Recipiente recipi, int nume) {
       reci=recipi;
       numero=nume;
   }
   
   public void run() {
       char valor=0;
       for (int i=1; i<=15; i++) {
           valor = reci.vaciar();
           System.out.println("Consumidor "+numero+" toma el valor "+valor);
           
           //espera un tiempo antes de volver a consumir
            try {
                sleep((int) (Math.random()*100));
            } catch (InterruptedException e)
            {
                System.out.println("Interrupcion del hilo...");
            }
        }
    }
 }
