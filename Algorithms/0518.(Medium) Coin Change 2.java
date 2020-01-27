/****************************************************************************************************
518. Coin Change 2
518. 零钱兑换 II

Difficulty: Medium

You are given coins of different denominations and a total amount of money. 
Write a function to compute the number of combinations that make up that amount. 
You may assume that you have infinite number of each kind of coin.

给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

Example 1:
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10] 
Output: 1

Note:
You can assume that: 
0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
****************************************************************************************************/


/****************************************************************************************************
解题思路：
使用动态规划DP：
DP定义：记dp[i]为凑成总金额 i 的各种硬币总组合数；
DP初始：dp[0] = 1，dp[1, 2,....., i] = 0;
DP更新：dp[i] = dp[i] + dp[i-coin]；

1）定义一个 amount＋1 长度的数组 dp[i]，表示达到金额 i 时用的最少硬币数。
2）当金额为0时，组合的方式为 1（即所有硬币都不出）。（dp[0] = 1）
3）每添加一种硬币 coins[i]，使用指针 j 尝试从金额 0 到 amount 递归的计算组合数量,找出能够组合出当前金额的所有组合可能。
4）状态转移方程为：dp[j] = dp[j] + dp[j - coins[i]]
5）dp[j] 为使用 coins[i] 以及之前所有硬币能够组合出来的总组合数。
6）dp[j - coins[i]] 为在不使用 coins[i] 的时候，之前所有硬币能够组合出来的总组合数。
****************************************************************************************************/


class Solution {
    public int change(int amount, int[] coins) {
        if(amount == 0)
            return 1;
        
        if(coins.length == 0)
            return 0;
        
        int[] dp = new int[amount + 1];
        //所有硬币组合出 0 元面额的方法数是 1（没有组合也是一种组合方式）。
        dp[0] = 1;
        
        for(int i = 0; i < coins.length; i++){ //for(int coin : coins)
            for(int j = 1; j <= amount; j++){
                if(j - coins[i] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        
        return dp[amount];
    }
}


