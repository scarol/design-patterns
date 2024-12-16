Builder pattern was introduced to solve some of the problems with Factory and Abstract
Factory design patterns when the Object contains a lot of attributes. There are three major
issues with Factory and Abstract Factory design patterns when the Object contains a lot of attributes.

1. Too Many arguments to pass from client program to the Factory class that can be error prone because
most of the time, the type of arguments are same and from client side its hard to maintain the order
of the argument. The number of constructors would also increase exponentially to cover all possible
options. Also, if objects contain fields of the same type, it might be
impossible to create all the variations because of overriding rules in most languages. And it might
result in hard-to-debug issues if we confuse the order of the parameters!

2. Some of the parameters might be optional but in Factory pattern, we are forced to send all the
parameters and optional parameters need to send as NULL.

3. If the object is heavy and its creation is complex, then all that complexity will be part of
Factory classes that is confusing.

We can solve the issues with large number of parameters by providing a constructor with required
parameters and then different setter methods to set the optional parameters. The problem with this
approach is that the Object state will be inconsistent until unless all the attributes are set
explicitly. Builder pattern solves the issue with large number of optional parameters and
inconsistent state by providing a way to build the object step-by-step and provide a method that
will actually return the final Object.

How to implement Builder Design Pattern in Java:
1. Create a static nested class and then copy all the attributes from the outer class to this Builder
class. Naming convention is if product is Computer then builder class is ComputerBuilder. Make out class
constructor as private.
2. Java Builder class should have a public constructor with all the required attributes as parameters.
3. Java Builder class should have setter methods for the optional parameters, returning the same
Builder object after setting the optional attribute.
4. The final step is to provide a build() method in the builder class that will return the Object
of the outer class, which will have a private constructor with Builder class as argument.

Very Important Read:
https://blogs.oracle.com/javamagazine/post/exploring-joshua-blochs-builder-design-pattern-in-java