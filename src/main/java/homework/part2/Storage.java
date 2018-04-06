package homework.part2;

import java.util.concurrent.TimeUnit;

public class Storage {
    private String string;
    public volatile boolean writerIsHere;

    public String getString() throws InterruptedException{
        synchronized (this) {
            if (this.writerIsHere) this.wait();
            System.out.println(Thread.currentThread().getName() + " in the storage");
            TimeUnit.SECONDS.sleep(1);
            if(!this.writerIsHere) this.notify();
            return string;
        }
    }

    public void setString(String string) throws InterruptedException{
        this.writerIsHere = true;
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " in the storage");
            TimeUnit.SECONDS.sleep(1);
            this.string = string;
            this.notify();
        }
        this.writerIsHere = false;
    }
}
