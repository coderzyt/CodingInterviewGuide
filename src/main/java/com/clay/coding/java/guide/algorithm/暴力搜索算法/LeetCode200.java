package com.clay.coding.java.guide.algorithm.暴力搜索算法;

/**
 * @author coderclay
 * <a href='https://leetcode.cn/problems/number-of-islands/'>...</a>
 */
public class LeetCode200 {
    int xLength;

    int yLength;

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands(char[][] grid) {
        int trackNum = 0;
        xLength = grid.length;
        yLength = grid[0].length;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid[i][j] == '1') {
                    trackNum++;
                    dfs(grid, i, j);
                }
            }
        }
        return trackNum;
    }

    void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= xLength || j >= yLength) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            dfs(grid, nextI, nextJ);
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        LeetCode200 leetCode200 = new LeetCode200();
        System.out.println(leetCode200.numIslands(grid));
    }
}
