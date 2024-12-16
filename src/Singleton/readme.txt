To understand Singleton Pattern, we use the Logger example which must be a single instance throughout an
application and is used by multiple clients to log messages.
Let us implement our MultiLoggerIssue class first without using the Singleton and see what problem it creates. We
define a public constructor. And invoke it from our client multiple times because in real prod scenarios logging is
used across application and invoked multiple times. We use a counter to keep track of the number of instances that
get created for the logger class. When we execute this program we see that more than 1 instance is getting created
for logger which is not what we want to do in such cases - logging, configuration management, cache manager, file system
manager, database or network connection,etc. such features require a single instance throughout the application to be used.

Now to make our logger Singleton we do the following:
1. Restrict users from creating objects of the class - make constructor private.
2. Make the single instance as private and static so that it belongs to the class and not to any object of the class.
3. Create a static method - And this instance is initialised by the public static get logger method which makes sure
that only 1 instance of the class exists throughout the application. Since the constructor is private, external
classes cannot directly call it to create objects. To overcome this, you can create a static method within the
class. Since it’s a static method, it can be called without the need for an object. This method is often referred
to as a factory method or static factory method.

BUT this approach is not thread safe. Singleton fails in multithreading environment. To handle this when first thread
finds that an instance is null and starts to create the instance, it must first obtain a lock around that code and
only then proceed to create the instance. This will ensure that if another simultaneously executing thread finds the
instance to be null, it will wait until the lock is released and not be able to execute the object creation code
while the first thread is already in the process of doing so. When the first thread completes its job it releases the
lock and returns that instance so the second thread does not create the instance again.

BUT locks are an overhead and they are expensive resources. Therefore, Double-Checked Locking is used to boost
performance. Even then there are 3 main scenarios that breaks Singleton even though we make it Thread Safe!
They are: Cloning, Deserialization and Reflection (and Copy Constructor? and operator overloading?)
1. Cloning: It is necessary for you Singleton to override clone() and throw exception if your Singleton class extends
a class that has a visible clone() method defined in its hierarchy.
2. Deserialization: To make a singleton class Serializable, it is not sufficient merely to add implements Serializable
to its declaration. To maintain the singleton guarantee, you have to declare all instance fields transient and provide
a readResolve method. Otherwise, each time a serialized instance is deserialized, a new instance will be created,
breaking the Singleton. To prevent this, add this readResolve method to the Singleton class:
// readResolve method to preserve singleton property
private Object readResolve() {
   // Return the one true INSTANCE and let the garbage collector take care of the object impersonator.
   return INSTANCE;
}
3. Reflection: A privileged client can invoke the private constructor reflectively with the aid of the
AccessibleObject.setAccessible method. To defend against this attack, modify the constructor to make it throw an
exception if it's asked to create a second instance.

Even though the double-checked locking can potentially speed things up, it has at least two issues:
1. since it requires the volatile keyword to work properly, Without volatile modifier, it’s possible for another thread
in Java to see half initialized state of instance variable, and  it’s not compatible with Java 1.4 and lower versions
2. it’s quite verbose and it makes the code difficult to read
For these reasons, let’s look into some other options without these flaws. Methods that delegate the synchronization
task to the JVM are best in such cases, like Early Initialization, Static Block Implementation, Initialization On
Demand, Lazy Loading ?
Early Init: // Singleton with public static final field
            public class Singleton {
               public static final Singleton INSTANCE = new Singleton();
               private Singleton() { ... }
               public void someMethod() { ... }
            }

But given the implementation of Singleton pattern as a class has so many pitfalls, it is best to implement a
Singleton as an Enum. Simply make an enum type with one element:
            // Enum singleton - the preferred approach
            public enum Singleton {
               INSTANCE;
               public void someMethod() { ... }
            }
This approach is functionally equivalent to the public static field approach, except that it is more concise, provides
the serialization machinery for free, and provides an ironclad guarantee against multiple instantiation, even in the
face of sophisticated serialization or reflection attacks.

https://medium.com/@kevalpatel2106/digesting-singleton-design-pattern-in-java-5d434f4f322
http://www.javabyexamples.com/singleton-pattern-in-java/

** Do 1 example of Lazy Initialization, Holder Class, Thread-safe (initialization-on-demand holder design pattern)
https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom#Example_Java_Implementation
https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java
https://java-design-patterns.com/snippets/

