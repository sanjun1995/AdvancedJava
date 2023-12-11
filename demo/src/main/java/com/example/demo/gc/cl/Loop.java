package com.example.demo.gc.cl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author caozhixin
 * @date 2023/12/5 11:00
 */
public class Loop {
    public static void main(String[] args) throws Exception {
        ClassLoaderB loaderB = new ClassLoaderB("CLB");
        loaderB.setPath("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/target/classes/");
        Class<?> clazz = loaderB.loadClass("com.example.demo.gc.MemoryDem");
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println("-----------------");
        System.gc();
        while (true) {
            Thread.sleep(100000);
        }
    }

    private static class ClassLoaderB extends ClassLoader {
        private String classLoaderName;
        //类的扩展名
        private final String fileExtension = ".class";
        private String path;

        public void setPath(String path) {
            this.path = path;
        }

        public ClassLoaderB(String classLoaderName) {
            super();
            this.classLoaderName = classLoaderName;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            System.out.println("loadClass invoked: " + name);
            System.out.println("class loader name: " + this.classLoaderName);
            return super.loadClass(name, resolve);
        }

        @Override
        protected Class<?> findClass(String className) {
            System.out.println("findClass invoked: " + className);
            System.out.println("class loader name: " + this.classLoaderName);
            byte[] data = this.loadClassDate(className);
            return this.defineClass(className, data, 0, data.length);
        }
        private byte[] loadClassDate(String name) {
            InputStream is = null;
            byte[] data = null;
            ByteArrayOutputStream baos = null;
            try {
                name = name.replace(".", "/");
                is = new FileInputStream(new File(this.path + name + this.fileExtension));
                baos = new ByteArrayOutputStream();
                int ch = 0;
                while ((ch = is.read()) != -1) {
                    baos.write(ch);
                }
                data = baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return data;
        }
    }
}