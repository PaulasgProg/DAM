public class Hilo2 implements Runnable {
    private int x;

    Hilo2(int x){
        this.x=x;
    }
    @Override
    public void run() {
        for(int i=0;i<=x;i++){
            System.out.println("TAC");
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
