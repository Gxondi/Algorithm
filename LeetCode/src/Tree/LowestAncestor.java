package Tree;

import java.util.HashMap;
import java.util.HashSet;
//在二叉树中找到两个节点的最近公共祖先
public class LowestAncestor {
    public static class node5 {
        public int value;
        public node5 left;
        public node5 right;

        public node5(int value, node5 left, node5 right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public node5(int value) {
            this.value = value;
        }
    }
    public node5 lowestancestor(node5 head,node5 o1,node5 o2){
        if (head == null){
            return null;
        }
        HashMap<node5,node5> parentMap = new HashMap<>();
        parentMap.put(head,null);
        fillparentNode(head,parentMap);
        HashSet<node5> o1set = new HashSet<>();
        node5 cur = o1;
        o1set.add(cur);
        if (parentMap.get(cur)!=null){
            cur = parentMap.get(cur);
            o1set.add(cur);
        }
        cur = o2;
        while (!o1set.contains(cur)){
            cur = parentMap.get(cur);
        }
        return cur;
    }
    private void fillparentNode(node5 head, HashMap<node5,node5> parentMap) {
        if (head.left != null){
            parentMap.put(head.left,head);
            fillparentNode(head.left,parentMap);
        }
        if (head.right != null){
            parentMap.put(head.right,head);
            fillparentNode(head.right,parentMap);
        }
    }
    public class info{
        boolean findA;
        boolean findB;
        node5 ans;

        public info(boolean findA, boolean findB, node5 ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }
    public info lowestancestor1(node5 head,node5 a,node5 b){
        if (head == null){
            return new info(false,false,null);
        }
        info leftInfo = lowestancestor1(head.left,a,b);
        info rightInfo = lowestancestor1(head.right,a,b);
        boolean findA = (head == a)||leftInfo.findA||rightInfo.findA;
        boolean findB = (head == b)||leftInfo.findB||rightInfo.findB;

        node5 ans = null;
        if (leftInfo.ans!=null){
            ans = leftInfo.ans;
        }else if(rightInfo.ans!=null){
            ans = rightInfo.ans;
        }else {
            if (findA&&findB){
                ans = head;
            }
        }
        return new info(findA,findB,ans);
    }
}
