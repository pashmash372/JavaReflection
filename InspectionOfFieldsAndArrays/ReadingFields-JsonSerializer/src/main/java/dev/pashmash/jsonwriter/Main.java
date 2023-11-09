package dev.pashmash.jsonwriter;


import dev.pashmash.data.Address;
import dev.pashmash.data.Company;
import dev.pashmash.data.Person;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Address address = new Address("Main Street", (short) 10);
        Company company = new Company("Google", "Delhi", address);
        Person person = new Person("John", true, 29, 100.555f, address, company);
//        String json = objectToJson(address, 0);
        String json = objectToJson(person, 0);
        System.out.println(json);
    }

    public static String objectToJson(Object instance, int indentSize) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        sb.append(indent(indentSize));
        sb.append("{");
        sb.append("\n");
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.isSynthetic()) {
                continue;
            }
            sb.append(indent(indentSize + 1));
            sb.append(formatStringValue(field.getName()));
            sb.append(":");
            if (field.getType().isPrimitive()) {
                sb.append(formatPrimitiveValue(field, instance));
            } else if (field.getType().equals(String.class)) {
                sb.append(formatStringValue(field.get(instance).toString()));
            } else {
                sb.append(objectToJson(field.get(instance), indentSize + 1));
            }
            if (i < fields.length - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append(indent(indentSize));
        sb.append("}");
        return sb.toString();
    }

    private static String indent(int indentSize) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentSize; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    private static String formatPrimitiveValue(Field field, Object parentInstance) throws IllegalAccessException {
        if (field.getType().equals(boolean.class) || field.getType().equals(short.class) || field.getType().equals(int.class) || field.getType().equals(long.class)) {
            return field.get(parentInstance).toString();
        } else if (field.getType().equals(float.class)) {
            return String.format("%.2f", field.get(parentInstance));
        }
        throw new RuntimeException(String.format("Type %s is not supported", field.getType().getSimpleName()));
    }

    private static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }
}
