### What we learn in this lecture

- Package - Private classes instantiation using Constructor class
- External Package Private classes access use cases 
- Dependency Injection Implementation

Public classes vs Package Private classes

- Public classes are accessible from anywhere
- Package Private classes are accessible only from within the package

### Package Private classes instantiation using Constructor class

```code
package some.package;

// can be accessed from inside the package 
public class PublicClass{
    public PublicClass(){
        ...
    }   
}
```

```code
package some.package

// cannot be accessed from outside the package
class InternalClass{
    public InternalClass(){
        ...
    }
}
```

Package-Private classes External Access
- There are cases where we need to acess package-private classes from outside the package for 
  - Reading
  - Intializing
- Typical when we want to use an external library to helpo us intialize those classes
- The code in the external libraries is outside of our package

### Package-Private classes Creation

```code 
public Object createClassInstance(Constructor<?> packagePrivateClassCtor,
                                  Object... ctorArgs) {
    return packagePrivateClassCtor.newInstance(ctorArgs);
}
```
### Dependencies Injection Implementation
- We performed the exact same task on each of the classes we visited
- A recursive algorithm(function that calls itself) is a great fit for the implementation
- For each dependency we visit
    - We keep going deep into the graph 
    - Unitl we find a class with no dependencies that we can instantiate
- Once the entire graph is covered : we are done


### Summary 
- We talked about another powerful capability of Java Reflection
  - Inspection and instantiation of package-private classes
- We discussed a few scenarios where breaking the private-package access modifier restrictyion is desirable 
- Implemented a basic but powerful version of Depedenecy Injection
