package A005二叉树系列.A001BFS;

import A005二叉树系列.TreeNode;

// 二叉树的最大特殊宽度
// 测试链接 : https://leetcode.cn/problems/maximum-width-of-binary-tree/
public class WidthOfBinaryTree {
    public static int MAX = 3001;
    public static int l, r;
    public static TreeNode[] nodeQueue = new TreeNode[MAX];
    public static int[] idQueue = new int[MAX];
    //思路：idQueue右边减去左边
    public static int widthOfBinaryTree(TreeNode root) {
        int ans = 1;
        if (root != null) {
            l = r = 0;
            nodeQueue[r] = root;
            idQueue[r++] = 1;
            while (l < r) {
                int size = r - l;
                ans = Math.max(ans, idQueue[r - 1] - idQueue[l] + 1);
                for (int i = 0; i < size; i++) {
                    TreeNode cur = nodeQueue[l];
                    int id = idQueue[l++];
                    if (cur.left != null) {
                        nodeQueue[r] = cur.left;
                        idQueue[r++] = id * 2;
                    }
                    if (cur.right != null) {
                        nodeQueue[r] = cur.right;
                        idQueue[r++] = id * 2 + 1;
                    }
                }
            }
        }
        return ans;
    }
}
