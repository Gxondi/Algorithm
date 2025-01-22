package A005二叉树系列.A002DFS;

import A005二叉树系列.TreeNode;

// 二叉树打家劫舍问题
// 测试链接 : https://leetcode.cn/problems/house-robber-iii/
public class HouseRobber {
    public static int yes, no;

    public static int rob(TreeNode head) {
        f(head);
        return Math.max(yes, no);
    }

    private static void f(TreeNode head) {
        if (head == null) {
            yes = 0;
            no = 0;
        } else {
            int y = head.val;
            int n = 0;
            f(head.left);
            y += no;
            n += Math.max(yes,no);
            f(head.right);
            n += Math.max(yes,no);
            y += no;
            yes = y;
            no = n;
        }
    }
}
