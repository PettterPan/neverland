package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 4:38 PM
 *
 * 给定两个任意字符串haystack、needle，返回haystack中第一次出现needle的索引，如果needle不是haystack的一部分，则返回-1。如果needle为空串，则返回0。例如：
 *
 * 输入：haystack =“hello”，needle =“ll”
 *
 * 输出：2
 *
 *
 *
 * 输入：haystack =“aaaaa”，needle =“bba”
 *
 * 输出：-1
 *
 *
 *
 * 输入：haystack =“”，needle =“”
 *
 * 输出：0
 */
public class ImplementstrStr {

    //haystack依次从0开始截取长度和needle一致的字符串，再与needle比较是否一致，能够匹配上则返回循环到的下标索引，否则返回-1。

    public int strStr(String haystack, String needle) {
        if ((haystack.isEmpty() && needle.length()>0) || needle.length() > haystack.length()) {
            return -1;
        }
        if (needle.isEmpty()  || (haystack.isEmpty()&&needle.isEmpty())) {
            return 0;
        }
        int j = needle.length();
        for(int i=0; j<=haystack.length(); i++,j++){
            if(needle.equals(haystack.substring(i, j))){
                return i;
            }
        }
        return -1;
    }

    //直接使用字符串本身的indexOf()方法。
    public int strStr2(String haystack, String needle) {
        int findResult = haystack.indexOf(needle);
        return findResult;
    }
}
