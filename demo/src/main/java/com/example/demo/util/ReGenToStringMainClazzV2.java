package com.example.demo.util;

import org.apache.commons.io.FileUtils;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.google.common.collect.Sets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 给定目录重写SCF实体类toString方法过滤敏感词
 *
 * @Date CreateDate:2023年8月4日
 */
public class ReGenToStringMainClazzV2 {

    /**
     * 敏感词关键字
     */
    private static final Set<String> keyWords = Sets.newHashSet("mobile", "bank", "addr", "card", "phone");

    /**
     * 自动覆盖已存在的toString方法，true直接覆盖，false保留原始再生成一个（生成的代码会在IDE里编译不通过报错）
     */
    private static boolean autoOverwriteToString = true;

    public static void main(String[] args) throws IOException {

        // 要处理的目录
        // String pathname = "D:\\source-code\\promo\\project\\contract";

        // String pathname = args[0];
        String pathname = "/Users/caozhixin/IdeaProjects/project/contract";
        System.out.println("开始！！");
        System.out.println("处理目录：" + pathname);
        dirEx(pathname);
    }

    /**
     * 要处理的目录
     *
     * @param pathname
     * @throws FileNotFoundException
     */
    private static void dirEx(String pathname) throws FileNotFoundException {

        File file = new File(pathname);
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String string : list) {
                String pathname2 = pathname + "/" + string;
                System.out.println("目录：" + pathname2);
                dirEx(pathname2);
            }

        } else {
            if (pathname.endsWith(".java")) {
                parseJava(file);
            }
        }
    }

    /**
     * 处理Java文件
     * @param file
     * @throws FileNotFoundException
     */
    private static void parseJava(File file) throws FileNotFoundException {
        CompilationUnit parse2 = StaticJavaParser.parse(file);
        Optional<PackageDeclaration> packageDeclaration = parse2.getPackageDeclaration();
        if (!packageDeclaration.isPresent()) {
            System.out.println("空代码Java文件：" + file.getAbsolutePath());
            return;
        }
        String packageName = packageDeclaration.get().getNameAsString();

        List<ClassOrInterfaceDeclaration> findAll3 = parse2.findAll(ClassOrInterfaceDeclaration.class);
        for (ClassOrInterfaceDeclaration bodyDeclaration : findAll3) {
            String clazzName = bodyDeclaration.getNameAsString();
            String fullName = packageName + "." + clazzName;
            // 这里过滤掉了非协议包的Java类
            if (bodyDeclaration.getAnnotationByName("Serializable").isPresent()) {
                System.out.println("实体类：" + fullName);

                String buildToString = buildToString(parse2);
                try {
                    // 重写覆盖源文件
                    FileUtils.writeStringToFile(new File(file.getAbsolutePath()), buildToString, Charset.forName("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * @return
     * @throws FileNotFoundException
     */
    public static String buildToString(CompilationUnit parse) throws FileNotFoundException {
        // 导入架构部脱敏工具类
        List<ClassOrInterfaceDeclaration> findAll2 = parse.findAll(ClassOrInterfaceDeclaration.class);
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = findAll2.get(0);

        List<MethodDeclaration> methodsByName = classOrInterfaceDeclaration.getMethodsByName("toString");
        if (methodsByName != null && methodsByName.size() > 0) {
            System.err.println("已经存在toString方法" + classOrInterfaceDeclaration.getNameAsString());
            if (autoOverwriteToString) {
                for (MethodDeclaration methodDeclaration : methodsByName) {
                    String string = methodDeclaration.toString();
                    if (string.contains("#superToString#")) {
                        classOrInterfaceDeclaration.remove(methodDeclaration);
                    }
                }
            }
        }

        List<FieldDeclaration> ffs = parse.findAll(FieldDeclaration.class);
        boolean has = false;
        for (FieldDeclaration fieldDeclaration : ffs) {
            NodeList<VariableDeclarator> variables = fieldDeclaration.getVariables();
            boolean isDesensitizeFeild = false;
            for (VariableDeclarator x : variables) {
                for (String keyWrod : keyWords) {
                    if (x.getNameAsString().toLowerCase().contains(keyWrod)) {
                        // 该字段是否需要脱敏
                        isDesensitizeFeild = true;
                        has = true;
                        break;
                    }
                }

            }
            if (isDesensitizeFeild) {
                Optional<AnnotationExpr> annotationByName = fieldDeclaration.getAnnotationByName("Desensitize");
                if (!annotationByName.isPresent()) {
                    fieldDeclaration.addAnnotation("Desensitize");
                }
            }
        }
        if (has) {
            parse.addImport("com.common.desensitize.Desensitize");
        }
        return parse.toString();
    }

}
