/****************************************************************************************************
136. Single Number

Difficulty: Easy

Given a non-empty array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）使用<异或运算>：
因为题中提到除了只出现一次的数字之外，其它数字都是出现两次。
所以可以用异或运算的特性，即相同数字做^异或运算，会得到0，来使得出现两次的相同数字异或的结果为0；
和与0异或的任意数字都不变，最后剩下的数字就是只出现了一次的数字。
****************************************************************************************************/


class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        
        for(int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        
        return result;
    }
}


