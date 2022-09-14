package com.clay.coding.java.guide.algorithm.暴力搜索算法;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/ZL6zAn/">...</a>
 */
public class LeetCodeOffer2_105 {

    int xLength, yLength;
    int maxArea = 0;
    public int maxAreaOfIsland(int[][] grid) {
        xLength = grid.length;
        yLength = grid[0].length;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= xLength || j >= yLength) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return dfs(grid, i - 1, j) +
                dfs(grid, i + 1, j) +
                dfs(grid, i, j - 1) +
                dfs(grid, i, j + 1) + 1;
    }
}
