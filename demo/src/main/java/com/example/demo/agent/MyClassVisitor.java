package com.example.demo.agent;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {

    public MyClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println("Class: " + name + " extends " + superName + " {");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
        super.visitEnd();
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = MyClassVisitor.class.getResourceAsStream("/com/example/demo/agent/Test.class");
        ClassReader classReader = new ClassReader(inputStream);
        ClassVisitor classVisitor = new MyClassVisitor(null);
        classReader.accept(classVisitor, 0);
    }
}

