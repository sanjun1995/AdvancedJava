package com.example.demo.agent;

import org.objectweb.asm.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class LabelDemo {

    public static void main(String[] args) throws IOException {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "MyClass1", null, "java/lang/Object", null);

        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "myMethod", "()V", null, null);
        mv.visitCode();

        // 创建一个标签
        Label myLabel = new Label();
        mv.visitLabel(myLabel);

        // 向方法中添加一些指令
        mv.visitInsn(Opcodes.ICONST_0);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitVarInsn(Opcodes.ISTORE, 2);

        // 在标签处插入一条指令
        mv.visitIincInsn(1, 1);

        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 3);
        mv.visitEnd();

        cw.visitEnd();

        // 保存生成的类文件
        byte[] code = cw.toByteArray();
        FileOutputStream fos = new FileOutputStream("MyClass1.class");
        fos.write(code);
        fos.close();
    }
}

