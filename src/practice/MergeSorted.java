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
     * 23. Merge k Sorted Lists
     * https://leetcode.com/problems/merge-k-sorted-lists
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return null;
    }

    public static void main (String args[])  {
        int A[] = {1, 3, 4, 9, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int B[] = {2, 4, 5, 6, 10, 13, 14};
        int m = 5, n = B.length;
        new MergeSorted().merge(A, m, B, n);
        for (int i = 0; i < m + B.length; i ++) {
            System.out.print(A[i] + (i == m + B.length - 1 ? "\n" : " "));
        }


        ListNode[] lists = {
                ListNode.link(1, 2, 5, 8, 10, 17),
                ListNode.link(1, 3, 7, 12, 14, 19),
                ListNode.link(4, 25),
                ListNode.link(9, 11, 13)
        };
        ListNode.print(new MergeSorted().mergeKLists(lists));
    }
}
