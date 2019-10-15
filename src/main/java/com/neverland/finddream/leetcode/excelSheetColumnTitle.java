package com.neverland.finddream.leetcode;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/15 6:59 PM
 *
 * 给定正整数，返回Excel工作表中显示的相应列标题。例如：
 * 1 - > A.
 * 2 - > B.
 * 3 - > C.
 * 26 - > Z.
 * 27 - > AA
 * 28 - > AB
 *
 * 输入：1
 * 输出：“A”
 *
 *
 * 输入：28
 * 输出：“AB”
 *
 * 输入：701
 * 输出：“ZY”
 */
public class excelSheetColumnTitle {


/*通过题目的几个小例子，最后得到的字符串结果，可以将其拆分两部分，一部分是除以26的商，一个是除以26的余数，余数肯定是会小于26的，但是商却是有可能大于26的，这时就需要利用循环或者递归来处理。
    当n小于等于0的时候，直接返回空串。
    当n小于27的时候，是可以直接在A-Z里面取到值的，因为A本身代表1，所以要减去1。
    当n大于27的时候，这时需要判断能否被26整除，也就是对26进行取余是否等于0，如果是26的整数倍，最后一位肯定代表的是Z，而前面的数就需要除以26再减1，减1是相当于n除以26的商向前移动一位，直到处理完所有数据，此步骤是递归操作的，因为这部分的数也是符合这三步判断的。如果不能被整除，那么最后一位所代表的就是n对26取余的余数所表示的字母，即余数和字符A的和再减1，因为A本身代表的就是1，而前面部分需要处理的就是n除以26的商再进行递归处理。
    */
    public String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }
        if (n < 27) {
            return "" + (char) ('A' + n - 1);
        } else {
            if (n % 26 == 0) {
                return convertToTitle(n / 26 - 1) + "Z";
            } else {
                return convertToTitle(n / 26) + (char) ('A' + n % 26 - 1);
            }
        }
    }

    /*此解法我们使用循环来处理数据，如果不使用第一种解法的判断，遇上26或者它的倍数的时候，拿到Z？
     如果直接用n对26取余，再和字符A相加，是得不到Z的，用n除以26的商更加得不到，
     这时我们可以想个折中的办法，先把n减1，再去对26取余，然后和字符A相加，
     因为A本身代表1，就相当于减去的1又补回来了。得到字符串后，此时的n等于n减去1再除以26的商。*/

    public String convertToTitle2(int n) {
        String str = "";
        while (n != 0) {
            int rem = (n - 1) % 26;
            str = (char) (rem + 'A') + str;
            n = (n - 1) / 26;
        }
        return str;
    }
//此解法更加丧心病狂，居然只有一行，是讨论区某位大神的杰作，真是佩服，为了可读性，将其改动了下。
    public String convertToTitle3(int n) {
        if (n == 0) {
            return "";
        } else {
            return convertToTitle3(--n/26) + (char) ('A' + (n % 26));
        }
    }
}
