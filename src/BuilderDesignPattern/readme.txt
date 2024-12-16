Builder Pattern helps resolve object creation problems for Complex Objects having multiple
configurations. Builder pattern was introduced to solve some of the problems with Factory and
Abstract Factory design patterns when the Object contains a lot of attributes.
1. Getter Setter Methods: Class properties can be encapsulated with a private keyword and respective
getter/setter methods are exposed to clients to get/set values, but there is no way to restrict
a client from setting a value multiple times. In cases where if once initialised the object value
should remain unchanged this can be a problem.
2. Constructor Overloading: Too many parameters, some may be optional, 2 to the power n constructors
are possible for complex objects, this is bad coding.
3. Backward Compatibility: It is common for complex objects to keep having new properties.
Changes to the object creation will force clients to change their code - this is not acceptable.
4. Client Enforcement: Forcing the clients to remember the number or sequence of parameters. This
can be confusing and frustrating because complex objects can have huge number of parameters.

How Builder pattern helps resolve these problems:
- Pass a temporary object with all the same properties as the main product to the main product class
constructor, so instead passing huge number of parameters clients will send a temporary object with
the relevant properties set
- To make the client code more efficient, this temporary object class's setter methods must return
an object of the same class (return this) instead of being void so the the client can invoke all setter
methods in a single call.
- Additionally the temporary class must provide a function to return an instance of the class too.

This temporary object/class is called the 'Builder' class, and this function is the 'build' method
of the 'Builder' class.

This pattern in addition to solving all the above problems, also eases the client side code to
hardly a line or two!
for example this
Product p = new TempClass().set1(aaa).set2(bbb).set3(ccc).build();
instead of this
Product p = new Product(TempClass tempObj)
p.set1(tempObj.get1()); p.set2(tempObj.get1()); p.set3(tempObj.get1());

Now our class TempClass has no existence without class Product. Additionally, if any changes in
attributes for the Product class, same have to be applied to the TempClass also. And this becomes a
maintenance issue. Therefore moving TempClass within Product makes it easier to remember for the
developers to make the changes - Inner and Outer classes.
But since Product constructor depends on TempClass object and TempClass is now an inner class
therefore make TempClass as a static inner class, so that it can be invoked without needing an
object of the Product.

class Product {
    static class X {

    }
}
Product p = new Product.TempClass().set1(aaa).set2(bbb).set3(ccc).build();

So here we call TempClass as the ProductBuilder and the function returning a Product as
build() method

See this video:
https://www.youtube.com/watch?v=PSYp5svfxEQ&list=PL4WwUkr0wZUQHOVBzZnvA0tEk9sPqBrHm&index=1