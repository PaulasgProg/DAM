public class Recipiente {

    private int tamañobuff=6;
    private char contenidoc;
    private boolean lleno = false;
    
    public synchronized char vaciar() {
        while (lleno == false ) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupcion del hilo ... ");
            }
        }
        tamañobuff--;
        lleno = false;
        notifyAll();
        return contenidoc;
    }
    
    public synchronized void llenar (char valor) {
        while (lleno == true && tamañobuff!=0)  {
           try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupcion del hilo ... ");
            }
        }
        tamañobuff++;
        contenidoc = valor;
        lleno = true;
        notifyAll();
    }
 }