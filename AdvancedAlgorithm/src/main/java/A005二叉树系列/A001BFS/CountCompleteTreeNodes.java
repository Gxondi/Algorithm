package A005二叉树系列.A001BFS;

import A005二叉树系列.TreeNode;

// 求完全二叉树的节点个数
// 测试链接 : https://leetcode.cn/problems/count-complete-tree-nodes/
public class CountCompleteTreeNodes {
    //思路：完全二叉树特性：遍历最左节点，就可以得到最深深度。
    //其次最后一个右节点等于树高度，那么说明左树是满二叉树，反而该节点上一个节点的树是满二叉树
    public static int depth(TreeNode head,int level){
        while (head!=null){
            level++;
            head = head.left;
        }
        return level - 1;
    }
    public static int countCompleteTreeNodes(TreeNode head) {
        if(head == null){
            return 0;
        }
        return f(head,1,depth(head,1));
    }

    /**
     *
     * @param cur 节点
     * @param level 当前level
     * @param h 整个树高度
     * @return
     */
    private static int f(TreeNode cur, int level, int h) {
        if (level == h){
            return 1;
        }
        //右树上最左节点和树高度一致
        if(depth(cur.right,level + 1) == h){
            return (1<<(h - level)) + f(cur.right,level + 1,h);
        }else {
            //cur右树最左节点没到树的高度
            return (1<<(h - level - 1)) + f(cur.left,level + 1,h);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        countCompleteTreeNodes(treeNode);
    }
}
