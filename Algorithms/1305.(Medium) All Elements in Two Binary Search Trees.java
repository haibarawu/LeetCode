/****************************************************************************************************
1305. All Elements in Two Binary Search Trees

Difficulty: Medium

https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/

Given two binary search trees root1 and root2.
Return a list containing all the integers from both trees sorted in ascending order.

给你 root1 和 root2 这两棵二叉搜索树。
请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.

Example 1:
  2      1
 / \    / \
1   4  0   3
Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]

Example 2:
Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]

Example 3:
Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]

Example 4:
Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]

Example 5:
Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]

Constraints:
Each tree has at most 5000 nodes.
Each node's value is between [-10^5, 10^5].

****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）遍历两个二叉树，将所有元素数值存入ArrayList。
2）使用Collections.sort() 排序ArrayList中所有数值。
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
    private List<Integer> result = new ArrayList<>();
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        DFS(root1);
        DFS(root2);
        
        Collections.sort(result);
        
        return result;
    }
    
    public void DFS(TreeNode node) {
        if(node == null) {
            return;
        }
        
        result.add(node.val);
        DFS(node.left);
        DFS(node.right);
    }
}

