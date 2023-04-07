package com.example.demo.flow.workflow;

import lombok.Data;

/**
 * @author caozhixin
 * @date 2023/4/7 15:25
 */
@Data
public class TaskA extends Task {
    private String name = "TaskA";
    private String className = this.getClass().getName();
    private String methodName = "execute";

    public TaskA() {
        super();

    }

    public TaskA(String name, String className, String methodName) {
        super(name, className, methodName);
    }


    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("task A execution");
    }
}