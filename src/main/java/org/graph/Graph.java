package org.graph;

import java.util.*;

public class Graph {
    Map<Integer, List<Integer>> graph;
    boolean bidirectional;

    public Graph() {
        this.graph = new HashMap<>();
        this.bidirectional = true;
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

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.connect(1, 2);
        graph.connect(1, 3);
        graph.connect(2, 4);
        graph.connect(5, 2);
        graph.connect(5, 4);
        graph.connect(3, 4);
        graph.traverse();
    }
}
