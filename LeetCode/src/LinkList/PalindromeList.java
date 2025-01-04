package LinkList;

import java.util.Stack;
//判断链表是否是回文
//1.使用栈
public class PalindromeList {
    public static boolean IsPaLindRomeList(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(cur!=null){
            stack.push(cur);
            cur = cur.getNext();
        }
        while(head!=null){
            if(head.getValue() != stack.pop().getValue()){
                return false;
            }
            head = head.getNext();
        }
        return true;
    }
    //2.使用反转链表
    public static boolean IsPaLindRomeList2(Node head){
        Node slow = head;
        Node fast = head;
        //次循环结束，slow位于中点（奇数）
        while (fast.getNext()!=null&&fast.getNext().getNext()!=null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        //把中点以后的链表反转
        Node n3 = null;//next node
        fast = slow.getNext(); //right part first node;
        slow.setNext(null);//mid.next = null
        while (fast!=null){ //reserve right part node
            n3 = fast.getNext();//get cur node's next node
            fast.setNext(slow);//set cur node's next
            slow = fast;//move next node
            fast = n3;//move next node
        }

        n3 = slow;//save last node
        fast = head;//left part first node
        boolean res = true;
        while (slow!=null&&fast!=null){
            if(slow.getValue()!= fast.getValue()){
                res = false;
                break;
            }
            slow = slow.getNext();
            fast = fast.getNext();
        }
        //recover list
        slow = n3.getNext(); //last node's next(is reserve list condition)
        n3.setNext(null);//last node's next = null
        while (slow!=null){
            fast = slow.getNext();//fast = get cur node's next node
            slow.setNext(n3);
            n3 = slow;
            slow = fast;
        }
        return res;
    }
    public static void main(String[] args) {
        Node l1 = new Node(1);
        Node l2 = new Node(2);
        Node l3 = new Node(3);
        Node l4 = new Node(2);
        Node l5 = new Node(1);
        Node head = new Node();
        l1.setNext(l2);
        l2.setNext(l3);
        l3.setNext(l4);
        l4.setNext(l5);
        l5.setNext(null);
        head.setNext(l1);
        PalindromeList p = new PalindromeList();

        System.out.println( p.IsPaLindRomeList(l1));
        System.out.println( p.IsPaLindRomeList2(l1));
    }
}
