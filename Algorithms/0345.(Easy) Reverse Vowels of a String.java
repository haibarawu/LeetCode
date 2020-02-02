/****************************************************************************************************
345. Reverse Vowels of a String

Difficulty: Easy

Write a function that takes a string as input and reverse only the vowels of a string.
编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

Example 1:
Input: "hello"
Output: "holle"

Example 2:
Input: "leetcode"
Output: "leotcede"

Note:
The vowels does not include the letter "y".
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）创建一个包含所有元音vowel的数组链。
2）两个指针头尾查询当下字母元素s.chatAt(i) 和s.charAt(j) 是否在vowel中。
3）两个都在的情况下，说明都是元音，则做字母交换。否则继续寻找，直到指针相遇。
****************************************************************************************************/


class Solution {
    public String reverseVowels(String s) {
        ArrayList<Character> vowel = new ArrayList<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U')); 
        
        char[] c = s.toCharArray();
  
        int i = 0;
        int j = s.length() - 1;
        
        while(i < j) {
            if(!vowel.contains(s.charAt(i))) {i++;}
            if(!vowel.contains(s.charAt(j))) {j--;}
            if(vowel.contains(s.charAt(i)) && vowel.contains(s.charAt(j))) {
                char temp = c[i];
                c[i] = c[j];
                c[j] = temp;
                
                i++;
                j--;
            }
        }
        
        return new String(c);
    }
}


