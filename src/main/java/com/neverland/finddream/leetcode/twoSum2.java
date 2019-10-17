package com.neverland.finddream.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/15 7:07 PM
 *
 * 给定已按升序排序的整数数组，找到两个数字，使它们相加到特定的目标数。函数twoSum应该返回两个数字的索引，使它们加起来到目标，其中index1必须小于index2。
 *
 * 注意：
 *
 * 返回的答案（index1和index2）不是从零开始的。
 *
 * 可以假设每个输入只有一个解决方案，并且您可能不会两次使用相同的元素。
 *
 *
 *
 * 例如：
 *
 * 输入：数字= [2,7,11,15]，目标= 9
 *
 * 输出：[1,2]
 *
 * 说明：2和7之和为9.因此index1 = 1，index2 = 2。
 */
public class twoSum2 {
    /*此题和LeetCode算法题的第一题Two Sum类似，不过此题定义和条件更加清晰，
        规定了只存在唯一解,并且将数组进行了排序。
        和以前一样，
        第一种解法是暴力解法，使用双层循环，依次遍历相加判断和是否等于目标值，
        直到找到两元素为止。但是元素的索引需要加1，因为题目要求       索引不从0开始。
       特殊情况：当数组为空或者其长度小于2时，直接返回空。
      此解法时间复杂度是O(n^2)，空间复杂度是O(1)。

*/
    public int[] twoSum (int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int[] index = new int[2];
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i+1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    index[0] = i+1;
                    index[1] = j+1;
                }
            }
        }
        return index[0] == 0 ? null : index;
    }

    /*
        使用HashMap，遍历数组时，判断当前元素和目标值之间的差值是否存在map中，
        如果存在，就返回map中此元素的value，即其索引，和当前元素的索引；
        否则，将当前元素作为key，索引作为value存入map中，然后进行下一次循环。
        特殊情况：当数组为空或者其长度小于2时，直接返回空。
    */

    public int[] twoSum2 (int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int[] index = new int[2];
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int k=0; k<numbers.length; k++) {
            if (map.containsKey(target-numbers[k])) {
                index[0] = map.get(target-numbers[k]);
                index[1] = k+1;
            }
            map.put(numbers[k], k+1);
        }
        return index[0] == 0 ? null : index;
    }
    /*
        既然数组已经是排过序的，使用双指针，每次从前往后和从后往前各取一个元素，
        判断两数之和是否等于目标值，如果小于目标值，则从前往后的指针向前移动一位；
        如果大于目标值，则从后往前的指针向前移动一位，直到两数之和等于目标值即可。
        特殊情况：当数组为空或者其长度小于2时，直接返回空
        此解法的时间复杂度是O(n)，空间复杂度是O(1)。
        */

    public int[] twoSum3(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                return new int[] {start+1, end+1};
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return null;
    }
}
