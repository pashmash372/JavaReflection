### What we learn in this lecture

- Non Public Constructor Invocation 
- Server Configuration using Reflection

### Calling Private Constructors

```java
public class RestrictedClass{
    private RestrictedClass(){
        // Cannot be called from outside the class 
    }
}

//RestrictedClass restrictedClass = new RestrictedClass(); // Compile Error
```
### Private Constructors with Reflection
- Using Reflection we have full access to all 
    - public
    - protected
    - package private aka default
    - private
  constructors
- Using Constructo.newInstance() we can create objects of a class using the restricted constructors
- Exception : A class belonging to a java module that does not allow access to a given class 

### Use cases - Configuration object

```java
public class Configuration { // Configuration accessible to everyone 
    private final int serverPort;
    private final bool keepAlive;
    private final String hostName;

    // Restricted to our library/framework
    private Configuration(int serverPort, boolean keepAlive, String hostName) {
        this.serverPort = serverPort;
        this.keepAlive = keepAlive;
        this.hostName = hostName;
    }

    // Accessible to everyone
    public int getServerPort() {
        return serverPort;
    }

    public boolean getKeepAlive() {
        return keepAlive;
    }

    public String getHostName() {
        return hostName;
    }
}
```

### Calling Private Constructors with Reflection 

```code
Constructor<?> constructor = getDeclaredConstructor();
constructor.setAccessible(true); // make private constructor accessible
constructor.newInstance(arg1,arg2);
```

### final result 
![img.png](img.png)


### Summary

- We learned an advanced feature of java reflectoin - ability to create objects using otherwise inaccessible constructos 
- we demostrated the feature in a use cases where we wanted to instantiate an object of a class that would be accessible to use, but impossible to create accidentally.
- All we need to do to use this feature is to call 
  - Constructor.setAccessible(true) before calling 
  - Constructor.newInstance(..)


### Importanty Notes
- This advanced feature should be reserved to specific use cases
- It should not be abused to invoke internal or deliberately restrictyed constructors on classes that do not belong to us 
- With Reflection we gain a lot of power, but also a lot of responsibility
- We need to be carefull about where we break the access-modifiers rules of java
- 