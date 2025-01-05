package A003链表排队系列.A005链表排序;

import A001递归和Master公式.ListNode;
import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

/**
 * 排序链表
 * 要求时间复杂度O(n*logn)，额外空间复杂度O(1)，还要求稳定性
 * 数组排序做不到，链表排序可以
 * 时间复杂度O(n*logn)，额外空间复杂度O(1)，有稳定性
 * 注意为了额外空间复杂度O(1)，所以不能使用递归
 * 因为mergeSort递归需要O(log n)的额外空间
 */
public class SortList {
    ListNode head = new ListNode(100);

    public static ListNode sortList(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.nextNode;
        }
        ListNode l1, r1, l2, r2, next, lastTeamEnd;
        for (int step = 1; step < n; step <<= 1) {
            l1 = head;
            r1 = findEnd(l1,step);
            l2 = r1.nextNode;
            r2 = findEnd(l2,step);
            next = r2.nextNode;
            r1.nextNode = null;
            r2.nextNode = null;
            merge(l1,r1,l2,r2);
            head = start;
            lastTeamEnd = end;

            while (next!=null){
                l1 = next;
                r1 = findEnd(l1,step);
                l2 = r1.nextNode;
                if(l2 == null){
                    lastTeamEnd.nextNode = l1;
                    break;
                }
                r2 = findEnd(l2,step);
                next = r2.nextNode;
                r1.nextNode = null;
                r2.nextNode = null;
                merge(l1,r1,l2,r2);
                lastTeamEnd.nextNode = start;
                lastTeamEnd = end;
            }
        }
        return head;

    }
    public static ListNode start,end;
    private static void merge(ListNode l1, ListNode r1, ListNode l2, ListNode r2) {
        ListNode pre;
        //这是特殊处理设置头
        if (l1.value <= l2.value){
            start = l1;
            pre = l1;
            l1 = l1.nextNode;
        }else {
            start = l2;
            pre = l2;
            l2 = l2.nextNode;
        }
        while (l1 != null && l2 != null){
            if (l1.value <= l2.value){
                pre.nextNode = l1;
                pre = l1;
                l1 = l1.nextNode;
            }else {
                pre.nextNode = l2;
                pre = l2;
                l2 = l2.nextNode;
            }
        }
        if (l1!=null){
            pre.nextNode = l1;
            end = r1;
        }else {
            pre.nextNode = l2;
            end = r2;
        }
    }

    private static ListNode findEnd(ListNode l1, int step) {
        while (step--!=0 && l1!=null){
            l1 = l1.nextNode;
        }
        return l1;
    }
}
