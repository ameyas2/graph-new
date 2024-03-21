package org.graph;

import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {
        /*ProvinceCount pc = new ProvinceCount();
        System.out.println(pc.provinceCount());
        System.out.println(islands(new int[][]{{0,1,1,0}, {0,1,1,0}, {0,0,1,0}, {0,0,0,0}, {1,1,0,1}}));*/

        System.out.println(rottenOrangesLevelCount(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
        System.out.println(rottenOrangesLevelCount(new int[][]{{0,1,2},{0,1,2},{2,1,1}}));
        System.out.println(rottenOrangesLevelCount(new int[][]{{0,1,2},{0,1,1},{2,1,1}}));
        System.out.println(rottenOrangesLevelCount(new int[][]{{1,2,1},{1,1,0},{0,0,1}}));
    }

    /**
     * Rotten Oranges
     * https://www.youtube.com/watch?v=yf3oUhkvqA0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=10
     *
     * 1. Loop through the matrix
     * 2. Identify the rotten oranges(value 2) and add them to queue
     * 3. Add a null value to mark the levels
     * 4. loop through the queue while its empty
     * 5. Pop elements from the queue
     * 6. If its 2 add the up left down right elements to the queue
     * 7. If its null increment the level count and add another null to the queue
     * 8. Loop through the matrix, check if any value is 1, if yes then return 1
     * */

    public static int rottenOrangesLevelCount(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        Queue<Location> queue = new LinkedList<>();
        int levels = -1;

        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                if(mat[row][col] == 2) {
                    queue.add(new Location(row, col));
                }
            }
        }
        queue.add(null);

        while(!queue.isEmpty()) {
            Location location = queue.poll();

            if(location == null) {
                levels++;
                if(!queue.isEmpty())
                    queue.add(null);
                continue;
            }

            int row = location.row;
            int col = location.col;

            if(row - 1 > 0 && mat[row - 1][col] == 1) { // up
                mat[row - 1][col] = 2;
                queue.add(new Location(row - 1, col));
            }

            if(row + 1 < n && mat[row + 1][col] == 1) { // down
                mat[row + 1][col] = 2;
                queue.add(new Location(row + 1, col));
            }

            if(col - 1 > 0 && mat[row][col - 1] == 1) { // left
                mat[row][col - 1] = 2;
                queue.add(new Location(row, col - 1));
            }

            if(col + 1 < n && mat[row][col  + 1 ] == 1) { // right
                mat[row][col + 1 ] = 2;
                queue.add(new Location(row, col + 1 ));
            }
        }

        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                if(mat[row][col] == 1) {
                    return -1;
                }
            }
        }

        return levels;
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
        Queue<Location> queue = new LinkedList<>();
        Set<Location> visited = new HashSet<>();
        int count = 0;

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {

                if(mat[i][j] == 0 || visited.contains(new Location(i, j)))
                    continue;

                Location start = new Location(i, j);
                queue.add(start);
                visited.add(start);
                while (!queue.isEmpty()) {
                    Location node = queue.poll();
                    for(int row = node.row - 1; row <= node.row + 1; row++) {
                        for(int col = node.col - 1; col <= node.col + 1; col++) {
                            if((row < 0 || row >= mat.length || col < 0 || col >= mat[0].length))
                                continue;
                            if(mat[row][col] == 0)
                                continue;
                            Location mn = new Location(row, col);
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
        Queue<Location> queue = new LinkedList<>();
        int sourceColor = mat[sourceRow][sourceCol];
        queue.add(new Location(sourceRow, sourceColor));
        visited[sourceRow][sourceCol] = 1;
        while (!queue.isEmpty()) {
            Location node = queue.poll();
            if(node.row - 1 >= 0 && mat[node.row - 1][node.col] == sourceCol && visited[node.row - 1][node.col] == 0)
                queue.add(new Location(node.row - 1, node.col));
            if(node.col - 1 >= 0 && mat[node.row][node.col - 1] == sourceCol && visited[node.row][node.col - 1] == 0)
                queue.add(new Location(node.row, node.col - 1));
            if(node.row + 1 < mat.length && mat[node.row + 1][node.col] == sourceCol && visited[node.row + 1][node.col] == 0)
                queue.add(new Location(node.row + 1, node.col));
            if(node.col + 1 < mat[0].length && mat[node.row][node.col + 1] == sourceCol && visited[node.row][node.col + 1] == 0)
                queue.add(new Location(node.row, node.col + 1));
        }
        Utils.print(mat);
    }
}
