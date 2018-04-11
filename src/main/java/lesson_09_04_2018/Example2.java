package lesson_09_04_2018;

import lombok.SneakyThrows;
import lombok.Synchronized;

import java.util.concurrent.TimeUnit;

public class Example2 {

    // x + y == 0
    private static class Storage {

        private int x = 0;
        private int y = 0;
        private final Object $lock = new Object();

        public void action() {
            synchronized ($lock) {
                try {
                    ++x;
                    TimeUnit.SECONDS.sleep(1);
                    --y;
                } catch (InterruptedException e) {
                    --x;
                }
            }
        }

        public int getX() {
            synchronized ($lock) {
                return x;
            }
        }

        public int getY() {
            synchronized ($lock) {
                return y;
            }
        }

        public int[] getValues() {
            synchronized ($lock) {
                return new int[]{x, y};
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();

        Runnable doIt = () -> {
            for (int i = 0; i < 1_000_000; ++i) {
                storage.action();
            }
        };

        Thread worker = new Thread(doIt);
        worker.start();
        worker.stop();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(storage.getX());
        System.out.println(storage.getY());
    }
}
