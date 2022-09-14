package com.clay.coding.java.guide.algorithm.暴力搜索算法;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/max-area-of-island/">...</a>
 */
public class LeetCode695 {

    int xLength;

    int yLength;

    int trackNum = 0;

    int maxArea = 0;

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        xLength = grid.length;
        yLength = grid[0].length;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    maxArea = Math.max(maxArea, trackNum);
                    trackNum = 0;
                }
            }
        }
        return maxArea;
    }

    void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= xLength || j >= yLength) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }
        if (grid[i][j] == 1) {
            trackNum++;
            grid[i][j] = 0;
        }
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            dfs(grid, nextI, nextJ);
        }
    }
}
