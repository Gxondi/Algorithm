package Tree;

public class BestDPTree {
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
        public int maxDistance;
        public int height;

        public info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }
    public info MaxDistance(node curNode){
        if (curNode == null){
            return new info(0,0);
        }
        info getLeftNode = MaxDistance(curNode.left);
        info getRightNode = MaxDistance(curNode.right);
        int height = Math.max(getLeftNode.height,getRightNode.height) + 1;
        int p1 = getLeftNode.maxDistance;
        int p2 = getRightNode.maxDistance;
        int p3 = getLeftNode.maxDistance + getRightNode.maxDistance + 1;
        int MaxDistance = Math.max(p1,Math.max(p2,p3));
        return new info(MaxDistance,height);
    }
}
























