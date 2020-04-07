/****************************************************************************************************
16. 3Sum Closest

Difficulty: Medium

Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. 
Return the sum of the three integers. You may assume that each input would have exactly one solution.

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

Example:
Given array nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）先将数组排序，时间复杂度 O(nlogn)。
2）在数组 nums 中，进行遍历，每遍历一个值利用其下标i，形成一个固定值 nums[i]。再使用前指针指向 start = i + 1 处，后指针指向 end = nums.length - 1 处，也就是结尾处。
3）根据 sum = nums[i] + nums[start] + nums[end] 的结果，判断 sum 与目标 target 的距离，如果更近则更新结果 result。
4）同时判断 sum 与 target 的大小关系，因为数组有序，如果 sum > target 则 end--，如果 sum < target 则 start++，如果 sum == target 则说明距离为 0 直接返回结果 result。
5）整个遍历过程，固定值为 n 次，双指针为 n 次，时间复杂度为 O(n^2)
总时间复杂度：O(nlogn) + O(n^2) = O(n^2)
****************************************************************************************************/


class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        
        for(int i = 0; i < nums.length; i++) {
            int start = i + 1; 
            int end = nums.length - 1;
            
            while(start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if(Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }
                if(sum < target) {
                    start++;
                }
                else if(sum > target) {
                    end--;
                }
                else {
                    return result;
                }
            }
        }
        
        return result;
    }
}


