/****************************************************************************************************
1200. Minimum Absolute Difference 
1200. 最小绝对差 

Difficulty: Easy

Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements. 
Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows: 
a, b are from arr; 
a < b; 
b - a equals to the minimum absolute difference of any two elements in arr. 

给你个整数数组 arr，其中每个元素都不相同。
请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。 

Example 1:
Input: arr = [4,2,1,3]
Output: [[1,2],[2,3],[3,4]]
Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.

Example 2:
Input: arr = [1,3,6,10,15]
Output: [[1,3]]

Example 3:
Input: arr = [3,8,-10,23,19,-4,-14,27]
Output: [[-14,-10],[19,23],[23,27]]

Constraints:
2 <= arr.length <= 10^5
-10^6 <= arr[i] <= 10^6

****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）循环整个数组，找出最小绝对差 min。
2）根据最小差，循环数组找出每一对最小差的元素对，现将元素对中的每个元素存入小数组 innerlist 中，再将 innerlist 存入结果中。
****************************************************************************************************/


class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }
        
        for (int i = 1; i  < arr.length; i++) {
            if (arr[i] - arr[i - 1] == min) {
                List<Integer> innerlist = new ArrayList<>();
                innerlist.add(arr[i - 1]);
                innerlist.add(arr[i]);
                result.add(innerlist);
            }
        }
        return result;
    }
}


