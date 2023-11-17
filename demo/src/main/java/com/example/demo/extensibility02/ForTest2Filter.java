package com.example.demo.extensibility02;

/**
 * @author caozhixin
 * @date 2023/11/17 16:05
 */
public class ForTest2Filter implements Filter {
    @Override
    public void doFilter(HttpRequest httpRequest, FilterChain filterChain) {
        // do

        System.out.println(this.getClass().getSimpleName() + " before " + System.currentTimeMillis());

        filterChain.doFilter(httpRequest);

        // after

        System.out.println(this.getClass().getSimpleName() + " end " + System.currentTimeMillis());
    }
}
