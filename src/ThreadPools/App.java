package ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

  private int id;

  public Processor(int i){
    this.id = i;
  }

  @Override
  public void run() {
    System.out.println("starting " + id);
    try {
      Thread.sleep(500 + (10000 * (id%2)));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("finishing " + id);
  }
}

public class App {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    for(int i=0;i<10;++i){
      executor.submit(new Processor(i));
    }
    executor.shutdown();
    System.out.println("All task submitted");
    try {
      executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
