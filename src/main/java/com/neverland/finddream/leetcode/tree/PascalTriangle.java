package com.neverland.finddream.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/17 2:16 PM
 * 给定非负整数numRows，生成Pascal三角形的第一个numRows。例如：
 *
 * 输入: 5
 *
 * 输出:
 *
 * [
 *
 * [1],
 *
 * [1,1],
 *
 * [1,2,1],
 *
 * [1,3,3,1],
 *
 * [1,4,6,4,1]
 *
 * ]
 */
public class PascalTriangle {


    //对于一个五层的三角形，每一层的首尾元素都是1，从第三层开始，中间元素为上一层对应下标元素与其前一元素之和，并且每一层的元素都要获取并存放于list中，前一层部分相邻元素之和等于下一层元素。
    //
    //特殊情况：当输入的numRows小于等于0时，直接返回空list
    // 上面的解法使用了两层循环，时间复杂度是O(numRows^2)，对于此种问题，这已经是最优的时间复杂度了。



    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i=0; i < numRows; i++) {
            List<Integer> list2 = new ArrayList<Integer>();
            for (int j=0; j<=i; j++) {
                if (j == 0 || j == i) {
                    list2.add(1);
                } else {
                    list2.add(list.get(i-1).get(j-1)+list.get(i-1).get(j));
                }
            }
            list.add(list2);
        }
        return list;
    }
}
