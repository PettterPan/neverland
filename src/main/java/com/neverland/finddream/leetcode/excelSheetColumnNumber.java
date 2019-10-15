package com.neverland.finddream.leetcode;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/15 5:11 PM
 * 给定Excel工作表中显示的列标题，返回其对应的列号。例如：
 *
 * A - > 1
 * B - > 2
 * C - > 3
 * Z - > 26
 * AA - > 27
 * AB - > 28
 * 输入：“A”
 *
 * 输出：1
 *
 * 输入：“AB”
 *
 * 产量：28
 *
 * 输入：“ZY”
 *
 * 输出：701
 */
public class excelSheetColumnNumber {
    public static void main(String args[]){


            System.out.println('C' - 'A');
    }
    /*
    *   这和前天的题目根据数字输出字符串列名类似，不过今天这道题是反过来的，根据列名字符串输出代表的数字。
        既然是反过来，那么此处就应该是要用乘法了，并且我们也可以观察几组数据得到大致的算法。
        "AA"27，可以看做26x1+1
        "AAA"表示数字703，可以看做26x(26+1)+1
        "AAAA"表示数字18279，可以看做 26x(26x(26+1)+1)+1
        每次循环时，新的sum等于上一次循环的sum乘以26加上当前循环字符所表示的数字。
        特殊情况：当字符串为空或者去掉空格后的长度等于0时，直接返回0。
    * */
    public int titleToNumber(String s) {
        int sum = 0;
        if (s.isEmpty() || s.trim().length() == 0) {
            return 0;
        }
        int len = s.length()-1;
        for (int i=0; i<=len; i++) {
            sum = (s.charAt(i)-'A' + 1) + sum * 26;
        }
        return sum;
    }
    /*我们同样以上面的三个字符串举例。
    "AA"表示数字28，可以看做1乘以26的一次方加上1乘以26的0次方。
    "AAA"表示数字703，可以看做1乘以26的2次方加上1乘以26的1次方再加上1乘以26的0次方。
    "AAAA"表示数字18279，可以看做1乘以26的3次方加上1乘以26的2次方再加上1乘以26的1次方再加上1乘以26的0次方。
    这种就类似二进制数转十进制数一样，变成了26进制数转十进制数。
    特殊情况：当字符串为空或者去掉空格后的长度等于0时，直接返回0。
    Math.pow(x,y)方法可返回 x 的 y 次幂的值。
*/

    public int titleToNumber2(String s) {
        int res = 0;
        if (s.isEmpty() || s.trim().length() == 0) {
            return 0;
        }
        int col = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            res += (s.charAt(i) - 64) * Math.pow(26, col--);
        }
        return res;
    }

}
