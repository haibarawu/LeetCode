/****************************************************************************************************
560. Subarray Sum Equals K

Difficulty: Medium

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2

Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）循环选择一个 start，同时迭代 end。
2）将对应于 end 的元素值 nums[end] 添加到目前的总和 sum 中。
3）当 sum 等于所需的 k 值时，我们可以更新 count 值 ++。

时间复杂度：O(n^2)。需考虑所有可能的数组。
空间复杂度：O(1)。仅需常数级的空间。
****************************************************************************************************/


class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}


