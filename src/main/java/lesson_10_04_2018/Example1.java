package lesson_10_04_2018;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Example1 {

    private static class SingleThreadWorker {

        private static final Runnable POISON_PILL = () -> {};
        private final BlockingQueue<Runnable> tasks = new BlockingQueue<>();
        private final Thread worker;
        private volatile boolean isShutdown = false;

        public SingleThreadWorker() {
            worker = new Thread(this::process, "Single-thread worker");
            worker.start();
        }

        private void process() {
            try {
                while (!Thread.interrupted()) {
                    Runnable task = tasks.remove();
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
            tasks.add(task);
        }

        public void shutdown() {
            if (!isShutdown) {
                synchronized (tasks) {
                    if (!isShutdown) {
                        tasks.add(POISON_PILL);
                        isShutdown = true;
                    } else {
                        throw new IllegalStateException("Already shutdown");
                    }
                }
            } else {
                throw new IllegalStateException("Already shutdown");
            }
        }

//        public List<Runnable> shutdownNow() {
//
//        }
    }


    public static void main(String[] args) {
        SingleThreadWorker worker = new SingleThreadWorker();

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
