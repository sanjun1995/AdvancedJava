package com.example.demo.xml;

import java.util.ArrayList;
import java.util.List;

public class SchoolDefinition {

    private List<ClassDefinition> classDefinitions = new ArrayList<>();

    public List<ClassDefinition> getClassDefinitions() {
        return classDefinitions;
    }

    public void setClassDefinitions(List<ClassDefinition> classDefinitions) {
        this.classDefinitions = classDefinitions;
    }

    public void addClass(ClassDefinition classDefinition) {
        classDefinitions.add(classDefinition);
    }

    @Override
    public String toString() {
        return "SchoolDefinition{" +
                "classDefinitions=" + classDefinitions +
                '}';
    }
}