package com.example.demo.xml;

import java.util.ArrayList;
import java.util.List;

public class ClassDefinition {

    private String className;

    private List<StudentDefinition> studentDefinitionList = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<StudentDefinition> getStudentDefinitionList() {
        return studentDefinitionList;
    }

    public void setStudentDefinitionList(List<StudentDefinition> studentDefinitionList) {
        this.studentDefinitionList = studentDefinitionList;
    }

    public void addStudent(StudentDefinition studentDefinition) {
        studentDefinitionList.add(studentDefinition);
    }

    @Override
    public String toString() {
        return "ClassDefinition{" +
                "className='" + className + '\'' +
                ", studentDefinitionList=" + studentDefinitionList +
                '}';
    }
}