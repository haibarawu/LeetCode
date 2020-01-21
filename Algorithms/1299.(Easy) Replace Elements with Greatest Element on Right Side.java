/****************************************************************************************************
1299. Replace Elements with Greatest Element on Right Side

Difficulty: Easy

https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side/

Given an array arr, replace every element in that array with the greatest element among the elements to its right, 
and replace the last element with -1.
After doing so, return the array.

给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
完成所有替换操作后，请你返回这个数组。

Example 1:
Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]

Constraints:
1 <= arr.length <= 10^4
1 <= arr[i] <= 10^5

****************************************************************************************************/


//Method1: 
/****************************************************************************************************
解题思路：
1）找到在数组中该元素 arr[i] 右边剩余元素的最大值 maxElement(arr, i + 1)。并赋予当前元素。
2）最后一个数组元素 arr[arr.length - 1] 赋值为－1 。
****************************************************************************************************/

class Solution {
    public int[] replaceElements(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            arr[i] = maxElement(arr, i + 1);
        }
        
        arr[arr.length - 1] = -1;
        
        return arr;
    }
    
    public int maxElement(int[] arr, int left) {
        int max = 0;
        
        for(int i = left; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        
        return max;
    }
}


/****************************************************************************************************/


//Method2:
/****************************************************************************************************
解题思路：
1）从右往左遍历数组，先记录右边最大值 rightMax 为最后一个值。
2）最后一个数组元素 arr[arr.length - 1] 赋值为－1 。
3）向左依次赋值，先使用变量 temp 先记住当前 arr[i]，再更新arr[i] ＝ rightMax。
4）如果当前temp比rightMax大，则更新最大值rightMax。
****************************************************************************************************/

class Solution {
    public int[] replaceElements(int[] arr) {
        int rightMax = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        
        for (int i = arr.length - 2; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = rightMax;
            if (temp > rightMax)
                rightMax = temp;
        }
        
        return arr;
    }
}

