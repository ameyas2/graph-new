package org.graph;

import java.util.*;

public class Cycle {
    public static void main(String[] args) {
        graph();
        //bfsCycle(1);
        dfsCycle(1);
    }

    /**
     * BFS Detect cycle
     * https://www.youtube.com/watch?v=BPlrALf1LDU
     * 1. Add the start node to the queue
     * 2. Loop till the queue is empty
     * 3. Pop node from qu eue and add its neighbouring nodes to the queue and store it in parent map
     * 4. If the neighbouring node is visited and it's not a parent then it's a cycle
     * */
    public static boolean bfsCycle(int start) {
        Graph graph = graph();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        parent.put(start, null);
        queue.add(start);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if(visited.contains(node))
                continue;
            visited.add(node);
            List<Integer> adjNodes = graph.get(node);
            for (Integer adjNode : adjNodes) {
                if(visited.contains(adjNode)) {
                    if(!parent.get(node).equals(adjNode)) {
                        System.out.println("Cycle Detected " + node + " " + adjNode);
                        return true;
                    }
                    continue;
                }
                queue.add(adjNode);
                parent.put(adjNode, node);
            }

        }
        return false;
    }

    /**
     * DFS Detect cycle
     * https://www.youtube.com/watch?v=BPlrALf1LDU
     * 1. Add the start node to the stack
     * 2. Loop till the stack is empty
     * 3. Pop node from stack and add its neighbouring nodes to the queue and store it in parent map
     * 4. If the neighbouring node is visited and it's not a parent then it's a cycle
     * */
    public static boolean dfsCycle(int start) {
        Graph graph = graph();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        parent.put(start, null);
        stack.add(start);

        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            if(visited.contains(node))
                continue;
            visited.add(node);
            List<Integer> adjNodes = graph.get(node);
            for (Integer adjNode : adjNodes) {
                if(visited.contains(adjNode)) {
                    if(!parent.get(node).equals(adjNode)) {
                        System.out.println("Cycle Detected " + node + " " + adjNode);
                        return true;
                    }
                    continue;
                }
                stack.add(adjNode);
                parent.put(adjNode, node);
            }

        }
        return false;
    }

    public static Graph graph() {
        Graph graph = new Graph();
        graph.connect(1, 2);
        graph.connect(1, 3);
        graph.connect(5, 2);
        graph.connect(3, 6);
        graph.connect(3, 4);
        graph.connect(7, 5);
        graph.connect(7, 6);
        //graph.traverse();
        return graph;
    }
}
