package semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

  public static void main(String[] args) throws Exception {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for(int i=0;i<200; i++){
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          Connection.getInstance().connect();
        }
      });
    }
    executorService.shutdown();
  }
}
