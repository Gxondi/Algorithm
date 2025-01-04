package A005树的遍历;

import java.util.Stack;

public class Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        root.left.right.left.left = new TreeNode(10);
        TreeNode lastTree = root;
        treeByStack(lastTree);
        System.out.println("#################################################");
        TreeNode orderTree = root;
        treeByStack1(orderTree);
    }

    private static void treeByStack(TreeNode h) {
        if (h != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(h);
            while (!stack.isEmpty()) {
                //当前节点位置
                TreeNode cur = stack.peek();
                if (cur.left != null && h != cur.left && h != cur.right) {
                    stack.push(cur.left);
                } else if (cur.right != null && h != cur.right) {
                    stack.push(cur.right);
                } else {
                    System.out.println(cur.val + " ");
                    //左右都遍历了或没有，弹出当前
                    h = stack.pop();
                }
            }
            System.out.println();
        }

    }
    private static void treeByStack1(TreeNode h) {
        if (h != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = h;
            while (cur != null || !stack.isEmpty()) {
                // 将当前节点的所有左子节点入栈
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                // 当前节点没有左子节点了
                cur = stack.pop();
                // 访问当前节点
                System.out.print(cur.val + " ");
                // 准备访问右子节点
                cur = cur.right;
            }
            System.out.println();
        }
    }

}
