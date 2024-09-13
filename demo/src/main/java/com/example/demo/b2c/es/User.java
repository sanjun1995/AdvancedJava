package com.example.demo.b2c.es;

/**
 * @author caozhixin
 * @date 2024/9/13 14:07
 */
public class User {
    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + '\'' + '}';
    }
}
