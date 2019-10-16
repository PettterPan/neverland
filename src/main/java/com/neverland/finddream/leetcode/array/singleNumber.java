package com.neverland.finddream.leetcode.array;

import java.util.Arrays;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 2:51 PM
 *
 * 给定一个非空的整数数组，除了一个元素外，每个元素都会出现两次。 找到那个只出现了一次的元素。例如：
 *
 * 输入：[2,2,1]
 *
 * 输出：1
 *
 *
 *
 * 输入：[4,1,2,1,2]
 *
 * 输出：4
 */
public class singleNumber {

/*
    因为已经限定传入的数组不为空，所以此题不需要考虑特殊情况。
    在解这道题之前，我们先来了解下Java中的异或（^）运算，它的计算规则是转换为二进制数后，
    两边的对应位不同时，取1，否则取0。如果遇到负数，需要用负数的补码进行计算。
    当两个相同的数做异或运算时，他们运算后的结果是0。
    当0和一个非零的数进行异或运算时，运算结果是那个非零的数。
    此题中，重复的元素都是出现两次，只会有一个元素只出现一次，那么对所有的元素进行异或运算，
    最后得到的结果就是那个只出现了一次的元素*/

    public int singleNumber(int[] nums) {
        int result = 0;
        for(int n : nums){
            result ^= n;
        }
        return result;
    }

/*我们可以先对数组进行升序排序，排序后出现两次的元素肯定是相邻的元素，
    对此可以使用count记数和相邻元素比较的方法，来找出只出现了一次的元素。
    第一次循环，count为1，此时需要判断i+1是否等于数组长度或者当前元素不等于后一元素，
    如果满足，那么再判断此时的count是否等于1，如果等于1，那么当前元素就是那个只出现了一次的元素，否则count重置为0，然后继续循环。*/
    public int singleNumber2(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count++;
            if (i + 1 == nums.length || nums[i] != nums[i + 1]) {
                if (count == 1) {
                    return nums[i];
                }
                count = 0;
            }
        }
        return -1;
    }
}
