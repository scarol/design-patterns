package SingletonDesignPattern;

import java.io.*;

public class MainTest implements Serializable {

    /*// Multithreading Demo - Thread-safe, no race condition
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> method1()); // lambda
        Thread t2 = new Thread(MainTest::method2); // method reference
        t1.start();
        t2.start();
    }

    private static void method1() {
        SingletonTestFinal obj1 = SingletonTestFinal.getSingletonObj();
        System.out.println("Instance hashCode is: " + obj1.hashCode());
    }

    private static void method2() {
        SingletonTestFinal obj2 = SingletonTestFinal.getSingletonObj();
        System.out.println("Instance hashCode is: " + obj2.hashCode());
    }*/

    /**
     * Reflection attack Demo: An advance user can change the private access modifier of the constructor using
     * reflection. If this happens, our only mechanism for non-instantiability breaks.
     *
     * To defend against this attack, modify the constructor to make it throw an exception if it's asked to create a
     * second instance.
     */
    /*public static void main(String[] args) throws Exception {
        SingletonTestFinal singleton = SingletonTestFinal.getSingletonObj();

        Constructor constructor = singleton.getClass().getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true); // Make the private constructor accessible

        SingletonTestFinal singleton2 = (SingletonTestFinal) constructor.newInstance(); // Create new instance with constructor

        if (singleton == singleton2) {
            System.out.println("Two objects are same");
        } else {
            System.out.println("Two objects are not same");
        }

        System.out.println(singleton.hashCode());
        System.out.println(singleton2.hashCode());
    }*/

    /**
     * Serialization and Deserialization Demo: In order to serialize our singleton class it must implement
     * the Serializable interface. But doing that is not enough. When deserializing a class, new instances are created.
     * Now it doesn’t matter the constructor is private or not. Now there can be more than one instance of the same
     * singleton class inside the JVM violating the singleton property.
     *
     * To maintain the singleton guarantee, provide a readResolve method in the Singleton class to return the one true
     * INSTANCE and let the garbage collector take care of the INSTANCE impersonator.
     */
    /*public static void main(String[] args) {
        SingletonTestFinal singleton = SingletonTestFinal.getSingletonObj();

        // Serialize the singleton with value = 1
        try {
            FileOutputStream fileOut = new FileOutputStream("out.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(singleton);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the serialized singleton with value 1
        SingletonTestFinal singleton2 = null;
        try {
            FileInputStream fileIn = new FileInputStream("out.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            singleton2 = (SingletonTestFinal) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }

        if (singleton == singleton2) {
            System.out.println("Two objects are same");
        } else {
            System.out.println("Two objects are not same");
        }

        System.out.println(singleton.hashCode());
        System.out.println(singleton2.hashCode());

    }*/
    public static void main(String[] args) {

        // Accessing Singleton Enum Demo
        SingletonTestFinal.SingletonEnum singletonObj = SingletonTestFinal.SingletonEnum.INSTANCE;

        System.out.println(singletonObj.hashCode() + " : " + singletonObj.getValue());
        singletonObj.setValue("test1");
        System.out.println(singletonObj.hashCode() + " : " + singletonObj.getValue());
        singletonObj.setValue("test2");
        System.out.println(singletonObj.hashCode() + " : " + singletonObj.getValue());

        // Multithreading Demo
        Thread t1 = new Thread(() -> method1()); // lambda
        Thread t2 = new Thread(MainTest::method2); // method reference
        t1.start();
        t2.start();
    }

    private static void method1() {
        SingletonTestFinal.SingletonEnum obj1 = SingletonTestFinal.SingletonEnum.INSTANCE;
        System.out.println("Thread1: Instance hashCode is: " + obj1.hashCode());
    }

    private static void method2() {
        SingletonTestFinal.SingletonEnum obj2 = SingletonTestFinal.SingletonEnum.INSTANCE;
        System.out.println("Thread2: Instance hashCode is: " + obj2.hashCode());
    }
}
