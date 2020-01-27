/****************************************************************************************************
322. Coin Change
322. 零钱兑换

Difficulty: Medium

You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

给定不同面额的硬币 coins 和一个总金额 amount。
编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
如果没有任何一种硬币组合能组成总金额，返回 -1。

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Note:
You may assume that you have an infinite number of each kind of coin.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
当要求一个最优解或在代码中有循环和 max，min 等函数时，使用动态规划：
假设 f(n) 为要凑出 n 元的最少硬币个数，则：
f(n) = min(f(n), f(n - c1)＋1, f(n - c2)+1, ... f(n - cn)+1) 
例如，f(11) = min(f(10)+1, f(9)+1, f(6)+1)，即原问题的解由子问题的最优解构成。

1）定义一个 amount＋1 长度的数组 dp[i]，表示达到金额 i 时用的最少硬币数。
2）初始化除了 dp[0] = 0 之外所有 dp 中的值为 (Integer.MAX_VALUE - 1)。（定义为最大值＋1会溢出变为最小值）
2）对于每一个面额，尝试每一种硬币，找出使用最少的硬币个数。
3）状态转移方程为：dp[i] = Math.min(dp[i - coin] + 1, dp[i])
4）如果dp[amount]结果不为初始值，则说明找到了最少硬币个数。
****************************************************************************************************/


class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE - 1);
        
        for(int i = 1; i <= amount; i++) {
            for(int coin : coins) {
                if(i - coin >= 0) {
                    dp[i] = Math.min(dp[i-coin] + 1, dp[i]);
                }
            }
        }
        
        if(dp[amount] != Integer.MAX_VALUE - 1) {
            return dp[amount];
        }
        else {
            return -1;
        }
    }
}


