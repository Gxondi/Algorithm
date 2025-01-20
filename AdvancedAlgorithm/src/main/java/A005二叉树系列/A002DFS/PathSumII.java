package A005二叉树系列.A002DFS;

import A005二叉树系列.TreeNode;

import java.util.ArrayList;
import java.util.List;

// 收集累加和等于aim的所有路径
// 测试链接 : https://leetcode.cn/problems/path-sum-ii/
public class PathSumII {
    public static List<List<Integer>> pathSum(TreeNode root, int aim) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            List<Integer> path = new ArrayList<>();
            process(root, aim, 0, path, ans);
        }
        return ans;
    }

    private static void process(TreeNode cur, int aim, int sum, List<Integer> path, List<List<Integer>> ans) {
        //如果是叶子节点
        if (cur.left == null && cur.right == null) {
            if (cur.val + sum == aim) {
                path.add(cur.val);
                copy(path, ans);
                path.remove(path.size() - 1);
            }
        } else {
            path.add(cur.val);
            if (cur.left != null) {
                process(cur.left, aim, sum + cur.val, path, ans);
            }

            if (cur.right != null) {
                process(cur.right, aim, sum + cur.val, path, ans);
            }
            path.remove(path.size() - 1);
        }
    }

    private static void copy(List<Integer> path, List<List<Integer>> ans) {
        List<Integer> copy = new ArrayList<>();
        for (Integer val : path) {
            copy.add(val);
        }
        ans.add(copy);
    }

    public static void main(String[] args) {

    }
}
