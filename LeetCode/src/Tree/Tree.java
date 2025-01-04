package Tree;

import java.util.Stack;

public class Tree {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    //非递归先序遍历二叉树 头左右
    public void pre(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()){
            cur = stack.pop();
            System.out.println(cur.value + " ");
            if (cur.right!=null){
                stack.push(cur.right);
            }
            if (cur.left!=null){
                stack.push(cur.left);
            }
        }
    }
    //非递归先序遍历二叉树 头右左
    public void pre1(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()){
            cur = stack.pop();
            System.out.println(cur.value + " ");
            if (cur.left!=null){
                stack.push(cur.left);
            }
            if (cur.right!=null){
                stack.push(cur.right);
            }
        }
    }
    //非递归后序遍历二叉树 左右头
    public void pos(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(cur);
        while (!stack1.isEmpty()){
            cur = stack1.pop();
            stack2.push(cur);
            if (cur.left!=null){
                stack1.push(cur.left);
            }
            if (cur.right!=null){
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()){
            cur = stack2.pop();
            System.out.println(cur.value + " ");
        }
    }
    //非递归中序遍历二叉树 左头右
    public void in(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null){
           if (cur!=null){
               stack.push(cur);
               cur = cur.left;
           }else {
               cur = stack.pop();
               System.out.println(cur.value + " ");
               cur = cur.right;
           }
        }
    }

    public static void main(String[] args) {
        Tree tr = new Tree();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
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
        tr.pre(n1);
        //tr.pre1(n1);
        //tr.pos(n1);
        //tr.in(n1);
    }
}
























