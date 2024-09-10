public class Hilo1 extends Thread{

    private int x;
    private int var=0;

    Hilo1(int x){
        this.x=x;
    }

    public void run(){
        for(int i=0;i<=x;i++){
            System.out.println(var);
            var+=1;
            try {
                sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
