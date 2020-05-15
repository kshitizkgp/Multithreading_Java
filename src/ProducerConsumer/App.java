package ProducerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
  private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

  public static void main(String[] args) {
    Runnable target;
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          producer();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          consumer();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();


    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void producer () throws InterruptedException {
    Random random = new Random();
    while(true){
      int val = random.nextInt(100);
      System.out.println("Produce :" + val);
      queue.put(val);
    }
  }

  public static void consumer() throws InterruptedException {
    while (true){
      Thread.sleep(300);
      Integer value = queue.take();
      System.out.println("Consume :" + value);
    }
  }
}
