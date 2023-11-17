package com.example.demo.extensibility02;

/**
 * @author caozhixin
 * @date 2023/11/17 16:08
 */
public class FilterClient {
    public static void main(String[] args) {
        FilterChain filterChain = new StandardFilterChain();

        filterChain.addFilter(new ForTest1Filter());
        filterChain.addFilter(new ForTest2Filter());

        filterChain.doFilter(new StandardHttpRequest());
    }
}
