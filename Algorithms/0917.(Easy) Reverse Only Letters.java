/****************************************************************************************************
917. Reverse Only Letters

Difficulty: Easy

Given a string S, return the "reversed" string，
where all characters that are not a letter stay in the same place, 
and all letters reverse their positions.

给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。

Example 1:
Input: "ab-cd"
Output: "dc-ba"

Example 2:
Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"

Example 3:
Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"

Note:
S.length <= 100
33 <= S[i].ASCIIcode <= 122 
S doesn't contain \ or "

****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）将 string 转换为 char array。
2）用双指针从前后遍历数组，用 isLetter() 检查当前元素是否为字母。当两个指针指向都为字母时，进行元素交换。
****************************************************************************************************/


class Solution {
    public String reverseOnlyLetters(String S) {
        char[] s = S.toCharArray();
        int start = 0;
        int end = s.length - 1;
		 
        while(start < end) {
          while(start < end && !isLetter(s[start])) {
            start ++;
          }
          while(start < end && !isLetter(s[end])) {
            end --;
          }
             
          char temp = s[start];
          s[start] = s[end];
          s[end] = temp;
          start ++;
          end --;
        }
		
        return new String(s);
    }
    
    public  boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}


