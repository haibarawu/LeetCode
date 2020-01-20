/****************************************************************************************************
1038. Binary Search Tree to Greater Sum Tree

Difficulty: Medium

Given the root of a binary search tree with distinct values, 
modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.
val.

As a reminder, a binary search tree is a tree that satisfies these constraints:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

给出二叉搜索树的根节点，该二叉树的节点值各不相同，
修改二叉树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

提醒一下，二叉搜索树满足下列约束条件：
节点的左子树仅包含键小于节点键的节点。
节点的右子树仅包含键大于节点键的节点。
左右子树也必须是二叉搜索树。

Example 1:

     4
   /   \
  1     6
 / \   / \
0   2 5   7
     \     \
      3     8
Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]

    30
   /  \
 36    21
 / \   / \
36 35 26 15
     \     \
     33     8
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

Note:
The number of nodes in the tree is between 1 and 100.
Each node will have value between 0 and 100.
The given tree is a binary search tree.

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
    private int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        //右、根、左即为倒序
        if(root != null) {
            if(root.right != null) {
                bstToGst(root.right);
            }
            
            root.val += sum;
            sum = root.val;
            
            if(root.left != null) {
                bstToGst(root.left);
            }
        }
        
        return root;
    }
}

