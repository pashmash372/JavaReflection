What we learn in this lecture 

- Intro to Constructor<?> and ways to obtain it
- Constructor parameters insepection using Reflection
- Dynamic object creation using Reflection


### java.lang.reflect.Constructor<T>
- A Constructor of a java class is represented an instance of Constructor<?> class
- The Constructor object contains all the information about the class's constructor including:
    - Number of parameters
    - Types of the parameters
- A class may have multiple constructors

### Methods to get Constructor <?> objects 
- Class.getDeclaredConstructors()
  - returns all declared constructors within the class
  - includes all the public and non-public constructors
- Class.getConstructors()
  - returns all public constructors within the class
- if you know the particular constructor parameter types we call:
    - Class.getConstructor(Class<?>... parameterTypes)
    - Class.getDeclaredConstructor(Class<?>... parameterTypes)
      - returns a Constructor<?> object for the constructor with the specified parameter types
      - throws NoSuchMethodException if no such constructor exists 

Getting the default constructor

```java
public class Person{
    // No declared constructors
    public Person(){} // Auto generated default constructor
}

Constructor<?> defaultConstructor = Person.class.getDeclaredConstructor();

Constructor<?>[]constructors = Person.class.getDeclaredConstructors();
```

### Goal and Motivation - Object Creator
- Implement a single factory method that can create an object of any class
- Depending on the arguments passed to our method , it will find the right constructor
- Create the given class object by calling the right constructor
- Without Reflection it is impossible.

Generic Object Creator without Reflection

```code
public Object CreateObject(Type type, Object arg){
    switch(type){
        case Type.Employee:return new Employee(arg);
        case Type.Contactor:return new Contractor(arg);
        case Type.TempWorker:return new TempWorker(arg);
        case Type.Investor:return new Investor(arg);
        case Type.Trainee:return new Trainee(arg);
        case ...
        case ...
        case ...        
    }    
}
```

```java
public class Employee{
    private String name;
    private int baseSalary;
    private float bonus;
    
    public Employee(String name,int baseSalary){
        this.name = name;
        this.baseSalary = baseSalary;
        this.bonus = 0.0f;
    }
    
    public Employee(String name,int baseSalary,float bonus){
        this.name = name;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }
}
```

### Object Creator with Reflection

- Constructor.newInstance(Object... args)
    - Takes a variable number of constructor arguments in the type and order of the constructor parameters declared in the constructor
- Upon success
  - Calls the appropriate constructor
  - Returns an object of the given class
- Upon failure
  - Throws an exception 


### Summary 
- we learned a few ways to get the information about declared public and non constructors of any java class
- we learned to get the list of parameter types of a constructor
- implemented a powerful,generic method that can create an object of any class
- The factory method can take any number of arguments and find just the right constructor
- We realized that implementation such a factory method without reflection is impossible
- With reflection, we achieved it just with a few lines of code 
- At the heart of that method was the Constructor.newInstance(Object... args) method
