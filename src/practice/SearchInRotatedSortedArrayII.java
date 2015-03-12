package practice;

/**
 * https://oj.leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 *
 * Created by mukui on 3/5/15.
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] A, int target) {
        for (int start = 0, end = A.length - 1; end >= start; ) {
            System.out.println(start + ", " +  end);
            if (end == start) {
                return A[start] == target;
            } else if (A[start] == target || A[end] == target) {
                return true;
            } else if (A[start] < A[end] && (target > A[end] || target < A[start])){
                return false;
            } else {
                int mid = ((end - start) >> 1) + start;
                if (A[mid] == target) return true;
                if (A[start] == A[mid]) {
                    while (start < mid && A[start] == A[mid]) {
                        ++start;
                    }
                }
                if (A[mid] == A[end]) {
                    while (end > mid && A[end] == A[mid]) {
                        -- end;
                    }
                }
                if (A[start] == target || A[end] == target) {
                    return true;
                }
                // all elements are duplicated
                if (start == end) return false;

                if (A[start] > A[mid]) {
                    // original A[0] is in [start, mid], [mid, end] is okay
                    if (target > A[mid] && target < A[end]) {
                        start = mid + 1; -- end;
                    } else {
                        ++ start; end = mid - 1;
                    }
                } else if (A[mid] > A[end]) {
                    // [start, mid] is okay, original A[0] is in [mid, end]
                    if (target > A[start] && target < A[mid]) {
                        ++ start; end = mid - 1;
                    } else {
                        start = mid + 1; -- end;
                    }
                } else {
                    if (target > A[mid]) {
                        start = mid + 1; -- end;
                    } else {
                        ++ start; end = mid - 1;
                    }
                }
            }
        }
        return false;

    }

    public static void main (String args[]) {
        System.out.println(new SearchInRotatedSortedArrayII().search(new int[] {3,4,4,4,4,4,4,5,5,6,6,6,6,6,6,6,7,7,7,7,7,7,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,10,10,10,-10,-10,-10,-9,-8,-8,-8,-8,-8,-7,-7,-7,-7,-6,-6,-6,-6,-6,-6,-6,-5,-5,-5,-4,-4,-4,-4,-3,-3,-3,-3,-3,-3,-2,-2,-2,-2,-1,-1,0,0,0,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3},
                2));
    }
}
