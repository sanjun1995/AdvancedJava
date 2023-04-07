package com.example.demo.flow.workflow;

/**
 * @author caozhixin
 * @date 2023/4/7 14:42
 */
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WorkflowEngine {
    private Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.put(task.getName(), task);
    }

    public void run(String workflowFile) throws Exception {
        List<Task> startTasks = new ArrayList<>();
        Map<String, List<Task>> nextTasks = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // 读取工作流定义文件
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(workflowFile));
        doc.getDocumentElement().normalize();

        // 解析任务定义
        NodeList taskNodeList = doc.getElementsByTagName("task");
        for (int i = 0; i < taskNodeList.getLength(); i++) {
            Node taskNode = taskNodeList.item(i);

            if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                Element taskElement = (Element) taskNode;
                String name = taskElement.getAttribute("name");
                String className = taskElement.getAttribute("class");
                String methodName = taskElement.getAttribute("method");

                Task task = new Task(name, className, methodName);
                addTask(task);
                inDegree.put(name, 0);
            }
        }

        // 解析连线定义
        NodeList routeNodeList = doc.getElementsByTagName("route");
        for (int i = 0; i < routeNodeList.getLength(); i++) {
            Node routeNode = routeNodeList.item(i);

            if (routeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element routeElement = (Element) routeNode;
                String from = routeElement.getAttribute("from");
                String to = routeElement.getAttribute("to");

                List<Task> nextTaskList = nextTasks.getOrDefault(from, new ArrayList<>());
                Task task = tasks.get(to);
                nextTaskList.add(task);
                nextTasks.put(from, nextTaskList);

                inDegree.compute(to, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        // 查找入度为0的任务，作为起始任务
        for (String name : inDegree.keySet()) {
            if (inDegree.get(name) == 0) {
                startTasks.add(tasks.get(name));
            }
        }

        // 接下来是执行任务的代码，包括串行和并行任务执行：

        // 串行执行起始任务列表
        for (Task startTask : startTasks) {
            List<Task> taskList = new ArrayList<>();
            taskList.add(startTask);

            while (!taskList.isEmpty()) {
                Task task = taskList.remove(0);
                System.out.println("Executing task: " + task.getName());
                task.execute();

                List<Task> nextTaskList = nextTasks.get(task.getName());
                if (nextTaskList != null) {
                    taskList.addAll(nextTaskList);
                }
            }
        }

        /*
         * 并行执行起始任务列表
         *
         * 这里假设所有入度为0的任务都可以作为起始任务，并且它们在并行执行时是相互独立的。
         */
        for (String name : inDegree.keySet()) {
            if (inDegree.get(name) == 0) {
                Task task = tasks.get(name);
                System.out.println("Starting parallel execution of tasks from: " + task.getName());

                List<Task> taskList = new ArrayList<>();

                List<Task> nextTaskList = nextTasks.getOrDefault(name, new ArrayList<>());
                for (Task nextTask : nextTaskList) {
                    int count = inDegree.compute(nextTask.getName(), (k, v) -> v - 1);
                    if (count == 0) {
                        taskList.add(nextTask);
                    }
                }

                // 执行起始任务
                System.out.println("Executing task: " + task.getName());
                task.execute();

                // 并行执行后续任务
                for (Task t : taskList) {
                    System.out.println("Starting task: " + t.getName());
                    new Thread(() -> {
                        t.execute();
                        List<Task> nextTaskList2 = nextTasks.getOrDefault(t.getName(), new ArrayList<>());
                        for (Task nextTask : nextTaskList2) {
                            int count = inDegree.compute(nextTask.getName(), (k, v) -> v - 1);
                            if (count == 0) {
                                System.out.println("Starting task: " + nextTask.getName());
                                new Thread(() -> {
                                    nextTask.execute();
                                }).start();
                            }
                        }
                    }).start();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        WorkflowEngine engine = new WorkflowEngine();

        // 运行工作流
        engine.run("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/workflow.xml");
    }
}
