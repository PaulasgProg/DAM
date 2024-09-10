public class Productor extends Thread {
    private Recipiente reci;
    private int numero;  //Si hay varios productores cada
                        //cada uno llevará un numero
    
    //Constructor
    public Productor(Recipiente recipi, int num) {
        reci=recipi;
        numero=num;
    }
    
    public void run() {
        char[] abecedario={'a','b' , 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o'};
        for (char c : abecedario) {
            reci.llenar(c);
            System.out.println("Productor "+numero+" deposita el caracter "+c);
            //espera un tiempo antes de volver a llenar
            try {
                sleep((int) (Math.random()*100));
            } catch (InterruptedException e)
            {
                System.out.println("Interrupcion del hilo...");
            }
        }
    }
 }