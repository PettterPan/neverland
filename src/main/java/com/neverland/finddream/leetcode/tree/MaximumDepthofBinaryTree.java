package com.neverland.finddream.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/17 11:24 AM
 *
 * 给定二叉树，找到它的最大深度。最大深度是从根节点到最远叶节点的最长路径上的节点数。叶子是没有子节点的节点。
 *
 *
 *
 * 例如：给定二叉树[3,9,20，null，null，15,7]，
 *
 *     3
 *
 *    /
 *
 * 9   20
 *
 *       /
 *
 *     15 7
 *
 * 返回其深度= 3。
 */
public class MaximumDepthofBinaryTree {

        static class TreeNode{
            TreeNode left;
            TreeNode right;
            int val ;

             TreeNode(int val){

            }
        }


//最大深度是根节点到最远的叶子节点路径上包含的节点数，以上面的二叉树为例，最长路径有两条：3->20->15,3->20->7，这两条路径上的节点数都是3个，所以最后得出其深度是3的结论。
//
//特殊情况一：当传入的二叉树为空时，它没有任何节点，它的深度是0。
//
//特殊情况二：只有根节点的时候，它的深度是1，只有它自身一个节点。
//
//正常情况：我们可以一步一步试着推导下，一个简单的二叉树深度计算过程，还是以上面的二叉树为例。
//
//从根节点开始，此时节点数为1,因为只有它一个节点。
//
//进入根节点的子节点，此时最长路径就是计算9这个左子节点和20这个右子节点的最长路径，显然9是一个叶子节点，只有本身一个节点；而20拥有自己的子节点，此时就需要算出从20出发的最长路径。
//
//20有左子节点15，右子节点7，这时需要继续计算15和7的最长路径，而15和7都是叶子节点，所以节点数只有1，再加上20节点本身这个属于根节点的子节点，20节点的最长路径就是2。
//
//同为3根节点的两个子节点9、20，子节点9的最长路径上节点数为1，子节点20的最长路径上节点数为2，取最大值后，再加上根节点自身的节点数，1+2=3,3就是最长路径上的节点数，也就是该二叉树的深度。
//
//分析到这里，我们知道了，要计算从根节点到最远叶子节点的节点个数，就需要先计算其左右子节点的最长路径，而要计算左右子节点的最长路径，就需要计算他们自身下面的左右子节点的最长路径了，对此我们可以使用递归，算完最下面叶子节点的个数后，再层层往上求其最大值。
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }


//既然可以使用递归，那我们也可以试着使用遍历的方法。从根节点开始，自顶向下遍历子节点，对此使用队列来临时存储每次遍历的子节点，遍历完一层子节点就加1，直到所有子节点都遍历完
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Queue<TreeNode> tem = new LinkedList<>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                if (node.left != null) {
                    tem.offer(node.left);
                }
                if (node.right != null) {
                    tem.offer(node.right);
                }
            }
            q = tem;
            depth++;
        }
        return depth;
    }

//第二种解法的内层循环那里，我们使用了新的队列来接收每次循环要进入的下一层节点数据，是否可以改动下，使其更加的简洁？这里我们使用队列的大小来控制它，从根节点开始，进入队列后，队列的size为1，开始进入内层循环，内层循环走了一次后，队列里又多了两个子节点，此时的size为2，然后继续开始内层循环，直到所有的子节点遍历完。
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0){
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }



    public static void main(String[] args) {
        MaximumDepthofBinaryTree instance = new MaximumDepthofBinaryTree();
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
        int result = instance.maxDepth(t);
        long end = System.nanoTime();
        System.out.println("maxDepth---输出："+result+" , 用时："+(end-start)/1000+"微秒");
        long start2 = System.nanoTime();
        int result2 = instance.maxDepth2(t);
        long end2 = System.nanoTime();
        System.out.println("maxDepth2---输出："+result2+" , 用时："+(end2-start2)/1000+"微秒");
        long start3 = System.nanoTime();
        int result3 = instance.maxDepth3(t);
        long end3 = System.nanoTime();
        System.out.println("maxDepth3---输出："+result3+" , 用时："+(end3-start3)/1000+"微秒");
    }
}
