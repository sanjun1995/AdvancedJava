package com.example.demo.xml;

import org.apache.commons.digester3.Digester;

import java.net.URL;

public class DigesterRule {

    public SchoolDefinition execute(String filePath) throws Exception {
        Digester digester = new Digester();
        digester.setValidating(false);
        // classes node
        digester.addObjectCreate("school/classes", SchoolDefinition.class);
        // class node
        digester.addObjectCreate("school/classes/class", ClassDefinition.class);
        // set properties
        digester.addSetProperties("school/classes/class");
        // student node
        digester.addObjectCreate("school/classes/class/student", StudentDefinition.class);
        // set properties
        digester.addBeanPropertySetter("school/classes/class/student/no");
        digester.addBeanPropertySetter("school/classes/class/student/name");
        digester.addBeanPropertySetter("school/classes/class/student/age");
        // add student
        digester.addSetNext("school/classes/class/student", "addStudent");
        // add class
        digester.addSetNext("school/classes/class", "addClass");
        // parse
        URL url = this.getClass().getClassLoader().getResource(filePath);
        System.out.println("url=" + url.toString());
        return digester.parse(url);
    }

    public static void main(String[] args) throws Exception {
        SchoolDefinition schoolDefinition = new DigesterRule().execute("school.xml");
        System.out.println(schoolDefinition);
    }
}
