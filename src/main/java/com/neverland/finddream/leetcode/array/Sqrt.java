package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 5:39 PM
 *
 * 计算并返回x的平方根，其中x保证为非负整数。 由于返回类型是整数，因此将截断十进制数字，并仅返回结果的整数部分。例如：
 *
 * 输入：4
 *
 * 输出：2
 *
 *
 *
 * 输入：8
 *
 * 输出：2
 *
 * 说明：8的平方根是2.82842 ...，从2以后小数部分被截断，返回2
 */
public class Sqrt {

    /*使用二分法来算平方根。
特殊情况一：在求中间数时，需要考虑整型溢出的情况。
因为在计算中间数时，习惯性很容易就写出mid = (right+left)/2，但是left+right的值如果溢出，那么整个计算都是失真的。此时，我们就需要做下替换，用减法替代加法：
            right/2 + left/2
            right/2 - left/2 + left
            (right-left)/2 + left
特殊情况二：在判断中间数的平方是否等于传入的参数时，习惯性就写出 mid*mid == x，这其实也是存在溢出风险的，也可以变换下，做除法，即 x/mid == mid。
特殊情况三：传入的参数小于等于0的时候，直接返回0即可。
二分法来取平方根，低位取1，高位是x本身，如果低位小于等于高位，就进入循环求得两者中间数，做除法比较是否相等，相等则返回中间数，如果高位除以中间数大于中间数，则低位等于中间数向前加1，如果高位除以中间数小于中间数，则高位等于中间数向后减1。直到低位大于高位，结束循环，返回高位。
*/
    public int mySqrt(int x) {
        if (x < 1)
            return 0;
        int low = 1;
        int high = x;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (x / mid == mid)
                return mid;
            if (x / mid > mid)
                low = mid + 1;
            if (x / mid < mid)
                high = mid - 1;
        }
        return high;
    }
//直接使用Math自身的sqrt()方法，如果面试时遇到此题，还是以上面的解法或者下面的第三种为好。
    public int mySqrt2(int x) {
        return (int) Math.sqrt(x);
    }

    //利用牛顿迭代法计算开平方根，在此不过多描述，会单独抽时间来写这个经典的求平方根解法。
    public int mySqrt3(int x) {
        double flag = 0.1d;
        if (x <= 0) {
            return 0;
        }
        double val = x;
        double last;
        do {
            last = val;
            val = (val + x / val) / 2;
        } while (val - last > flag || val - last < -flag);
        return (int) val;
    }

    public static void main(String[] args) {
        Sqrt instance = new Sqrt();
        int arg = 2;
        long start = System.nanoTime();
        int result = instance.mySqrt(arg);
        long end = System.nanoTime();
        System.out.println("mySqrt()---输入:" + arg + " , 输出:" + result + " , 用时:" + ((end - start) / 1000) + "微秒");
        System.out.println("-----------------------------------------------");
        long start2 = System.nanoTime();
        int result2 = instance.mySqrt2(arg);
        long end2 = System.nanoTime();
        System.out.println("mySqrt2()---输入:" + arg + " , 输出:" + result2 + " , 用时:" + ((end2 - start2) / 1000) + "微秒");
        System.out.println("-----------------------------------------------");
        long start3 = System.nanoTime();
        int result3 = instance.mySqrt3(arg);
        long end3 = System.nanoTime();
        System.out.println("mySqrt3()---输入:" + arg + " , 输出:" + result3 + " , 用时:" + ((end3 - start3) / 1000) + "微秒");
    }
}
