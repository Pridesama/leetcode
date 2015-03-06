package practice;

/**
 *
 * https://oj.leetcode.com/problems/merge-sorted-array/
 *
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 *
 * You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B.
 * The number of elements initialized in A and B are m and n respectively.
 *
 * Created by mukui on 3/6/15.
 */
public class MergeSortedArray {

    public void merge(int A[], int m, int B[], int n) {
        int p = m + n - 1;
        m--;
        n--;
        while (p >= 0) {
            A[p--] = (m < 0 || (n >= 0 && A[m] < B[n])) ? B[n--] : A[m--];
        }
    }
}
