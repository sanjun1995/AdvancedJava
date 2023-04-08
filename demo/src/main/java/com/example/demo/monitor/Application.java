package com.example.demo.monitor;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new MetricsServlet(), "/prometheus");
    }

    // Export JVM metrics
    @Bean
    public DefaultExports defaultExports() {
        return new DefaultExports();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}