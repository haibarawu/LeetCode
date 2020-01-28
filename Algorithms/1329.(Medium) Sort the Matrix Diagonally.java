/****************************************************************************************************
1329. Sort the Matrix Diagonally
1329. 将矩阵按对角线排序

Difficulty: Medium

Given a m * n matrix mat of integers, 
sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.

给你一个 m * n 的整数矩阵 mat ，请你将同一条对角线上的元素（从左上到右下）按升序排序后，返回排好序的矩阵。

Example 1:

   \3 \3 \1 \1           \1 \1 \1 \1
 \2 \2 \1 \2    =>     \1 \2 \2 \2
1 \1 \1 \2            1 \2 \3 \3

Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）遍历矩阵，找到每一个对角线的起点。
2）将当前得到的对角线所有元素存到临时动态数组中。
3）对动态数组的元素重新排序。
4）将动态数组的元素重新按顺序存回矩阵的该对角线。
****************************************************************************************************/


class Solution {
    public int[][] diagonalSort(int[][] mat) {
        // 矩阵行数
        int m = mat.length;
        // 矩阵列数
        int n = mat[0].length;
        
        //循环找出矩阵中的每一条对角线的起点，排序该对角线元素，重新存储入矩阵。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 不是对角线的起点，跳过。
                if (!(i == 0 || j == 0)) continue;
                
                //创建存储当前对角线元素的动态数组。
                ArrayList<Integer> list = new ArrayList<>();    
                
                //将当前对角线上的数加入数组。
                for(int x = i, y = j; x < m && y < n; x++, y++)
                    list.add(mat[x][y]);
                
                //重新排序当前对角线元素。
                Collections.sort(list);
                
                //将元素重新放入矩阵的当前对角线。
                for (int s = 0, x = i, y = j; x < m && y < n; x++, y++, s++)
                    mat[x][y] = list.get(s);
            }
        }
        return mat;
    }
}


