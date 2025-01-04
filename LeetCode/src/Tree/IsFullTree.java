package Tree;
//
public class IsFullTree {
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
    public class info{
        public int height;
        public int nodes;

        public info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }
    public info IsFull(node CurNode){
        if (CurNode == null){
            return new info(0,0);
        }
        info getLeftInfo = IsFull(CurNode.left);
        info getRightInfo = IsFull(CurNode.right);
        int height = Math.max(getLeftInfo.height,getRightInfo.height) + 1;
        int nodes = getLeftInfo.nodes +getRightInfo.height+1;
        return new info(height,nodes);
    }
    public boolean test(node head){
        if (head == null){
            return false;
        }
        info all = IsFull(head);
        return (1<<all.height)-1 == all.nodes; //节点数 = 2的h次方-1
    }
}
