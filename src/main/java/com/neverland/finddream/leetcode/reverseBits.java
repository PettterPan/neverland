package com.neverland.finddream.leetcode;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/15 3:39 PM
 *
 * 给定32位无符号整数，求它的反转位。例如：
 *
 *
 *
 * 输入：43261596
 *
 * 输出：964176192
 *
 * 说明：43261596以二进制表示为00000010100101000001111010011100，
 *
 * 964176192以二进制表示为00111001011110000010100101000000
 */
public class reverseBits {

    public  static void main(String args[]){
        System.out.println(100  >> 2);
        System.out.println(100  >>> 2);
        System.out.println(100  << 1);

    }


    /*
        既然是做位运算，那么依次将原数从左往右移动一位，
        取出移动的位判断是0还是1，然后加到反转的结果上，
        并且反转的结果是从右往左移动一位，
        循环控制的次数为32次，
        因为是32位整数。
        其中涉及到左移、右移、与（&）运算，
        与（&）运算的规则是相同的位上均为1时结果为1，
        否则结果为0，而左移、右移的规则就是从右往左补0和从左往右补0了。
    */

    public int reverseBits(int n) {
        int result = 0;
        for (int i=0; i<32; i++) {
            if ((n & 1) == 1) {
                result = (result << 1) + 1;
            } else {
                result = result << 1;
            }
            n = n >> 1;
        }
        return result;
    }

    /*
        可以将上面的步骤再简化下，进入循环时，无论原数右移出来的位是0还是1，都需要结果值左移一位，对此我们可以进行或（|）运算操作。
        先将结果值左移一位，然后计算n和1的与（&）运算结果，再将两数做或（|）运算，如果n和1的与（&）运算结果为1，那么结果值就加1，为0就加0。
        或（|）运算的规则是当两边操作数的位有一边为1时，结果为1，否则为0
  */

    public int reverseBits2(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>= 1;
        }
        return result;
    }

/*先将原数转为二进制字符串，再利用StringBuilder的reverse方法得到反转的二进制字符串，再将二进制字符串变为整数返回。*/
    public int reverseBits3(int n) {
        String inputBinary = this.decimalToBinary(n);
        String reversedInputBinary = new StringBuilder(inputBinary).reverse().toString();
        return this.binaryToDecimal(reversedInputBinary);
    }

    private String decimalToBinary(int n) {
        StringBuilder sb = new StringBuilder();
        while (Integer.compareUnsigned(n, 0) > 0) {
            sb.append(Integer.remainderUnsigned(n, 2));
            n = Integer.divideUnsigned(n, 2);
        }
        while (sb.length() < 32) {
            sb.append('0');
        }
        return sb.reverse().toString();
    }

    private int binaryToDecimal(String str) {
        int res = 0;
        for (char c: str.toCharArray()) {
            res *= 2;
            res += c == '0' ? 0 : 1;
        }
        return res;
    }

    /*利用包装类Integer自带的方法，reverse()方法即可反转原数。*/

    public int reverseBits4(int n) {
        return Integer.reverse(n);
    }
}
