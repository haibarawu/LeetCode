/**************************************************************************************************** 
102. Binary Tree Level Order Traversal

Difficulty: Medium

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
****************************************************************************************************/


/**************************************************************************************************** 
解题思路：
1）采用深度优先遍历，从根遍历，层数设置为从 0 开始，将每一个元素存入它所在层数的数组中。
2）如果当前元素所在层的数组还不存在，则为当前层数创建一个动态数组。
3）将遍历到的当前值存入它所在层数的数组中。
4）向下遍历元素的左右孩子。
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        DFS(root, 0, result);
        return result;
    }
    
    public void DFS(TreeNode root, int level, List<List<Integer>> result) {
        if(root == null) {
            return;
        }
        
        //如果当前层数还不存在，则为当前层创建新的空动态数组
        if(result.size() <= level) {
            result.add(new ArrayList<>());
        }
        
        //将当前值加入当前层的动态数组中
        result.get(level).add(root.val);
        
        //遍历左右孩子
        DFS(root.left, level + 1, result);
        DFS(root.right, level + 1, result);
    }
}


