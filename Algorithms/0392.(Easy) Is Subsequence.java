/****************************************************************************************************
392. Is Subsequence
392. 判断子序列

Difficulty: Easy

Given a string s and a string t, check if s is subsequence of t.
You may assume that there is only lower case English letters in both s and t. 
t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
A subsequence of a string is a new string which is formed from the original string 
by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. 
(ie, "ace" is a subsequence of "abcde" while "aec" is not).

给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

Example 1:
s = "abc", t = "ahbgdc"
Return true.

Example 2:
s = "axc", t = "ahbgdc"
Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, 
and you want to check one by one to see if T has its subsequence. 
In this scenario, how would you change your code?

后续挑战 :
如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
在这种情况下，你会怎样改变代码？
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）两个字符串分别用指针 i，j 来对应遍历字符串 s，t。（i对s，j对t）
2）循环对 t 的遍历，当找到字符串 s 的子元素＝＝字符串 t 的子元素，s 的指针 i 移动到下一个元素。
3）遍历完 t 后，如果指针 i 完成对 s 的遍历（i == s.length()）,则 s 为 t 的子序列，反之则不是。
****************************************************************************************************/


class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        
        //遍历字符串t
        for(int j = 0; j < t.length() && i < s.length(); j++) {
            //如果当前s元素与当前t元素相同，则去s的下一个元素去对比。
            if(s.charAt(i) == t.charAt(j)) {
                i++;
            }
        }
        //如果字符串s遍历完成，则为子序列
        return i == s.length();
    }
}


