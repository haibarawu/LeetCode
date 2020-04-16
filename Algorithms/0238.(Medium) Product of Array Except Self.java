/****************************************************************************************************
238. Product of Array Except Self
238. 除自身以外数组的乘积

Difficulty: Medium

Given an array nums of n integers where n > 1, 
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]

Constraint: 
It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

Note: Please solve it without division and in O(n).
说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

Follow up:
Could you solve it with constant space complexity? 
(The output array does not count as extra space for the purpose of space complexity analysis.)
进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）用一个遍历来跟踪左边元素的乘积 L。并更新数组 result[i]=L。然后 L 更新为 L=L*nums[i]。
2）用一个遍历来跟踪右边元素的乘积 R。并更新数组 result[i]=result[i]∗R。然后 R 更新为 R=R*nums[i]。
****************************************************************************************************/


class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        
        // L contains the product of all the elements to the left.
        // Note: for the element at index '0', there are no elements to the left,
        // so L would be 1 at the beginning.
        int L = 1;
        for (int i = 0; i <= nums.length - 1; i++) {
            result[i] = L;
            L *= nums[i];
        }
        
        // R contains the product of all the elements to the right.
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so R would be 1 at the beginning.
        int R = 1;
        for (int i = nums.length - 1; i >= 0 ; i--) {
            result[i] *= R;
            R *= nums[i];
        }
        
        return result;
    }
}


