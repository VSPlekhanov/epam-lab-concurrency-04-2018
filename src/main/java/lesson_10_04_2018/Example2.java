package lesson_10_04_2018;

import java.io.Serializable;

public class Example2 {


}

class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {}

    public static void method() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}

class Singleton2 {

    private static Singleton2 INSTANCE;

    private Singleton2() {}

    public static void method() {

    }

    public synchronized static Singleton2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }

}

class Singleton3 {

    private volatile static Singleton3 INSTANCE;

    private Singleton3() {}

    public static void method() {

    }

    // double check locking (DCL)
    public static Singleton3 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton3.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }

}

class Singleton4 {

    private volatile static Singleton4 INSTANCE;

    private Singleton4() {}

    public static void method() {

    }

    public static Singleton4 getInstance() {
        Singleton4 localRef = INSTANCE;
        if (localRef == null) {
            synchronized (Singleton4.class) {
                localRef = INSTANCE;
                if (localRef == null) {
                    localRef = new Singleton4();
                }
            }
        }
        return localRef;
    }
}

interface MyInterface {
    void method();
}

enum Singleton5 implements Comparable<Singleton5>, MyInterface {
    INSTANCE(42);

    private final int field;

    Singleton5(int field) {
        this.field = field;
    }

    public int getValue() {
        return 42;
    }

    @Override
    public void method() {

    }
}

class Singleton6 {

    private Singleton6() {}

    public static Singleton6 getInstance() {
        return Singleton6Holder.INSTANCE;
    }

    public static void method() {

    }

    private static class Singleton6Holder {
        private final static Singleton6 INSTANCE = new Singleton6();
    }

}

class Launcher {

    public static void main(String[] args) {
        Singleton5.INSTANCE.getValue();
    }
}
