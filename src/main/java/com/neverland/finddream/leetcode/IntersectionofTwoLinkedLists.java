package com.neverland.finddream.leetcode;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 11:14 AM
 *
 * 编写程序以找到两个单链表交叉的节点。例如：
 * 以下两个链表：
 * A：        a1→a2
 *
 *                            ↘
 *
 *                                 c1→c2→c3
 *
 *                            ↗
 *
 * B：b1→b2→b3
 *
 * 链表A和链表B在c1处相交。
 *
 * 注意：
 *
 * 如果两个链接列表根本没有交集，则返回null。
 *
 * 函数返回后，链接列表必须保留其原始结构。
 *
 * 可以假设整个链接结构中没有任何环。
 *
 * 代码最好在O（n）时间内运行，并且只使用O（1）内存。
 */
public class IntersectionofTwoLinkedLists {

    class ListNode{
        ListNode next;
        ListNode pre;
        int val ;
    }

    /*这两个单链表，也可以看做是两数组，找出其中重复元素开始位置的值，
        最直接的办法就是上两层循环，外层遍历链表A，内层循环遍历链表B，
        直到遇到相交的节点为止，否则返回空。
        特殊情况：当两链表中有一个为空的话，直接返回空。
        但是此种解法的时间时间复杂度是O(n^2)，最坏的情况是两链表的长度都是n，并且相交的节点在最后位置或者没有相交的点。
        */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode B = headB;
        while (headA != null) {
            while (headB != null) {
                if (headA == headB) {
                    return headA;
                }
                headB = headB.next;
            }
            headA = headA.next;
            headB = B;
        }
        return null;
    }

    /*
    * 先来看个例子，假设A和B两人要比武，A的功夫等级是10级，B的功夫等级是8级，
    * B觉得这样比武不公平，就让A把实力压制到8级，再去和B比武，此时两人的功夫等级都是8级，
    * 于是开始了一场惊天动地的比武...
      回到此题中来，因为无法确定两个链表的长度是否相等，就算两者有交点，
      但是起点不一样，还是会错过，无法找到交点。这时，我们就需要判断两链表的长度，重新定义好起点，才能开始遍历节点。
如果链表A长度大于链表B的长度，此时链表A就需要先向前移动两者之间长度差值个节点，然后才能和B开始依次遍历比较，反之B也是如此。
    * */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int len1 = findLength(headA);
        int len2 = findLength(headB);
        int diff = (len1 >= len2) ? (len1 - len2) : (len2 - len1);
        if (len1 >= len2) {
            while (diff > 0) {
                headA = headA.next;
                diff--;
            }
        } else {
            while (diff > 0) {
                headB = headB.next;
                diff--;
            }
        }
        while (headA != null || headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    public int findLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len += 1;
            head = head.next;
        }
        return len;
    }

    /*
    还记得之前那道判断单链表是否有环的题目吗？
    没错，此题我们也可以借助环的思路来解题。
    当遍历A链表时，如果遍历到了最后一位节点，此时跳转到B链表的起始节点，然后开始遍历B链表；
    当遍历B链表时，如果遍历到了最后一位节点，此时跳转到A链表的起始节点，然后开始遍历A链表。
    此时就相当于在遍历一条由链表A和链表B组合起来的新链表，如果此新链表存在环，那么表示两链表是有交点的，如果遍历完都没有碰到环，说明两链表是没有交点的。
    */

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {
            if (A != null) {
                A = A.next;
            } else {
                A = headB;
            }
            if (B != null) {
                B = B.next;
            } else {
                B = headA;
            }
        }
        return A;
    }
}
