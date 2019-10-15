package com.neverland.finddream.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/15 5:27 PM
 *
 * 给定大小为n的数组，找到数组中出现次数超过n/2的元素。假设该数组非空，并且该元素始终存在于数组中。例如：
 *
 * 输入：[3,2,3]
 *
 * 输出：3
 *
 *
 *
 * 输入：[2,2,1,1,1,2,2]
 *
 * 输出：2
 */
public class majorityElement {

    /*
    我们可以先将数组排序，然后使用for循环遍历前n-1个元素，使用count计算元素出现的次数。
    如果第i个元素与第i+1个元素相等，并且i等于数组长度减2，那么count要加两次，否则只用加1此，接着判断count是否大于n/2，如果大于 则返回第i个元素。
    如果第i个元素与第i+1个元素不相等，这时需要判断i是否大于0并且第i个元素与第i-1个元素是否相等，如果相等，count加1，接着判断count是否大于n/2，如果大于则返回第i个元素，如果不相等，count归为0，再重新开始循环。
    因为题目已经表示数组不会为空，所以不需要考虑特殊情况，但是有一点就要考虑，那就是如果没有找到那个出现次数大于n/2的元素，需要返回数组最后一个元素。
    此解法的时间复杂度是O(nlog(n))+O(n)，空间复杂度是O(1)。
    */

    public int majorityElement(int[] nums) {
        int size = nums.length / 2;
        int result = nums[nums.length - 1];
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                if (i == nums.length - 2) {
                    count++;
                }
                count++;
                if (count > size) {
                    return nums[i];
                }
            } else {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    count++;
                    if (count > size) {
                        return nums[i];
                    }
                } else {
                    count = 0;
                }
            }
        }
        return result;
    }

    /*
    还是先利用Arrays的排序方法，将数组排序。题目要找的是出现次数大于n/2的元素，
    那么排过序后，假如存在该元素，那么数组的中间位元素，即nums[n/2]应该就是该元素。
    但是，还需要一步判断，如果数组长度是偶数，比如是4，那么中间位元素是nums[2]，
    即是数组第三位元素，这时就需要判断nums[1]和nums[0]是否相等了，如果相等那么返回nums[0]或者nums[n/2 - 1]都行。
    此解法的时间复杂度是O(n log(n))，空间复杂度是O(1)。
    */
    public int majorityElement2(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        Arrays.sort(nums);
        if (len%2 == 0 && nums[0] == nums[len/2 - 1]) {
            return nums[0];
        }
        return nums[len/2];
    }

    /*取一临时变量candidate，存储出现次数大于n/2的元素。取一临时变量count，存储出现次数.
      进入循环，判断count是否等于0，等于0，就将当前元素赋值给candidate。接着判断当前元素是否和candidate相等，相等的话count加1，否则减1，直到遍历完所有元素。
      此解法，关键在于，碰到当前重复元素就加1，没碰到就减1，如果最后count不等于0，当前重复元素就是出现次数最多的元素，如果count变成0了，说明之前遇到的重复元素和不重复元素出现次数是相等的，相互抵消了，就需要重新去获取当前重复元素了。
      此解法的时间复杂度是O(n)，空间复杂度是O(1)。

      */

    public int majorityElement3(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    /*
    使用HashMap，key存储数组元素，value存储元素出现的次数，如果最后value值大于n/2，那么该key就是出现次数最多的元素
    此解法的时间复杂度是O(n)，空间复杂度是O(n)。
    * */
    public int majorityElement4(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int freq = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                freq = map.get(nums[i]) + 1;
                map.put(nums[i], freq);
            } else {
                freq = 1;
                map.put(nums[i], 1);
            }
            if (freq > nums.length / 2)
                return nums[i];
        }
        return nums[nums.length - 1];

    }


    public static void main(String[] args) {
        majorityElement instance = new majorityElement();
        int[] arg = { 3, 2, 3, 1, 2, 3, 2, 3};
        long start = System.nanoTime();
        int result = instance.majorityElement(arg);
        long end = System.nanoTime();
        System.out.println("majorityElement---输入:" + Arrays.toString(arg) + " , 输出:" + result + " , 用时:" + ((end - start) / 1000) + "微秒");
        int[] arg2 = { 3, 2, 3, 1, 2, 3, 2, 3};
        long start2 = System.nanoTime();
        int result2 = instance.majorityElement2(arg2);
        long end2 = System.nanoTime();
        System.out.println("majorityElement2---输入:" + Arrays.toString(arg2) + " , 输出:" + result2 + " , 用时:" + ((end2 - start2) / 1000) + "微秒");
        int[] arg3 = { 3, 2, 3, 1, 2, 3, 2, 3};
        long start3 = System.nanoTime();
        int result3 = instance.majorityElement3(arg3);
        long end3 = System.nanoTime();
        System.out.println("majorityElement3---输入:" + Arrays.toString(arg3) + " , 输出:" + result3 + " , 用时:" + ((end3 - start3) / 1000) + "微秒");
        int[] arg4 = { 3, 2, 3, 1, 2, 3, 2, 3};
        long start4 = System.nanoTime();
        int result4 = instance.majorityElement4(arg4);
        long end4 = System.nanoTime();
        System.out.println("majorityElement4---输入:" + Arrays.toString(arg4) + " , 输出:" + result4 + " , 用时:" + ((end4 - start4) / 1000) + "微秒");
    }
}
