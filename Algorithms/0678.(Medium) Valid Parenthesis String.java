/****************************************************************************************************
678. Valid Parenthesis String
678. 有效的括号字符串

Difficulty: Medium

Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. 
We define the validity of a string by these rules:
1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
4. '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
5. An empty string is also valid.

给定一个只包含三种字符的字符串： '(' ， ')' 和 '*'，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
1. 任何左括号 '(' 必须有相应的右括号 ')' 。
2. 任何右括号 ')' 必须有相应的左括号 '(' 。
3. 左括号 '(' 必须在对应的右括号之前 ')' 。
4. '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串。
5. 一个空字符串也被视为有效字符串。

Example 1:
Input: "()"
Output: True

Example 2:
Input: "(*)"
Output: True

Example 3:
Input: "(*))"
Output: True

Note:
The string size will be in the range [1, 100].
****************************************************************************************************/


/****************************************************************************************************
解题思路：<双栈解法>
1）一栈 left 存左括号的索引，一栈 star 存星号的索引。
2）遍历字符串，将左括号和星号的索引分别放入各自的栈，并判断是否有足够的右括号使他们出栈。优先抵消左括号，左括号没有了再抵消星号。若还有右括号却没有左括号或者星号，则失败。
3）遍历结束（即没有右括号）后，两栈同时出栈并判断，要求所有左括号，都有<其右边索引的星号>能使其抵消。（如果星号在左括号的左边 则不能作为右括号与其抵消。）
4）最后判断左括号有没有剩下，左括号栈空则成功。
****************************************************************************************************/


class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                left.push(i);
            }
            else if(c == '*') {
                star.push(i);
            }
            else if(c == ')') {
                if(!left.isEmpty()) {
                    left.pop();
                }
                else if(!star.isEmpty()) {
                    star.pop();
                }
                else return false;
            }
        }
        
        while(!left.isEmpty() && !star.isEmpty()) {
            if(left.pop() > star.pop()) {
                return false;
            }
        }
        
        return left.isEmpty();
    }
}


