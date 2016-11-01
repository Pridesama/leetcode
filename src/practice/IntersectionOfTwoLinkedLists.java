package practice;

import model.ListNode;

/**
 * 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 * A:          a1 → a2
 *                      ↘
 *                      c1 → c2 → c3
 *                      ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 *
 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) return null;
        ListNode tailA = headA;
        int sizeA = 0;
        while (null != tailA.next) {
            tailA = tailA.next;
            ++ sizeA;
        }
        ListNode tailB = headB;
        int sizeB = 0;
        while (null != tailB.next) {
            tailB = tailB.next;
            ++ sizeB;
        }

        if (!tailA.equals(tailB)) return null;

        ListNode PA = headA, PB = headB;
        while (sizeB > sizeA) {
            PB = PB.next;
            -- sizeB;
        }
        while (sizeA > sizeB) {
            PA = PA.next;
            -- sizeA;
        }
        while (null != PA) {
            if (PA.equals(PB)) return PA;
            PA = PA.next;
            PB = PB.next;
        }
        return null;
    }

    public static void main (String args[]) {
        ListNode c = ListNode.link(1);
        ListNode.print(new IntersectionOfTwoLinkedLists().getIntersectionNode(c, c));
    }
}
