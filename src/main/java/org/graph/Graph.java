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



    // https://www.youtube.com/watch?v=muncqlKJrH0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=8
    /**
     *
     * 1. Loop through the matrix
     * 2. If found 1 add it to the queue
     * 3. Traverse the queue while empty
     * 4. Dequeue the queue element
     * 5. Find unvisited neighbour and add it the queue
     * 6. increment the count when the queue is empty
     *
     */

    public static int islands(int[][] mat) {
        Queue<MatNode> queue = new LinkedList<>();
        Set<MatNode> visited = new HashSet<>();
        int count = 0;

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {

                if(mat[i][j] == 0 || visited.contains(new MatNode(i, j)))
                    continue;

                MatNode start = new MatNode(i, j);
                queue.add(start);
                visited.add(start);
                while (!queue.isEmpty()) {
                    MatNode node = queue.poll();
                    for(int row = node.row - 1; row <= node.row + 1; row++) {
                        for(int col = node.col - 1; col <= node.col + 1; col++) {
                            if((row < 0 || row >= mat.length || col < 0 || col >= mat[0].length))
                                continue;
                            if(mat[row][col] == 0)
                                continue;
                            MatNode mn = new MatNode(row, col);
                            if(!visited.contains(mn)) {
                                queue.add(mn);
                                visited.add(mn);
                            }
                        }
                    }

                }
                count++;
            }
        }

        return count;
    }

    public static void floodFill(int[][] mat, int newColor, int sourceRow, int sourceCol) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] visited = new int[n][m];
        Queue<MatNode> queue = new LinkedList<>();
        int sourceColor = mat[sourceRow][sourceCol];
        queue.add(new MatNode(sourceRow, sourceColor));
        visited[sourceRow][sourceCol] = 1;
        while (!queue.isEmpty()) {
            MatNode node = queue.poll();
            if(node.row - 1 >= 0 && mat[node.row - 1][node.col] == sourceCol && visited[node.row - 1][node.col] == 0)
                queue.add(new MatNode(node.row - 1, node.col));
            if(node.col - 1 >= 0 && mat[node.row][node.col - 1] == sourceCol && visited[node.row][node.col - 1] == 0)
                queue.add(new MatNode(node.row, node.col - 1));
            if(node.row + 1 < mat.length && mat[node.row + 1][node.col] == sourceCol && visited[node.row + 1][node.col] == 0)
                queue.add(new MatNode(node.row + 1, node.col));
            if(node.col + 1 < mat[0].length && mat[node.row][node.col + 1] == sourceCol && visited[node.row][node.col + 1] == 0)
                queue.add(new MatNode(node.row, node.col + 1));
        }
        print(mat);
    }

    public static void print(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            Arrays.toString(mat[i]);
        }
    }



    public static void main(String[] args) {
        //simpleGraph();
        ProvinceCount pc = new ProvinceCount();
        System.out.println(pc.provinceCount());
        System.out.println(islands(new int[][]{{0,1,1,0}, {0,1,1,0}, {0,0,1,0}, {0,0,0,0}, {1,1,0,1}}));
    }
}

class MatNode {
    int row;
    int col;

    public MatNode(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatNode matNode = (MatNode) o;
        return row == matNode.row && col == matNode.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
