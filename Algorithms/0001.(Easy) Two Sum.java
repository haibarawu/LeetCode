/****************************************************************************************************
1. Two Sum

Difficulty: Easy

Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
****************************************************************************************************/


//Java Solution 1:
/****************************************************************************************************
解题思路：
1）考察数组是否存在两个及以上元素，如果没有则返回 0。
2）创建哈希表，第一次循环，将每个元素的值 nums[i] 和它的索引位置 i 添加到哈希表<key, value>中。
3）第二次循环中，检查每个元素所对应的目标元素（target - nums[i]）是否存在于表中。
4）如果存在，并且目标元素不是 nums[i] 本身，则为结果。
****************************************************************************************************/


class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        
        if(nums.length < 2 || nums == null) {
            return result;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for(int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            
            if(map.containsKey(num) && map.get(num) != i) {
                result[0] = i;
                result[1] = map.get(num);
                return result;
            }
        }
        
        return result;
    }
}


/****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）在进行迭代并将元素插入到哈希表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
2）如果它存在，那我们已经找到了对应解，并立即将其返回。
3）如果不存在，就将当前元素加入哈希表中继续循环下一个元素。
****************************************************************************************************/


//Java Solution 2:
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int result[] = new int[2];
        
        if(nums == null || nums.length < 2){
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            else{
                map.put(nums[i], i);
            }
        }
        
        return result;
    }
}


/****************************************************************************************************/


//C++ solution:
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        
        vector<int> result;
        int length = sizeof(nums);
        for(int i = 0; i < length; i++){
            for(int j = i+1; j <= length; j++){
                if (nums[i] + nums[j] == target) {
                    //cout << "[" << i << "," << j << "]" << endl;
                    result.push_back(i);
                    result.push_back(j);
                    return result;
                } 
            }
        }
        cout << "no match\n";
        return result;
    }
};


