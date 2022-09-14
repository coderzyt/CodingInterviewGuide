package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.HashSet;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/number-of-distinct-islands/">...</a>
 */
public class LeetCode694 {

    int xLength;

    int yLength;

    public int numDistinctIslands(int[][] grid) {
        xLength = grid.length;
        yLength = grid[0].length;
        HashSet<String> islands = new HashSet<>();
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 666);
                    islands.add(sb.toString());
                }
            }
        }
        return islands.size();
    }

    void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        if (i < 0 || j < 0 || i >= xLength || j >= yLength) {
            return;
        }
        if ( grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        sb.append(dir).append(",");

        dfs(grid, i - 1, j, sb, 1);
        dfs(grid, i + 1, j, sb, 2);
        dfs(grid, i, j - 1, sb, 3);
        dfs(grid, i, j + 1, sb, 4);

        sb.append(-dir).append(",");
    }
}
