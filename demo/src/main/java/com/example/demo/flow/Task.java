package com.example.demo.flow;

import lombok.Data;

import java.util.UUID;

/**
 * @author caozhixin
 * @date 2023/4/6 21:50
 */
// 任务类，用于保存任务状态和信息等
@Data
public class Task {
    private String id;              // 任务 ID
    private String name;            // 任务名称
    private String description;     // 任务描述
    private boolean completed;      // 任务是否已完成

    public Task(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    // 标记任务为完成状态
    public void complete() {
        this.completed = true;
    }
}