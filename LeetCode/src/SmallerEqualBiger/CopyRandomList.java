package SmallerEqualBiger;

import java.util.HashMap;

public class CopyRandomList {
    public class node{
        public int value;
        public node next;
        public node random;
        public node(int value) {
            this.value = value;
        }
    }
    public node copylist(node head){
        HashMap<node,node> map = new HashMap<>();
        node cur = head;
        while (cur!=null){
            map.put(cur,new node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur!=null){
            //cur 老
            //map.get(cur) 新
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
    public node copyLsit(node head){
        node cur = head;
        node next = null;
        // 1 -> 2 -> 3 -> null
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        while (cur!=null){
            next = cur.next;
            cur.next = new node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        node copy = null;
        while (cur!=null){
            next = cur.next.next;
            copy = cur.next;
            copy.random = copy.random!=null?cur.random.next:null;
            cur = next;
        }
        node res = head.next;
        cur = head;
        while (cur!= null){
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null?next.next:null;
            cur = next;
        }
        return res;
    }
}
