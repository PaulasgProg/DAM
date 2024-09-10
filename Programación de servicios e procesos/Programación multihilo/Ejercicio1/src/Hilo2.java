public class Hilo2 extends Thread{
    private int x;
    private int var=0;

    Hilo2(int x){
        this.x=x;
    }

    public void run(){
        for(int i=0;i<=x;i++){
            System.out.println(var);
            var+=50;
            try {
                sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
