/****************************************************************************************************
1079. Letter Tile Possibilities

Difficulty: Medium

You have a set of tiles, where each tile has one letter tiles[i] printed on it. 
Return the number of possible non-empty sequences of letters you can make. 

Example 1:
Input: "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

Example 2:
Input: "AAABBC"
Output: 188

Note:
1 <= tiles.length <= 7
tiles consists of uppercase English letters.

****************************************************************************************************/


/****************************************************************************************************
通常情况下，求所有的排列组合可以想到使用DFS深度优先搜索来解决。
我们将 tiles 中的每一个字符看作为一个节点，以任意一个字符为起点DFS每一条路径就可以搜索出所有的线路。
不过本题需要注意存在重复答案，如何排重是本题优化的关键。

解题思路：
1）先将 tiles 字符串中每种字符出现的次数统计出来。
2）以每种字符作为起点开始DFS查找。这样，相同的字符不会重复作为起点，从而避免了重复。
****************************************************************************************************/

class Solution {
    public int numTilePossibilities(String tiles) {
        int[] count = new int[26]; // 统计每种字符出现的次数
        
        for(int i = 0; i < tiles.length(); i++) {
            count[tiles.charAt(i) - 'A']++;
        }
        
        return DFS(count); // 开始DFS（递归）
    }
    
    public int DFS(int[] count) {
        int result = 0; // 返回值，组合个数
        
        for(int i = 0; i < 26; i++) { // 以每一个大写字母作为起点循环
            if(count[i] == 0) { // 该字母个数为0，跳过
                continue;
            }
            result++; // 返回结果加1。
            count[i]--; // 消耗当前字符
            result += DFS(count); // 递归到下一层求子问题解（消耗掉当前字符后的解）。
            count[i]++; // 还原当前字符继续下一个字母的组合循环
        }
        
        return result;
    }
}


