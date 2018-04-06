package homework.part2;

import java.util.concurrent.TimeUnit;

public class Reader extends Thread{
    private final Storage storage;

    public Reader(Storage storage, String name) {
        super(name);
        this.storage = storage;
    }

    public void run(){
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(storage.getString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
