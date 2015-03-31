package practice;

/**
 * https://leetcode.com/problems/sort-list/
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Created by mukui on 3/27/15.
 */
public class SortList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
            next = null;
        }
    }

    public ListNode sortList(ListNode head) {
        // use the head as pivot, put head at the

        ListNode cursor = head.next, last = head;
        while (null != cursor) {
            if (cursor.val >= last.val) {
                last = cursor;
                cursor = cursor.next;
            } else {
                last.next = cursor.next;
                // find the position of cursor in (head -> cursor)
                if (cursor.val < head.val) {
                    cursor.next = head;
                    head = cursor;
                } else {
                    List
                }
                cursor = last.next;
            }
        }

        return head;
    }

    public ListNode compose (int[] arr) {
        ListNode head = new ListNode(arr[0]), cursor = head;
        for (int i = 1; i < arr.length; i ++) {
            cursor.next = new ListNode(arr[i]);
            cursor = cursor.next;
        }
        return head;
    }

    public void print (ListNode head) {
        ListNode cursor = head;
        while (null != cursor) {
            System.out.print(cursor.val + " ");
            cursor = cursor.next;
        }
        System.out.println();
    }

    public static void main (String args[]) {
        SortList solution =  new SortList();
        ListNode head = solution.compose(new int[]{3, 5, 4, 2, 1, 7});
        solution.print(head);
        solution.print(solution.sortList(head));

    }
}
