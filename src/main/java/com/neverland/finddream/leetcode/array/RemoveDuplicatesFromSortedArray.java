package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 4:29 PM
 * 给定一个已经排序(由小到大)的整数数组（元素可以重复），计算其中不重复元素的个数n，并将数组的前n个元素依次赋值为筛选后的不重复元素。不许使用新数组接收数据。例如：
 *
 * nums = {1,1,2}
 *
 * 输出不重复元素的个数为2
 *
 * 数组前2个元素为1和2，即nums = {1,2,2}
 */
public class RemoveDuplicatesFromSortedArray {

/*特殊情况：如果数组中元素个数小于等于1，其不重复元素的个数即为数组的长度，也不用重新给前面的元素重新赋值。
获取第一个元素，与第二个元素比较，如果两数不等，count加1，数组的新第2个元素为原数组第2个元素，依次往后循环。
最后count需要加1，因为在第一次循环两数不等的时候，count是从第二个元素开始累加的，还需要算上最开始的第一位元素。*/
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int count = 0;
        for(int i=0; i<nums.length-1; i++){
            if (nums[i] != nums[i+1]) {
                count++;
                nums[count] = nums[i+1];
            }
        }
        return count+1;
    }
}


