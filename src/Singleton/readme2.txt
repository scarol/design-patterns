Singleton Issues:
1. A singleton is supposed to be unique per JVM. This might be a problem for distributed systems or systems whose
internals are based on distributed technologies.
2. Every class loader might load its version of the singleton.
3. A singleton might be garbage-collected once no one holds a reference to it. This issue doesn’t lead to the presence
of multiple singleton instances at a time, but when recreated, the instance might differ from its previous version.
4. Conceptually, a singleton is a kind of global variable. In general, we know that global variables should be avoided,
especially if their states are mutable.
5. We’re not saying that we should never use singletons; however, we are saying that there might be more efficient ways
to organize our code. If a method’s implementation depends on a singleton object, why not pass it as a parameter?
In this case, we explicitly show what the method depends on. As a result, we may easily mock these dependencies
(if necessary) when performing testing. For example, singletons are often used to encompass the application’s
configuration data (i.e., connection to the repository). If they’re used as global objects, it becomes difficult to
choose the configuration for the test environment. Therefore, when we run the tests, the production database gets
spoiled with the test data, which is hardly acceptable. If we need a singleton, we might consider the possibility of
delegating its instantiation to another class, a sort of factory, that should take care of assuring that there’s just
one instance of the singleton in play.

When to Use Singleton Design Pattern
For resources that are expensive to create (like database connection objects)
It’s good practice to keep all loggers as Singletons which increases performance
Classes which provide access to configuration settings for the application
Classes that contain resources that are accessed in shared mode
