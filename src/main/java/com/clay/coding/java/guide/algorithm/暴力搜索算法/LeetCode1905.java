package com.clay.coding.java.guide.algorithm.暴力搜索算法;

/**
 * @author coderclay
 */
public class LeetCode1905 {

    int xLength;

    int yLength;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        xLength = grid2.length;
        yLength = grid2[0].length;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    dfs(grid2, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid2[i][j] == 1) {
                    count++;
                    dfs(grid2, i, j);
                }
            }
        }
        return count;
    }

    void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= xLength || j >= yLength) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            dfs(grid, nextI, nextJ);
        }
    }
}
