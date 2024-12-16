The Singleton Holder idiom in Java is thread-safe and is another preferred approach to Enum. This is a widely used
approach for a Singleton class as it doesn’t require synchronization, is thread safe, enforces lazy initialization
and has comparatively less boilerplate.

How it works:
Leverages Class Loading: The Singleton instance is created within a private static inner class. This class is only
loaded when it's first accessed, which is guaranteed to happen only once by the JVM, even in a multithreaded environment.

No Explicit Synchronization: This approach doesn't require any explicit synchronization mechanisms like synchronized
blocks or the volatile keyword, making it simpler to implement and understand.

Example:
public class Singleton {
    private Singleton() {}
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
Explanation:
The Singleton class has a private constructor to prevent instantiation from outside the class.
The SingletonHolder class is a private static inner class that holds the single instance of the Singleton.
The getInstance() method provides access to the singleton instance. When this method is called for the first time,
the SingletonHolder class is loaded, which initializes the INSTANCE variable. This initialization is guaranteed to
be thread-safe by the JVM.