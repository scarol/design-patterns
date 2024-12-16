The Builder pattern, which is one of the 23 Gang of Four (GoF) design patterns is a creational design pattern
that lets you construct complex objects step by step. It allows you to produce different types and representations
of a product using the same construction code. However, this pattern should be used only if you need to build
different immutable objects using the same building process.

The Builder pattern differs not very much from another important GoF creational pattern, the Abstract Factory
pattern. While the Builder pattern focuses on constructing a complex object step by step, the Abstract Factory
pattern emphasizes a family of Product objects, either simple or complex. Whereas the Builder pattern returns
the final Product as a last step, the Abstract Factory pattern returns the Product immediately.

Bloch’s version of the Builder pattern provides a simple and safe way to build objects that have many optional
parameters, so it addresses the telescoping constructor problem (which I describe shortly). In addition, with
large constructors, which in most cases have several parameters of the same type, it is not always obvious which
value belongs to which parameter. Therefore, the likelihood of mixing up parameter values is high.

The idiom used by Bloch’s Builder pattern addresses these issues by creating a static inner Builder class that can
be accessed without creating an instance of the outer class (the product being built) but that still has access to
the outer private constructor.

eg., public class Book {
       private final String isbn; //mandatory
       private final String title; //mandatory
       private final Genre genre; //optional
       private final String author; //optional
       private final Year published; //optional
       private final String description; //optional
       public Book(String isbn, String title, Genre genre, String author, Year published, String description) {
           this.isbn = isbn; this.title = title; this.genre = genre; this.author = author;
           //and so on...
       }
       //getters()....
       //no setter().....this is an immutable object
   }
   The Book class has six final fields, one constructor taking all the parameters to be set, and the corresponding
   getters to read the object’s fields once the object has been created. As a consequence, all objects derived
   from this class are immutable. Now how can you construct objects with different combinations of optional
   parameters by using an appropriate constructor for each given combination? Because the objects are intended to
   be immutable, Enterprise JavaBean–like setters are out of question.

   Telescoping constructors
   One possible solution consists of telescoping constructors, where the first constructor takes only the
   mandatory fields; for every optional field, there is a further constructor that takes the mandatory fields
   plus the optional fields. Every constructor calls the subsequent one by passing a null value in place of the
   missing parameter. Only the final constructor in the chain will set all the fields by using the values provided
   by the parameters.
   public class Book {
       private final String isbn;
       private final String title;
       private final Genre genre;
       private final String author;
       private final Year published;
       private final String description;

       public Book(String isbn, String title) {
           this(isbn, title, null);
       }
       public Book(String isbn, String title, Genre genre) {
           this(isbn, title, genre, null);
       }
      //and so on...
       public Book(String isbn, String title, Genre genre, String author, Year published, String description) {
            this.isbn = isbn; this.title = title; this.genre = genre; this.author = author;
            //and so on...
       }
       //getters()....
       //no setter().....this is an immutable object

Unfortunately, the telescoping constructors will not prevent you from having to pass null values in some cases.
For instance, if you had to create a Book with ISBN, title, and author, what would you do? There is no such
constructor! You would probably use an existing constructor and pass a null value in place of the missing parameter.
    new Book("isbn", "title", null, "auth");
However, the use of null values can be avoided by creating an appropriate constructor, but for another combination
such as isbn, title, and description instead of author, you may want to create another constructor.
This would not work. Two constructors of the same signature cannot coexist in the same class, because the compiler
would not know which one to choose. In addition, creating a constructor for every useful combination of parameters
would result in a large combination of constructors, making the resulting code hard to read and even harder to
maintain. Therefore, neither telescoping constructors nor any other possible combination of constructor parameters
is a good approach to solve the issues related to the construction of objects that have numerous optional fields.
This is where Bloch’s version of the Builder pattern comes in. Bloch’s Builder pattern is a variation of the GoF
Builder pattern. The GoF Builder pattern has four components: the Director, the Builder (interface), the
ConcreteBuilder (implementation), and the Product. Bloch’s Builder pattern is shorthand for the GoF’s counterpart in the
sense that it consists of only two of the four components: the ConcreteBuilder and the Product. In addition,
Bloch’s Builder has a Java-specific implementation since the Builder consists of a nested static class (located
inside the Product class itself).


