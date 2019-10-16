package com.neverland.finddream.leetcode.List;

import java.util.HashSet;
import java.util.Set;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 11:48 AM
 *
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 *
示例 2：

输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。


示例 3：

输入：head = [1], pos = -1
输出：false
解释：链表中没有环


进阶：

你能用 O(1)（即，常量）内存解决此问题吗？


什么样结构的链表才算是拥有一个循环呢？

链表中某一节点的引用指向了当前链表中已经存在的另一节点时，此链表存在循环。

ListNode L = new ListNode(1);
ListNode L2 = new ListNode(2);
ListNode L3 = new ListNode(3);
ListNode L4 = new ListNode(4);
ListNode L5 = new ListNode(5);
ListNode L6 = new ListNode(6);
L.next = L2;
L2.next = L3;
L3.next = L4;
L4.next = L5;
L5.next = L3;
上面的链表就存在循环，L5的引用指向了L3。如果仅仅是节点值相等，是不存在循环的，因为他们的引用始终不同

 */
public class LinkedListCycle {

    /*
    可以打个比方，如果两个人甲和乙同时在一条跑道上跑步，甲以2.5m/s的速度向前跑，
    乙以5m/s的速度向前跑，如果跑道是环形的，甲和乙肯定会在某一个地方至少相遇一次；
    如果跑道是直线形或非环形的，甲和乙是不会相遇的。
    使用双指针，一个是正常向前，一次移动一个节点，第二个是间隔一个节点向前移动，如果第二个指针碰到了可以循环的节点，会在循 环的过程中遇到第一个指针，此时就满足了形成循环的条件。
特殊情况：链表为空时，直接返回false。
*/

    class ListNode {
        ListNode pre;
        ListNode next;
        int val ;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /*还是以上面跑步为例，不过稍微有点变化。甲还是以2.5m/s的速度跑步，
    此时乙在跑道中给了甲一根棒子，并站在原地等甲把棒子还给自己，
    如果跑道是环形的，乙肯定是会等到甲把棒子还给自己，如果跑道是直线或者非环形的
    ，甲就带着棒子自己跑了。
    我们可以使用一个标记，赋值给每一个节点，然后使用递归，直到碰到给出去的标志为止。
    如果某一个节点正好本身就拥有所要赋值的标志呢？会有一定影响，如果限制节点值的取值范围，此解法是完全可行的。
    */

    public boolean hasCycle2(ListNode head) {
        if(head == null) {
            return false;
        }
        if (head.val == Integer.MIN_VALUE-1) {
            return true;
        }
        head.val = Integer.MIN_VALUE-1;
        return hasCycle2(head.next);
    }
/*使用哈希表，将每一个节点的引用都存起来，如果哈希表中存在了某一个节点的引用，说明此链表是循环的。
* 此解法的空间复杂度是O(n)。
*/
    public boolean hasCycle3(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }



}
