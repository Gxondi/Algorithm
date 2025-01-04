package Tree;

public class BinarySearchTree {
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

    public class info {
        public boolean isBST;
        public int max;
        public int min;

        public info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    //判断是否是搜索二叉树
    /*
     * 当前节点的左树小于当前节点，右树大于当前节点
     * */
    public info isBST(node5 curNode) {
        if (curNode == null) {
            return null;
        }
        info getLeftInfo = isBST(curNode.left);
        info getRightInfo = isBST(curNode.right);
        int max = curNode.value; //找到最大值
        if (getLeftInfo != null) {
            Math.max(max, getLeftInfo.max);
        }
        if (getRightInfo != null) {//找到最大值
            Math.max(max, getRightInfo.max);
        }
        int min = curNode.value;//找到最小值
        if (getLeftInfo != null) {
            Math.min(max, getLeftInfo.min);
        }
        if (getRightInfo != null) {//找到最小值
            Math.min(max, getRightInfo.min);
        }
        boolean isBST = true;
        if (getLeftInfo!=null&&!getLeftInfo.isBST){ //左树违反
            isBST = false;
        }
        if (getRightInfo!=null&&!getRightInfo.isBST){//右树违反
            isBST = false;
        }
        if (getLeftInfo.max >= curNode.value&&getLeftInfo!=null){//当前节点左树中最大值 不小于 当前节点
            isBST = false;
        }
        if (getRightInfo.min <= curNode.value&&getLeftInfo!=null){//当前节点右树中最小值 不大于 当前节点
            isBST = false;
        }
        return new info(isBST,max,min);
    }
}
