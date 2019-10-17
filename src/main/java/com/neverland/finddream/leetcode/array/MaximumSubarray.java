package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 5:16 PM
 *
 * 给定一个整数数组nums，找出一个最大和，此和是由数组中索引连续的元素组成，至少包含一个元素。例如：
 *
 * 输入：[-2, 1, -3, 4, -1, 2, 1, -5,4]
 *
 * 输出：6
 *
 * 说明：[4，-1,2,1]具有最大的和为6
 *
 *
 *
 * 输入：[1, 2, 3]
 *
 * 输出：6
 *
 * 说明：[1, 2, 3]具有最大的和为6
 */
public class MaximumSubarray {

/*因为本题最后输出的是最大值，所以需要进行求和，并且要从第一位元素开始，依次和相邻元素相加来判断。
    第一次循环，得到数组第一个元素，与0相加，此时最大值是元素本身。
    第二次循环，得到数组第二个元素，与第一个元素相加，此时相加的和需要先判断是否大于第二个元素本身，因为如果两个数的和还没有本身大，那么此时最大和就是第二个元素本身。其次，还要和上一个和判断，如果大于第一次循环得到的和，那么新的最大和即为第一个元素和第二个元素之和或者第二个元素本身；反之最大和依旧是第一次循环后的最大和。

后面的循环与上面一致，最开始第一次的循环也是如此，为了方便对比，只是详细说明了第二次循环的处理逻辑。*/
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] > sum) {
                sum = nums[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

//对于上面的代码，我们还可以再简化下。
    public int maxSubArray2(int[] nums) {
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = Math.max(nums[i] + sum, nums[i]);
            result = Math.max(result, sum);
        }
        return result;
    }

//还有一种思路，就是分而治之，将大问题拆分成小问题，找到小问题的答案后，最后合在一起再得出最后的答案。下面的代码是讨论区里某位大神的，可以好好看下。
    public int maxSubArray3(int[] a) {
        return helper(a, 0, a.length - 1);
    }

    int helper(int[] a, int l, int r) {
        if(l > r) return Integer.MIN_VALUE;
        if(l == r) return a[l];
        int mid = l + (r - l)/2;
        return Math.max(crossMidMax(a, l, r), Math.max(helper(a, l, mid - 1), helper(a, mid + 1, r)));
    }

    int crossMidMax(int[] a, int l, int r) {
        int mid = l + (r - l)/2;

        int lmax = a[mid], lg =  a[mid];
        for(int i = mid -1; i >= l; i--) {
            lmax += a[i];
            lg = Math.max(lmax, lg);
        }

        int rmax = a[mid], rg =  a[mid];
        for(int i = mid +1; i <= r; i++) {
            rmax += a[i];
            rg = Math.max(rmax, rg);
        }

        return lg + rg - a[mid];
    }
}
