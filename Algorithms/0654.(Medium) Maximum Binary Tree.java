/****************************************************************************************************
654. Maximum Binary Tree

Difficulty: Medium

Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：

二叉树的根是数组中的最大元素。
左子树是通过数组中最大值左边部分构造出的最大二叉树。
右子树是通过数组中最大值右边部分构造出的最大二叉树。
通过给定的数组构建最大二叉树，并且输出这个树的根节点。

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1

Note:
The size of the given array will be in the range [1,1000].

****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）在nums[]数组中，使用findMaxIndex(nums, lIndex, rIndex)，找出当前index (left ~ right) 范围内的最大数值所在位置。
2）根据位置(index)创建二叉树的根，同样方法创建左子树，右子树。（在数组中，在根值左侧的数字为左子树，右侧为右子树。）
3）在数组中，使用maxTree(nums, lIndex, index - 1)继续找左子树数字的最大值，为根节点的左孩子。以此递归，创建左子树。
4）同样使用maxTree(nums, index + 1, rIndex)找出右子树数字的最大值，为根节点的右孩子。以此递归，创建右子树。
3）直到index到达临界值(lIndex > rIndex)，二叉树完成。
****************************************************************************************************/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int length = nums.length;
        return maxTree(nums, 0, length - 1);
    }
    
    public TreeNode maxTree(int[] nums, int lIndex, int rIndex) {
        if(lIndex > rIndex) {
            return null;
        }
        
        int index = findMaxIndex(nums, lIndex, rIndex);
        TreeNode root = new TreeNode(nums[index]);
        
        root.left = maxTree(nums, lIndex, index - 1);
        root.right = maxTree(nums, index + 1, rIndex);
        
        return root;
    }
    
    public int findMaxIndex(int[] nums, int lIndex, int rIndex) {
        int max = Integer.MIN_VALUE;
        int maxIndex = lIndex;
        
        for(int i = lIndex; i <= rIndex; i++) {
            if(max < nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
}

