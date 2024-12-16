When to Use Builder Pattern
When the process involved in creating an object is extremely complex, with lots of mandatory and optional parameters
When an increase in the number of constructor parameters leads to a large list of constructors
When client expects different representations for the object that’s constructed

The This implementation also supports the fluent design approach by having the setter methods return the builder object.

Makes use of Fluent Design which is to chain a set of method, which operates/shares the same object.