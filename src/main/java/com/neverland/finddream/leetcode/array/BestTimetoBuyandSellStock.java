package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 3:20 PM
 * 假设有一个数组，其中第i个元素是第i天给定股票的价格。如果只被允许完成最多一笔交易（即买入并卖出一股股票），请设计算法以找到最大利润。请注意，在购买之前不能出售股票。例如：
 *
 * 输入：[7,1,5,3,6,4]
 *
 * 输出：5
 *
 * 说明：在第2天买入（价格= 1）并在第5天卖出（价格= 6），利润= 6-1 = 5。不是7-1 = 6，因为售价需要大于购买价格。
 *
 *
 *
 * 输入：[7,6,4,3,1]
 *
 * 输出：0
 *
 * 说明：在这种情况下，不进行任何交易，即最大利润= 0。
 */
public class BestTimetoBuyandSellStock {

    /*特殊情况一：当传入的数组为null时，直接返回0。
      特殊情况二：当传入的数组不为null，但是其内只有0个或者1个元素时，无法支持买入并卖出操作，直接返回0。
      正常情况：要想股票获利，需要满足低买高卖的条件，否则不获利甚至亏损。先将第1天的价格单独拿出来，接着开始遍历数组（从第二个元素开始遍历），如果第二天的价格高于第一天的价格，此时的最大利润是第二天的价格减去第一天的价格与最大利润初始值之间的最大值，否则买入价格就应该换成第二天的价格，依次向后循环，直到比较完所有的价格。最后，如果最大利润大于0，此时最大利润就是其本身，否则最大利润为0。*/
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int maxProfit = Integer.MIN_VALUE;
        int buyPrice = prices[0];
        for (int i=1; i < prices.length; i++) {
            if (prices[i] > buyPrice) {
                maxProfit = Math.max(maxProfit, prices[i]-buyPrice);
            } else {
                buyPrice = prices[i];
            }
        }
        if (maxProfit > 0) {
            return maxProfit;
        }
        return 0;
    }
// 对于上面的最大利润的初始值，也可以设为0，最后直接返回最大利润即可，就无需再加多一步判断了。

}
