package SyncronisedCodeBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

  Random random = new Random();

  private Object lock1 = new Object();
  private Object lock2 = new Object();

  private List<Integer> l1 = new ArrayList<>();
  private List<Integer> l2 = new ArrayList<>();

  public void stageOne(){

    synchronized (lock1) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      l1.add(random.nextInt(100));
    }
  }

  public void stageTwo(){
    synchronized (lock2) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      l2.add(random.nextInt(100));
    }
  }

  public void process(){
    stageOne();
    stageTwo();
  }

  public static void main(String[] args) {

    App app = new App();
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i=0;i<1000;++i)
          app.process();
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i=0;i<1000;++i)
          app.process();
      }
    });

    long currTime = System.currentTimeMillis();
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long finalTime = System.currentTimeMillis();
    System.out.println("Time Taken : " + (finalTime - currTime));
    System.out.println(app.l1.size());
    System.out.println(app.l2.size());
  }
}
