package com.example.demo.b2c.snapshot;

/**
 * @author caozhixin
 * @date 2024/9/18 12:56
 */

public class Product implements Entity<Integer> {
    private Integer id;
    private String status;
    private String lockEvent;

    public Product(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String lockBy() {
        return lockEvent;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLockEvent(String lockEvent) {
        this.lockEvent = lockEvent;
    }
}

