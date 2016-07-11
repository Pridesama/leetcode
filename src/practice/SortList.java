package practice;

/**
 * https://leetcode.com/problems/sort-list/
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * https://leetcode.com/problems/insertion-sort-list/
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
        return sortList_insertion(head);
    }


    // 1. 归并排序
    public ListNode sortList_mergeSort(ListNode head) {
        if (head.next == null) return head;
        ListNode middle = getMiddle(head);
        ListNode another = middle.next;
        middle.next = null;
        return merge(sortList_mergeSort(head), sortList_mergeSort(another));
    }

    private ListNode merge(ListNode one, ListNode another) {
        ListNode head = new ListNode(0), cursor = head;
        while (one != null && another != null) {
            if (another.val < one.val) {
                cursor.next = another;
                another = another.next;
            } else {
                cursor.next = one;
                one = one.next;
            }
            cursor = cursor.next;
        }
        cursor.next = one == null ? another : one;
        return head.next;
    }

    private ListNode getMiddle(ListNode head) {
        ListNode slow = head, fast = head;

        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 2. 插入排序
    // https://leetcode.com/problems/insertion-sort-list/
    // 取出节点，和已排序的链表逐一对比
    public ListNode sortList_insertion(ListNode head) {
        if (null == head) return null;

        ListNode sortedHead = head, cursor = head.next, nextCursor, left, right;
        sortedHead.next = null;

        while (null != cursor) {
            nextCursor = cursor.next;
            cursor.next = null;
            if (cursor.val <= sortedHead.val) {
                cursor.next = sortedHead;
                sortedHead = cursor;
            } else {
                left = right = sortedHead;
                do {
                    if (right.val <= cursor.val) {
                        if (null == right.next) {
                            right.next = cursor;
                            break;
                        }
                        left = right;
                        right = right.next;
                    } else {
                        left.next = cursor;
                        cursor.next = right;
                        break;
                    }
                } while (null != right);
            }

            cursor = nextCursor;
        }

        return sortedHead;
    }

    // 按我自认为的快排的方式比较 （TimeExceed……） 这不算快排吗？
    // 取首节点开始向后比较，小的节点迁移到当前head之前
    // 当前比较结束后，原head则处于左边全小右边全大的位置
    // 以 [new-head, head] 及 [head.next, tail] 为新的区间开始再进行处理
    public ListNode sortList_quickSort(ListNode head, ListNode tail) {
        if (null == head || head == tail || head.next == null || head.next == tail) return head;
        
        ListNode left = head, right = head, cursor = head.next;
        while (null != cursor && (null == tail || cursor != tail)) {
            if (cursor.val < head.val) {
                right.next = cursor.next;
                cursor.next = left;
                left = cursor;
            } else {
                right = cursor;
            }

            cursor = right.next;
        }

        // left ... head head.next ...
        head.next = sortList_quickSort(head.next, null);

        return sortList_quickSort(left, head);
    }

    public static void main (String args[]) {
        SortList solution =  new SortList();

        int[] arr = new int[]{1, 1};
        ListNode head = new ListNode(arr[0]), cursor = head;
        for (int i = 1; i < arr.length; i ++) {
            cursor.next = new ListNode(arr[i]);
            cursor = cursor.next;
        }

        head = solution.sortList(head);
        while (null != head) {
            System.out.print(" " + head.val);
            head = head.next;
        }
    }
}
