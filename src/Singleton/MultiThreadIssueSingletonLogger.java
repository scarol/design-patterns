package Singleton;

public class MultiThreadIssueSingletonLogger {
    private static MultiThreadIssueSingletonLogger loggerObj = null; // private static single class object only available to the
    // class and not to the users of the class
    static int ctr = 0; // make instance counter to be used from within the constructor as static so that it
    // belongs to the class and not to the object. This is so that it does not get re-initialised everytime the
    //constructor is called and so we can count the TOTAL number of class objs created.

    private MultiThreadIssueSingletonLogger() { // private constructor to restrict other code from accessing it
        ctr++;
        System.out.println("New instance created.");
        System.out.println("Number of logger instances: " + ctr);
    }

    static MultiThreadIssueSingletonLogger getLogger() { // static method that returns a single instance of the class
        long startTime = System.currentTimeMillis();
        if (loggerObj == null)
            loggerObj = new MultiThreadIssueSingletonLogger();
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.println("Time taken with non synchronized threads: " + timeTaken);
        return loggerObj;
    }

    void log(String msg) {
        System.out.println("Log msg is: " + msg);
    }
}

class MultiThreadIssueSingletonMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() { // using Anonymous Runnable class method
            @Override
            public void run() {
                user1Log();
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                user2Log();
            }
        });
        t2.start();
    }

    private static void user1Log() {
        MultiThreadIssueSingletonLogger logger1 = MultiThreadIssueSingletonLogger.getLogger();
        logger1.log("This msg is from user1.");
    }

    private static void user2Log() {
        MultiThreadIssueSingletonLogger logger2 = MultiThreadIssueSingletonLogger.getLogger();
        logger2.log("This msg is from user2.");
    }
}
