package lesson_10_04_2018;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example5 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);

        lock.lock();

        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (lock.tryLock()) {
            // logic
        }


        lock.unlock();

    }
}
