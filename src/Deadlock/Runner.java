package Deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

  private Account acc1 = new Account();
  private Account acc2 = new Account();

  private Lock lock1 = new ReentrantLock();
  private Lock lock2 = new ReentrantLock();

  private void acquireLock(Lock lock1, Lock lock2) throws InterruptedException{

    while(true){
      boolean gotFirstLock = false, gotSecondLock = false;

      try {
        gotFirstLock = lock1.tryLock();
        gotSecondLock = lock2.tryLock();
      }
      finally {
        if(gotFirstLock && gotSecondLock){
          return;
        }

        if(gotFirstLock){
          lock1.unlock();
        }
        if(gotSecondLock){
          lock2.unlock();
        }

      }
      Thread.sleep(1);
    }
  }

  public void thread1() throws InterruptedException {
    Random random = new Random();

    for(int i=0;i<10000;++i){

      acquireLock(lock1, lock2);
      try {
        Account.transfer(acc1, acc2, random.nextInt(100));
      }
      finally {
        lock1.unlock();
        lock2.unlock();
      }
    }
  }

  public void thread2() throws InterruptedException {
    Random random = new Random();

    acquireLock(lock2, lock1);
    try {
      for (int i = 0; i < 10000; ++i) {
        Account.transfer(acc2, acc1, random.nextInt(100));
      }
    }
    finally {
      lock1.unlock();
      lock2.unlock();
    }
  }

  public void finish() {
    System.out.println("Account-1 Balance : " + acc1.getBalance());
    System.out.println("Account-2 Balance : " + acc2.getBalance());
    System.out.println("Total Balance : " + (acc1.getBalance() + acc2.getBalance()));
  }
}
