package com.example.demo.b2c.cyclechain;

import java.util.*;

/**
 * @author caozhixin
 * @date 2024/9/24 21:02
 */
public class CycleDetection {

    static class GenericFsmNode {
        private final int mappingId;
        private final List<GenericFsmNodeChain> nodeChains;

        public GenericFsmNode(int mappingId) {
            this.mappingId = mappingId;
            this.nodeChains = new ArrayList<>();
        }

        public int getMappingId() {
            return mappingId;
        }

        public void addNodeChain(GenericFsmNodeChain chain) {
            nodeChains.add(chain);
        }

        public List<GenericFsmNodeChain> getNodeChains() {
            return nodeChains;
        }
    }

    static class GenericFsmNodeChain {
        private final GenericFsmNode nextNode;

        public GenericFsmNodeChain(GenericFsmNode nextNode) {
            this.nextNode = nextNode;
        }

        public GenericFsmNode getNextNode() {
            return nextNode;
        }
    }

    public class Graph {
        public List<List<Integer>> findCyclicChains(GenericFsmNode startNode) {
            List<List<Integer>> cyclicChains = new LinkedList<>();
            if (startNode == null || startNode.getNodeChains().isEmpty()) {
                return cyclicChains;
            }

            Stack<GenericFsmNode> nodeStack = new Stack<>();
            Stack<Set<GenericFsmNodeChain>> visitedChainsStack = new Stack<>();
            Set<Integer> visitedMappingIds = new HashSet<>();

            nodeStack.push(startNode);
            visitedChainsStack.push(new HashSet<>());

            while (!nodeStack.isEmpty()) {
                GenericFsmNode currentNode = nodeStack.peek();
                Set<GenericFsmNodeChain> visitedChains = visitedChainsStack.peek();

                if (visitedMappingIds.contains(currentNode.getMappingId())) {
                    // 找到循环
                    List<Integer> cyclicChain = new ArrayList<>(visitedMappingIds);
                    cyclicChain.add(currentNode.getMappingId());
                    cyclicChains.add(cyclicChain);
                    nodeStack.pop();
                    visitedChainsStack.pop();
                    continue;
                }

                visitedMappingIds.add(currentNode.getMappingId());

                boolean foundNext = false;
                for (GenericFsmNodeChain chain : currentNode.getNodeChains()) {
                    if (!visitedChains.add(chain)) {
                        continue;
                    }
                    nodeStack.push(chain.getNextNode());
                    visitedChainsStack.push(new HashSet<>());
                    foundNext = true;
                    break;
                }

                if (!foundNext) {
                    nodeStack.pop();
                    visitedChainsStack.pop();
                }
            }

            return cyclicChains;
        }
    }

    public static void main(String[] args) {
        CycleDetection cycleDetection = new CycleDetection();
        Graph graph = cycleDetection.new Graph();

        GenericFsmNode node1 = new GenericFsmNode(1);
        GenericFsmNode node2 = new GenericFsmNode(2);
        GenericFsmNode node3 = new GenericFsmNode(3);

        // 创建循环
        node1.addNodeChain(new GenericFsmNodeChain(node2));
        node2.addNodeChain(new GenericFsmNodeChain(node3));
        node3.addNodeChain(new GenericFsmNodeChain(node1)); // 创建循环

        // 查找循环
        List<List<Integer>> cycles = graph.findCyclicChains(node1);
        if (!cycles.isEmpty()) {
            System.out.println("Cyclic chains found: " + cycles);
        } else {
            System.out.println("No cyclic chains found.");
        }
    }
}