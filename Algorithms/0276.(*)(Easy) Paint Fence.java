/****************************************************************************************************
276. Paint Fence

Difficulty: Easy

There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

我们有一个栅栏，它有n个柱子，现在要给柱子染色，有k种颜色可以染。
必须保证不存在超过2个相邻的柱子颜色相同，求有多少种染色方案。

Example 1:
Input: n=3, k=2  
Output: 6
Explanation:
          post 1,   post 2, post 3
    way1    0         0       1 
    way2    0         1       0
    way3    0         1       1
    way4    1         0       0
    way5    1         0       1
    way6    1         1       0

Example 2:
Input: n=2, k=2  
Output: 4
Explanation:
          post 1,   post 2
    way1    0         0       
    way2    0         1            
    way3    1         0          
    way4    1         1       

Notice
n and k are non-negative integers.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
动态规划DP：
最多 2 个相邻的 fence 颜色相同
- 假设i是和 i-1不同，那么结果就是 (k-1) * dp[i - 1]
- 假设i是何 i-1相同，那么根据条件，i-1和i-2肯定不同。那么所有的结果就是(k-1) * dp[i - 2]
- dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2])
- 加法原理
- time, space: O(n)
****************************************************************************************************/


public class Solution {
    /**
     * @param n: non-negative integer, n posts
     * @param k: non-negative integer, k colors
     * @return: an integer, the total number of ways
     */
    public int numWays(int n, int k) {
        if (n <= 1 || k <= 0) {
            return n * k;
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k + k * (k - 1); // [1,2] same color + [1,2] diff color
        
        for (int i = 3; i <= n; i++) {
            dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]);
        }
        
        return dp[n];
    }
}


