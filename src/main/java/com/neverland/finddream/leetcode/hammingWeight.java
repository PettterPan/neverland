package com.neverland.finddream.leetcode;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/15 3:21 PM
 *
 *
 * 编写一个带无符号整数的函数，并返回它所具有的“1”位数。例如：
 *
 *
 *
 * 输入：11
 *
 * 输出：3
 *
 * 说明：整数11具有二进制表示00000000000000000000000000001011
 *
 *
 *
 * 输入：128
 *
 * 输出：1
 *
 * 说明：整数128具有二进制表示00000000000000000000000010000000
 */
public class hammingWeight {

    public static void main(String args[]){


        System.out.println(14 & 13);
        System.out.println(hammingWeight(14));
        System.out.println(hammingWeight2(14));
        System.out.println(hammingWeight3(14));
        System.out.println(hammingWeight4(14));


    }

    /*
    * 这道题与昨天的题类似，都是和位操作有关。
    * 此题是统计二进制位“1”的出现次数，
    * 对此可以使用与（&）运算，对于n还是依次从左往右移动。
    * */

    public static int hammingWeight(int n) {
        int count = 0;
        for(int i=0; i<32; i++){
            if ((n&1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }
    /*
        对于上面的解法，还可以再简化下，省掉那个判断，
        因为和1做与运算，如果最后一位是1，整个运算结果就是1，就相当于是自增加1
     *
     * */

    public static int hammingWeight2(int n) {
        int count = 0;
        for (int i=0; i<32; i++) {
            count += n&1;
            n = n >> 1;
        }
        return count;
    }

    /*
    * 上面的两种解法都是拿n和1做与运算，其实还可以换种思路，
    * 使用n和n-1做位运算。此时，分两种情况：当n的二进制最后一位是1的时候，
    * n-1的二进制位除了最后一位和n不一样，其他位都是一样的，此时n&(n-1)得到的结果是n-1，
    * 此时消除的是n的二进制最后一位；当n的二进制最后一位是0的时候，n-1的二进制位最后一位是1，
    * n从最后一位1往左开始和n-1的对应位是一样的，此时n&(n-1)得到的是n最后一位1出现位置往左之前的位，
    * 此时消除的n的二进制位中最后出现的1。每次都会消除一位1，直到n最后变成0。

    例如：

    10  1010

    9    1001

    与：1000


    14  1110

    13  1101

    与：1100
        *
        * */

    public static int hammingWeight3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    /*将n变成二进制字符串，然后依次查找字符串中的1，
    并记数。其中，整数1的Unicode十进制值是49，
    所以使用charAt方法时，是和49判 断。
     */

    public static int hammingWeight4(int n) {
        int count =0;
        String binary = Integer.toBinaryString(n);
        for (int i =0; i<binary.length(); i++) {
            if (binary.charAt(i) == 49) {
                count++;
            }
        }
        return count;
    }
    /*直接使用包装类Integer自带的方法bitCount即可。
     */
    public static int hammingWeight5(int n) {
        return Integer.bitCount(n);
    }
}
