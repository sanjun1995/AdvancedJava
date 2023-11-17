package com.example.demo.extensibility02;

/**
 * @author caozhixin
 * @date 2023/11/17 16:04
 */
public interface Filter {

    void doFilter(HttpRequest httpRequest, FilterChain filterChain);
}
