package com.example.demo.extension;

import java.util.HashMap;
import java.util.Map;

public class PluginClassLoader extends ClassLoader {
    private final Map<String, byte[]> classes = new HashMap<>();

    public void addClass(String name, byte[] bytecode) {
        classes.put(name, bytecode);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytecode = classes.get(name);
        if (bytecode != null) {
            return defineClass(name, bytecode, 0, bytecode.length);
        }
        throw new ClassNotFoundException(name);
    }
}