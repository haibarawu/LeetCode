/****************************************************************************************************
200. Number of Islands
200. 岛屿数量

Difficulty: Medium

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
你可以假设网格的四个边均被水包围。

Example 1:
Input:
11110
11010
11000
00000
Output: 1

Example 2:
Input:
11000
11000
00100
00011
Output: 3
****************************************************************************************************/


/****************************************************************************************************
解题思路：
深度优先遍历DFS
设目前指针指向一个岛屿中的某一点 (i, j)，寻找包括此点的岛屿边界。
从 (i, j) 向此点的上下左右 (i-1,j),(i+1,j),(i,j-1),(i,j+1) 做深度搜索。

终止条件：
(i, j) 越过矩阵边界;
grid[i][j] == 0，代表此分支已越过岛屿边界。

搜索岛屿的同时，执行 grid[i][j] = '0'，即将岛屿所有节点删除，以免之后重复搜索相同岛屿。

主循环：
遍历整个矩阵，当遇到 grid[i][j] == '1' 时，从此点开始做深度优先搜索 dfs，岛屿数 count + 1 且在深度优先搜索中删除此岛屿。
最终返回岛屿数 count 即可。

时间复杂度 : O(M×N)，其中 M 和 N 分别为行数和列数。
空间复杂度 : 最坏情况下为 O(M×N)，此时整个网格均为陆地，深度优先搜索的深度达到 M×N。
****************************************************************************************************/


class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}


