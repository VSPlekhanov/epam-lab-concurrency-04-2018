package lesson_06_04_2018;

import java.util.concurrent.TimeUnit;

public class Example1 {

    private static class SimpleWorker {

        public void execute(Runnable task) {
            new Thread(task).start();
        }
    }

    public static void main(String[] args) {
        SimpleWorker worker = new SimpleWorker();

        Runnable task = () -> {
            try {
                System.out.println("I'm worker " + Thread.currentThread());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        worker.execute(task);
        worker.execute(task);
        worker.execute(task);
        worker.execute(task);
    }
}
