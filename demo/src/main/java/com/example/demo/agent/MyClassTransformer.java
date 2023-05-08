package com.example.demo.agent;

import aj.org.objectweb.asm.Opcodes;
import org.objectweb.asm.*;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;

import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

import static org.objectweb.asm.Opcodes.*;

public class MyClassTransformer implements ClassFileTransformer {
    // basePackages是需要增强的类所在的包的集合
    private final Set<String> basePackages;
    // 获取该ClassTransformer类的全限定名，将包名中的点号替换为文件路径中的分隔符
    private static final String OWNER = MyClassTransformer.class.getCanonicalName().replace(".", File.separator);

    public MyClassTransformer(Set<String> basePackages) {
        this.basePackages = basePackages;
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // 判断是否需要对该类进行增强，如果不需要直接返回原字节码数据
        if (!needEnhance(className)) {
            return classfileBuffer;
        }

        System.out.println("Transforming class: " + className);

        // 利用ASM对字节码进行增强
        try {
            // 创建ClassReader对象
            ClassReader cr = new ClassReader(className);
            // 创建ClassWriter对象
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            // 创建ClassVisitor对象，对字节码进行访问
            ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    // 对每个方法进行访问，返回MethodVisitor对象进行访问
                    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                    // 创建MethodVisitor对象，对方法字节码进行访问
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        // 存储try-catch处理器的标签
                        private final Set<Label> tryCatchBlockHandlers = new HashSet<>();

                        @Override
                        public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
                            // 对visitTryCatchBlock方法进行访问，在访问方法中存储try-catch处理器的标签
                            tryCatchBlockHandlers.add(handler);
                            super.visitTryCatchBlock(start, end, handler, type);
                        }

                        @Override
                        public void visitLineNumber(int line, Label start) {
                            // 对visitLineNumber方法进行访问，在访问方法中插入方法调用指令
                            if (tryCatchBlockHandlers.contains(start)) {
                                // 当该行代码处于try-catch块中时，在该行代码前插入方法调用指令
                                mv.visitMethodInsn(INVOKESTATIC, OWNER, "logStackTrace", "(Ljava/lang/Throwable;)V", false);
                            }
                            super.visitLineNumber(line, start);
                        }
                    };
                }
            };
            // 开始访问ClassReader中的字节码
            cr.accept(cv, ClassReader.EXPAND_FRAMES);

            // 返回增强后的字节码数据
            return cw.toByteArray();
        } catch (Exception e) {
            System.out.println("MyClassTransformer e=" + e);
        }
        // 出现异常时返回原字节码数据
        return classfileBuffer;
    }

    // 定义方法
    public static void logStackTrace(Throwable throwable) {
        System.out.println("统一打印堆栈：");
        throwable.printStackTrace();
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


