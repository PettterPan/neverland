package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 3:12 PM
 * 给定一个字符串，确定它是否是回文，只考虑字母数字字符并忽略大小写。空字符串是有效回文。例如：
 *
 * 输入："A man, a plan, a canal: Panama"
 *
 * 输出：true
 *
 *
 *
 * 输入："race a car"
 *
 * 输出：false
 */
public class ValidPalindrome {
  /*特殊情况一：当传入的字符串为null时，直接返回true。
    特殊情况二：当传入的字符串去掉空格后的长度为0时，直接返回true。
    正常情况：因为传入的字符串可能包含特殊字符，如空格、","、":"、"?"等，这些都是非字母、数字字符，这对我们的判断增加了难度，但是其核心思想是不变的。
首先可以将字符串全部转为小写，然后建立两个索引，一个从前往后，一个从后往前，依次获取相应的字符，在判断是否相等前，先要判断获取到的字符是否是字母、数字，不是字母和数字，索引向前移动一位，否则判断两个字符是否相等，不等于直接返回false，如果等于，索引向前移动一位，继续循环，直到遍历完判断完所有的字符。*/

    public boolean isPalindrome(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            Character c1 = s.charAt(left);
            Character c2 = s.charAt(right);
            if (!isAlphaNumeric(c1)) {
                left++;
            }else if (!isAlphaNumeric(c2)) {
                right--;
            } else {
                if (c1 != c2) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static boolean isAlphaNumeric(Character c){
        if (c >= 'a' && c<= 'z') {
            return true;
        }
        if (c >= 'A' && c<= 'Z') {
            return true;
        }
        if (c >= '0' && c<= '9') {
            return true;
        }
        return false;
    }

//    此解法和第一种解法思路一样，但是借助了Character.isLetterOrDigit()方法来帮我们判断获取到的字符是否为字母或者数字。

    public boolean isPalindrome2(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            Character c1 = s.charAt(left);
            Character c2 = s.charAt(right);
            if (!Character.isLetterOrDigit(c1)) {
                left++;
            } else if (!Character.isLetterOrDigit(c2)) {
                right--;
            } else {
                if (c1 != c2) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    /*
    * 此解法不那么正规，利用字符串本身的一些方法以及StringBuffer类的reverse()方法来完成判断。
    * 先将传入的字符串转为小写，并且去掉空格，然后利用正则，将非字母、数字的其他字符全部替换掉
    * ，再利用StringBuffer的reverse()方法，最后判断两字符串是否相等。*/
    public boolean isPalindrome3(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        String newString = s.toLowerCase().replace(" ", "").replaceAll("\\W", "");
        String newString2 = new StringBuffer(newString).reverse().toString();
        return newString.equals(newString2);
    }

    public static void main(String[] args) {
        ValidPalindrome instance = new ValidPalindrome();
        String arg = "A man, a plan, a canal: Panama";
        long start = System.nanoTime();
        boolean result = instance.isPalindrome(arg);
        long end = System.nanoTime();
        System.out.println("isPalindrome---输入："+arg+" , 输出："+result+" , 用时："+(end-start)/1000+"微秒");

        long start2 = System.nanoTime();
        boolean result2 = instance.isPalindrome2(arg);
        long end2 = System.nanoTime();
        System.out.println("isPalindrome2---输入："+arg+" , 输出："+result2+" , 用时："+(end2-start2)/1000+"微秒");

        long start3 = System.nanoTime();
        boolean result3 = instance.isPalindrome3(arg);
        long end3 = System.nanoTime();
        System.out.println("isPalindrome3---输入："+arg+" , 输出："+result3+" , 用时："+(end3-start3)/1000+"微秒");
    }

}
