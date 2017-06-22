package practice;

import model.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 19. Remove Nth Node From End Of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Given a linked list, remove the nth node from the end of list and return its head.
 *
 * For example,
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 *
 */
public class RemoveNthNodeFromEndOfList {

    // 快慢指针，之间跨度n，一起走
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (null != head) {

            int i = 0, j = 0;
            ListNode fast = head, slow = head;
            while (i - j < n) {
                fast = fast.next;
                i++;
            }

            if (null == fast) return head.next;

            while (null != fast.next) {
                fast = fast.next;
                slow = slow.next;
            }

            // 移除slow.next
            ListNode toDel = slow.next;
            slow.next = toDel.next;
        }

        return head;
    }

    public static void main (String args[]) {
        ListNode head = ListNode.link(1);
        ListNode.print(head);

        ListNode.print(new RemoveNthNodeFromEndOfList().removeNthFromEnd(head, 1));
    }
}
