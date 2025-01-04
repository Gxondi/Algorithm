package Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeToBinary {
    public class node2{
        public int value;
        public List<node2> children;

        public node2(int value) {
            this.value = value;
        }

        public node2(int value, List<node2> children) {
            this.value = value;
            this.children = children;
        }
    }
    public class treeNode{
        public int value;
        treeNode left;
        treeNode right;

        public treeNode(int value) {
            this.value = value;
        }
    }
    public treeNode encode(node2 root){
        if (root == null)return null;
        treeNode head = new treeNode(root.value);
        head.left = en(root.children);
        return head;
    }

    private treeNode en(List<node2> children) {
        treeNode head = null;
        treeNode cur = null;
        for (node2 child : children) {
            treeNode tnode = new treeNode(child.value);
            if (head == null){
                head = tnode;
            }else {
                head.right = tnode;
            }
            cur = tnode;
            cur.left = en(child.children);
        }
        return head;
    }
    private node2 decode(treeNode root){
        if (root == null)return null;

        return new node2(root.value,de(root.left));
    }

    private List<node2> de(treeNode root) {
        List<node2> children = new ArrayList<>();
        while (root!=null){
            node2 cur = new node2(root.value,de(root.left));
            children.add(cur);
            root = root.right;
        }
        return children;
    }
}
