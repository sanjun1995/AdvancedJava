package com.example.demo.extensibility02;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author caozhixin
 * @date 2023/11/17 16:06
 */
public class StandardFilterChain implements FilterChain {

    private List<Filter> filterList = Lists.newArrayList();

    private int currentIndex = 0;

    @Override
    public void doFilter(HttpRequest httpRequest) {
        if (currentIndex == filterList.size()) { return; }

        Filter filter = filterList.get(currentIndex);

        currentIndex = currentIndex + 1;

        filter.doFilter(httpRequest, this);
    }

    @Override
    public void addFilter(Filter filter) {
        if (filterList.contains(filter)) {
            return;
        }

        filterList.add(filter);
    }

}
