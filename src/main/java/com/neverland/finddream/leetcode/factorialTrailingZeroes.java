package com.neverland.finddream.leetcode;

import java.math.BigInteger;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/15 4:24 PM
 * 给定一个整数n，返回n！中的尾随零数。例如：
 *
 * 输入：3
 *
 * 输出：0
 *
 * 说明：3！ = 6，没有尾随零。
 *
 *
 *
 * 输入：5
 *
 * 输出：1
 *
 * 说明：5！ = 120，一个尾随零。
 */
public class factorialTrailingZeroes {


    public static void main(String args[]){

        System.out.println(trailingZeroes2(200));

    }

    /*特殊情况：当n小于等于0的时候，直接返回0。
    先把n的阶乘算出来，再转为字符串，
    接着从字符串的最后一位往前遍历找0出现的次数，
    没有碰到0就就结束循环。为了避免计算阶乘溢出，使用BigInteger来做计算，借助其multiply方法。
    此解法是一种思路，但是不推荐这么做，时间复杂度是O(n)，空间复杂度是0(n)。
    */

    public static int trailingZeroes(int n) {
        int result = 0;
        if (n <= 0) {
            return result;
        }
        BigInteger num = new BigInteger("1");
        for (long i=1; i <= n; i++) {
            num = num.multiply(new BigInteger(i+""));
        }
        String str = num + "";
        if (str.lastIndexOf("0") != -1) {
            for (int j = str.length(); j > 0; j--) {
                if ("0".equals(str.substring(j-1, j))) {
                    result++;
                } else {
                    break;
                }
            }
        }
        return result;
    }


    /*要判断n做完阶乘后的整数带几个0，可以反过来思考，尾数0可以由那些数相乘得到？
      0可以由10的倍数来得到，但是n的阶乘我们不能单独判断10出现的次数，还要继续分解，
      10是2乘以5的结果，任意一个正整数的阶乘，2出现的次数肯定多于5出现的次数，那就计算5出现的次数，到此是否就完了？
      还没有，因为有些数字自身就是带5的，比如25,125之类的，最后可以归纳成f(n)=n/5 + f(n/5)，
      可以使用递归，也可以使用循环结构。这是递归的解法。*/
    public  static int trailingZeroes2(int n) {
        if (n<5) return 0;
        if (n<10) return 1;
        return n/5 + trailingZeroes2(n/5);
    }
/*
    这是使用循环结构的解法。
*/
public static int trailingZeroes3(int n) {
    int result = 0;
    while (n>0) {
        result += n/5;
        n /= 5;
    }
    return result;
}
/*还有更加疯狂的，一行代码搞定。
 */
public static int trailingZeroes4(int n) {
    return n == 0 ? 0 : n/5 + trailingZeroes4(n/5);
}



}
