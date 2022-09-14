package com.clay.coding.java.guide.algorithm.暴力搜索算法;

/**
 * @author coderclay
 */
public class MatrixTraverse {

    // 二叉树遍历框架
    void traverse(TreeNode root) {
        traverse(root.left);
        traverse(root.right);
    }

    // 二维矩阵遍历框架
    void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // 超出索引边界
            return;
        }
        if (visited[i][j]) {
            // 已经遍历过
            return;
        }
        // 进入节点（i，j）
        visited[i][j] = true;
        // 向上
        dfs(grid, i - 1, j, visited);
        // 向下
        dfs(grid, i + 1, j, visited);
        // 向左
        dfs(grid, i, j - 1, visited);
        // 向右
        dfs(grid, i, j + 1, visited);
    }

    // 方向数组，分别代表上，下，左，右
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    void dfs1(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        for (int[] d : dirs) {
            int next_i = i + d[0];
            int next_j = j + d[1];
            dfs1(grid, next_i, next_j, visited);
        }
    }
}
