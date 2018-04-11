package task3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingStack<T> {
    private final Object[] arr;
    private volatile int current = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public BlockingStack(int size) {
        this.arr = new Object[size];
    }

    public void push(T el){
        while(true) {
            if (current < arr.length) {
                lock.lock();
                try {
                    if (current < arr.length) {
                        arr[current++] = el;
                        condition.signal();
                        return;
                    }
                }finally {
                    lock.unlock();
                }
            } else {
                while (current >= arr.length){
                    condition.awaitUninterruptibly();
                }
            }
        }
    }

    public T pop(){
        while(true) {
            if (current > 0) {
                lock.lock();
                try {
                    if (current > 0) {
                        T el = (T) arr[current--];
                        condition.signalAll();
                        return el;
                    }
                }finally {
                    lock.unlock();
                }
            } else {
                while (current == 0){
                    condition.awaitUninterruptibly();
                }
            }
        }
    }
}
