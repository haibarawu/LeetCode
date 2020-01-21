/****************************************************************************************************
1304. Find N Unique Integers Sum up to Zero

Difficulty: Easy

https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
https://leetcode-cn.com/problems/find-n-unique-integers-sum-up-to-zero/

Given an integer n, return any array containing n unique integers such that they add up to 0.

给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。

Example 1:
Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].

Example 2:
Input: n = 3
Output: [-1,0,1]

Example 3:
Input: n = 1
Output: [0]

Constraints:
1 <= n <= 1000

****************************************************************************************************/


／****************************************************************************************************
1）创建数组，数组初始化就是 0。
2）如果数组只有一位，则直接返回。
3）每两个正负的数字循环放入数组，+1，-1，+2，-2。
4）奇偶可以不判断，奇数必定有一个 0，第3步之后剩下的就是 0 了。
****************************************************************************************************/


class Solution {
    public int[] sumZero(int n) {
        //Default value for integer array is 0.
        int[] result = new int[n];
        
        if(n == 1) {
            return result;
        }
        
        for(int i = 0; i < n / 2; i++) {
            result[i] = i + 1;
            result[n - i - 1] = -result[i];
        }
        
        return result;
    }
}

