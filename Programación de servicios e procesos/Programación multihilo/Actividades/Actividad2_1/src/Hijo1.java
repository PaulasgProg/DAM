public class Hijo1 extends Thread {
    private int x;
    Hijo1 (int x){
        this.x=x;
    }
    public void run(){
        for(int i=0;i<=x;i++){
            System.out.println("TIC");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
