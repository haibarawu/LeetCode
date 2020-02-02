/****************************************************************************************************
15. 3Sum

Difficulty: Medium

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.
Note:
The solution set must not contain duplicate triplets.

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。

Example:
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

****************************************************************************************************/


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if(nums == null || nums.length == 0) {
            return result;
        }
        
        Map<Integer,Integer> map = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for(int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            
            for(int j = i + 1; j < nums.length; j++) {
                int num2 = - (num1 + nums[j]);
                if(map.containsKey(num2) && map.get(num2) > j) {
                    List<Integer> array = new ArrayList<>();
                    array.add(num1);
                    array.add(num2);
                    array.add(nums[j]);
                    result.add(array);
                }
            }
        }
        return result;
    }
}


