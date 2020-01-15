/********************************************************************************
1281. Subtract the Product and Sum of Digits of an Integer

Difficulty: Easy

Given an integer number n, return the difference between the product of its digits and the sum of its digits.

Example 1:
Input: n = 234
Output: 15 

Explanation: 
Product of digits = 2 * 3 * 4 = 24 
Sum of digits = 2 + 3 + 4 = 9 
Result = 24 - 9 = 15

Example 2:
Input: n = 4421
Output: 21

Explanation: 
Product of digits = 4 * 4 * 2 * 1 = 32 
Sum of digits = 4 + 4 + 2 + 1 = 11 
Result = 32 - 11 = 21

Constraints:
1 <= n <= 10^5

********************************************************************************/


//Method1: 
class Solution {
    public int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        int digit;
        
        while(n != 0) {
            digit = n % 10;
            product *= digit;
            sum += digit;
            n = n / 10;
        }
        
        return product - sum;
    }
}


//Method2:
class Solution {
    public int subtractProductAndSum(int n) {
        String nString = String.valueOf(n);
        char[] digits = nString.toCharArray();
        
        int prod = Character.getNumericValue(digits[0]);
        int sum = Character.getNumericValue(digits[0]);
        
        for (int i = 0; i < (digits.length - 1); i++) {
            prod *= Character.getNumericValue(digits[i+1]);
            sum += Character.getNumericValue(digits[i+1]);
        }
        
        return prod - sum;
    }
}

