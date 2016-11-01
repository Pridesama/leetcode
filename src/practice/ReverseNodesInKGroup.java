package practice;

import model.ListNode;

import java.util.Stack;

public class ReverseNodesInKGroup {

    /**
     * https://leetcode.com/problems/reverse-nodes-in-k-group/
     *
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     * You may not alter the values in the nodes, only nodes itself may be changed.
     * Only constant memory is allowed.
     *
     * For example,
     * Given this linked list: 1->2->3->4->5
     * For k = 2, you should return: 2->1->4->3->5
     * For k = 3, you should return: 3->2->1->4->5
     *
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || null == head.next) return head;
        ListNode fake = new ListNode(0), prev = fake, cur = head, next;
        fake.next = head;

        Stack<ListNode> stack = new Stack<>();
        do {
            do {
                stack.push(cur);
                cur = cur.next;
            } while (stack.size() < k && null != cur);
            if (stack.size() == k) {
                cur = stack.pop();
                next = cur.next;
                prev.next = cur;
                while (!stack.isEmpty()) {
                    cur.next = stack.pop();
                    cur = cur.next;
                }
                cur.next = next;

                prev = cur;
                cur = next;
            } else {
                break;
            }
        } while (null != cur);

        return fake.next;
    }

    private ListNode reverse(ListNode p_head, int k) {
        ListNode head = p_head.next, tail = head.next;
        int step = 1;
        while(null != tail) {
            head.next = tail.next;
            tail.next = p_head.next;
            p_head.next = tail;
            if (++ step >= k) break;
            tail = head.next;
        }
        return p_head;
    }

    /**
     * https://leetcode.com/problems/swap-nodes-in-pairs/
     *
     * Given a linked list, swap every two adjacent nodes and return its head.
     * For example,
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     *
     * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
     */
    public ListNode swapPairs(ListNode head) {
        ListNode fake = new ListNode(0);
        fake.next = head;

        return _swapPairs(fake).next;
    }

    private ListNode _swapPairs(ListNode head) {

        ListNode one, two;
        if ((null == (one = head.next)) || (null == (two = one.next))) return head;
        head.next = two;
        one.next = two.next;
        two.next = one;

        _swapPairs(one);
        return head;
    }

    public static void main (String args[]) {
        ListNode head = ListNode.link(1, 2, 3, 4, 5, 6, 7);
        ListNode.print(head);
        ListNode.print(new ReverseNodesInKGroup().reverseKGroup(head, 2));
    }
}
