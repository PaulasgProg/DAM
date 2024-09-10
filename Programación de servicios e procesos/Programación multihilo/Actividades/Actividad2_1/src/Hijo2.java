public class Hijo2 extends Thread {
    private int x;
    Hijo2 (int x){
        this.x=x;
    }
    public void run(){
        for(int i=0;i<=x;i++){
            System.out.println("TAC");
            try {
                sleep(700);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
