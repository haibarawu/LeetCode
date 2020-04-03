/****************************************************************************************************
422. Valid Word Square

Difficulty: Easy

Given a sequence of words, check whether it forms a valid word square.
A sequence of words forms a valid word square if the k^th row and column read the exact same string, 
where 0 ≤ k < max(numRows, numColumns).

给定一个单词序列，检查它是否构成一个有效单词广场。
一个有效的单词广场应满足以下条件：对于满足 0 ≤ k < max(numRows numColumns) 的 k ，第 k 行和第 k 列对应的字符串应该相同,。

Example1
Input:  
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]
Output: true
Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crmy".
The fourth row and fourth column both read "dtye".
Therefore, it is a valid word square.

Example2
Input:  
[
  "abcd",
  "bnrt",
  "crm",
  "dt"
]
Output: true
Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crm".
The fourth row and fourth column both read "dt".
Therefore, it is a valid word square.

Example3
Input:  
[
  "ball",
  "area",
  "read",
  "lady"
]
Output: false
Explanation:
The third row reads "read" while the third column reads "lead".
Therefore, it is NOT a valid word square.

Notice
The number of words given is at least 1 and does not exceed 500.
Word length will be at least 1 and does not exceed 500.
Each word contains only lowercase English alphabet a-z.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
第 i 个word的第 j 个character必须和第 j 个Word的第 i 个character相同。
因为这里的word的长度可能不相等，所以注意检查i, j 是否超出边界。

//数组的长度就是元素字符串一共的长度是words.length;以数组为单位。
//每个元素字符串中字符的长度就是words[].length(),以String为单位。
****************************************************************************************************/


public class Solution {
    public boolean validWordSquare(List<String> words) {
        if(words == null || words.size() == 0) {
            return true;
        }
        int n = words.size();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < words.get(i).length(); j++) {
                if(n <= j || words.get(j).length() <= i || words.get(j).charAt(i) != words.get(i).charAt(j))
                    return false;
            }
        }
        return true;
    }
}



public class Solution {
    /**
     * @param words: an array of string
     * @return: a boolean
     */
    public boolean validWordSquare(String[] words) {
        for(int i = 0 ; i < words.length - 1 ; i++){
            for(int j = 0; j < words[i].length() - 1;j++){
                if(words[i].charAt(j) != words[j].charAt(i)){
                    return false;
                }
            }
        }
        return true;
    }
}


