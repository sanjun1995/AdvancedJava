package com.example.demo.extension;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import javax.tools.*;

public class PluginManager {
    private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    public boolean uploadPlugin(String name, String sourceCode) {
        // 检查插件是否已经存在，如果已经存在则覆盖
        // 将插件源代码保存到数据库中
        // 编译插件源代码生成.class文件
        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {
            Iterable<? extends JavaFileObject> compilationUnits = Collections.singletonList(new StringJavaFileObject(name, sourceCode));
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
            return task.call();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 加载插件
    public Extension loadPlugin(String name) throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.loadClass(name);
        try {
            return (Extension) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 自定义JavaFileObject，用于从数据库中读取Java源代码
    private static class StringJavaFileObject extends SimpleJavaFileObject {
        private final String content;

        public StringJavaFileObject(String className, String content) {
            super(toURI(className), Kind.SOURCE);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }

        private static URI toURI(String className) {
            File file = new File(className);
            if (file.exists()) {
                return file.toURI();
            } else {
                try {
                    final StringWriter stringWriter = new StringWriter();
                    final PrintWriter printWriter = new PrintWriter(stringWriter);
                    printWriter.close();
                    return new URI("string:///" + className.replace('.', '/') + Kind.SOURCE.extension);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 自定义ClassLoader，用于从数据库中读取编译后的.class文件
    private static class MyClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes = readClassBytesFromDatabase();
            return defineClass(name, bytes, 0, bytes.length);
        }

        private byte[] readClassBytesFromDatabase() {
            // 由于编译问题，.class文件生成在别的路径下，所以这里强行指定一下
            String name = "/Users/xxxxx/IdeaProjects/AdvancedJava/BusinessExtension.class";
            Path path = Paths.get(name);
            try {
                return Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
    }
}