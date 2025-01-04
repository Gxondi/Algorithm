package Tree;

import sun.reflect.annotation.AnnotationSupport;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;

public class BFS {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }
    }
    //头左右
    public void BFS(Node head){
        if (head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value + " ");
            if (cur.left!=null){
                queue.add(cur.left);
            }
            if (cur.right!=null){
                queue.add(cur.right);
            }
        }
    }
    //先序序列化传入队列中
    public Queue<String> preSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        pres(head,ans);
        return ans;
    }

    private void pres(Node head, Queue<String> ans) {
        if (head == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }
    //先序反序列化，从队列变成二叉树
    public Node buildByPreQue(Queue<String> prelist){
        if (prelist == null || prelist.size() == 0){
            return null;
        }
        return preb(prelist);
    }

    private Node preb(Queue<String> prelist) {
        String value = prelist.poll();
        if (value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }
    //广度优先遍历->序列化
    public Queue<String> BFSSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        if (head == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){
                head = queue.poll();
                if (head.left != null){
                    queue.add(head.left);
                    ans.add(String.valueOf(head.left.value));
                }else {
                    ans.add(null);
                }
                if (head.right != null){
                    queue.add(head.right);
                    ans.add(String.valueOf(head.right.value));
                }else {
                    ans.add(null);
                }
            }
        }
        return  ans;
    }
    //广度优先遍历->反序列化
    public Node BuildByBFS(Queue<String> ans){
        if (ans == null || ans.size() == 0){
            return null;
        }
        Node head = generatenode(ans.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null){
            queue.add(head );
        }
        Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = generatenode(ans.poll());
            node.right = generatenode(ans.poll());
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
        return head;
    }

    private Node generatenode(String poll) {
        if (poll == null){
            return null;
        }
        Node node = new Node(Integer.valueOf(poll));
        return node;
    }

    public static void main(String[] args) {
        BFS bfs = new BFS();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        //Node n3 = new Node(3);
        Node n3 = null;
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        //Node n6 = new Node(6);
        Node n6 = null;
        Node n7 = new Node(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        //n3.left = n6;
        //n3.right = n7;
        n4.left = null;
        n4.right = null;
        n5.left = null;
        n5.right = null;
        //n6.left = null;
        //n6.right = null;
        n7.left = null;
        n7.right = null;
        //bfs.BFS(n1);
        Queue<String> queue = bfs.BFSSerial(n1);
        for (String s : queue) {
            System.out.print(s+ " ");
        }
    }
}
