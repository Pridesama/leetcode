package practice;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * <p/>
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p/>
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * <p/>
 * Created by mukui on 3/12/15.
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] num) {
        int i, l = num.length;
        for (i = 0; i < l - 1; i++) {
            if (num[i] - num[i + 1] >= 0) {
                return num[i + 1];
            }
        }
        return num[0];
    }

    public int findMin_1(int[] num) {
        int pivot = -1;
        for (int start = 0, end = num.length - 1; start < end; ) {
            int mid = ((end - start) >> 1) + start;
            if (end != mid && num[start] > num[mid]) {
                // {start}..{pivot}..{mid}..{end}
                end = mid;
            } else if (start != mid && num[mid] > num[end]) {
                // {start}..{mid}..{pivot}..{end}
                start = mid;
            } else {
                // since no dup, {start}{end}
                pivot = num[start] < num[end] ? start : end;
                break;
            }
        }
        return num[pivot < num.length && pivot >= 0 ? pivot : 0];
    }
}
