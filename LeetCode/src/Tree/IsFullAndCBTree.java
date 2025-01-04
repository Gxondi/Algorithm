package Tree;
/*
* 完全，满二叉树
*
* */
public class IsFullAndCBTree {
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
        boolean isFull;
        boolean isCBT;
        int height;

        public info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }
    public info process(node curNod){
        if (curNod == null){
            return new info(true,true,0);
        }
        info leftinfo = process(curNod.left);
        info rightinfo = process(curNod.right);
        int height = Math.max(leftinfo.height,rightinfo.height)+1;
        boolean isFull = leftinfo.isFull && rightinfo.isFull;
        boolean isCBT = false;
        if (leftinfo.isFull&&rightinfo.isFull&&leftinfo.height == rightinfo.height){
            isCBT = true;
        }
        if (leftinfo.isCBT&&rightinfo.isFull&&leftinfo.height - 1 == rightinfo.height){
            isCBT = true;
        }
        if (leftinfo.isFull&&rightinfo.isFull&&leftinfo.height - 1 == rightinfo.height){
            isCBT = true;
        }
        if (leftinfo.isFull&&rightinfo.isCBT&&leftinfo.height == rightinfo.height){
            isCBT = true;
        }
        return new info(isFull,isCBT,height);
    }
}













