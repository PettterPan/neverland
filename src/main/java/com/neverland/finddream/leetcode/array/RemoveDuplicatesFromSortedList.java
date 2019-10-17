package com.neverland.finddream.leetcode.array;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 5:48 PM
 * 。给定已排序的链接列表，删除所有重复项，使每个元素只出现一次。例如：
 *
 * 输入：1-> 1-> 2
 *
 * 输出：1-> 2
 *
 *
 *
 * 输入：1-> 1-> 2-> 3-> 3
 *
 * 输出：1-> 2-> 3
 */
public class RemoveDuplicatesFromSortedList {
    class ListNode {
        ListNode next;
        int val;
    }


    //特殊情况一：当head为空时，直接返回空。
    //
    //特殊情况二：当head没有下一个节点时，肯定是没有重复节点值的，直接返回head本身。
    //
    //正常情况：既然要判断节点值是否重复，免不了循环。另外，还要考虑是否要建一个新的链表来连接最后去重的节点值。
    //
    //首先，获取head的下一个节点值，判断head.val和head.next.val是否相等，如果相等，此时head节点的下一个节点应该跳到head.next.next这里，如果不相等，那么head节点的下一个节点只用跳到head.next即可，接着进入下一次判断。由此，我们可以直接使用head本身，只是需要重新改变它的节点连接对象，也就是它改变原本每个节点的引用，而不需要额外使用新的空间来存储去重后的链表。

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = first.next;
        while (second != null) {
            if (first.val == second.val) {
                second = second.next;
                first.next = second;
            } else {
                first = first.next;
                second = second.next;
            }
        }
        return head;
    }
}
