package A005二叉树系列.A001BFS;

import A005二叉树系列.TreeNode;

// 验证完全二叉树
// 测试链接 : https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
public class CompletenessOfBinaryTree {
    //思路：首先是有右无左就不是完全二叉树，其次：遇见左右节点不双全的，该节点之后的节点都要是叶子，否则不是完全二叉树
    public static int MAX = 2001;
    public static TreeNode[] queue = new TreeNode[MAX];
    public static int l, r;

    public static boolean completenessOfBinaryTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        l = r = 0;
        queue[r++] = head;
        //是否遇见左右节点不双全的
        boolean leaf = false;
        while (l < r) {

            head = queue[l++];
            if (head.left == null && head.right != null) {
                return false;
            }
            if (leaf && (head.left != null || head.right != null)) {
                return false;
            }
            if (head.left != null) {
                queue[r++] = head.left;
            }
            if (head.right != null) {
                queue[r++] = head.right;
            }
            //一旦遇见不双全的，后续的节点都要判断是否属于叶节点
            if (head.left == null || head.right == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        completenessOfBinaryTree(treeNode);
    }
}
