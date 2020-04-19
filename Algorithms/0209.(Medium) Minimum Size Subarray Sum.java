****************************************************************************************************/
209. Minimum Size Subarray Sum
209. 长度最小的子数组

Difficulty: Medium

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. 
If there isn't one, return 0 instead.
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。


Example: 
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）使用滑动窗口，也就是用双指针的方法。用双指针 left 和 right 表示一个窗口。
2）right 向右移增大窗口，直到窗口内的数字和大于等于 s。
3）记录此时的长度，left 向右移动，开始减少长度，每减少一次，就更新最小长度。直到当前窗口内的数字和小于了 s，回到第 2 步。
时间复杂度：O(n)
空间复杂度：O(1)
********************************************************************************
举例：
s = 7, nums = [2,3,1,2,4,3]

2 3 1 2 4 3
^
l
r
上边的窗口内所有数字的和 2 小于 7, r 右移

2 3 1 2 4 3
^ ^
l r
上边的窗口内所有数字的和 2 + 3 小于 7, r 右移

2 3 1 2 4 3
^   ^
l   r
上边的窗口内所有数字的和 2 + 3 + 1 小于 7, r 右移

2 3 1 2 4 3
^     ^
l     r
上边的窗口内所有数字的和 2 + 3 + 1 + 2 大于等于了 7, 记录此时的长度 min = 4, l 右移

2 3 1 2 4 3
  ^   ^
  l   r
上边的窗口内所有数字的和 3 + 1 + 2  小于 7, r 右移

2 3 1 2 4 3
  ^     ^
  l     r
上边的窗口内所有数字的和 3 + 1 + 2 + 4 大于等于了 7, 更新此时的长度 min = 4, l 右移

2 3 1 2 4 3
    ^   ^
    l   r
上边的窗口内所有数字的和 1 + 2 + 4 大于等于了 7, 更新此时的长度 min = 3, l 右移

2 3 1 2 4 3
      ^ ^
      l r
上边的窗口内所有数字的和 2 + 4 小于 7, r 右移

2 3 1 2 4 3
      ^   ^
      l   r
上边的窗口内所有数字的和 2 + 4 + 3 大于等于了 7, 更新此时的长度 min = 3, l 右移

2 3 1 2 4 3
        ^ ^
        l r
上边的窗口内所有数字的和 4 + 3 大于等于了 7, 更新此时的长度 min = 2, l 右移

2 3 1 2 4 3
          ^
          r
          l
上边的窗口内所有数字的和 3 小于 7, r 右移，结束
********************************************************************************
****************************************************************************************************/


class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        
        while(right < nums.length) {
            sum += nums[right];
            right ++;
            while(sum >= s) {
                min = Math.min(min, right - left);
                sum -= nums[left];
                left ++;
            }
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}


