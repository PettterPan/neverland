package com.neverland.finddream.leetcode.tree;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/17 1:28 PM
 * 给定二叉树，判断它是否是高度平衡的。对于此问题，高度平衡二叉树定义为：一个二叉树，其中每个节点的两个子树的深度从不相差超过1。例如：
 *
 * 给定以下树[3,9,20，null，null，15,7]：
 *
 *        3
 *
 *       /
 *
 *     9    20
 *
 *   /
 *
 * 15    7
 *
 * 返回true。
 *
 *
 *
 * 给定以下树[1,2,2,3,3，null，null，4,4]：
 *
 *           1
 *
 *          /
 *
 *        2   2
 *
 *       /
 *
 *     3   3
 *
 *    /
 *
 *  4    4
 *
 * 返回false。
 */
public class BalancedBinaryTree {

//在昨天的那道题里，我们介绍了平衡树的概念，今天这道题也和平衡树有关，要判断一个二叉树是否为一颗平衡树，我们需要计算从根节点开始的左右子树的深度。
//
//之前有道题是求二叉树的最长路径，如果没有印象的话可以找找历史文章。对于此题，可以借鉴那道题的思路，但是稍微有点不同，在拿到左子树和右子树的深度后，我们还要做下判断，看是否相差大于1，这一步在返回最后的深度前。
//
//特殊情况：当二叉树为空时，他也是一颗平衡树


    class TreeNode{
        TreeNode left ;
        TreeNode right;
        int val ;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int dep = getDeepth(root);
        return dep >= 0;
    }

    public int getDeepth(TreeNode t){
        if (t == null) {
            return 0;
        }
        int left = getDeepth(t.left);
        int right = getDeepth(t.right);
        if (left == -1 || right == -1 || Math.abs(left-right)>1) {
            return -1;
        }
        return 1+Math.max(left, right);
    }


}
