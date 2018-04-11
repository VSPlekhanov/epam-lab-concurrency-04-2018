package lesson_06_04_2018;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Example2 {

    private static class SingleTrheadWorker {

        private static final Runnable POISON_PILL = () -> {};
        private final Queue<Runnable> tasks = new LinkedList<>();
        private final Thread worker;


        public SingleTrheadWorker() {
            worker = new Thread(this::process, "Single-thread worker");
            worker.start();
        }

        private void process() {
            try {
                while (!Thread.interrupted()) {
                    Runnable task;
                    synchronized (tasks) {
                        while (tasks.isEmpty()) {
                            tasks.wait();
                        }
                        task = tasks.remove();
                    }
                    if (task == POISON_PILL) {
                        return;
                    }
                    task.run();
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        public void execute(Runnable task) {
            synchronized (tasks) {
                tasks.add(task);
                tasks.notify();
            }
        }

        public void shutdown() {
            synchronized (tasks) {
                tasks.add(POISON_PILL);
            }
        }
    }


    public static void main(String[] args) {
        SingleTrheadWorker worker = new SingleTrheadWorker();

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
        worker.shutdown();
    }
}
