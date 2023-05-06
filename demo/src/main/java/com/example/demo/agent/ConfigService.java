package com.example.demo.agent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ConfigService {
    private static final String CONFIG_FILE_PATH = "demo/src/main/resources/application.properties";
    private static final String BASE_PACKAGES_PROPERTY = "basePackages";
    private static Set<String> basePackages;

    static {
        Properties props = new Properties();
        try (InputStream is = new FileInputStream(CONFIG_FILE_PATH)) {
            props.load(is);
            String basePackagesStr = props.getProperty(BASE_PACKAGES_PROPERTY);
            basePackages = new HashSet<>(Arrays.asList(basePackagesStr.split(",")));
            System.out.println("basePackages=" + basePackages);
        } catch (IOException e) {
            // 处理异常
        }
    }

    public static Set<String> getBasePackages() {
        return basePackages;
    }
}

