package lesson_10_04_2018;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Example4 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);

        ExecutorService unconfigurable = Executors.unconfigurableExecutorService(service);

        clientMethod(unconfigurable);

        System.out.println(((ThreadPoolExecutor) service).getMaximumPoolSize());
    }

    public static void clientMethod(ExecutorService service) {
        service.submit(() -> 42);

        if (service instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor pool = (ThreadPoolExecutor) service;
            pool.setMaximumPoolSize(100);
        }

    }
}
