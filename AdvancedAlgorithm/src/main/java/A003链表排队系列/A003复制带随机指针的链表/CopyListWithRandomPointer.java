package A003链表排队系列.A003复制带随机指针的链表;

import A001递归和Master公式.ListNode;

/**
 * 题目：复制带随机指针的链表
 * 思路：
 * 1.1：在旧链表的每个节点后面添加新节点（copy节点）
 * 1.2：利用上面构成的新老节点，设置新节点的random
 * 1.3：修复新老节点
 */
public class CopyListWithRandomPointer {
    public ListNode getCopyListWithRandomPointer(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode next = null;

        //在旧链表的每个节点后面添加新节点（copy节点）
        while (cur.nextNode != null) {
            next = cur.nextNode;
            cur.nextNode = new ListNode(cur.value);
            cur.nextNode.nextNode = next;
            cur = next;
        }
        cur = head;
        //利用上面构成的新老节点，设置新节点的random
        ListNode copy = null;
        while (cur != null) {
            next = cur.nextNode.nextNode;
            copy = cur.nextNode;
            copy.randomNode = cur.randomNode != null ? cur.randomNode.nextNode : null;
            cur = next;
        }
        //修复新老节点
        ListNode ans = head.nextNode;
        cur = head;
        while (cur != null) {
            //旧节点下一个
            next = cur.nextNode.nextNode;
            //新节点下一个
            copy = cur.nextNode;
            cur.nextNode = next;
            copy.nextNode = next != null ? next.nextNode : null;
            cur = next;
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
