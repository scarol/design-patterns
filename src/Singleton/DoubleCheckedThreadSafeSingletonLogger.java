package Singleton;


public class DoubleCheckedThreadSafeSingletonLogger {
    private static volatile DoubleCheckedThreadSafeSingletonLogger loggerObj = null; // One thing to keep in mind with
    // Double-Checked Locking pattern is that the field needs to be volatile to prevent cache incoherence issues.
    // In fact, the Java memory model allows the publication of partially initialized objects and this may lead in
    // turn to subtle bugs.
    static int ctr = 0;

    private DoubleCheckedThreadSafeSingletonLogger() {
        ctr++;
        System.out.println("New instance created.");
        System.out.println("Number of logger instances: " + ctr);
    }

    static DoubleCheckedThreadSafeSingletonLogger getLogger() {
        long startTime = System.currentTimeMillis();
        /* To fix performance drawback of thread safe locking, we instead start by verifying if
         we need to create the object in the first place and only in that case we would acquire
         the lock. This is called Double-Checked Locking. */
        if (loggerObj == null) { // double-checked locking design pattern. This pattern reduces the number of lock
            // acquisitions by simply checking the locking condition beforehand. As a result of this, there’s usually
            // a performance boost.
            // One thing to keep in mind with this pattern is that the field needs to be VOLATILE to prevent cache
            // incoherence issues. In fact, the Java memory model allows the publication of partially initialized
            // objects and this may lead in turn to subtle bugs.
            synchronized (DoubleCheckedThreadSafeSingletonLogger.class){
                if (loggerObj == null)
                    loggerObj = new DoubleCheckedThreadSafeSingletonLogger();
            }
            //In practice, the excessive verbosity and lack of backward compatibility make this pattern error-prone
            // and thus we should avoid it. Instead, we should use an alternative that lets the JVM do the
            // synchronizing.
        }
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.println("Time taken with double checked locking: " + timeTaken);
        return loggerObj;
    }

    void log(String msg) {
        System.out.println("Log msg is: " + msg);
    }
}

class DoubleCheckedThreadSafeSingletonMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(DoubleCheckedThreadSafeSingletonMain::user1Log);
        t1.start();
        Thread t2 = new Thread(() -> user2Log());
        t2.start();
    }

    private static void user1Log() {
        DoubleCheckedThreadSafeSingletonLogger logger1 = DoubleCheckedThreadSafeSingletonLogger.getLogger();
        logger1.log("This msg is from user1.");
    }

    private static void user2Log() {
        DoubleCheckedThreadSafeSingletonLogger logger2 = DoubleCheckedThreadSafeSingletonLogger.getLogger();
        logger2.log("This msg is from user2.");
    }
}
