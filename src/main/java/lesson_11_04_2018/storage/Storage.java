package lesson_11_04_2018.storage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Storage {

    private volatile String string = "DEFAULT";

    private final Lock readLock;
    private final Lock writeLock;

    public Storage() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
        writeLock = lock.writeLock();
        readLock = lock.readLock();
    }

    public String get() throws InterruptedException {
        readLock.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            return string;
        } finally {
            readLock.unlock();
        }
    }

    public void set(String string) throws InterruptedException {
        writeLock.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            this.string = string;
        } finally {
            writeLock.unlock();
        }
    }
}
