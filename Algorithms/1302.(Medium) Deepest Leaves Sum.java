/****************************************************************************************************
1302. Deepest Leaves Sum

Difficulty: Medium

Given a binary tree, return the sum of values of its deepest leaves.

Example 1:
      1
     / \
    2   3
   / \   \
  4   5   6
 /         \
7           8

Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15

Constraints:
The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.

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
    public int deepestLeavesSum(TreeNode root) {
        int maxDepth = maxDepth(root);
        return deepestLeavesSumWithDepth(root, maxDepth);
    }
    
    // find the maximum depth
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    
    // compute the sum of deepest leaves with the tree depth
    public int deepestLeavesSumWithDepth(TreeNode root, int depth) {
        if(root == null) return 0;
        if(depth == 1) return root.val;
        
        return deepestLeavesSumWithDepth(root.left, depth-1) + deepestLeavesSumWithDepth(root.right, depth-1);
    }
}

