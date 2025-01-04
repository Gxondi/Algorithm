package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BestWidth {
    public static class node{
        public int value;
        public node left;
        public node right;

        public node() {
        }

        public node(int value) {
            this.value = value;
        }
    }
    public static int MaxWidth(node head){
        if (head == null){
            return 0;
        }
        Queue<node> queue = new LinkedList<>();
        queue.add(head);
        int max = 0;
        int curLevel = 1; //当前层
        int curLevelNode = 0; // 当前层node个数
        node curend = head;
        node nextend = null;
        while (!queue.isEmpty()){
            node cur = queue.poll();
            if (cur.left!=null){
                nextend = cur.left;
                queue.add(cur.left);
            }
            if (cur.right!=null){
                nextend = cur.right;
                queue.add(cur.right);
            }
            curLevelNode++;
            if (cur == curend){
                max = Math.max(max,curLevelNode);
                curLevelNode = 0;
                curend = nextend;
            }
        }
        return max;
    }
    public static int MaxWidthUseMap(node head){
        if (head == null){
            return 0;
        }
        Queue<node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<node,Integer> map = new HashMap<>();
        map.put(head,1);
        int max = 0;
        int curLevel = 1; //当前层
        int curLevelNode = 1; // 当前层node个数
        while(!queue.isEmpty()) {
            node cur = queue.poll();
            int curNodeLevel = map.get(cur);//get cur node level
            if (cur.left != null) {
                map.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                map.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curLevel == curNodeLevel) {
                curLevelNode++;
            } else {
                max = Math.max(max, curLevelNode);
                curLevel++;
                curLevelNode = 1;
            }
        }
        max = Math.max(max, curLevelNode);
        return max;
    }

    public static void main(String[] args) {

        node n1 = new node(1);
        node n2 = new node(2);
        node n3 = new node(3);
        node n4 = new node(4);
        node n5 = new node(5);
        node n6 = new node(6);
        node n7 = new node(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = null;
        n4.right = null;
        n5.left = null;
        n5.right = null;
        n6.left = null;
        n6.right = null;
        n7.left = null;
        n7.right = null;
        BestWidth b = new BestWidth();

        System.out.println(b.MaxWidthUseMap(n1));
        System.out.println(b.MaxWidth(n1));

    }
}






























