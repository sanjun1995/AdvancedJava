package com.example.demo.b2c.validator;

/**
 * @author caozhixin
 * @date 2024/9/13 13:15
 */
public class SimpleQueryDTO {
    private Integer pageNum;
    private Integer pageSize;

    // Getters and Setters
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
