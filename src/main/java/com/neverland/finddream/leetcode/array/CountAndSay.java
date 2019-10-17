package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 5:00 PM
 *
 * count-and-say序列是整数序列，前五个术语如下：
 *
 * 1. 1
 *
 * 2. 11
 *
 * 3. 21
 *
 * 4. 1211
 *
 * 5. 111221
 *
 *
 *
 * 1被读作“一个一”或者11。第二项的值是第一项的读法。
 *
 * 11被读作“两个一”或者21。第三项的值是第二项的读法。
 *
 * 21被读作“一个二，两个一”或者1211。第四项的值是第三项的读法。
 *
 *
 *
 * 给定整数n，其中1≤n≤30，生成count-and-say序列的第n项。整数序列的每个术语将表示为一个字符串。例如：
 *
 * 输入：1
 *
 * 输出：“1”
 *
 *
 *
 * 输入：4
 *
 * 输出：“1211”
 */
public class CountAndSay {

    /*第一步，界定n的范围，在1到30之间。
        第二步，处理特殊情况，n为1的时候，可以直接返回字符串1。
        第三步，通过题意，我们知道了n代表的字符串是n-1代表的字符串的读法，即n-1表示的字符串读法就是我们想要的答案。
        第一层循环，从1开始循环，直到不小于n，并且接受内层循环的处理结果当做进入下一次循环的条件。
第二层循环，对初始值为“1”的previous字符串进行处理。
第三层循环，顺位判断该字符串每一位字符重复出现的次数，此循环至少会走一次，再将每个字符和其出现的次数拼接成字符串，最后将得到此字符串的读法
。*/

    public String countAndSay(int n) {
        if (n > 30 || n < 0) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        String previous = "1";
        for (int j = 1; j < n; j++) {
            String str = "";
            for (int i = 0; i < previous.length(); i++) {
                char cc = previous.charAt(i);
                int count = 0;
                while (i < previous.length() && cc == previous.charAt(i)) {
                    count++;
                    i++;
                }
                i--;
                str = str + count + cc;
            }
            previous = str;
        }
        return previous;
    }

/*第一种解法用了三层循环，其实还可以简化下，变成两层循环,将内层的两个循环变成一个。先将字符串第一个字符作为初始条件放在外面，初始化记数为1，然后开始循环，从字符串索引1开始，判断第0位和第1位字符是否相等，相等count就加1，继续循环，如果不相等，拼接字符串，并将count还原为1，判断对象由第0位字符换成第1位字符，继续循环。*/
    public String countAndSay2(int n) {
        if (n > 30 || n < 0) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        String previous = "1";
        for (int j = 1; j < n; j++) {
            String str = "";
            char c = previous.charAt(0);
            int count = 1;
            for (int i = 1; i < previous.length(); i++) {
                char cc = previous.charAt(i);
                if (cc == c) {
                    count++;
                } else {
                    str = str + count + c;
                    count = 1;
                    c = cc;
                }
            }
            str = str + count + c;
            previous = str;
        }
        return previous;
    }

}
