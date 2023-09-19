package org.example;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(Exercise.createPopupTypeInfoFromClass(Class.forName("java.lang.String")));
        System.out.println(Exercise.createPopupTypeInfoFromClass(Product.class));
    }
}

class Product {
    int id;
    String name;
    float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;

    }
}