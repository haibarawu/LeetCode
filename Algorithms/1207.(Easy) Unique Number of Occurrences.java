/****************************************************************************************************
1207. Unique Number of Occurrences

Difficulty: Easy

Given an array of integers arr, 
write a function that returns true if and only if the number of occurrences of each value in the array is unique.

给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。

Example 1:
Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.

Example 2:
Input: arr = [1,2]
Output: false

Example 3:
Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true

Constraints:
1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000

****************************************************************************************************/


/********************************************************************************
解题思路：
1）遍历数组，将<数字，出现频率>存入 HashMap 中。
2）利用 HashSet 不能存有重复值的属性，取出 Map 的所有 value 逐个插入 Set中存储。
3）若存在与当前 value 一样的值，返回 false。若不存在，则将 value 存入 Set。
********************************************************************************/


class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Set<Integer> set = new HashSet<Integer>();
        
        // 记录出现次数
        for(int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], 1);
            }
            else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        
        // 验证重复值
        for (int value : map.values()) {
            if (set.contains(value)) {
                return false;
            }
            set.add(value);
        }
        return true;
    }
}


