package com.neverland.finddream.leetcode.array;

import java.util.Arrays;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 6:06 PM
 * 给定两个排序的整数数组nums1和nums2，将nums2中的元素合并到nums1中，并且作为一个排序的数组。在nums1和nums2中初始化的元素个数分别为m和n。假设nums1有足够的空间（大于或等于m + n）来保存nums2中的其他元素。例如：
 *
 * 输入：nums1 = [1,2,3,0,0,0]，m = 3，nums2 = [2,5,6]，n = 3
 *
 * 输出：[1,2,2,3,5,6]
 */



public class MergeSortedArray {

    //给的条件很清晰，nums1的长度大于等于m+n的和，nums2的元素是肯定可以全部放进nums1的，既如此，那我们是不是可以先把nums2中的元素添加进nums1，从下标m开始，移入n个新的元素，然后再对nums1进行排序，最后得到新的nums1数组。排序算法使用了冒泡排序，当然使用其他的排序算法也是可以的。
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i=m, k=0; i<nums1.length && k < n ; i++,k++) {
            nums1[i] = nums2[k];
        }
        for (int j=0; j<nums1.length-1; j++) {
            for (int h=0; h<nums1.length-1-j; h++) {
                if (nums1[h] > nums1[h+1]) {
                    int temp = nums1[h+1];
                    nums1[h+1] = nums1[h];
                    nums1[h] = temp;
                }
            }
        }
    }

    //上面的解法的时间复杂度是O(n^2)，那能不能再降低点？让其更快一点？让我们来借助例子分析下。
    //
    //nums1 = [1,2,3,0,0,0]，m = 3
    //
    //nums2 = [2,5,6]，n = 3
    //
    //既然最后结果是将新的nums1排序，那么按照升序来排，两个数组中最大的一个值肯定在第m+n位。
    //
    //第一步，分别获取nums1的第3位元素、nums2的第3位元素，比较两数大小，数值大的放在nums1的第6位，此时nums1变成了[1,2,3,0,0,6]。
    //
    //第二步，分别获取nums1的第3位元素、nums2的第2位元素，比较两数大小，数值大的放在nums1的第5位，此时nums1变成了[1,2,3,0,5,6]。因为第一步是将nums2的元素移到了nums1，所以nums2向前移动一位，但是nums1的第3位并没有完全确定大小，所以保持不动。
    //
    //第三步，分别获取nums1的第3位元素、nums2的第1位元素，比较两数大小，数值大的放在nums1的第4位，此时nums1变成了[1,2,3,3,5,6]。
    //
    //第四步，分别获取nums1的第2位元素、nums2的第1位元素，比较两数大小，数值大的放在nums1的第3位，此时nums1变成了[1,2,2,3,5,6]。
    //
    //依据上面的步骤，可以很快的写出代码，但是有种特殊情况要考虑，当nums1的最小值，也就是nums1的第一位元素，它比nums2的最大值都大的时候，nums2的元素是要全部移到nums1原元素的前面，此时在循环完后，就要加多一个判断，判断nums2的元素下标是否已经移动到了第一位。

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[i + j + 1] = nums1[i];
                i--;
            } else {
                nums1[i + j + 1] = nums2[j];
                j--;
            }
        }
        while (j >= 0) {
            nums1[i + j + 1] = nums2[j];
            j--;
        }
    }

    public static void main(String[] args) {
        MergeSortedArray instance = new MergeSortedArray();
        int[] nums1 = {1,2,2,3,4,5,0,0,0};
        int m = 6;
        int[] nums2 = {2,5,8};
        int n = 3;

        long start = System.nanoTime();
        instance.merge(nums1, m, nums2, n);
        long end = System.nanoTime();
        System.out.println("merge---输出："+Arrays.toString(nums1)+" , 用时："+(end-start)/1000+"微秒");

        int[] nums3 = {1,2,2,3,4,5,0,0,0};
        int m2 = 6;
        int[] nums4 = {2,5,8};
        int n2 = 3;
        long start2 = System.nanoTime();
        instance.merge2(nums3, m2, nums4, n2);
        long end2 = System.nanoTime();
        System.out.println("merge2---输出："+Arrays.toString(nums1)+" , 用时："+(end2-start2)/1000+"微秒");
    }
}
