package com.example.demo.design.component;

/**
 * @author caozhixin
 * @date 2023/11/17 18:52
 */
public class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("File: " + name);
    }
}
