package A003分割链表;
//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//你应当 保留 两个分区中每个节点的初始相对位置。

public class SplitList {
    public static void main(String[] args) {
        ListNode head = new ListNode(6, null);
        ListNode node1 = new ListNode(5, null);
        ListNode node2 = new ListNode(3, null);
        ListNode node3 = new ListNode(4, null);
        ListNode node4 = new ListNode(2, null);
        ListNode node5 = new ListNode(1, null);
        ListNode node6 = new ListNode(1, null);
        ListNode node7 = new ListNode(7, null);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        ListNode listNode = splitList(4, head);

        while (listNode != null) {
            ListNode cur = listNode;
            System.out.println(cur.getData());
            listNode = listNode.getNext();
        }
    }

    static ListNode splitList(int x, ListNode head) {
        ListNode leftHead = null, leftTail = null;
        ListNode rightHead = null, rightTail = null;
        ListNode next = null;
        ListNode cur = head;
        while (cur != null) {
            //保存下个节点
            next = cur.getNext();
            cur.setNext(null);
            if (cur.getData() < x) {
                if (leftHead == null) {
                    leftHead = cur;
                } else {
                    leftTail.setNext(cur);
                }
                leftTail = cur;
            } else {
                if (rightHead == null) {
                    rightHead = cur;
                } else {
                    rightTail.setNext(cur);
                }
                rightTail = cur;
            }
            cur = next;
        }
        if(leftHead == null){
            return rightHead;
        }
        leftTail.setNext(rightHead);
        return leftHead;
    }

    public static class ListNode {
        public Integer data;
        public ListNode next;

        public ListNode() {

        }

        public ListNode(Integer data) {
            this.data = data;
        }

        public ListNode(Integer data, ListNode node) {
            this.data = data;
            this.next = node;
        }

        public int size() {
            int count = 0;
            while (next != null) {
                next = next.next;
                count++;
            }
            return count;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }


}
