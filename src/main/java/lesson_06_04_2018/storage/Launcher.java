package lesson_06_04_2018.storage;

import java.util.stream.IntStream;

public class Launcher {

    public static void main(String[] args) {
        Storage storage = new Storage();

        new Writer(storage).start();

        IntStream.range(0, 5)
                 .mapToObj(index -> new Reader("Reader-" + index, storage))
                 .forEach(Thread::start);
    }
}
