package lesson_10_04_2018;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private final Queue<T> tasks = new LinkedList<>();

    public T remove() throws InterruptedException {
        synchronized (tasks) {
            while (tasks.isEmpty()) {
                tasks.wait();
            }
            return tasks.remove();
        }
    }

    public void add(T element) {
        synchronized (tasks) {
            tasks.add(element);
            tasks.notify();
        }
    }

    public T peek() {
        return tasks.peek();
    }
}
