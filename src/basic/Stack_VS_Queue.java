package basic;

import java.util.LinkedList;

/**
 * Created by mukui on 12/7/14.
 */
public class Stack_VS_Queue {
    public static void main (String args[]) {
        // Queue，相当于stack with 2 entries
        System.out.println("------------ queue ------------");
        java.util.LinkedList<String> queue = new LinkedList<String>();
        queue.push("bruno");
        queue.push("martin");
        queue.push("arthur");
        System.out.println(queue.poll());
        System.out.println(queue.peekFirst());
        System.out.println(queue.peekLast());

        // stack，先进后出
        System.out.println("------------ stack ------------");
        java.util.Stack<String> stack = new java.util.Stack<String>();
        stack.push("bruno");
        stack.push("martin");
        stack.push("arthur");
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}
