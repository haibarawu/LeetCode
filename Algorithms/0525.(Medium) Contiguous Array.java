/****************************************************************************************************
525. Contiguous Array
525. 连续数组

Difficulty: Medium

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）使用一个变量 count ，用来保存遍历数组过程中到目前为止遇到的 0 和 1 的相对数量。遇到 1 的时候 count 变量加 1 ，遇到 0 的时候 count 变量减 1 。
2）使用一个 HashMap 来保存所有的 (index, count)。对于一个 count ，我们只记录它第一次出现的位置，后面遇到相同的 count 我们都用这个第一次记录的 index 减去当前的元素位置来计算子数组的长度。

时间复杂度：O(n)。整个数组遍历一遍。
空间复杂度：O(n)。最坏情况为所有元素都为 1 或者都为 0。
****************************************************************************************************/


class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }
}


