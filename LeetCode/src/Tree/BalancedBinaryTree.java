package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BalancedBinaryTree {
    public static class node4{
        public int value;
        public node4 left;
        public node4 right;

        public node4(int value, node4 left, node4 right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public node4(int value) {
            this.value = value;
        }
    }
    //完全二叉树
    /*
    * 二叉树左null,右不null直接返回false
    *左右不双全，剩下节点必须是叶节点
    * */
    public boolean CompleteBinaryTree1(node4 head){
        if (head == null){
            return true;
        }
        Queue<node4> queue = new LinkedList<>();
        boolean leaf = false;
        node4 l = null;
        node4 r = null;
        queue.add(head);
        while (!queue.isEmpty()){
            node4 cur = queue.poll();
            l = cur.left;
            r = cur.right;
            if ((l==null&&r!=null) || leaf&&(l!=null||r!=null)){
                return false;
            }
            if (l != null){
                queue.add(l);
            }
            if (r != null){
                queue.add(r);
            }
            if (l ==null||r==null){
                leaf = true;
            }
        }
        return leaf;
    }
    //平衡二叉树
    /*
    * 什么是平衡二叉树？当前节点左树最大深度与右树最大深度的差绝对值不超过1
    * */
    public class info{
        boolean isBalance;
        int height;

        public info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }
    public info isBalancedBinaryTree2(node4 head){
        if (head == null){
            return new info(true,0);
        }
        //假设得到左右info
        info getLeftInfo =  isBalancedBinaryTree2(head.left);
        info getRightInfo =  isBalancedBinaryTree2(head.right);
        boolean isBalanced = true;
        //从info得到左右树的高度
        int height = Math.max(getLeftInfo.height,getRightInfo.height)+1;
        if (!getLeftInfo.isBalance){
            isBalanced = false;
        }
        if (!getRightInfo.isBalance){
            isBalanced = false;
        }
        if (Math.abs(getLeftInfo.height - getRightInfo.height)>1){
            isBalanced = false;
        }
        return new info(isBalanced,height);
    }

    public static void main(String[] args) {
        BalancedBinaryTree b = new BalancedBinaryTree();
        node4 n1 = new node4(1);
        node4 n2 = new node4(2);
        node4 n3 = new node4(3);
        node4 n4 = new node4(4);
        node4 n5 = new node4(5);
        node4 n6 = new node4(6);
        node4 n7 = new node4(7);
        node4 n8 = new node4(8);
        node4 n9 = new node4(9);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = null;
        n4.right = n9;
        n5.left = null;
        n5.right = null;
        n6.left = null;
        n6.right = null;
        n7.left = null;
        n7.right = null;
        n8.left = null;
        n8.right = null;
        n9.left = null;
        n9.right = null;
        System.out.println(b.CompleteBinaryTree1(n1) );
        System.out.println(b.isBalancedBinaryTree2(n1).isBalance);
    }
}


















