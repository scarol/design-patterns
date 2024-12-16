package SingletonDesignPattern;

import java.io.Serializable;

public class SingletonTestFinal implements Serializable {

    /**
     * See below 4 methods to CREATE the Singleton INSTANCE in order to implement the Singleton pattern
     */

    /**
     * 1. Eager Initialization, Public Member, Implicit Thread-safe, global access factory method
     * We access the instance through a public static member, not through a public static factory method.
     * Instance is initialized when the class is loaded, hence it is eager and thus also guarantees thread-safe code.
     public static final SingletonTestFinal INSTANCE = new SingletonTestFinal();
     */

    /**
     * 2. Static block initialization, Eager init, Private member, Implicit Thread-safe, global access factory method,
     * Exception handling
     * Implicit Thread-safe because static block is initialized only once during class loading.
    private static SingletonTestFinal INSTANCE = null;
    static {
        try {
            INSTANCE = new SingletonTestFinal();
        }
        catch(Exception ex) {
            throw new RuntimeException("Exception occurred while creating the SingletonTestFinal class instance.");
        }
    }

    public static SingletonTestFinal getSingletonObj() {
        System.out.println("get singleton obj called!");

        return INSTANCE;
    }*/

    /**
     * 3. Lazy Initialization, Private Member, Explicit Thread-safe, global access factory method, Double-Checked
     * Locking, Volatile Member
     *
     * Using synchronized incurs a performance cost, as multiple threads obtain the lock even if the instance already
     * exists. Therefore, using Double-Checked Locking pattern ensures speed optimization.
     *
     * Double-Checked Locking pattern works with volatile keyword to ensure threads do not access half initialised
     * instance variable. But volatile is not backward compatible prior to java 1.5
     * *//*
    private static volatile SingletonTestFinal INSTANCE = null;

    public static SingletonTestFinal getSingletonObj() {
        if (INSTANCE == null) { // Double-Checked Locking
            synchronized (SingletonTestFinal.class) { // Explicit Thread-safe, acquire lock before entering this block
                if (INSTANCE == null)
                    INSTANCE = new SingletonTestFinal();
            }
        }
        return INSTANCE;
    }*/

    /**
     * 4. Lazy Initialization, Static Holder Pattern, Implicit Thread-safe, Public Static Factory Method
     *
     * The JVM defers initializing the SingletonHolder class until it is actually used, and because the INSTANCE
     * is initialized with a static initializer, no additional synchronization is needed. The first call to
     * getSingletonObj by any thread causes SingletonHolder to be loaded and initialized, at which time the
     * initialization of the INSTANCE happens through the static initializer.

    private static class SingletonHolder {

        private SingletonHolder() {
            System.out.println("Holder class constructor called!");
        }
        private static final SingletonTestFinal INSTANCE = new SingletonTestFinal();
    }

    public static SingletonTestFinal getSingletonObj() {

        System.out.println("get singleton obj called!");
        return SingletonHolder.INSTANCE;
    }*/

    /**
     * Private and Reflection attack safe constructor.
     *
     * A privileged client can invoke the private constructor reflectively with the aid of the
     * AccessibleObject.setAccessible method. If you need to defend against this attack, modify the constructor
     * to make it throw an exception if it's asked to create a second instance.
    private SingletonTestFinal() {

        System.out.println("Top level class constructor called!");

        if (SingletonEnum.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated!");
        }
    }*/

    /**
     * Serialization safe - readResolve method.
     *
     * To make a singleton class that is implemented using either of the previous approaches Serializable, it is not
     * sufficient merely to add implements Serializable to its declaration. To maintain the singleton guarantee,
     * you have to declare all instance fields transient and provide a readResolve method. Otherwise, each time a
     * serialized instance is deserialized, a new instance will be created. If your Singleton Class maintains state,
     * this can become even more complex, as you need to make them transient.
    // readResolve method to preserve singleton property
    private Object readResolve() {
        // Return the one true INSTANCE and let the garbage collector take care of the INSTANCE impersonator.
        return SingletonEnum.INSTANCE;
    }*/

    /*private void doSomething() {
        System.out.println("SingletonTestFinal::doSomething is called.");
    }*/

    /* ========================================================================================== */
    /**
     * Enum singleton - the preferred approach
     *
     * Simply make an enum type with one element. We use enums when we have values that we know aren't going to change.
     * This approach is functionally equivalent to 1. Eager Initialization, Public Member approach, except that it
     * is more concise, provides the serialization machinery for free, and provides an ironclad guarantee against
     * multiple instantiation, even in the face of sophisticated serialization or reflection attacks.
     * A single-element enum type is the best way to implement a singleton.
     */
    public enum SingletonEnum {
        // An enum can, just like a class, have attributes and methods. The only difference is that enum constants are
        // public, static and final (unchangeable - cannot be overridden).
        INSTANCE;
        String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void doSomething() {
            System.out.println("SingletonEnum::doSomething is called.");
        }
     }

}
