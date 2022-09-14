package com.clay.coding.java.guide.algorithm.暴力搜索算法;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/number-of-closed-islands/">...</a>
 */
public class LeetCode1254 {

    int xLength;

    int yLength;

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int closedIsland(int[][] grid) {
        xLength = grid.length;
        yLength = grid[0].length;
        int trackNum = 0;
        for (int i = 0; i < xLength; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, yLength - 1);
        }
        for (int j = 0; j < yLength; j++) {
            dfs(grid,0, j);
            dfs(grid, xLength - 1, j);
        }
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid[i][j] == 0) {
                    trackNum++;
                    dfs(grid, i, j);
                }
            }
        }
        return trackNum;
    }

    void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= xLength || j >= yLength) {
            return;
        }
        if (grid[i][j] == 1) {
            return;
        }
        grid[i][j] = 1;
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            dfs(grid, nextI, nextJ);
        }
    }
}
