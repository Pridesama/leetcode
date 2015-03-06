package practice;

/**
 * https://oj.leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 *
 * Created by mukui on 3/5/15.
 */
public class SearchInRotatedSortedArray {
    public int search(int[] A, int target) {
        for (int start = 0, end = A.length - 1; end >= start; ) {
            if (end == start) {
                return A[start] == target ? start : -1;
            } else if (A[start] == target) {
                return start;
            } else if (A[end] == target) {
                return end;
            } else if (A[start] < A[end] && (target > A[end] || target < A[start])){
                return -1;
            } else {
                int mid = ((end - start) >> 1) + start;
                if (A[mid] == target) {
                    return mid;
                }

                if (A[start] > A[mid]) {
                    // original A[0] is in [start, mid], [mid, end] is okay
                    if (target > A[mid] && target < A[end]) {
                        start = mid + 1; -- end;
                    } else {
                        ++ start; end = mid - 1;
                    }
                } else {
                    // [start, mid] is okay, original A[0] is in [mid, end]
                    if (target > A[start] && target < A[mid]) {
                        ++ start; end = mid - 1;
                    } else {
                        start = mid + 1; -- end;
                    }
                }
            }
        }
        return -1;
    }
}
