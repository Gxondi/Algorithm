package A005二叉树系列.A002DFS;

import A005二叉树系列.TreeNode;

// 搜索二叉树上寻找两个节点的最近公共祖先
// 测试链接 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestorBinarySearch {
    /**
        root从上到下
        如果先遇到了p，说明p是答案
        如果先遇到了q，说明q是答案
        如果root在p~q的值之间，不用管p和q谁大谁小，只要root在中间，那么此时的root就是答案
        如果root在p~q的值的左侧，那么root往右移动
        如果root在p~q的值的右侧，那么root往左移动
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        while (root.val != p.val && root.val != q.val){
            if(Math.min(p.val, q.val) < root.val && root.val < Math.max(q.val, p.val)){
                break;
            }
            root = root.val < Math.min(p.val, q.val) ? root.right : root.left;
        }
        return root;
    }
    public static void main(String[] args) {

    }
}
