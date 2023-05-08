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
            ClassVisitor cv = new ClassVisitor(Opcodes.ASM8, cw) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                    return new MethodVisitor(Opcodes.ASM8, mv) {
                        private Label startLabel = new Label();
                        private Label endLabel = new Label();

                        @Override
                        public void visitCode() {
                            super.visitCode();
                            Label tryStart = new Label();
                            Label tryEnd = new Label();
                            Label catchHandler = new Label();
                            mv.visitTryCatchBlock(tryStart, tryEnd, catchHandler, "java/lang/Throwable");
                            mv.visitLabel(tryStart);
                        }

                        @Override
                        public void visitMaxs(int maxStack, int maxLocals) {
                            mv.visitLabel(endLabel);
                            mv.visitInsn(RETURN);

                            Label catchHandler = new Label();
                            mv.visitLabel(catchHandler);
                            mv.visitVarInsn(ASTORE, 1);
                            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            mv.visitLdcInsn("Exception caught:");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                            mv.visitVarInsn(ALOAD, 1);
                            mv.visitMethodInsn(INVOKESTATIC, OWNER, "logStackTrace", "(Ljava/lang/Throwable;)V", false);
                            mv.visitInsn(ATHROW); // throw the caught exception

                            mv.visitTryCatchBlock(startLabel, endLabel, catchHandler, "java/lang/Throwable");
                            super.visitMaxs(maxStack, maxLocals);
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
        throwable.printStackTrace(System.out);
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


