package callableAndFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

    Future<Integer> future = executorService.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        Random random = new Random();
        System.out.println("Starting....");
        int duration = random.nextInt(300);

        if(duration > 200){
          throw new IOException("Kola is here yayayaya!!");
        }

        Thread.sleep(duration);
        System.out.println("Finished.");
        return duration;
      }
    });

    executorService.shutdown();

    try {
      System.out.println("Result is: " + future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
