package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;
        Map<String, Integer> mapObject = new HashMap<>();
        Class<?> hashMapClass = mapObject.getClass();
        Class<?> squareClass = Class.forName("org.example.Main$Square");
//        printClassInfo(stringClass, hashMapClass, squareClass);

        var circleObject = new Drawable(){
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };

        printClassInfo(Collection.class,boolean.class,int [][].class,Color.class,circleObject.getClass());

    }

    private static void printClassInfo(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            System.out.println(String.format("Class name : %s, class package name : %s",
                    clazz.getSimpleName(),
                    clazz.getPackageName()));

            Class<?>[] implementedInterface = clazz.getInterfaces();
            for (Class<?> implementedInterfaceClass : implementedInterface) {
                System.out.println(String.format("Implemented interface name : %s",
                        implementedInterfaceClass.getSimpleName()
                        ));
            }
            System.out.println("Is array : " + clazz.isArray());
            System.out.println("Is primitive : " + clazz.isPrimitive());
            System.out.println("Is enum : " + clazz.isEnum());
            System.out.println("Is interface : " + clazz.isInterface());
            System.out.println("Is anonymous class : " + clazz.isAnonymousClass());

            System.out.println();
            System.out.println();
        }
    }

    private static class Square implements Drawable {
        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private static interface Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        RED, GREEN, BLUE
    }
}