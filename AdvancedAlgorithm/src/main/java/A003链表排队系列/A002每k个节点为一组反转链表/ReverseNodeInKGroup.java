package A003链表排队系列.A002每k个节点为一组反转链表;

import A001递归和Master公式.ListNode;

/**
 * 题目：一个链表，k个数为一组，进行反转
 * 思路：
 * 1.1：判断链表长度是否能到k
 * 1.2：k长度的小链表进行反转
 * 1.3：小链表的头变成尾，尾要链接下一组小链表反转后的从尾变成的头
 * 1.4：循环上述操作
 */
public class ReverseNodeInKGroup {
    public static ListNode getReverseNodeInKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getTeamEnd(head, k);
        if (end == null) {
            return head;
        }
        //第一组反转的尾巴变成头部，也是整个大链表的头部
        head = end;
        reserve(start, end);
        //小链表内反转之后start变成end
        ListNode lastTeamEnd = start;
        //循环
        while (lastTeamEnd.nextNode != null) {
            //上一组反转后获取的尾链接着下一组的头
            start = lastTeamEnd.nextNode;
            end = getTeamEnd(start, k);
            if (end == null) {
                return head;
            }
            reserve(start, end);
            //上一组的尾巴连到这一组的头上去，end反转后变成头
            lastTeamEnd.nextNode = end;
            //小链表内反转之后start变成end
            lastTeamEnd = start;
        }
        return head;
    }

    /**
     * 反转过程中要记住下一个准备反转的小链表的头
     *
     * @param start
     * @param end
     */
    private static void reserve(ListNode start, ListNode end) {
        end = end.nextNode;

        ListNode pre = null;
        ListNode next = null;
        ListNode cur = start;

        while (cur.nextNode != end) {
            next = cur.nextNode;
            cur.nextNode = pre;
            pre = cur;
            cur = next;
        }
        //end就是下一组开头，需要链接上去
        start.nextNode = end;
    }

    private static ListNode getTeamEnd(ListNode head, int k) {
        while (k-- != 0 && head != null) {
            return head = head.nextNode;
        }
        return null;
    }


    public static void main(String[] args) {
        ListNode h1 = new ListNode();
        int k = 2;
        getReverseNodeInKGroup(h1, k);
    }
}
