package dev.pashmash;

import java.lang.reflect.Field;

import static dev.pashmash.Main.printDeclaredFields;

public class Main {
    public static <T> void  printDeclaredFields(Class<? extends T> clazz, T instance) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("Field name: %s, type: %s%n", field.getName(), field.getType().getSimpleName());
            System.out.printf("Is synthetic field : %s%n", field.isSynthetic());
            System.out.printf("Field value is : %s%n", field.get(instance));
            System.out.println();
        }
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Movie movie = new Movie("Lord of the Rings", 2001, 10.99, true, Category.ADVANTURE, 10.99);
        printDeclaredFields(movie.getClass(), movie);
        Field minPriceStaticField = Movie.class.getDeclaredField("MINIMUM_PRICE");
        System.out.println(String.format("static MINIMUM_PRICE value :%f%n", minPriceStaticField.get(null)));

    }

    public enum Category {
        ADVANTURE, ACTION, COMEDY
    }

    public static class Movie extends Product {
        private static final double MINIMUM_PRICE = 10.99;
        private final boolean isReleased;
        private final Category category;
        private final double actualPrice;

        public Movie(String name, int year, double actualPrice, boolean isReleased, Category category, double actualPrice1) {
            super(name, year, actualPrice);
            this.isReleased = isReleased;
            this.category = category;
            this.actualPrice = actualPrice1;
        }

        //  Nested class
        public class MovieStats {
            private final double timesWatched;

            public MovieStats(double timesWatched) {
                this.timesWatched = timesWatched;
            }

            public double getRevenue() {
                return actualPrice * timesWatched;
            }
        }
    }

    public static class Product {
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year, double actualPrice) {
            this.name = name;
            this.year = year;
            this.actualPrice = actualPrice;
        }
    }
}