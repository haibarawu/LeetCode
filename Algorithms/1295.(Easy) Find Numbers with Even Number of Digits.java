/********************************************************************************
1295. Find Numbers with Even Number of Digits

Difficulty: Easy

Given an array nums of integers, return how many of them contain an even number of digits.

Example 1:
Input: nums = [12,345,2,6,7896]
Output: 2

Explanation: 
12 contains 2 digits (even number of digits). 
345 contains 3 digits (odd number of digits). 
2 contains 1 digit (odd number of digits). 
6 contains 1 digit (odd number of digits). 
7896 contains 4 digits (even number of digits). 
Therefore only 12 and 7896 contain an even number of digits.

Example 2:
Input: nums = [555,901,482,1771]
Output: 1 

Explanation: 
Only 1771 contains an even number of digits.

********************************************************************************/


//Method1: 
class Solution {
    public int findNumbers(int[] nums) {
        int length = 0;
        int result = 0;
        
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            while(num != 0) {
                length++;
                num = num / 10;
            }
            
            if(length % 2 == 0) {
                result++;
            }
            
            length = 0;
        }
        
        return result;
    }
}


//Method2: 
class Solution {
    public int findNumbers(int[] nums) {
        int length = 0;
        int result = 0;
        
        for(int i = 0; i < nums.length; i++) {
            length = String.valueOf(nums[i]).length();
            
            if(length % 2 == 0) {
                result++;
            }
        }
        
        return result;
    }
}

