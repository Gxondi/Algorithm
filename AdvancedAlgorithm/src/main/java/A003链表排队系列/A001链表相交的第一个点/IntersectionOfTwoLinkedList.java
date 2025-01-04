package A003链表排队系列.A001链表相交的第一个点;

/**
 * 题目：返回两个无环链表的第一个节点
 * <p>
 * 思路1：使用额外容器。
 * 1.1 hashSet，将一个链表节点地址逐个放入hash中，判断另一个链表节点地址是否存在于hash表中。
 * <p>
 * 思路2：使用链表差值
 * 2.1 循环链表获取长度
 * 2.2 长的减去短的，求出插值
 * 2.3 长的减去插值，最终长度一致
 * 2.4 循环遍历双列表，相等返回
 */
public class IntersectionOfTwoLinkedList {
    public static class ListNode {
        public int value;
        public ListNode nextNode;
    }

    public static ListNode getIntersectionOfTwoLinkedList(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) {
            return null;
        }
        ListNode a = h1, b = h2;
        int diff = 0;
        while (h1.nextNode != null) {
            a = h1.nextNode;
            diff++;
        }
        while (h2.nextNode != null) {
            b = h2.nextNode;
            diff--;
        }
        //判断最后节点是否相交
        if (a != b) {
            return null;
        }
        //判断那个长哪个短
        if (diff >= 0) {
            a = h1;
            b = h2;
        } else {
            a = h2;
            b = h1;
        }
        diff = Math.abs(diff);
        while (diff-- != 0) {
            a = a.nextNode;
        }
        while (a!=b) {
            a = a.nextNode;
            b = b.nextNode;
        }
        return a;
    }

    public static void main(String[] args) {
        ListNode h1 = new ListNode();
        ListNode h2 = new ListNode();
        IntersectionOfTwoLinkedList.getIntersectionOfTwoLinkedList(h1,h2);
    }
}
