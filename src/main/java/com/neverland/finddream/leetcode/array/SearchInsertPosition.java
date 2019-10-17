package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 4:45 PM
 * 给定排序数组和目标值，如果找到目标，则返回索引。 如果没有，请返回索引按顺序插入的索引。假设数组中没有重复项。例如：
 *
 * 输入：[1,3,5,6]，5
 *
 * 输出：2
 *
 *
 *
 * 输入：[1,3,5,6]，2
 *
 * 输出：1
 *
 *
 *
 * 输入：[1,3,5,6]，7
 *
 * 输出：4
 *
 *
 *
 * 输入：[1,3,5,6]，0
 *
 * 输出：0
 */
public class SearchInsertPosition {

    //首先排除几种特殊情况，然后顺位循环，拿每一个元素与目标值比较。
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0 || nums[0] > target) {
            return 0;
        }
        if(nums[nums.length-1] < target){
            return nums.length;
        }
        int result = 0;
        for(int i=0; i<nums.length; i++){
            if (nums[i] < target) {
                result++;
            }
            if (nums[i] == target) {
                return i;
            }
        }
        return result;
    }

//使用二分法快速定位，取中间位索引判断值，直到匹配上。
    public int searchInsert2(int[] nums, int target) {
        int L = 0;
        int R = nums.length-1;
        while (true) {
            if (target <= nums[L]) {
                return L;
            }
            if (target > nums[R]) {
                return R+1;
            }
            int mid = (L+R)/2;
            if (target <= nums[mid]) {
                R = mid-1;
            }
            if (target > nums[mid]) {
                L = mid+1;
            }
        }
    }
}
