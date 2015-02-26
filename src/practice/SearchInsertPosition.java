package practice;

/**
 * https://oj.leetcode.com/problems/search-insert-position/
 *
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 *
 * Created by mukui on 3/5/15.
 */
public class SearchInsertPosition {
    public int binarySearch (int[] A, int start, int end, int target) {
        if (A[start] >= target) {
            return start;
        } else if (A[end] == target) {
            return end;
        } else if (A[end] < target) {
            return end + 1;
        } else if (end - start <= 1) {
            return start + 1;
        } else {
            int pos = (end - start) / 2 + start;
            if (A[pos] == target) {
                return pos;
            } else if (A[pos] < target) {
                return binarySearch(A, pos + 1, end, target);
            } else {
                return binarySearch(A, start, pos - 1, target);
            }
        }
    }
    public int searchInsert(int[] A, int target) {
        return binarySearch(A, 0, A.length - 1, target);
    }

    public static void main (String args[]) {
        System.out.println(new SearchInsertPosition().searchInsert(new int[] {1,3,5,6}, 4));
    }
}
