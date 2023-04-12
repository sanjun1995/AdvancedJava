package com.example.demo.flow.workflow;

import lombok.Data;

/**
 * @author caozhixin
 * @date 2023/4/7 15:25
 */
@Data
public class TaskC {
    private String name = "TaskC";
    private String className = this.getClass().getName();
    private String methodName = "execute";

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("task C execution");
    }
}