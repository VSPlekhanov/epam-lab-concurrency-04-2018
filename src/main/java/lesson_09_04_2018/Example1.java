package lesson_09_04_2018;

import java.util.concurrent.TimeUnit;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
//        Object monitor = new Object();

        Thread thread = new Thread(() -> {
            System.out.println("Before sleep");

//            synchronized (monitor) {
                int val = 0;
                for (int i = 0; i < 1_000_000_000; ++i) {
                    for (int j = 0; j < 1_000_000_000; ++j) {
                        System.out.println(++val);
                    }
                }
//            }

            System.out.println("After sleep");
            System.out.println("Thread-0 end");
        });

        thread.start();

        TimeUnit.SECONDS.sleep(3);

        System.out.println("Before suspend");
        thread.suspend();
//        System.out.println("After suspend");

//        TimeUnit.SECONDS.sleep(3);

//        thread.resume();

//        synchronized (monitor) {
//            System.out.println("Got it!");
//        }

        thread.join();
        System.out.println("Main end");
    }

    private static void pause(Thread thread) {
        thread.suspend();
    }

    private static void resume(Thread thread) {
        if (System.getProperty("isStopped") != null) {
            thread.resume();
        }
    }
}
