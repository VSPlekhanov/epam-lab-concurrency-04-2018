package lesson_11_04_2018.storage;

public class Reader extends Thread {

    private final Storage storage;

    public Reader(String name, Storage storage) {
        super(name);
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(getName() + ": " + storage.get());
                sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
