/****************************************************************************************************
1309. Decrypt String from Alphabet to Integer Mapping

Difficulty: Easy

https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
https://leetcode-cn.com/problems/decrypt-string-from-alphabet-to-integer-mapping/

Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:
Characters ('a' to 'i') are represented by ('1' to '9') respectively.
Characters ('j' to 'z') are represented by ('10#' to '26#') respectively. 
Return the string formed after mapping.
It's guaranteed that a unique mapping will always exist.

给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符：
字符（'a' - 'i'）分别用（'1' - '9'）表示。
字符（'j' - 'z'）分别用（'10#' - '26#'）表示。 
返回映射之后形成的新字符串。
题目数据保证映射始终唯一。

Example 1:
Input: s = "10#11#12"
Output: "jkab"
Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".

Example 2:
Input: s = "1326#"
Output: "acz"

Example 3:
Input: s = "25#"
Output: "y"

Example 4:
Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
Output: "abcdefghijklmnopqrstuvwxyz"

Constraints:
1 <= s.length <= 1000
s[i] only contains digits letters ('0'-'9') and '#' letter.
s will be valid string such that mapping is always possible.

****************************************************************************************************/


/****************************************************************************************************
1）对字符串 s 进行顺序遍历。
2）当遍历到位置 i 时，先向后看两个字符（即 s[i + 2]），如果 s[i + 2] 存在且为 '#'，那么位置 i，i + 1 和 i + 2 表示一个 'j' 到 'z' 之间的字符，否则位置 i 表示一个 'a' 到 'i' 的字符。
3）根据对 s[i + 2] 的判断，我们可以使用字符串转整数的方法得到对应的字符的 ASCII 码，从而得到字符本身。
4）添加至output之后，我们将位置 i 后移1位或者3位，继续进行遍历直到结束。
****************************************************************************************************/


class Solution {
    public String freqAlphabets(String s) {
        String result = "";
        int i = 0;
        
        while(i < s.length()) {
            if((i + 2) < s.length() && s.charAt(i + 2) == '#') {
                result += (char)('a' + Integer.parseInt(s.substring(i, i + 2)) - 1);
                i = i + 3;
            }
            else {
                result += (char)('a' + Integer.parseInt(s.substring(i, i + 1)) - 1);
                i++;
            }
        }
        
        return result;
    }
}

