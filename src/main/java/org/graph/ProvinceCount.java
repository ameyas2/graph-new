package org.graph;

import java.util.*;

// https://www.youtube.com/watch?v=ACzkVtewUYA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=7&pp=iAQB
public class ProvinceCount {
    public Graph graph() {
        Graph graph = new Graph();
        graph.connect(1, 2);
        graph.connect(1, 3);

        graph.connect(5, 4);
        graph.connect(5, 6);

        graph.connect(7, 8);
        return graph;
    }

    public int provinceCount() {
        return provinceCount(this.graph());
    }
    private int provinceCount(Graph graph) {
        Set<Integer> visited = new HashSet<>();
        int provinceCount = 0;
        for (Map.Entry<Integer, List<Integer>> entry : graph.graph.entrySet()) {
            int node = entry.getKey();
            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> province = new HashSet<>();

            if(visited.contains(node))
                continue;
            queue.add(node);

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                if(visited.contains(vertex))
                    continue;
                visited.add(vertex);
                province.add(vertex);
                List<Integer> vertList = graph.graph.get(vertex);
                for (Integer adjNode : vertList) {
                    queue.add(adjNode);
                }
                updateProvince(province);
                provinceCount++;
            }

        }

        return provinceCount;
    }

    private void updateProvince(Set<Integer> province) {
        System.out.println(province);
        province.clear();
    }
}
