/****************************************************************************************************
1252. Cells with Odd Values in a Matrix

Difficulty: Easy

https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
https://leetcode-cn.com/problems/cells-with-odd-values-in-a-matrix/

Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where indices[i] = [ri, ci]. 
For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
Return the number of cells with odd values in the matrix after applying the increment to all indices.

给你一个 n 行 m 列的矩阵，最开始的时候，每个单元格中的值都是 0。
另有一个索引数组 indices，indices[i] = [ri, ci] 中的 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
你需要将每对 [ri, ci] 指定的行和列上的所有单元格的值加 1。
请你在执行完所有 indices 指定的增量操作后，返回矩阵中 「奇数值单元格」 的数目。

Example 1:
0,0,0 -> 1,2,1 -> 1,3,1
0,0,0    0,1,0    1,3,1
Input: n = 2, m = 3, indices = [[0,1],[1,1]]
Output: 6
Explanation: Initial matrix = [[0,0,0],[0,0,0]].
After applying first increment it becomes [[1,2,1],[0,1,0]].
The final matrix will be [[1,3,1],[1,3,1]] which contains 6 odd numbers.

Example 2:
0,0 -> 0,1 -> 2,2
0,0    1,2    2,2
Input: n = 2, m = 2, indices = [[1,1],[0,0]]
Output: 0
Explanation: Final matrix = [[2,2],[2,2]]. There is no odd number in the final matrix.

Constraints:
1 <= n <= 50
1 <= m <= 50
1 <= indices.length <= 100
0 <= indices[i][0] < n
0 <= indices[i][1] < m
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）indices[0] = [row,column]，即对matrix中第row行的所有元素＋1，第column列的元素＋1。
2）再执行下一个indices。
3）完成增值后，循环找出矩阵matrix中奇数的总个数。
****************************************************************************************************/


class Solution {
    public int oddCells(int n, int m, int[][] indices) {
        int result = 0;
        int[][] matrix = new int[n][m];
        
        for(int i = 0; i < indices.length; i++) {
            int row = indices[i][0];
            int col = indices[i][1];
            
            for(int r = 0; r < n; r++) {
                matrix[r][col]++;
            }
            
            for(int c = 0; c < m; c++) {
                matrix[row][c]++;
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] % 2 == 1) {
                    result++;
                }
            }
        }
        
        return result;
    }
}

