package A003链表排队系列.A004判断链表是否是回文;

import A001递归和Master公式.ListNode;

import java.util.List;

/**
 * 题目：判断列表是否是回文
 * 思路：
 * 1.1：使用快慢指针
 * 1.2：快指针到end，慢指针正好在mid
 * 1.3：反转mid-end的链表
 * 1.4：从后从尾遍历链表判断
 * 1.5：恢复链表
 */
public class PalindromeLinkedList {
    public static boolean getPalindromeLinkedList(ListNode head) {
        if (head == null || head.nextNode == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        while (fast.nextNode != null && fast.nextNode.nextNode != null) {
            slow = slow.nextNode;
            fast = fast.nextNode.nextNode;
        }
        //反转mid-end链表
        //mid
        ListNode pre = slow;
        ListNode cur = pre.nextNode;
        ListNode next = null;
        pre.nextNode = null;
        while (cur != null) {
            next = cur.nextNode;
            cur.nextNode = pre;
            pre = cur;
            cur = next;
        }
        //反转mid-end链表
        //从后从尾遍历链表判断
        ListNode left = head;
        ListNode right = pre;
        boolean ans = true;
        while (left != null || right != null) {
            if (left.value != right.value) {
                ans = false;
                break;
            }
            left = left.nextNode;
            right = right.nextNode;
        }
        //恢复链表
        cur = pre.nextNode;
        next = null;
        pre.nextNode = null;
        while (cur != null) {
            next = cur.nextNode;
            cur.nextNode = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}

