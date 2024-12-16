package Singleton;

public class MultiLogger {
    static int ctr = 0; // make instance counter to be used from within the constructor as static so that it
    // belongs to the class and not to the object. This is so that it does not get re-initialised everytime the
    //constructor is called and so we can count the TOTAL number of class objs created.

    public MultiLogger() {
        ctr++;
        System.out.println("New instance created.");
        System.out.println("Number of logger instances: " + ctr);
    }

    void log(String msg) {
        System.out.println("Log msg is: " + msg);
    }
}

class MultiLoggerMain {
    public static void main(String[] args) {
        MultiLogger logger1 = new MultiLogger();
        logger1.log("This msg is from user1.");
        MultiLogger logger2 = new MultiLogger();
        logger2.log("This msg is from user2.");
    }
}
