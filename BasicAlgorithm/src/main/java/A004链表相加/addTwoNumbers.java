package A004链表相加;

import A003分割链表.SplitList;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 例子：
 * 678
 * 链表格式：8->7->6
 * 个位十位百位依次
 * 方便进行下去
 */
public class addTwoNumbers {
    public static void main(String[] args) {
        //476
        SplitList.ListNode head = new SplitList.ListNode(9, null);
        SplitList.ListNode node1 = new SplitList.ListNode(9, null);
        SplitList.ListNode node2 = new SplitList.ListNode(9, null);
        SplitList.ListNode node3 = new SplitList.ListNode(9, null);
        SplitList.ListNode node4 = new SplitList.ListNode(9, null);
        SplitList.ListNode node5 = new SplitList.ListNode(9, null);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        //398
        SplitList.ListNode head2 = new SplitList.ListNode(9, null);
        SplitList.ListNode node12 = new SplitList.ListNode(9, null);
        SplitList.ListNode node22 = new SplitList.ListNode(9, null);
        SplitList.ListNode node33 = new SplitList.ListNode(9, null);
        head2.setNext(node12);
        node12.setNext(node22);
        node22.setNext(node33);
        SplitList.ListNode listNode = addTwoNumbers(head, head2);
        while (listNode != null) {
            SplitList.ListNode cur = listNode;
            System.out.println(cur.getData());
            listNode = listNode.getNext();
        }
    }

    static SplitList.ListNode addTwoNumbers(SplitList.ListNode head1, SplitList.ListNode head2) {
        SplitList.ListNode res = null, cur = null;
        int carry = 0;
        while (head1 != null || head2 != null) {
            int sum = (head1 == null ? 0 : head1.getData())
                    + (head2 == null ? 0 : head2.getData())
                    + carry;
            int val = sum % 10;
            carry = sum / 10;

            if(res == null){
                res = new SplitList.ListNode(val);
                cur = res;
            }else {
                cur.setNext(new SplitList.ListNode(val));
                cur = cur.next;
            }
            head1 = head1 == null?null:head1.getNext();
            head2 = head2 == null?null:head2.getNext();
        }
        if(carry == 1){
            cur.setNext(new SplitList.ListNode(1));
        }
        return res;
    }

}
