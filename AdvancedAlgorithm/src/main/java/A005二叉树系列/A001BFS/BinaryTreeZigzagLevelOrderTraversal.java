package A005二叉树系列.A001BFS;

import A005二叉树系列.TreeNode;

import java.util.ArrayList;
import java.util.List;

// 二叉树的锯齿形层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
public class BinaryTreeZigzagLevelOrderTraversal {
    public static int MAX = 2001;
    public static int l, r;
    public static TreeNode[] queue = new TreeNode[MAX];

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            l = r = 0;
            queue[r++] = root;
            boolean reserve = false;
            while (l < r) {
                ArrayList<Integer> list = new ArrayList<>();
                int size = r - l;
                //关键循环条件
                for (int i = reserve ? r - 1 : l, j = reserve ? -1 : 1, k = 0; k < size; i += j, k++){
                    TreeNode cur = queue[i];
                    list.add(cur.val);
                }
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue[l++];
                    if (cur.left != null) {
                        queue[r++] = cur.left;
                    }
                    if (cur.right != null) {
                        queue[r++] = cur.right;
                    }
                }
                reserve = !reserve;
                ans.add(list);
            }
        }
        return ans;
    }
}
