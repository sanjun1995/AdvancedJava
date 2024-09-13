package com.example.demo.b2c.es;

/**
 * @author caozhixin
 * @date 2024/9/13 14:05
 */
public abstract class AbstractEsCondition {
    private int pageSize;
    private int pageNumber;

    public AbstractEsCondition(int pageSize, int pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
