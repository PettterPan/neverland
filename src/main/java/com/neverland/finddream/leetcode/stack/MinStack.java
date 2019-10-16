package com.neverland.finddream.leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author siwei.pan
 * @Description: ${todo}
 * @DATE 2019/10/16 11:31 AM
 * 设计一个支持push，pop，top和在恒定时间内检索最小元素的堆栈。
 *
 * push（x） - 将元素x推入堆栈。
 *
 * pop（） - 删除堆栈顶部的元素。
 *
 * top（） - 获取顶部元素。
 *
 * getMin（） - 检索堆栈中的最小元素。
 *
 * 例如：
 *
 * MinStack minStack = new MinStack（）;
 *
 * minStack.push（-2）;
 *
 * minStack.push（0）;
 *
 * minStack.push（-3）;
 *
 * minStack.getMin（）; - >返回-3。
 *
 * minStack.pop（）;
 *
 * minStack.top（）; - >返回0。
 *
 * minStack.getMin（）; - >返回-2。
 */
public class MinStack {

/*利用整型数组和ArrayList作为栈。
    入栈的时候，创建一个容量为2的数组，数组第一个元素是要入栈的元素，第二个元素是最小值，将数组添加到list中。
    出栈的时候，获取list的最后一个元素，并将其移除，此时的最小值是list最后一位元素（数组）的第二个值。
    获取栈顶，即是list中最后一位元素（数组）的第一个值。
    最小值直接返回最小值即可。
    */
        private List<int[]> stack ;
        private int min ;

        public MinStack() {
            stack = new ArrayList<int[]>();
        }

        public void push(int x) {
            int[] arr = new int[2];
            arr[0] = x;
            arr[1] = stack.isEmpty() ? x : Math.min(x, min);
            min = arr[1];
            stack.add(arr);
        }

        public void pop() {
            if (!stack.isEmpty()) {
                stack.remove(stack.size()-1);
                min = stack.isEmpty() ? 0 : stack.get(stack.size()-1)[1];
            }
        }

        public int top() {
            return stack.get(stack.size()-1)[0];
        }

        public int getMin() {
            return min;
    }


}


/*
* 此解法使用了栈本身和优先队列两种结构，优先队列是为了解决最小值的问题。

入栈、出栈、栈顶这些操作都可以用栈本身的方法，而最小值则是优先队列的头部元素，因为优先队列自带排序算法，在初始化时如果不指定排序方式，则默认以自然方式排序。所以在入栈时，一并也将元素放入优先队列中，而最小值就是队列的头部元素，而其他元素的顺序是不是按升序依次排列的，这个还真不一定，但是如果你通过实现Comparable接口，重写其compareTo方法，可以按照自己定义的方式来排序。
* */
class MinStack2 {
    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
    Stack<Integer> stack = new Stack<Integer>();

    public MinStack2() {}

    public void push(int x) {
        pQueue.add(x);
        stack.push(x);
    }

    public void pop() {
        int trmp = stack.pop();
        pQueue.remove(trmp);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return pQueue.peek();
    }
}


/*使用两个栈，一个作为正常的栈进行入栈、出栈、获取栈顶操作，另外一个栈则存储最小值，每次在第一个栈进行入栈和出栈操作时，都要进行判断，对第二个栈中的最小值进行相应的操作。
* */
class MinStack3 {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public MinStack3() {}

    public void push(int x) {
        s1.push(x);
        if (s2.isEmpty() || s2.peek() >= x) {
            s2.push(x);
        }
    }

    public void pop() {
        int x = s1.pop();
        if (s2.peek() == x) s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }
}


class MinStack4 {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public MinStack4() {}

    public void push(int x) {
        s1.push(x);
        if (s2.isEmpty() || s2.peek() >= x) {
            s2.push(x);
        }
    }

    public void pop() {
        int x = s1.pop();
        if (s2.peek() == x) s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }
}