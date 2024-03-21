package org.graph;

import java.util.LinkedList;
import java.util.Queue;

public class NearestCellDistance {
    public static void main(String[] args) {
        nearestCellDistance(new int[][]{{0,0,0}, {0,1,0}, {1,0,1}});
    }

    /**
     * <a href="https://www.youtube.com/watch?v=edXdVwkYHF8">Nearest Cell Distance</a>
     *  1. Traverse the matrix and add  cell containing 1 to the queue with distance of 0
     *  2. Loop the queue while empty
     *  3. Pop the element and add the unvisited neighbours to the queue
     *  4. Add the node to the visited matrix
     *  5. Update the resultant matrix
     * */
    public static void nearestCellDistance(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] visited = new int[n][m];
        int[][] result = new int[n][m];
        Queue<LocationValue> queue = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(mat[i][j] == 1) {
                    queue.add(new LocationValue(i, j, 0));
                    visited[i][j] = 1;
                }
            }
        }

        while (!queue.isEmpty()) {
            LocationValue value = queue.poll();
            int row = value.row;
            int col = value.col;
            result[row][col] = value.value;

            if(row - 1 >= 0 && visited[row - 1][col] != 1) {
                visited[row - 1][col] = 1;
                queue.add(new LocationValue(row - 1, col, value.value + 1));
            }

            if(row + 1 < n && visited[row + 1][col] != 1) {
                visited[row + 1][col] = 1;
                queue.add(new LocationValue(row + 1, col, value.value + 1));
            }

            if(col + 1 < m && visited[row][col + 1] != 1) {
                visited[row][col + 1] = 1;
                queue.add(new LocationValue(row, col + 1, value.value + 1));
            }

            if(col - 1 >= 0 && visited[row][col - 1] != 1) {
                visited[row][col - 1] = 1;
                queue.add(new LocationValue(row, col - 1, value.value + 1));
            }
        }
        Utils.print(mat);
        Utils.print(result);
    }
}
