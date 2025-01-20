package A005二叉树系列.A001BFS;

import A005二叉树系列.TreeNode;

import java.util.*;

// 二叉树的层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
public class BFS {
    public static List<List<Integer>> levelOrder1(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            //需要队列辅助bfs遍历
            Queue<TreeNode> queue = new LinkedList<>();
            HashMap<TreeNode, Integer> hashMap = new HashMap<>();
            queue.add(root);
            //层数
            hashMap.put(root, 0);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                int level = hashMap.get(cur);
                if (ans.size() == level) {
                    //加入结果构造中
                    ans.add(new ArrayList<>());
                }
                ans.get(level).add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                    hashMap.put(cur.left, level + 1);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    hashMap.put(cur.left, level + 1);
                }
            }
        }
        return ans;
    }

    public static int MAX = 2001;
    public static int l, r = 0;
    public static TreeNode[] queue = new TreeNode[MAX];

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            l = r = 0;
            queue[r++] = root;
            while (l < r) {
                ArrayList<Integer> list = new ArrayList<>();
                int size = r - l;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue[l++];
                    list.add(cur.val);
                    if (cur.left!=null){
                        queue[r++] = cur.left;
                    }
                    if (cur.right!=null){
                        queue[r++] = cur.right;
                    }
                }
                ans.add(list);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        levelOrder1(treeNode);
    }

}
