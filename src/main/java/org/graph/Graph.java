package org.graph;

import java.util.*;

public class Graph {
    Map<Integer, List<Integer>> graph;
    boolean bidirectional;

    public Graph() {
        this.graph = new HashMap<>();
        this.bidirectional = true;
    }

    public List<Integer> get(int key) {
        return this.graph.get(key);
    }

    public Graph(boolean bidirectional) {
        this();
        this.bidirectional = bidirectional;
    }

    public void connect(int src, int dest) {
        this.addEdge(src, dest);
        if(this.bidirectional)
            this.addEdge(dest, src);
    }

    /**
    *
    * 1. Get the adjacency list of the source
    * 2. Add the destination source to that list
    * 3. store the list back in the graph
    *
    * */
    private void addEdge(int src, int dest) {
        List<Integer> adjList = graph.containsKey(src) ? graph.get(src) : new LinkedList<>();
        adjList.add(dest);
        graph.put(src, adjList);
    }

    /**
     *
     * 1. Loop through each node in the map
     * 2. Loop through all the edges connected to that node
     * 
     */
    public void traverse() {
        graph.forEach((node, edges) -> {
            System.out.print(node + " : ");
            edges.forEach(edge -> System.out.print(edge + " -> "));
            System.out.println();
        });
    }

    // https://www.youtube.com/watch?v=-tgVpUgsQ5k&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=5&pp=iAQB
    /**
     * Breadth first search traversal
     * @param start
     *
     * 1. Create a queue and add the start node to it
     * 2. Create a set of visited nodes
     * 3. Start a loop while queue is not empty
     * 4. Pop node from queue and visit all of the neighbouring nodes
     * 5. If the node is visited continue, else add it to the queue.
     */
    public void bfs(int start) {
        System.out.println("BFS");
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int node = queue.poll();
            if(visited.contains(node))
                continue;
            visited.add(node);
            System.out.println("visiting " + node);
            List<Integer> adjList = graph.get(node);
            for (Integer adjNode : adjList) {
                queue.add(adjNode);
            }
        }
    }

    // https://www.youtube.com/watch?v=Qzf1a--rhp8&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=6&pp=iAQB
    /**
     * Depth first search traversal
     * @param start
     *
     * 1. Create a stack and add the start node to it
     * 2. Create a set of visited nodes
     * 3. Start a loop while stack is not empty
     * 4. Pop node from stack and visit all of the neighbouring nodes
     * 5. If the node is visited continue, else add it to the stack.
     */
    public void dfs(int start) {
        System.out.println("DFS");
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.add(start);
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(visited.contains(node))
                continue;
            visited.add(node);
            System.out.println("visiting " + node);
            List<Integer> adjList = graph.get(node);
            for (Integer adjNode : adjList) {
                stack.add(adjNode);
            }
        }
    }

    public static void simpleGraph() {
        Graph graph = new Graph();
        graph.connect(1, 2);
        graph.connect(1, 3);
        graph.connect(2, 4);
        graph.connect(5, 2);
        graph.connect(5, 4);
        graph.connect(3, 4);
        graph.traverse();
        graph.bfs(1);
        graph.dfs(1);
    }

    public static void main(String[] args) {
        //simpleGraph();
    }
}
