package com.example.demo.serializable;

import java.io.*;

class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Serialization proxy for Person class
    private static class PersonProxy implements Serializable {
        private String name;

        public PersonProxy(String name) {
            this.name = name;
        }

        // Custom method to create a Person object from the proxy
        private Object readResolve() {
            // Create a Person object using the proxy data
            return new Person(name, 10); // Age not serialized
        }
    }

    // Custom method to create a serialization proxy
    private Object writeReplace() {
        return new PersonProxy(name);
    }

    @Override
    public String toString() {
        return "Person[name='" + name + "', age=" + age + "]";
    }

    public static void main(String[] args) throws Exception {
        // Create a Person object
        Person originalPerson = new Person("Alice", 30);

        // Serialize the Person object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            out.writeObject(originalPerson);
        }

        // Deserialize the Person object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.ser"))) {
            Person deserializedPerson = (Person) in.readObject();
            System.out.println("Deserialized Person: " + deserializedPerson);
        }
    }
}