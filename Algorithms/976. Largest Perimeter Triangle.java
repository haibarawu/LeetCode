/****************************************************************************************************
976. Largest Perimeter Triangle
976. 三角形的最大周长

Difficulty: Easy

Given an array A of positive lengths, 
return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
If it is impossible to form any triangle of non-zero area, return 0.

给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
如果不能形成任何面积不为零的三角形，返回 0。

Example 1:
Input: [2,1,2]
Output: 5

Example 2:
Input: [1,2,1]
Output: 0

Example 3:
Input: [3,2,3,4]
Output: 10

Example 4:
Input: [3,6,2,3]
Output: 8

Note:
3 <= A.length <= 10000
1 <= A[i] <= 10^6
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）对数组 A 进行排序。
2）从大到小循环搜索 A 中能构成三角形的最大三边（两边之和大于第三边）。
3）如果循环完所有元素也没有找到，则返回0。
****************************************************************************************************/


class Solution {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        
        for(int i = A.length - 3; i >= 0; i--) {
            if(A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }
        
        return 0;
    }
}


