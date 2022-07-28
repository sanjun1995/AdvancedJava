package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author caozhixin
 * @date 2022/7/28 11:58 PM
 */
public class CustomClassLoader extends ClassLoader {
    // 需要该类加载器直接加载的类文件的基目录
    private String baseDir;

    public CustomClassLoader(String baseDir, String[] classes) throws IOException {
        super();
        this.baseDir = baseDir;
        loadClassByMe(classes);
    }

    private void loadClassByMe(String[] classes) {
        for (int i = 0; i < classes.length; i++) {
            findClass(classes[i]);
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        Class<?> clazz = null;
        StringBuilder stringBuilder = new StringBuilder(baseDir);
        String className = name.replace('.', File.separatorChar) + ".class";
        stringBuilder.append(File.separator + className);
        File classF = new File(stringBuilder.toString());
        try {
            clazz = instantiateClass(name, new FileInputStream(classF), classF.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private Class<?> instantiateClass(String name, FileInputStream fileInputStream, long length) throws IOException {
        byte[] raw = new byte[(int)length];
        fileInputStream.read(raw);
        fileInputStream.close();
        return defineClass(name, raw, 0, raw.length);
    }
}
