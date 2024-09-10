public class Hilo1 implements Runnable{

    private int x;
    Hilo1 (int x){
        this.x=x;
    }
    
    public void run(){
        for(int i=0;i<=x;i++){
            System.out.println("TIC");
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
