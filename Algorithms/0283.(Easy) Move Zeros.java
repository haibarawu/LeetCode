/****************************************************************************************************
283. Move Zeros

Difficulty: Easy

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

Example:
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
****************************************************************************************************/


/****************************************************************************************************
解题思路01：
1）创建两个指针i和j，进行两次遍历。
2）第一次遍历的时候指针j用来记录当前有多少非0元素。即遍历的时候每遇到一个非0元素就将其往数组左边挪，第一次遍历完后，j指针的下标就指向了最后一个非0元素下标。
3）第二次遍历的时候，起始位置就从j开始到结束，将剩下的这段区域内的元素全部置为0。
****************************************************************************************************/


public class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        //第一次遍历，j指针记录非0的个数，只要是非0的统统都赋给nums[j]。
        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        //非0元素统计完了，剩下的都是0了。
		//所以第二次遍历把末尾的元素都赋为0即可。
        while(j < nums.length){
            nums[j] = 0;
            j++;
        }
    }
}


/****************************************************************************************************
解题思路02：
1）创建两个指针i和j，进行一次遍历。
2）j负责找到当前最靠前的0。只要nums[i]!=0，我们就交换nums[i]和nums[j]。
****************************************************************************************************/


public class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
			return;
		}
		//两个指针i和j
		int j = 0;
		for(int i = 0; i < nums.length; i++) {
			//当前元素 != 0，就把其交换到左边，等于0的交换到右边
			if(nums[i] != 0) {
				int tmp = nums[i];
				nums[i] = nums[j];
				nums[j] = tmp;
                j++;
			}
		}
    }
}


