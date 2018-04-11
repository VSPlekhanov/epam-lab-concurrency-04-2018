package lesson_09_04_2018;

import java.util.concurrent.*;

public class Example3 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<Integer> task1 = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return 42;
        });



        Future<Integer> task2 = service.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 43;
        });

//        service.shutdown();
        System.out.println(service.shutdownNow().size());

//        System.out.println(service.awaitTermination(5, TimeUnit.SECONDS));

        try {
            System.out.println(task1.get());
            System.out.println(task2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e);
        }

//        Future<Integer> task2 = service.submit(() -> 43);
    }
}
