package com.neverland.finddream.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/17 2:11 PM
 * 给定二叉树，找到它的最小深度。最小深度是沿从根节点到最近的叶节点的最短路径上的节点数。叶子节点是没有子节点的节点。例如：
 *
 * 给定二叉树[3,9,20，null，null，15,7]，
 *
 *        3
 *
 *       /
 *
 *     9   20
 *
 *          /
 *
 *        15    7
 *
 * 返回其最小深度= 2。
 */
public class MinimumDepthofBinaryTree {


    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }


    //之前我们有解过最大深度的题，今天这道题相反，求最短路径，那是不是直接将原来代码中的最大改为最小即可？如果你这样试过，会发现根本不是那么回事！
    //
    //特殊情况一：当传入的二叉树为空时，最短路径就是0。
    //
    //特殊情况二：当传入的二叉树只有根节点时，最短路径是1.
    //
    //正常情况：当某一节点的左子节点为空时，这时我们需要求其右子节点的最短路径；当某一节点的右子节点为空时，这时我们需要求其左子节点的最短路径；当某一节点的左子节点和右子节点都不为空时，这时我们要求其左子树和右子树的最短路径。

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return 1+Math.min(minDepth(root.left), minDepth(root.right));
    }

    //除了上面的递归外，我们依旧可以使用遍历的方法。此解法与求最大深度时的第三种解法类似，也是利用队列，只是多了一步判断：当左右节点都为空时，此节点是叶子节点，需要更新最短路径的值。

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int minDepth = Integer.MAX_VALUE;
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode t = queue.poll();
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
                if (t.left == null && t.right == null) {
                    minDepth = Math.min(minDepth, depth);
                }
                size--;
            }
            depth++;
        }
        return minDepth;
    }
}
