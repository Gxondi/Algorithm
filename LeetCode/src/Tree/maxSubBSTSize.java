package Tree;
/*
* 分情况讨论
* 1.最大搜索二叉树不包括curnode
* 1.1说明最大二叉树可能再左边 p1 = getInfo.maxBST；
* 1.2最大二叉树也可能再右边  p2 = getInfo.maxBST；
*2.最大搜索二叉树包括Curnode
* P3 = 左+右
* 判断左边如果是搜索二叉树返回true
* 判断右边边如果是搜索二叉树返回true
* 两边都为true
* 再次判断左边最大值是否小于curnode，判断右边是否大于curnode
* 判断为true
* 获取左右边数量，P3 = 左+右
* */
public class maxSubBSTSize {
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
        public int AllSize;//整个树大小
        public int maxBSTSubtreeSize;//子树最大数量

        public info(int max, int min, int allSize, int maxBSTSubtreeSize) {
            this.max = max;
            this.min = min;
            AllSize = allSize;
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
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
        int allSize = 1;
        if (getLeftInfo!=null){
            max = Math.max(max,getLeftInfo.max);
            min = Math.min(max,getLeftInfo.min);
            allSize += getLeftInfo.AllSize;
        }
        if (getRightInfo!=null){
            max = Math.max(max,getRightInfo.max);
            min = Math.min(max,getRightInfo.min);
            allSize += getRightInfo.AllSize;
        }
        int p1 = -1;
        if (getLeftInfo!=null){
            p1 = getLeftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if (getLeftInfo!=null){
            p2 = getRightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;
        boolean leftBST = getLeftInfo == null?true:(getLeftInfo.maxBSTSubtreeSize == getLeftInfo.AllSize);
        boolean rightBST = getRightInfo == null?true:(getRightInfo.maxBSTSubtreeSize == getRightInfo.AllSize);
        if (leftBST&&rightBST){
            boolean LeftMaxLessX = getLeftInfo == null?true:(getLeftInfo.max<CurNode.value);
            boolean RightMaxLessX = getRightInfo == null?true:(getRightInfo.max>CurNode.value);
            if (LeftMaxLessX&&LeftMaxLessX){
                int leftSize = getLeftInfo == null?0:getLeftInfo.AllSize;
                int rightSize = getRightInfo == null?0:getRightInfo.AllSize;
                p3 = leftSize + rightSize+1;
            }
        }
        return new info(Math.max(p1,Math.max(p2,p3)),allSize,max,min);
    }

}
