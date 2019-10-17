package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 5:18 PM
 * 给定一个字符串，包含戴尔字母、小写字母和空格，返回最后一个单词的长度，如果最后一个单词不存在则返回0。另外，单词不包含空格。例如：
 *
 * 输入: "Hello World"
 *
 * 输出: 5
 *
 * 说明：最后一个单词为world，其长度为5
 */
public class LengthOfLastWord {

    /*第一步考虑特殊情况，传入的字符串全部由空格组成，这时可以直接返回0。

第二步，获取最后一个单词结束字符的位置。

既然是获取最后一个单词，那么最后一个单词的情况分两种：以单词结尾；不已单词结尾，后面还带了空格。

先将字符串转为字符数组，从后往前依次获取每一个字符，如果遇到空格，继续向前循环，直到第一个字符；反之则表示遇到了一个单词，将其位置记录为end。

第三步，获取最后一个单词开始字符的位置。接着第二步的索引继续向前判断，不过判断的条件变成了不等于空格，直到条件不满足，则表示此单词已经查找完毕，将其位置记录为start。

第三步，用end减去start，即为最后一个单词的长度。*/

    public int lengthOfLastWord(String s) {
        if("".equals(s.trim())) { return 0; }
        int n = s.length()-1;
        char[] ch = s.toCharArray();
        while (n >= 0 && ch[n] == ' ') {
            n--;
        }
        int end = n;
        while(n >= 0 && ch[n] != ' ') {
            n--;
        }
        int start = n;
        return end - start;
    }

    /*既然此字符串是由大小写字母和空格组成，那么是否可以使用空字符串将其分割为多个子字符串？如果原字符串不包含空格，那么分割后还是该字符串；如果包含字符串，那么最后一个被分割出来的子字符串就是我们想要的最后的一个单词。和第一种解法最开始一样，特殊情况也是要考虑进去的。*/
    public int lengthOfLastWord2(String s) {
        if("".equals(s.trim())) { return 0; }
        if (s.length() ==0 || s.indexOf(" ") == -1) {
            return s.length();
        }
        String[] arr = s.split(" ");
        return arr[arr.length-1].length();
    }

//    将原字符串首尾的空格去掉，然后找到最后一次出现空格的位置，两者相减再减1即为最后单词的长度。

    public int lengthOfLastWord3(String s) {
        return s.trim().length()-s.trim().lastIndexOf(" ")-1;
    }
}
