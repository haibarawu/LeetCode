/****************************************************************************************************
445. Add Two Numbers II
445. 两数相加 II

Difficulty: Medium

You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
你可以假设除了数字 0 之外，这两个数字都不会以零开头。

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

进阶：
如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
****************************************************************************************************/


/****************************************************************************************************
解题思路：
反转两个链表。进行加法运算。再反转一遍结果链表。
****************************************************************************************************/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode nl1 = reverseList(l1);
        ListNode nl2 = reverseList(l2);
        //进位
        int carry = 0;
        //新结果链表的临时头。
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while(nl1 != null || nl2 != null || carry != 0) {
            //反转后的链表取值并求和
            int n1 = nl1 == null ? 0 : nl1.val;
            int n2 = nl2 == null ? 0 : nl2.val;
            //指向下一个节点
            nl1 = nl1 == null ? nl1 : nl1.next;
            nl2 = nl2 == null ? nl2 : nl2.next;
            //将当前节点的和连接到 next
            int num = (n1 + n2 + carry) % 10;
            ListNode node = new ListNode(num);
            cur.next = node;
            cur = node;
            //保存需要进位的值，进入下一轮节点值加法中。
            carry = (n1 + n2 + carry) / 10;
        }
        //反转结果链表
        ListNode result = reverseList(head.next);
        return result;
    }
    
    
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode cur = head;
        ListNode pre = null;
        ListNode nxt = null;
        
        while (cur != null) {
            //记录当前节点的下一个节点
            nxt = cur.next;
            //当前节点指向pre
            cur.next = pre;
            //pre和cur移动到下一个节点
            pre = cur;
            cur = nxt;
        }
        return pre;
    } 
}


