package Tree;
/*
* 思路
* 1.不包括当前节点的最大搜索二叉树
* 2.包括当前节点的最大搜索二叉树
* 返回最大搜索二叉树的头节点
* */
public class maxSubBSTheadNode {
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
        public int max;
        public int min;
        public int maxBSTHeadSIze;
        public node maxBSTHeadNode;

        public info(int max, int min, int maxBSTHeadSIze, node maxBSTHeadNode) {
            this.max = max;
            this.min = min;
            this.maxBSTHeadSIze = maxBSTHeadSIze;
            this.maxBSTHeadNode = maxBSTHeadNode;
        }
    }
    public info process(node CurNode){
        if (CurNode == null){
            return null;
        }
        info getLeftInfo = process(CurNode.left);
        info getRightInfo = process(CurNode.right);
        int max = CurNode.value;
        int min = CurNode.value;
        int maxBSTHeadSIze = 0;
        node maxBSTHeadNode = null;
        if (getLeftInfo!=null){
            max = Math.max(max,getLeftInfo.max);
            min = Math.min(min,getLeftInfo.min);
            maxBSTHeadSIze = getLeftInfo.maxBSTHeadSIze;
            maxBSTHeadNode = getLeftInfo.maxBSTHeadNode;
        }
        if (getRightInfo!=null){
            max = Math.max(max,getRightInfo.max);
            min = Math.min(min,getRightInfo.min);
            if (getRightInfo.maxBSTHeadSIze > maxBSTHeadSIze){
                maxBSTHeadSIze = getRightInfo.maxBSTHeadSIze;
                maxBSTHeadNode = getRightInfo.maxBSTHeadNode;
            }
        }
        if ((getLeftInfo ==null?true:(getLeftInfo.maxBSTHeadNode == CurNode.left && getLeftInfo.max<CurNode.value))
                && getRightInfo == null?true:(getRightInfo.maxBSTHeadNode == CurNode.right&&getRightInfo.min>CurNode.value)
        ){
            maxBSTHeadNode = CurNode;
            maxBSTHeadSIze = (getLeftInfo == null?0:getLeftInfo.maxBSTHeadSIze)+(getRightInfo == null?0:getRightInfo.maxBSTHeadSIze)+1;
        }
        return new info(max,min,maxBSTHeadSIze,maxBSTHeadNode);
    }
}





























