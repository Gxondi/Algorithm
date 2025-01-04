package Tree;

public class SuccessfulNode {
    public class node3{
        public int value;
        public node3 left;
        public node3 right;
        public node3 parent;

        public node3(int value) {
            this.value = value;
        }
    }
    public node3 getSuccessfulNode(node3 node){
        if (node == null){
            return null;
        }
        if (node.right!=null){
            return getMostLeft(node.right);
        }else {
            node3 parent = node.parent;
            while (parent!=null&&parent.right==node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private node3 getMostLeft(node3 node) {
        if (node.left == null){
            return node;
        }
        while(node.left!=null){
            node = node.left;
        }

        return node;
    }
}
