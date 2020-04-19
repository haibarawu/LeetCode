/****************************************************************************************************
64. Minimum Path Sum
64. 最小路径和

Difficulty: Medium

Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。

Example:
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
题目要求，只能向右或向下走，换句话说，只能从左方单元格 (i-1,j) 或上方单元格 (i,j-1) 走到当前单元格 (i,j) ，因此只需要考虑矩阵左边界和上边界。

走到当前单元格 (i,j) 的最小路径和 == “从左方单元格 (i-1,j) 与 从上方单元格 (i,j−1) 走来的<两个最小路径和中较小的>” ++ 当前单元格值 grid[i][j] 。具体分为以下 4 种情况：
1）当左边和上边都不是矩阵边界时：即当 i\=0, j\=0 时，dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j] ；
2）当只有左边是矩阵边界时：只能从上面来，即当i = 0, j \= 0 时，dp[i][j] = dp[i][j - 1] + grid[i][j] ；
3）当只有上边是矩阵边界时：只能从左面来，即当i \= 0, j = 0 时，dp[i][j] = dp[i - 1][j] + grid[i][j] ；
4）当左边和上边都是矩阵边界时：即当i = 0, j = 0 时，其实就是起点，dp[i][j] = grid[i][j] ；

我们完全不需要建立 dp 矩阵浪费额外空间，直接遍历 grid[i][j] 修改即可。这是因为：grid[i][j] = min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j] ；原 grid 矩阵元素中被覆盖为 dp 元素后（都处于当前遍历点的左上方），不会再被使用到。

时间复杂度 O(M×N) ： 遍历整个 grid 矩阵元素。
空间复杂度 O(1) ： 直接修改原矩阵，不使用额外空间。
****************************************************************************************************/


class Solution {
    public int minPathSum(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) 
                    continue;
                else if(i == 0)  
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if(j == 0)  
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                else 
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}


