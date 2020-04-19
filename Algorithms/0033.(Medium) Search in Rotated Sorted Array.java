/****************************************************************************************************
33. Search in Rotated Sorted Array
33. 搜索旋转排序数组

Difficulty: Medium

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

假设按照升序排序的数组在预先未知的某个点上进行了旋转。=
( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
你可以假设数组中不存在重复的元素。
你的算法时间复杂度必须是 O(log n) 级别。

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
****************************************************************************************************/


/****************************************************************************************************
解题思路：
使用二分查找。
1 2 3 4 5 6 7 可以大致分为两类，
第一类 2 3 4 5 6 7 1 这种，也就是 nums[start] <= nums[mid]。此例子中就是 2 <= 5。
这种情况下，前半部分有序。因此如果 nums[start] <= target < nums[mid]，则在前半部分找，否则去后半部分找。

第二类 6 7 1 2 3 4 5 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2。
这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]，则在后半部分找，否则去前半部分找。
****************************************************************************************************/


class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            
            if (nums[low] <= nums[mid]) { //前半部分有序
                if (nums[low] <= target && target < nums[mid]) { //target在前半部分区间
                    high = mid -1;
                } 
                else {
                    low = mid + 1;
                } 
            } 
            else { //后半部分有序
                if (nums[mid] < target && target <= nums[high]) { //target在后半部分区间
                    low = mid + 1;
                } 
                else {
                    high = mid - 1;
                }
            }
        }
        //没有找到target，返回 -1。
        return -1;
    }
}


