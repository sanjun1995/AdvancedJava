package com.example.demo.b2c.cyclechain;

import java.util.*;

/**
 * @author caozhixin
 * @date 2024/9/24 21:02
 */
// 循环检测主类
public class CycleDetection {

    public static void main(String[] args) {
        // 创建节点
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // 添加链，形成循环
        node1.addNodeChain(new NodeChain(node2));
        node2.addNodeChain(new NodeChain(node3));
        node3.addNodeChain(new NodeChain(node4));
        node4.addNodeChain(new NodeChain(node2));

        // 创建节点
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node5.addNodeChain(new NodeChain(node6));
        node6.addNodeChain(new NodeChain(node7));

        // 查找循环链
        List<List<Integer>> cyclicChains = findCyclicChains(node1);
        System.out.println("Cyclic chains found: " + cyclicChains);
    }

    // 查找给定节点的循环链
    protected static List<List<Integer>> findCyclicChains(Node node) {
        List<List<Integer>> cyclicChains = new ArrayList<>(); // 存储找到的循环链
        Set<Integer> visited = new HashSet<>(); // 用于跟踪已访问的节点
        findCyclicChainsHelper(node, visited, new LinkedList<>(), cyclicChains); // 调用递归助手方法
        return cyclicChains; // 返回所有找到的循环链
    }

    // 递归助手方法，查找循环链
    private static void findCyclicChainsHelper(Node node, Set<Integer> visited, LinkedList<Integer> path, List<List<Integer>> cyclicChains) {
        // 如果当前节点已经被访问过，说明找到了一个循环
        if (visited.contains(node.getId())) {
            List<Integer> cycle = new ArrayList<>(path); // 复制当前路径
            cycle.add(node.getId()); // 将当前节点添加到循环中
            cyclicChains.add(cycle); // 将找到的循环添加到结果列表中
            return; // 返回
        }

        // 标记当前节点为已访问
        visited.add(node.getId());
        path.add(node.getId()); // 将当前节点添加到路径中

        // 遍历当前节点的所有链
        for (NodeChain chain : node.getNodeChains()) {
            // 递归调用助手方法，查找下一个节点的循环链
            findCyclicChainsHelper(chain.getNextNode(), visited, path, cyclicChains);
        }

        // 回溯：从路径中移除当前节点
        path.removeLast();
        visited.remove(node.getId()); // 将当前节点标记为未访问
    }
}

// 表示一个有限状态机节点
class Node {
    private final int id; // 节点的映射ID
    private final List<NodeChain> nodeChains; // 当前节点的链列表

    public Node(int id) {
        this.id = id;
        this.nodeChains = new ArrayList<>(); // 初始化链列表
    }

    public int getId() {
        return id; // 获取节点的映射ID
    }

    public void addNodeChain(NodeChain chain) {
        nodeChains.add(chain); // 添加链到当前节点
    }

    public List<NodeChain> getNodeChains() {
        return nodeChains; // 获取当前节点的链列表
    }
}

// 表示连接两个节点的链
class NodeChain {
    private final Node nextNode; // 链接到的下一个节点

    public NodeChain(Node nextNode) {
        this.nextNode = nextNode; // 初始化下一个节点
    }

    public Node getNextNode() {
        return nextNode; // 获取下一个节点
    }
}