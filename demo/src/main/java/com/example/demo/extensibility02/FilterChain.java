package com.example.demo.extensibility02;

/**
 * @author caozhixin
 * @date 2023/11/17 16:05
 */
public interface FilterChain {
    void doFilter(HttpRequest httpRequest);

    void addFilter(Filter filter);
}
