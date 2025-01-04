package A002合并有序链表;

public class MergeList {
    public static void main(String[] args) {
        ListNode head = new ListNode(100, null);
        ListNode node1 = new ListNode(101, null);
        ListNode node2 = new ListNode(103, null);
        ListNode node3 = new ListNode(105, null);
        ListNode node4 = new ListNode(107, null);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        ListNode head2 = new ListNode(99, null);
        ListNode node12 = new ListNode(102, null);
        ListNode node22 = new ListNode(104, null);
        ListNode node32 = new ListNode(106, null);
        ListNode node42 = new ListNode(108, null);
        ListNode node52 = new ListNode(110, null);
        head2.setNext(node12);
        node12.setNext(node22);
        node22.setNext(node32);
        node32.setNext(node42);
        node42.setNext(node52);
        ListNode newNode = mergeList(head, head2);
        while (newNode != null) {
            ListNode cur = newNode;
            System.out.println(cur.getData());
            newNode = newNode.getNext();
        }
    }

    private static ListNode mergeList(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        //必定赋值一个链表
        ListNode head = head1.getData() >= head2.getData() ? head2 : head1;
        //上述赋值head的下一个
        ListNode cur1 = head.getNext();
        //另一个链表
        ListNode cur2 = head == head1 ? head2 : head1;

        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.getData() <= cur2.getData()) {
                pre.setNext(cur1);
                cur1 = cur1.getNext();
            } else {
                pre.setNext(cur2);
                cur2 = cur2.getNext();
            }
            pre = pre.getNext();
        }
        //循环退出，必然有一个为null了
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
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