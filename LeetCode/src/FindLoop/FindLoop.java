package FindLoop;

import javax.swing.*;
import javax.swing.text.rtf.RTFEditorKit;

//两个链表相交环
public class FindLoop {
    public class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    //获取第一个环的node
    /*
    * 找链表第一个环点思路
    * 使用快慢指针，因为是环表，循环到最后，快慢指针会相遇（循环遍历while退出条件是指针相等）
    * 然后快指针指向头部head，slow和fast都变成慢指针进行循环，当while(两个指针再次相等)退出的时候说明找到了第一个node
    * */
    public Node getLoopNode(Node head){
        if (head == null&& head.next == null && head.next.next == null){
            return null;
        }
        //fast slow point;
        Node cur = head;
        Node slow = cur.next;
        Node fast = cur.next.next;
        //slow = fast
        while (slow!=fast){
            if (fast.next == null && fast.next.next ==null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //fast = head begin while
        fast = head;
        //while slow = fast,first loopNode found;
        while (slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    //两个链表都无环，返回相交点
    //双链表无环相交思路
    /*
    * .     .
    *  .   .
    *    .
    *    .
    *    .
    * 因为是单链表，只有一个指针，如上图所示
    * cur1代表左链表循环到尾部，cur1.next不等于空，因此cur1是到了最后一个node，len记录遍历了几个node
    * 然后cur2开始循环遍历 len--；
    * 连个链表都循环到end并且值相等说明有相交点
    * len的差值大于零 说明 head1链表长
    * 长的链表用三元运算给cur1赋值
    * 长链表先循环len次（循环完和短链表一样长）
    * 此时双链表一起循环遍历当两个链表的地址相等的时候说明碰到了第一个node
    * */
    public Node noLoop(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int len = 0;
        while (cur1.next != null){
            len++;
            cur1 = cur1.next;
        }
        while (cur2.next!=null){
            len--;
            cur2 = cur2.next;
        }
        if (cur1!=cur2){return null;}
        cur1 = len > 0? head1:head2;
        cur2 = cur1 == head1?head2:head1;
        len = Math.abs(len);
        while (len!=0){
            len--;
            cur1 = cur1.next;
        }
        while (cur1!=cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
    //一个有环一个无环，是不可能相交的 最后一种可能就是两个都有环
    /*
    * 1.两个环在一个点上
    * cur1，cur2循环到这个点上，这个点也就是end点。
    * len大于0说明cur1的链表更长，如上述步骤一样
    * 2.loop1 开始 循环回来又到loop1的说明为不相交，循环过程中碰到loop2说明相交！
    *
    * */
    public Node bothLoop(Node head1 ,Node head2,Node loop1 , Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int len = 0;
            while (cur1!=loop1){
                len++;
                cur1 = cur1.next;
            }
            while (cur2!=loop2){
                len--;
                cur2 = cur2.next;
            }
            cur1 = len > 0?cur1:cur2;
            cur2 = cur1 == head1?head2:head1;
            len = Math.abs(len);
            while (len!=0){
                cur1 = cur1.next;
                len--;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while (cur1!=loop1){
                if (cur1 == loop2){
                    return  cur1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
    public Node getInstersectNdoe(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return  null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2==null){
            return noLoop(head1,head2);
        }
        if (loop1!=null&&loop2!=null){
            return bothLoop(head1,head2,loop1,loop2);
        }
        return null;
    }
}































