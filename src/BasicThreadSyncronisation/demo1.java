package BasicThreadSyncronisation;

import java.util.Scanner;

class Processor extends Thread {
  private volatile boolean running = true;

  @Override
  public void run(){

    while(running){
      System.out.println("I am running");

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void terminate(){
    this.running = false;
  }
}

public class demo1 {
  public static void main(String[] args) {

    Processor p1 = new Processor();
    Scanner sc = new Scanner(System.in);
    p1.start();

    sc.nextLine();
    p1.terminate();
  }
}
