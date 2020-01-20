/****************************************************************************************************
938. Range Sum of BST

Difficulty: Easy

https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/

Given the root node of a binary search tree, 
return the sum of values of all nodes with value between L and R (inclusive).
The binary search tree is guaranteed to have unique values.

给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
二叉搜索树保证具有唯一的值。

Example 1:
Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

Example 2:
Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23

Note:
The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.

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


//Method1:
class Solution {
    private int result = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {

        if(root == null) {
            return 0;
        }
        
        if(root.val >= L && root.val <= R) {
            result += root.val;
        }
        
        rangeSumBST(root.left, L, R);
        rangeSumBST(root.right, L, R);
        
        return result;
    }
}


//Method2: 
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        
        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }
        
        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }
        
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }
}

