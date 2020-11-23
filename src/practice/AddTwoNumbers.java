package practice;

import model.ListNode;

/**
 * https://oj.leetcode.com/problems/add-two-numbers/
 *
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * 342 + 465 = 807
 *
 * Created by mukui on 1/23/15.
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1 && null == l2) {
            return l1;
        }
        int val1 = l1.val, val2 = null != l2 ? l2.val : 0, sum = val1 + val2;
        boolean carry = (sum > 9);
        l1.val = (sum % 10);

        if (null == l1.next && null != l2 && null != l2.next) {
            l1.next = l2.next;
            l2.next = null;
        }
        if (carry) {
            if (null == l1.next) {
                l1.next = new ListNode(0);
            }
            l1.next.val = l1.next.val + 1;
        }
        if (carry || null != l2) {
            addTwoNumbers(l1.next, null == l2 ? null : l2.next);
        }

        return l1;
    }

    public static void main (String args[]) {
       ListNode.print(new AddTwoNumbers().addTwoNumbers(ListNode.link(2, 4, 3), ListNode.link(5, 6, 4)));
    }
}
