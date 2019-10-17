package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 5:44 PM
 *
 * 你正在爬楼梯，它需要n步才能达到顶峰。每次你可以爬1或2步，你可以通过多少不同的方式登顶？注意：给定n是一个正整数。例如：
 *
 * 输入：2
 *
 * 输出：2
 *
 * 说明：有两种方法可以爬到顶端
 *
 * 1、1步 + 1步
 *
 * 2、2步
 *
 *
 *
 * 输入：3
 *
 * 输出：3
 *
 * 说明：有三种方法可以爬到顶端
 *
 * 1、1步 + 1步 + 1步
 *
 * 2、1步 + 2步
 *
 * 3、2步 + 1步
 *
 *
 *
 * 输入：4
 *
 * 输出：5
 *
 * 说明：有5种方法可以爬到顶端
 *
 * 1、1步 + 1步 + 1步 + 1步
 *
 * 2、1步 + 1步 + 2步
 *
 * 3、1步 + 2步 + 1步
 *
 * 4、2步 + 1步 + 1步
 *
 * 5、2步 + 2步
 */
public class ClimbingStairs {

    //关于题目，可以从简单的条件开始推导。
    //
    //当n等于1的时候，有1种方式。
    //
    //当n等于2的时候，有2种方式。
    //
    //当n等于3的时候，有3种方式。
    //
    //当n等于4的时候，有5种方式。
    //
    //当n等于5的时候，有8种方式。
    //
    //看到这，你会发现从n等于2开始，后一项等于前两项的和，这时很容易想到递归，于是就有了第一种解法
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n-1) + climbStairs(n-2);
    }

//上面的解法，你应该也发现问题了，运行太慢了。有什么可以优化的呢？既然只是求前两个数的和，那么可以引用数组来存值，而不是每次都来重新算一次。
// 在climb方法里判断里，是要加上else if那个判断的，目的是避免重复计算，不判断的话就和第一种差不多了。

    public int climbStairs2(int n) {
        int[] arr = new int[n+1];
        return climb(n, arr);
    }


    public int climb(int n, int[] arr){
        if (n == 0 || n == 1) {
            return 1;
        } else if(arr[n] > 0) {
            return arr[n];
        }
        arr[n] = climb(n-1, arr) + climb(n-2, arr);
        return arr[n];
    }
//既然利用数组来存值，那么是不是可以省掉递归？直接拿数组元素返回结果就行。
    public int climbStairs3(int n) {
        int[] arr = new int[n+1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i=2; i<arr.length; i++) {
            arr[i] = arr[i-1]+arr[i-2];
        }
        return arr[n];
    }

    public static void main(String[] args) {
        //可以看出来，第三种解法是最优的，递归是好算法，但是会造成很多重复计算，影响速度，需要分场景来使用递归算法。
        ClimbingStairs instance = new ClimbingStairs();
        int arg = 44;
        long start = System.nanoTime();
        int result = instance.climbStairs(arg);
        long end = System.nanoTime();
        System.out.println("climbStairs---输入："+arg+" , 输出："+result+" , 用时："+(end-start)/1000+"微秒");
        System.out.println("----------------------------");
        long start2 = System.nanoTime();
        int result2 = instance.climbStairs2(arg);
        long end2 = System.nanoTime();
        System.out.println("climbStairs2---输入："+arg+" , 输出："+result2+" , 用时："+(end2-start2)/1000+"微秒");
        System.out.println("----------------------------");
        long start3 = System.nanoTime();
        int result3 = instance.climbStairs3(arg);
        long end3 = System.nanoTime();
        System.out.println("climbStairs3---输入："+arg+" , 输出："+result3+" , 用时："+(end3-start3)/1000+"微秒");
    }
}
