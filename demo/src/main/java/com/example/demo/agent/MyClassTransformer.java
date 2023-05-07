package com.example.demo.agent;

import aj.org.objectweb.asm.Opcodes;
import org.objectweb.asm.*;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;

import java.security.ProtectionDomain;
import java.util.Set;

import static org.objectweb.asm.Opcodes.*;

public class MyClassTransformer implements ClassFileTransformer {
    private final Set<String> basePackages;
    private static final String OWNER = MyClassTransformer.class.getCanonicalName().replace(".", File.separator);

    public MyClassTransformer(Set<String> basePackages) {
        this.basePackages = basePackages;
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (!needEnhance(className)) {
            return classfileBuffer;
        }

        System.out.println("Transforming class: " + className);

        // asm进行字节码增强
        try {
            ClassReader cr = new ClassReader(className);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new ClassVisitor(Opcodes.ASM7, cw) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
                            super.visitTryCatchBlock(start, end, handler, type);
                            // 替换原来的异常处理代码
                            mv.visitLabel(handler);
                            mv.visitLdcInsn("Exception caught:");
                            mv.visitMethodInsn(INVOKESTATIC, OWNER, "logStackTrace", "(Ljava/lang/Throwable;)V", false);
                        }
                    };
                }
            };
            cr.accept(cv, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        } catch (Exception e) {
            System.out.println("MyClassTransformer e=" + e);
        }
        return classfileBuffer;
    }

    // 定义方法
    public static void logStackTrace(Throwable throwable) {
        System.out.println(throwable);
    }

    private boolean needEnhance(String className) {
        for (String basePackage : basePackages) {
            if (className.startsWith(basePackage)) {
                return true;
            }
        }
        return false;
    }
}


