package practice;

import model.ListNode;

/**
 * https://leetcode.com/problems/reorder-list/
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (null == head || null == head.next) return;

        ListNode slow = head, fast = head, right;
        while (null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (null == fast) {
                break;
            }
        }
        right = slow.next;
        slow.next = null;

        // reverse [right, fast] and merge into [head, slow]
        ListNode cur = right;
        while (null != cur && null != cur.next) {
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = right;
            right = tmp;
        }
        ListNode l_cur = head, r_cur = right;
        while (null != r_cur) {
            right = r_cur.next;
            r_cur.next = l_cur.next;
            l_cur.next = r_cur;
            l_cur = r_cur.next;
            r_cur = right;
        }

    }

    public void reorderList_1(ListNode head) {
        if (null == head || null == head.next) return;

        ListNode right = head, tail = head;
        int width = 0;

        while (null != tail.next) {
            right = right.next;
            if (null != tail.next.next) {
                tail = tail.next.next;
                ++ width;
            } else {
                tail = tail.next;
            }
        }

        // mid位置不变，将[right, tail] 的元素插入 [head, left]的空隙中, left&right本身不动
        while (width > 0){
            --width;
            ListNode el = right.next;
            ListNode slot = head;
            for (int i = 0; i < width; i++) {
                slot = slot.next;
            }
            right.next = el.next;
            el.next = slot.next;
            slot.next = el;
        }
    }

    public static void main (String args[]) {
        ListNode head = ListNode.link(1, 2);
        ListNode.print(head);
        new ReorderList().reorderList(head);
        ListNode.print(head);

    }
}
