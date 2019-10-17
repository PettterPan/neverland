package com.neverland.finddream.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 6:12 PM
 * 给定二叉树，检查它是否是自身的镜像（即，围绕其中心对称）。
 *
 * 例如，这个二叉树[1,2,2,3,4,4,3]是对称的：
 *
 *      1
 *
 *     /
 *
 *   2    2
 *
 *  /     /
 *
 * 3  4  4  3
 *
 * 但是以下[1,2,2，null，3，null，3]不是：
 *
 *     1
 *
 *    /
 *
 *  2   2
 *
 *
 *
 *    3    3
 */
public class SymmetricTree {

   class TreeNode{
       TreeNode left,right;
       int val;

   }

   //如果你看过昨天的Same Tree题，看到今天这道题时，有没有什么想法？可能你已经发现了，要判断一个二叉树是否中心对称，如果把它从根节点“一分为二”的看做两个二叉树，只要判断这两个二叉树的左右节点是否对称相等即可，即左边新的二叉树的左节点值和右边新的二叉树的右节点值相等。对此，我们可以借助昨天的代码，很容易的就将其写出来。

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSameTree(root.left, root.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        boolean f = p.val == q.val;
        boolean f2 = isSameTree(p.left, q.right);
        boolean f3 = isSameTree(p.right, q.left);
        return f && f2 && f3;
    }
//除了上面的递归方法外，我们还可以使用另外一种解法。
//
//题目的意思是只要每个节点的值对称相等就行，那我们可以将每层节点的值存起来，然后再进行比较，直到比较完所有的值。因为是自顶向下比较，所以先存起来的值就需要先拿出来比较，我们可以使用队列来当做存值的载体，借助其先进先出的特性。
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}
