package org.graph;

import java.util.Arrays;

public class Utils {
    public static void print(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
        System.out.println();
    }
}
