Factory Pattern is used in creation of objects.

To see how and where Factory pattern is relevant we take an example of runtime polymorphism, where a client
requests for an object of type Vehicle based on a certain input. We start by first looking at what problem we
may encounter in this case if not using the Factory pattern.

We first define a base class named Vehicle containing an abstract method which the child classes, Car and Bike, have
to provide implementation for. This makes our Vehicle library code that can be shipped to the client as a jar file.
Our client code, VehicleMain main class, will now take an input string for the type of vehicle it wants to create.
Depending on the 'type' with the if-else statements it will create the desired object.

Now the issue with this approach is that firstly, the client code is too tightly coupled with the library. Any changes
to the library will force a change to the client code, like renaming type parameters or handling of a new type, etc.
Secondly, the client only needed a Vehicle object but this approach is also dumping upon the client the vehicle
object creation logic. So in order to deal with these 2 problems we use the Factory Design Pattern.

We introduce another class called VehicleFactory containing 1 method to get vehicle object depending on the type of
the input received. And we move the object creation logic that the client main class was handling to the Factory
class. Now the client does not have to bother about the object creation logic and it only has to invoke the factory
method to get the desired vehicle object by passing a type. It is the Factory method responsibility now to see
which object to create based on the input received.

The Factory method has been made static because we do not need the factory class object and only need the function
to return the desired object. So in order to avoid creating the factory object we make the method as static so
that it can be invoked using class name itself as static members belong to a class and not to an object.