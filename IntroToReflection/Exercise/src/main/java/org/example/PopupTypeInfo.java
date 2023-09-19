package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PopupTypeInfo {
    private final List<String> inheritedClassNames = new ArrayList<>();
    private boolean isPrimitive;
    private boolean isInterface;
    private boolean isEnum;
    private String name;
    private boolean isJdk;

    public PopupTypeInfo addAllInheritedClassNames(String[] inheritedClassNames) {
        if (inheritedClassNames != null) {
            this.inheritedClassNames.addAll(Arrays.stream(inheritedClassNames).collect(Collectors.toList()));
        }
        return this;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public PopupTypeInfo setPrimitive(boolean isPrimitive) {
        this.isPrimitive = isPrimitive;
        return this;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public PopupTypeInfo setInterface(boolean isInterface) {
        this.isInterface = isInterface;
        return this;
    }

    public boolean isEnum() {
        return isEnum;
    }

    public PopupTypeInfo setEnum(boolean isEnum) {
        this.isEnum = isEnum;
        return this;
    }

    public String getName() {
        return name;
    }

    public PopupTypeInfo setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isJdk() {
        return isJdk;
    }

    public PopupTypeInfo setJdk(boolean isJdkType) {
        this.isJdk = isJdkType;
        return this;
    }

    public List<String> getInheritedClassNames() {
        return Collections.unmodifiableList(inheritedClassNames);
    }

    @Override
    public String toString() {
        return "PopupTypeInfo{" + "isPrimitive=" + isPrimitive + ", isInterface=" + isInterface + ", isEnum=" + isEnum + ", name='" + name + '\'' + ", isJdk=" + isJdk + ", inheritedClassNames=" + inheritedClassNames + '}';
    }
}