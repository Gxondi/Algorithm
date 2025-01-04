package A002排序.A005堆的排序;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请将所有链表合并到一个升序链表中，返回合并后的链表。
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MergeKLists {
    public static void main(String[] args) {

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        //小根堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode h : lists) {
            if(h!=null){
                heap.add(h);
            }
        }
        if (heap.isEmpty()){
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if(pre.next!=null){
            heap.add(pre.next);
        }
        while(!heap.isEmpty()){
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;

            if(cur.next!=null){
                heap.add(cur.next);
            }
        }
        return head;
    }
}
