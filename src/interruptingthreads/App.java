package interruptingthreads;

public class App {
  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Starting...");
        for(int i=0;i<1e8;++i){
          try{
            Thread.sleep(1);
          }catch (InterruptedException e){
            System.out.println("Interrupted!!");
          }
        }
      }
    });
    t1.start();
    Thread.sleep(500);
    t1.interrupt();
    t1.join();
    System.out.println("Finished!!");
  }
}
