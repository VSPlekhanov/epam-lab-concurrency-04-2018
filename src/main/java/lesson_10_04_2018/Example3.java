package lesson_10_04_2018;

import java.util.concurrent.*;

public class Example3 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(3);

        ExecutorService threadPool = new ThreadPoolExecutor(0, 50, 10, TimeUnit.SECONDS, new SynchronousQueue<>());
        threadPool.submit(() -> 50);


        service.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return 42;
        });

        service.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return 42;
        });

        TimeUnit.SECONDS.sleep(6);

        service.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return 42;
        });


    }
}
