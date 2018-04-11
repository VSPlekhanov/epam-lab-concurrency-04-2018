package lesson_11_04_2018;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example3 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        Lock lock = new ReentrantLock();

        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        service.submit(() -> {
            lock.lock();

            try {
                System.out.println("Before waiting");
                condition1.awaitUninterruptibly();
                System.out.println("After waiting");
            } finally {
                lock.unlock();
            }
        });

        service.submit(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Before signal");
                condition1.signal();
                System.out.println("After signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

    }
}
