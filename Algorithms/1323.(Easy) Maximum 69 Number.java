/****************************************************************************************************
1323. Maximum 69 Number

Difficulty: Easy

Given a positive integer num consisting only of digits 6 and 9.
Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).

给你一个仅由数字 6 和 9 组成的正整数 num。
你最多只能翻转一位数字，将 6 变成 9，或者把 9 变成 6 。
请返回你可以得到的最大数字。

Example 1:
Input: num = 9669
Output: 9969
Explanation: 
Changing the first digit results in 6669.
Changing the second digit results in 9969.
Changing the third digit results in 9699.
Changing the fourth digit results in 9666. 
The maximum number is 9969.

Example 2:
Input: num = 9996
Output: 9999
Explanation: Changing the last digit 6 to 9 results in the maximum number.

Example 3:
Input: num = 9999
Output: 9999
Explanation: It is better not to apply any change.

Constraints:
1 <= num <= 10^4
num's digits are 6 or 9.

****************************************************************************************************/


//Method1: 
class Solution {
    public int maximum69Number (int num) {
        String s = Integer.toString(num);
        
        for(int i = 0; i < s.length(); i++) {
            // find the first '6' in the number in order
            if(s.charAt(i) == '6') {
                // replace the first '6' to '9'
                s = s.substring(0, i) + '9' + s.substring(i + 1); 
                // only want to change the very first '6' (the largest digit)
                break;
            }
        }
        
        int result = Integer.parseInt(s);
        
        return result;
    }
}


//Method2:
class Solution {
    public int maximum69Number (int num) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        boolean changed = false;
        
        while (num > 0)
        {
            stack.push(num % 10);
            num = num / 10;
        }
        
        while (!stack.isEmpty())
        {
            int digit = stack.pop();
            
            if (digit == 6 && !changed)
            {
                digit = 9;
                changed = true;
            }
            
            result = 10 * result + digit;
        }
        
        return result;
    }
}

