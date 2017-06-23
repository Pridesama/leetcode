package practice;

import model.ListNode;

public class MergeSorted {

    /**
     * 88. Merge Sorted Array
     * https://oj.leetcode.com/problems/merge-sorted-array/
     *
     * Given two sorted integer arrays A and B, merge B into A as one sorted array.
     *
     * You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B.
     * The number of elements initialized in A and B are m and n respectively.
     */
    public void merge(int A[], int m, int B[], int n) {
        int p = m + n - 1;
        m--;
        n--;
        while (p >= 0) {
            A[p--] = (m < 0 || (n >= 0 && A[m] < B[n])) ? B[n--] : A[m--];
        }
    }


    /**
     * 21. Merge Two Sorted Lists
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cursor = head;
        while (null != l1 && null != l2) {
            if (l1.val <= l2.val) {
                cursor.next = l1;
                l1 = l1.next;
            } else {
                cursor.next = l2;
                l2 = l2.next;
            }
            cursor = cursor.next;
        }
        if (null != l1) cursor.next = l1;
        if (null != l2) cursor.next = l2;

        return head.next;
    }

    /**
     * 23. Merge k Sorted Lists
     * https://leetcode.com/problems/merge-k-sorted-lists
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int from, int to) {
        if (to == from) return lists[to];
        if (to - from == 1) return mergeTwoLists(lists[from], lists[to]);
        System.out.println("from = " + from  + ", to = " + to + " | " + (to - from) / 2);
        return mergeTwoLists(
                mergeKLists(lists, from, (to + from) / 2),
                mergeKLists(lists, (to + from) / 2 + 1, to)
        );
    }

    public static void main (String args[])  {
        int A[] = {1, 3, 4, 9, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int B[] = {2, 4, 5, 6, 10, 13, 14};
        int m = 5, n = B.length;
        new MergeSorted().merge(A, m, B, n);
        for (int i = 0; i < m + B.length; i ++) {
            System.out.print(A[i] + (i == m + B.length - 1 ? "\n" : " "));
        }

        ListNode.print(new MergeSorted().mergeTwoLists(
                ListNode.link(1, 3, 7, 8, 12, 19),
                ListNode.link(1, 2, 3, 4, 5, 6, 7)
        ));

        ListNode[] lists = {
                ListNode.link(-10,-9,-9,-3,-1,-1,0),
                ListNode.link(-5),
                ListNode.link(-4),
                ListNode.link(-8),
                ListNode.link(),
                ListNode.link(-9,-6,-5,-4,-2,2,3),
                ListNode.link(-3,-3,-2,-1,0)
        };
        ListNode.print(new MergeSorted().mergeKLists(lists));
    }
}
