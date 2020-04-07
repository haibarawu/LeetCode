/****************************************************************************************************
49. Group Anagrams

Diffiulty: Medium

Given an array of strings, group anagrams together.
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

Example:
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:
All inputs will be in lowercase.
The order of your output does not matter.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
将每个单词按照字母表顺序排序。比如eat，aet排序好以后都是aet。
遍历原数组，取出每个单词按字母表排序后的结果。将其加入map。
对于strs = {"eat", "tea", "tan", "ate", "nat", "bat"}，我们会统计出如下的map。
{aet=[eat, tea, ate], abt=[bat], ant=[tan, nat]}。map的value即为我们所求。
****************************************************************************************************/


class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            //将每个单词按照字母表顺序排序
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } 
            else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }
        return new ArrayList<List<String>>(map.values());
    }
}


