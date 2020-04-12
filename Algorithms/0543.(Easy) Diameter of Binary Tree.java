/****************************************************************************************************
543. Diameter of Binary Tree

Difficulty: Easy

Given a binary tree, you need to compute the length of the diameter of the tree. 
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
This path may or may not pass through the root.

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）二叉树的直径一定是经过某一点的左右两子树深度之和，所以我们遍历所有节点，并维护一个最大值，就可以找到答案。
3）深度优先遍历求每个节点的左右两子树的深度，得到每个节点的深度就是左右两子树深度更大的那个再+1。
3）维护一个左右两子树深度之和的最大值，这个值就是该二叉树的直径了。

1）定义一个全局变量 result，用来记录最大直径。
2）使用 depth(node) 遍历所有的节点，depth(node) 的作用是：找出以 node 为根节点的二叉树的最大深度。
3）node 为跟节点的最大深度为 Math.max(leftDepth,rigthDepth) + 1。
4）result 取值为以经过 node，左右子树的最大深度之和 leftDepth + rigthDepth。
5）通过递归，找到 result 的最大值。
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
    int result;
    public int diameterOfBinaryTree(TreeNode root) {
        result = 0;
        depth(root);
        return result;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = depth(node.left);
        int R = depth(node.right);
        result = Math.max(result, L+R);
        return Math.max(L, R) + 1;
    }
}


