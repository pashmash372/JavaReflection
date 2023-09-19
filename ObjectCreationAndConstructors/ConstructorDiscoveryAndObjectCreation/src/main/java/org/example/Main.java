package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
//        printConstructorData(Person.class);

//        Person person1 = createInstanceWithArguments(Person.class);
//        System.out.println(person1);
//        Person person2 = createInstanceWithArguments(Person.class, "John", 30);
//        System.out.println(person2);
//        Person person3 = createInstanceWithArguments(Person.class, new Address("street", "city", "country"), "John", 30);
//        System.out.println(person3);

//        without casting
        Person person1 = createInstanceWithArguments(Person.class);
        System.out.println(person1);
        Person person2 = createInstanceWithArguments(Person.class, "John", 30);
        System.out.println(person2);
        Person person3 = createInstanceWithArguments(Person.class, new Address("street", "city", "country"), "John", 30);
        System.out.println(person3);

    }

    public static <T> T createInstanceWithArguments(Class<T> clazz, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length == args.length) {
                return (T) constructor.newInstance(args);
            }
        }
        System.out.println("An appropriate constructor was not found");
        return null;
    }

    public static void printConstructorData(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.printf("Class %s has %d constructors%n", clazz.getName(), constructors.length);
        for (int i = 0; i < constructors.length; i++) {
            Class<?>[] parametersTypes = constructors[i].getParameterTypes();

            List<String> parametersTypesNames = List.of(parametersTypes).stream().map(Class::getName).toList();
            System.out.println(parametersTypesNames);
        }
    }

    public static class Person {
        private final Address address;
        private final String name;
        private final int age;

        // default constructor
        public Person() {
            this.address = null;
            this.name = "anonymous";
            this.age = 0;
        }

        public Person(String name, int age) {
            this.address = null;
            this.name = name;
            this.age = age;
        }

        public Person(Address address, String name, int age) {
            this.address = address;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" + "address=" + address + ", name='" + name + '\'' + ", age=" + age + '}';
        }
    }

    public static class Address {
        private final String street;
        private final String city;
        private final String country;

        public Address(String street, String city, String country) {
            this.street = street;
            this.city = city;
            this.country = country;
        }

        @Override
        public String toString() {
            return "Address{" + "street='" + street + '\'' + ", city='" + city + '\'' + ", country='" + country + '\'' + '}';
        }
    }

}