package Singleton;

public class SingletonLogger {
    private static SingletonLogger loggerObj = null; // private static single class object only available to the
    // class and not to the users of the class
    static int ctr = 0; // make instance counter to be used from within the constructor as static so that it
    // belongs to the class and not to the object. This is so that it does not get re-initialised everytime the
    //constructor is called and so we can count the TOTAL number of class objs created.

    private SingletonLogger() { // private constructor to restrict other code from accessing it
        ctr++;
        System.out.println("New instance created.");
        System.out.println("Number of logger instances: " + ctr);
    }

    static SingletonLogger getLogger() { // static method that returns a single instance of the class
        if (loggerObj == null)
            loggerObj = new SingletonLogger();
        return loggerObj;
    }

    void log(String msg) {
        System.out.println("Log msg is: " + msg);
    }
}

class SingletonLoggerMain {
    public static void main(String[] args) {

        SingletonLogger logger1 = SingletonLogger.getLogger();
        logger1.log("This msg is from user1.");

        SingletonLogger logger2 = SingletonLogger.getLogger();
        logger2.log("This msg is from user2.");
    }
}
