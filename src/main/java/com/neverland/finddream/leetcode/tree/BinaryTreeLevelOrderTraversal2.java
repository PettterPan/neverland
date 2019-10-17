package com.neverland.finddream.leetcode.tree;

import java.util.*;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/17 11:32 AM
 *
 * 。给定二叉树，返回其节点值的自下而上级别顺序遍历（即从左到右，逐层逐层）。例如：
 *
 * 给定二叉树[3,9,20，null，null，15,7]，
 *
 *     3
 *
 *    /
 *
 *  9   20
 *
 *       /
 *
 *     15   7
 *
 * 返回其自下而上的级别顺序遍历：[[15,7]，[9,20]，[3]]。
 */
public class BinaryTreeLevelOrderTraversal2 {

    static class TreeNode{
        TreeNode left ;
        TreeNode right ;
        int val ;

        TreeNode(int val){

        }
    }
//特殊情况：当传入的二叉树为空时，返回我们新定义的空List即可。
//
//正常情况：从示例最后要求输出的结果来看，根节点是数组的最后一位元素，如果是自顶向下遍历节点，我们可以使用队列，借助其先进先出的特点，一层一层遍历节点，将每一层遍历的节点值存入List中，再将List放入List2中，最后再倒序遍历List2存入List3中，List3就是最后的结果。

    //遍历节点的处理方法和昨天那道求最长路径的写法类似，借助队列先进先出的特点，从左往右依次循环。
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list2 = new ArrayList<>();
        if (root == null) {
            return list2;
        }
        List<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            list = new ArrayList<Integer>();
            Queue<TreeNode> tem = new LinkedList<>();
            while (!q.isEmpty()) {
                TreeNode t = q.poll();
                list.add(t.val);
                if (t.left != null) {
                    tem.add(t.left);
                }
                if (t.right != null) {
                    tem.add(t.right);
                }
            }
            list2.add(list);
            q = tem;
        }
        List<List<Integer>> list3 = new ArrayList<>();
        for(int i=list2.size()-1; i >= 0; i--){
            list3.add(list2.get(i));
        }
        return list3;
    }
//我们可以将第一种解法再优化下，不再创建新的队列来存新的一层的节点。和昨天那道题的写法类似，借助队列的size来作为判断条件。
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> list2 = new ArrayList<>();
        if (root == null) {
            return list2;
        }
        List<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            list = new ArrayList<Integer>();
            int n = q.size();
            while (n-- > 0) {
                TreeNode t = q.poll();
                list.add(t.val);
                if (t.left != null) {
                    q.offer(t.left);
                }
                if (t.right != null) {
                    q.offer(t.right);
                }
            }
            list2.add(list);
        }
        List<List<Integer>> list3 = new ArrayList<>();
        for(int i=list2.size()-1; i >= 0; i--){
            list3.add(list2.get(i));
        }
        return list3;
    }

//上面两种解法都是使用新的List来存储List2倒序遍历的值作为最后结果返回，既然是先进后出的特点，我们可以使用栈来存储每遍历一层节点存入的List，再使用栈的pop方法出栈存入新的List
    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        List<List<Integer>> list2 = new ArrayList<>();
        if (root == null) {
            return list2;
        }
        List<Integer> list = new ArrayList<Integer>();
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            list = new ArrayList<Integer>();
            int n = q.size();
            while (n-- > 0) {
                TreeNode t = q.poll();
                list.add(t.val);
                if (t.left != null) {
                    q.offer(t.left);
                }
                if (t.right != null) {
                    q.offer(t.right);
                }
            }
            stack.add(list);
        }
        while (!stack.isEmpty()) {
            list2.add(stack.pop());
        }
        return list2;
    }

//上面的三种都是使用遍历的方式，那我们可不可以使用递归的方法遍历每一层的节点值？显然是可以的，我们可以使用层数作为标记，来判断现阶段处于那一层，从而来决定是新建一个List还是取已存在的List往里面存入新的节点值。
  //  从根节点开始，先递归获取根节点左子节点及其子节点的节点值，然后再递归获取根节点右子节点及其子节点的节点值
    public List<List<Integer>> levelOrderBottom4(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<List<Integer>> node = new ArrayList<List<Integer>>();
        viewTree(root, res, 0);
        for (int i=res.size()-1; i>=0; i--) {
            node.add(res.get(i));
        }
        return node;
    }

    public void viewTree(TreeNode root, List<List<Integer>> res, int deep) {
        if(root == null)
            return;
        if (res.size() <= deep) {
            List<Integer> node = new ArrayList<Integer>();
            node.add(root.val);
            res.add(node);
        } else {
            List<Integer> node = (List<Integer>)res.get(deep);
            node.add(root.val);
        }
        viewTree(root.left, res, deep+1);
        viewTree(root.right, res, deep+1);
    }


    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal2 instance = new BinaryTreeLevelOrderTraversal2();
        TreeNode t = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        t.left = t2;
        t.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        t7.left = t8;
        long start = System.nanoTime();
        List<List<Integer>> result = instance.levelOrderBottom(t);
        long end = System.nanoTime();
        System.out.println("levelOrderBottom---输出："+result.toString()+" , 用时："+(end-start)/1000+"微秒");
        long start2 = System.nanoTime();
        List<List<Integer>> result2 = instance.levelOrderBottom2(t);
        long end2 = System.nanoTime();
        System.out.println("levelOrderBottom2---输出："+result2.toString()+" , 用时："+(end2-start2)/1000+"微秒");
        long start3 = System.nanoTime();
        List<List<Integer>> result3 = instance.levelOrderBottom3(t);
        long end3 = System.nanoTime();
        System.out.println("levelOrderBottom3---输出："+result3.toString()+" , 用时："+(end3-start3)/1000+"微秒");
        long start4 = System.nanoTime();
        List<List<Integer>> result4 = instance.levelOrderBottom4(t);
        long end4 = System.nanoTime();
        System.out.println("levelOrderBottom4---输出："+result4.toString()+" , 用时："+(end4-start4)/1000+"微秒");
    }
}
