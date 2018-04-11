package lesson_11_04_2018.storage;

import java.util.concurrent.TimeUnit;

public class Writer extends Thread {

    private final Storage storage;

    public Writer(Storage storage) {
        super("Writer");
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; ++i) {
                storage.set(String.valueOf(i));
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
