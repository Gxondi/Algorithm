package A001单双链表反转;

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode node4 = new ListNode(104,null);
        ListNode node3 = new ListNode(103,node4);
        ListNode node2 = new ListNode(102,node3);
        ListNode node1 = new ListNode(101,node2);
        ListNode head = new ListNode(100,node1);
        ListNode temp = head;
        while(temp!=null){
            ListNode currentNode = temp;
            System.out.println(currentNode.getData());
            temp = temp.getNext();
        }
        ListNode reversedList = reverseSingleList(head);
        System.out.println("#################################################");
        while(reversedList!=null){
            ListNode currentNode = reversedList;
            System.out.println(currentNode.data);
            reversedList = reversedList.next;
        }

        DoubleNode doubleHead = new DoubleNode(1000, null, null);
        DoubleNode doubleNode1 = new DoubleNode(1001, doubleHead, null);
        DoubleNode doubleNode2 = new DoubleNode(1002, doubleNode1, null);
        DoubleNode doubleNode3 = new DoubleNode(1003, doubleNode2, null);
        DoubleNode doubleNode4 = new DoubleNode(1004, doubleNode3, null);
        doubleHead.next = doubleNode1;
        doubleNode1.next = doubleNode2;
        doubleNode2.next = doubleNode3;
        doubleNode3.next = doubleNode4;
        /**
         * 双链表反转
         */
        DoubleNode doubleNode = reverseDoubleList(doubleHead);
        System.out.println("#################################################");
        while(doubleNode!=null){
            DoubleNode currentNode = doubleNode;
            System.out.println(currentNode.data);
            doubleNode = doubleNode.next;
        }
    }

    private static ListNode reverseSingleList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        ListNode currentNode = head;
        while(currentNode!=null){
            next = currentNode.next;
            currentNode.next = pre;
            pre = currentNode;
            currentNode = next;
        }
        return pre;
    }
    private static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode currentNode = head;
        DoubleNode pre = null;
        DoubleNode next = null;
        while(currentNode!=null){
            next = currentNode.next;
            currentNode.next = pre;
            pre = currentNode;
            currentNode.pre = next;
            currentNode = next;
        }
        return pre;
    }
    /**
     * 单链表
     */
    public static class ListNode {
        public Integer data;
        public ListNode next;
        public ListNode(){

        }
        public ListNode(Integer data){
            this.data = data;
        }
        public ListNode(Integer data,ListNode node){
            this.data = data;
            this.next = node;
        }
        public int size(){
            int count = 0;
            while(next!=null){
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

    /**
     * 双链表
     */
    public static class DoubleNode {
        public Integer data;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(Integer data){
            this.data = data;
        }
        public DoubleNode(Integer data,DoubleNode preNode,DoubleNode nextNode){
            this.data = data;
            this.pre = preNode;
            this.next = nextNode;
        }
    }
}

