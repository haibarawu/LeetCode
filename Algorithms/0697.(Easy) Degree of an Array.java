/****************************************************************************************************
697. Degree of an Array
697. 数组的度

Difficulty: Easy

Given a non-empty array of non-negative integers nums, 
the degree of this array is defined as the maximum frequency of any one of its elements.
Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6

Note:
nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.

****************************************************************************************************/


class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        Map<Integer, Integer> starts = new HashMap<>();
        Map<Integer, Integer> ends = new HashMap<>();
        
        int maxCount = Integer.MIN_VALUE;
        int result = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            if (!starts.containsKey(num)) {
                starts.put(num, i);
                counts.put(num, 0);
            }
            
            counts.put(num, counts.get(num) + 1);
            ends.put(num, i);
            maxCount = Math.max(maxCount, counts.get(num));
        }
        
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == maxCount) {
                result = Math.min(result, ends.get(entry.getKey()) - starts.get(entry.getKey()) + 1);
            }
        }
        
        return result;
    }
}


