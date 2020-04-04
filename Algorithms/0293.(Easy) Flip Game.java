/****************************************************************************************************
293. Flip Game

Difficulty: Easy

You are playing the following Flip Game with your friend: 
Given a string that contains only two characters: + and -, you can flip two consecutive "++" into "--", you can only flip one time. 
Please find all strings that can be obtained after one flip.
Write a program to find all possible states of the string after one valid move.

翻转游戏：
给定一个只包含两种字符的字符串：+和-，你可以将两个连续的“++”翻转成"--"，你只可以翻转一次。
找到翻转后所有可能得到的结果。
编写一个程序，找到字符串在一次有效翻转后的所有可能状态。

Example1
Input:  s = "++++"
Output: 
[
  "--++",
  "+--+",
  "++--"
]

Example2
Input: s = "---+++-+++-+"
Output: 
[
	"---+++-+---+",
	"---+++---+-+",
	"---+---+++-+",
	"-----+-+++-+"
]
****************************************************************************************************/


/****************************************************************************************************
解题思路：
从第二个字母开始遍历，每次判断当前字母和之前那个字母的组合是否为"++"，如果都为+，则将翻转为"--"后的字符串存入结果中。
****************************************************************************************************/


public class Solution {
    /**
     * @param s: the given string
     * @return: all the possible states of the string after one valid move
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<String>();
        
        for(int i = 1; i < s.length(); i++) {
            if(s.substring(i-1, i+1).equals("++")) {
                result.add(s.substring(0, i-1) + "--" + s.substring(i+1));
            }
        }
        
        return result; 
    }
}


