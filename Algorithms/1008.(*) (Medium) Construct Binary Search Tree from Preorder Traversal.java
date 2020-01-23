/****************************************************************************************************
1008. Construct Binary Search Tree from Preorder Traversal

Difficulty: Medium

Return the root node of a binary search tree that matches the given preorder traversal.
(Recall that a binary search tree is a binary tree where for every node, any descendant of node.
left has a value < node.val, and any descendant of node.right has a value > node.val.  
Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
(回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，
对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。
此外，先序遍历首先显示节点的值，然后遍历 node.left，接着遍历 node.right。）

Example 1:
Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
    8
   / \
  5   10
 / \   \
1   7   12

Note: 
1 <= preorder.length <= 100
The values of preorder are distinct.

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
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length == 0) {
            return null;
        }
        
        return traversal(preorder, 0, preorder.length - 1);
    }

    private TreeNode traversal(int[] preorder, int left, int right) {
        //当index遍历完没有找到大于preorder[left]时，会出现left > right的情况。
        //这时候判定为叶子节点，直接返回null。
        if(left > right) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[left]);
        int index = left + 1;
        
        //找到大于根节点的第一个值索引index
        while(index < preorder.length && preorder[index] < preorder[left]) {
            index++;
        }
        
        root.left = traversal(preorder, left + 1, index - 1);
        root.right = traversal(preorder, index, right);
        
        return root;
    }
}




