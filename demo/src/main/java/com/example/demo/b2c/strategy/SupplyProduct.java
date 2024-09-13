package com.example.demo.b2c.strategy;

/**
 * @author caozhixin
 * @date 2024/9/13 09:35
 */
public class SupplyProduct {
    private String name;
    private QcLevel qcLevel;

    public SupplyProduct(String name, QcLevel qcLevel) {
        this.name = name;
        this.qcLevel = qcLevel;
    }

    public String getName() {
        return name;
    }

    public QcLevel getQcLevel() {
        return qcLevel;
    }
}

