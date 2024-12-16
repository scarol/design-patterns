package Singleton;

public class ThreadSafeSingletonLogger {
    private static ThreadSafeSingletonLogger loggerObj = null; // private static single class object only available to the
    // class and not to the users of the class
    static int ctr = 0; // make instance counter to be used from within the constructor as static so that it
    // belongs to the class and not to the object. This is so that it does not get re-initialised everytime the
    //constructor is called and so we can count the TOTAL number of class objs created.

    private ThreadSafeSingletonLogger() { // private constructor to restrict other code from accessing it
        ctr++;
        System.out.println("New instance created.");
        System.out.println("Number of logger instances: " + ctr);
    }

    static ThreadSafeSingletonLogger getLogger() { // static method that returns a single instance of the class
        long startTime = System.currentTimeMillis();
        synchronized (ThreadSafeSingletonLogger.class){
            if (loggerObj == null)
                loggerObj = new ThreadSafeSingletonLogger();
        }
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.println("Time taken with locking/synchronized threads: " + timeTaken);
        /* Despite this class being thread-safe, we can see that there’s a clear performance drawback:
        each time we want to get the instance of our singleton, we need to acquire a potentially
        unnecessary lock.*/
        return loggerObj;
    }

    void log(String msg) {
        System.out.println("Log msg is: " + msg);
    }
}

class ThreadSafeSingletonLoggerMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(ThreadSafeSingletonLoggerMain::user1Log); // using Method Reference instead of
        // Anonymous class method new Runnable() {
        //            @Override
        //            public void run() {
        //                user1Log();
        //            }
        //        }
        t1.start();
        Thread t2 = new Thread(() -> user2Log()); // using Lambda Expression instead of
        // Anonymous class method new Runnable() {
        //            @Override
        //            public void run() {
        //                user1Log();
        //            }
        //        }
        t2.start();
    }

    private static void user1Log() {
        ThreadSafeSingletonLogger logger1 = ThreadSafeSingletonLogger.getLogger();
        logger1.log("This msg is from user1.");
    }

    private static void user2Log() {
        ThreadSafeSingletonLogger logger2 = ThreadSafeSingletonLogger.getLogger();
        logger2.log("This msg is from user2.");
    }
}
