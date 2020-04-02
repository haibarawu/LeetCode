/****************************************************************************************************
56. Merge Intervals

Difficulty: Medium

Given a collection of intervals, merge all overlapping intervals.

给出一个区间的集合，请合并所有重叠的区间。

Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1.首先根据二维数组中每个一维数组的[0]进行升序排序，即根据start进行排序；
2.合并条件是前一个的结束 大于等于 后一个的开始
3.然后建立linkedList作为中间处理对象，当集合为空或者不满足条件，加入集合的末尾；
4.集合不为空且满足合并条件时，取集合中最后一个元素让它的end为原值和合并区间end中的最大值，解决1,5;2,4这样的区间问题。
5.集合处理完成，对其循环遍历取首元素，放入res结果数组中
****************************************************************************************************/


class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.parallelSort(intervals, Comparator.comparingInt(x -> x[0]));

        LinkedList<int[]> list = new LinkedList<>();
        
        for (int i = 0; i < intervals.length; i++) {
            //集合为空，或不满足条件，向后新增
            if (list.size() == 0 || list.getLast()[1] < intervals[i][0]) {
                list.add(intervals[i]);
            } 
            //满足条件，集合最后元素的end = 最大值
            else {
                list.getLast()[1] = Math.max(list.getLast()[1], intervals[i][1]);
            }
        }
        //生成结果数组
        int[][] result = new int[list.size()][2];
        int index = 0;
        
        while (!list.isEmpty()) {
            //删除集合首元素
            result[index++] = list.removeFirst();
        }
        
        return result;
    }
}


