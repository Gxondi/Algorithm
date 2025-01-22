package A005二叉树系列.A002DFS;

import A005二叉树系列.TreeNode;
// 验证平衡二叉树
// 测试链接 : https://leetcode.cn/problems/balanced-binary-tree/
public class BalancedBinaryTree {

    public static Boolean balance;
    public static boolean isBalanced(TreeNode root){
        balance = true;
        height(root);
        return balance;
    }

    private static int height(TreeNode cur) {
        if(!balance || cur == null){
            return 0;
        }
        int left = height(cur.left);
        int right = height(cur.right);
        //平衡二叉树，任何节点高度差距小于1，
        if(Math.abs(right - left) > 1){
            balance = false;
        }
        return Math.max(right,left) + 1;
    }

    public static void main(String[] args) {

    }
}
