package com.neverland.finddream.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 3:48 PM
 *
 * 给定非负索引k，其中k≤33，返回Pascal三角形的第k个索引行。行索引从0开始。在Pascal的三角形中，每个数字是它上面两个数字的总和。例如：
 *
 * 输入: 2
 *
 * 输出: [1,2,1]
 *
 *
 *
 * 输入: 3
 *
 * 输出: [1,3,3,1]
 *
 *
 */
public class PascalsTriangleII {

/*
    昨天的那道题，是要求输出所有层的数据，今天这题只要求具体某一行的数据，那是不是完全可以借用昨天的代码，
    最后返回具体某一层即可？这种解法当然是可行的，但是题目有个提示，要求空间复杂度是O(k)，
    其中k代表某一行。而昨天的解法，最后的空间复杂度是O(numRows^2)，那能不能把空间复杂度再缩小点？答案是可以的。
    我们可以先将list中的每个元素初始值设为1，然后开始循环处理。外层循环控制层数，进入内层循环，改变具体位置元素的值。我们  知道第k层第j（j>=1）个元素的值是第k-1层第j-1个元素和第j个元素之和，如果按照从左至右的顺序，后面元素的值需要借助前面   元素的值的时候，是已经被改变过的值，最后的结果会失真，所以我们采用从右至左的顺序设值。

注意：list.set(j, value)此方法是找到索引j所在元素，将value设值给它，不会新增元素，而add(value)是会新增元素的。
*/

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        for (int k = 0; k <= rowIndex; k++) {
            list.add(1);
        }
        for (int i = 1; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                list.set(j, list.get(j - 1) + list.get(j));
            }
        }
        return list;
    }


    /*同样是第一种解法的思路，但是我想从左至右设值怎么办？

既然想要从左至右设值，那就需要左边的数据是新的，而不是已经被计算过的新值，就需要把每次计算过的值往后移动一位，此时我们就无法在开始时就将list的初始元素值设为1，二是要在循环中添加新的元素，从而把旧的元素往后挪一位。

外层循环一方面是控制层数，另外一个方面就是每次在list的索引0位置新增元素1。只有当r等于2时，才会进入内层循环，此时list中至少有三个元素。

因为首尾元素都是1，所以内层循环的索引起始位置是1（第二位），依次往右重新设值。*/

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for (int r = 0; r <= rowIndex; r++) {
            list.add(0, 1);
            for (int i = 1; i < r; i++) {
                list.set(i, list.get(i) + list.get(i + 1));
            }
        }
        return list;
    }

    /*我们还可以使用数组来操作此题，思路与昨天的那道题大致类似。

外层循环每次创建一个新的数组，数组长度是i+1，并且此数组的首尾元素是为1，然后开始内层循环，新数组的第j（j>=1）位元素是上一数组中的第j-1位和第j位元素之和。在结束内层循环后，还要将旧数组指向经过内层循环赋值完后的新数组。最后，将数组里面的元素遍历赋值给list。*/
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        int[] result = new int[1];
        for (int i = 0; i <= rowIndex; i++) {
            int[] next = new int[i + 1];
            next[0] = 1;
            next[i] = 1;
            for (int j = 1; j < i; j++) {
                next[j] = result[j - 1] + result[j];
            }
            result = next;
        }
        for (int in : result) {
            list.add(in);
        }
        return list;
    }

    public static void main(String[] args) {
        PascalsTriangleII instance = new PascalsTriangleII();
        int rowIndex = 3;
        long start = System.nanoTime();
        List<Integer> list = instance.getRow(rowIndex);
        long end = System.nanoTime();
        System.out.println("getRow---输入："+rowIndex+" , 输出："+list+" , 用时："+(end-start)/1000+"微秒");
        long start2 = System.nanoTime();
        List<Integer> list2 = instance.getRow2(rowIndex);
        long end2 = System.nanoTime();
        System.out.println("getRow2---输入："+rowIndex+" , 输出："+list2+" , 用时："+(end2-start2)/1000+"微秒");
        long start3 = System.nanoTime();
        List<Integer> list3 = instance.getRow3(rowIndex);
        long end3 = System.nanoTime();
        System.out.println("getRow3---输入："+rowIndex+" , 输出："+list3+" , 用时："+(end3-start3)/1000+"微秒");

    }
}
