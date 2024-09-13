package com.example.demo.b2c.es;

import java.util.List;

/**
 * @author caozhixin
 * @date 2024/9/13 14:04
 */
public class Page<R> {
    private List<R> items;
    private int pageNumber;
    private int pageSize;

    public Page(List<R> items, int pageNumber, int pageSize) {
        this.items = items;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public List<R> getItems() {
        return items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "items=" + items +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
