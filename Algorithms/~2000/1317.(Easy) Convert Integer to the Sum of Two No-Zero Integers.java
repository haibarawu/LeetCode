/****************************************************************************************************
1317. Convert Integer to the Sum of Two No-Zero Integers

Difficulty: Easy

Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.
Return a list of two integers [A, B] where:
A and B are No-Zero integers.
A + B = n
It's guarateed that there is at least one valid solution. 
If there are many valid solutions you can return any of them.

「无零整数」是十进制表示中 不含任何 0 的正整数。
给你一个整数 n，请你返回一个 由两个整数组成的列表 [A, B]，满足：
A 和 B 都是无零整数
A + B = n
题目数据保证至少有一个有效的解决方案。
如果存在多个有效解决方案，你可以返回其中任意一个。

Example 1:
Input: n = 2
Output: [1,1]
Explanation: A = 1, B = 1. A + B = n and both A and B don't contain any 0 in their decimal representation.

Example 2:
Input: n = 11
Output: [2,9]

Example 3:
Input: n = 10000
Output: [1,9999]

Example 4:
Input: n = 69
Output: [1,68]

Example 5:
Input: n = 1010
Output: [11,999]

Constraints:
2 <= n <= 10^4
****************************************************************************************************/


/****************************************************************************************************
解题思路1：
1）for循环给出 i 和 n-i。
2）用String.valueOf(i).contains("0") 判断 i 和 n-i 是否都不包含 0。
****************************************************************************************************/

class Solution {
    public int[] getNoZeroIntegers(int n) {
        int[] result = new int[2];
        
        for(result[0] = 1; result[0] < n; result[0]++) {
            result[1] = n - result[0];
            if(!String.valueOf(result[0]).contains("0") && !String.valueOf(result[1]).contains("0")) {
                return result;
            }
        }
        return result;
    }
}


／****************************************************************************************************/


/****************************************************************************************************
解题思路2：
1）for循环给出 i 和 n-i。
2）用containsZero() 判断 i 和 n-i 是否都不包含 0。
3）containsZero() 通过循环，n%10 的余数是否为 0。
****************************************************************************************************/

class Solution {
    public int[] getNoZeroIntegers(int n) {
        int[] result = new int[2];
        
        for(result[0] = 1; result[0] < n; result[0]++) {
            result[1] = n - result[0];
            if(!containsZero(result[0]) && !containsZero(result[1])) {
                return result;
            }
        }
        return result;
    }
    
    public boolean containsZero(int n) {
        while(n > 0) {
            if(n % 10 == 0) {
                return true;
            }
            n = n / 10;
        }
        return false;
    }
}


