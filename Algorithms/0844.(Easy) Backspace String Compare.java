/****************************************************************************************************
844. Backspace String Compare

Difficulty: Easy

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。

Example 1:
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".

Example 2:
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".

Example 3:
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".

Example 4:
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".

Note:
1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.

Follow up:
Can you solve it in O(N) time and O(1) space?
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）一个字符是否属于最终字符串的一部分，取决于它后面有多少个退格符。
2）反向遍历字符串 S 和 T，如果遍历到一个退格符，那么再往左第一个非退格字符将会被删除，剩余未被删除的字符就是最终的字符串。
3）比较当前未被删除的字符，如果不同则为 false。
4）如果处理后的两个字符串 S 和 T 长度不同，则结果为 false。
****************************************************************************************************/


class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        // While there may be chars in S or T
        while (i >= 0 || j >= 0) { 
            // Find position of next possible char in S
            while (i >= 0) { 
                if (S.charAt(i) == '#') {skipS++; i--;}
                else if (skipS > 0) {skipS--; i--;}
                else break;
            }
            
            // Find position of next possible char in T
            while (j >= 0) { 
                if (T.charAt(j) == '#') {skipT++; j--;}
                else if (skipT > 0) {skipT--; j--;}
                else break;
            }
            
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            
            i--; 
            j--;
        }
        
        return true;
    }
}


