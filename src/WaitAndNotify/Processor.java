package WaitAndNotify;

public class Processor {

  public void producer() throws InterruptedException {

    synchronized (this){
      System.out.println("Producer running");
      wait();
      System.out.println("Resumed Control");
    }
  }

  public void consumer() throws InterruptedException {
    synchronized (this){
      Thread.sleep(3000);
      System.out.println("Consumer running");
      notify();
      Thread.sleep(5000);
    }
  }
}
