/****************************************************************************************************
1325. Delete Leaves With a Given Value
1325. 删除给定值的叶子节点

Difficulty: Medium

Given a binary tree root and an integer target, delete all the leaf nodes with value target.
Note that once you delete a leaf node with value target, 
if it's parent node becomes a leaf node and has the value target, 
it should also be deleted (you need to continue doing that until you can't).

给你一棵以 root 为根的二叉树和一个整数 target ，请你删除所有值为 target 的 叶子节点 。
注意，一旦删除值为 target 的叶子节点，它的父节点就可能变成叶子节点；如果新叶子节点的值恰好也是 target ，那么这个节点也应该被删除。
也就是说，你需要重复此过程直到不能继续删除。

Example 1:
    1               1            1
   / \             / \            \
  2   3     =>    2   3     =>     3
 /   / \               \            \
2   2   4               4            4

Input: root = [1,2,3,2,null,2,4], target = 2
Output: [1,null,3,null,4]
Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left). 
After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).

Example 2:
    1                1
   / \              /
  3   3     =>     3
 / \                \
3   2                2
Input: root = [1,3,3,3,2], target = 3
Output: [1,3,null,null,2]

Example 3:
Input: root = [1,2,null,2,null,2], target = 2
Output: [1]
Explanation: Leaf nodes in green with value (target = 2) are removed at each step.

Example 4:
Input: root = [1,1,1], target = 1
Output: []

Example 5:
Input: root = [1,2,3], target = 1
Output: [1,2,3]

Constraints:
1 <= target <= 1000
Each tree has at most 3000 nodes.
Each node's value is between [1, 1000].

****************************************************************************************************/


/********************************************************************************
关于树的题目多数要考虑使用dfs或者bfs来解题，本题需要删除某些满足条件的叶子节点，即我们需要遍历所有路径找到叶子节点，因此dfs应是最优选择。

解题思路：
1）先判断当前节点，如果为空，直接返回空。
2）递归左右子节点到其叶子节点，如果当前叶子节点的值等于 target ，说明当前叶子节点需要被删除，返回空，否则返回该叶子节点，并回到上一层递归。
3）在上一层递归中判断当前节点是否变成了叶子节点，如果是，则需判断当前节点的值是否等于target，如果是也应被删除掉，返回空，否则返回当前节点。
********************************************************************************/


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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) {
            return null;
        }
        
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        
        if(root.left == null && root.right == null && root.val == target) {
            root = null;
        }
        
        return root;
    }
}


