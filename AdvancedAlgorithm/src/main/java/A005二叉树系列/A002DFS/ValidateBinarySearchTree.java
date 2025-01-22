package A005二叉树系列.A002DFS;

import A005二叉树系列.TreeNode;

import java.util.Stack;

// 验证搜索二叉树
// 测试链接 : https://leetcode.cn/problems/validate-binary-search-tree/
public class ValidateBinarySearchTree {
    Stack<TreeNode> stack = new Stack<>();

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode pre = null;
        stack.push(root);
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null && pre.val >= root.val) {
                    return false;
                }
                pre = root;
                root = root.right;
            }
        }
        return true;
    }
    public static long min, max;
    public boolean isValidBST1(TreeNode root){
        if (root == null) {
            min = Long.MAX_VALUE;
            max = Long.MIN_VALUE;
            return true;
        }
        boolean left = isValidBST1(root.left);
        long lmin = min;
        long lmax = max;
        boolean right = isValidBST1(root.right);
        long rmin = min;
        long rmax = max;
        min = Math.min(Math.min(lmin,rmin),root.val);
        max = Math.max(Math.max(lmax,rmax),root.val);
        return left && right && lmax < root.val && root.val < rmin;
    }
}
