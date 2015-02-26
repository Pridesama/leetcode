package practice;

/**
 * https://oj.leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Created by mukui on 1/19/15.
 *
 * https://oj.leetcode.com/discuss/21346/share-my-o-log-m-n-iterative-solution
 */
public class MedianOfTwoSortedArrays {
    // locate the target in arr
    // e.g. the pos of 10 in [8, 12, 14] should be 1
    public int binaryLocate (int target, int arr[], int start, int end) {
        if (start >= arr.length) {
            return arr.length;
        } else if (end - start <= 1) {
            return arr[start] >= target ? start : end;
        } else if (target > arr[end - 1]) {
            return end;
        } else if (target < arr[start]) {
            return start;
        } else {
            int midPos = start + (end - start) / 2, mid = arr[midPos];
            if (target == mid) {
                return midPos;
            } else if (target < mid) {
                return binaryLocate(target, arr, start, midPos);
            } else {
                return binaryLocate(target, arr, midPos + 1, end);
            }
        }
    }

    public double medianLocate (int targetPos, int anotherPos, int[] A, int startA, int endA, int B[], int startB, int endB) {
        int posA = startA + (endA - startA) / 2,
                posB = binaryLocate(A[posA], B, startB, endB),
                mergedPos = posA + posB;

        // B[posB] is always not smaller than A[posA], B[posB] may not exist, A[posA] always has number
        if (mergedPos == targetPos) {
            return (double)A[posA] / 2 + (double)(anotherPos == targetPos ? A[posA] :
                    (posA == A.length - 1 || (posB < B.length && B[posB] < A[posA + 1]) ? B[posB] : A[posA + 1])) / 2;
        } else if (posB < B.length && B[posB] == A[posA] && mergedPos == targetPos - 1) {
            return (double)B[posB] / 2 + (double)(anotherPos == targetPos ? B[posB] :
                    (posB == B.length - 1 || B[posB + 1] > A[posA + 1] ? A[posA + 1] : B[posB + 1])) / 2;
        } else {
            if (mergedPos > targetPos) {
                // jump to left
                if (posA - startA <= 1) {
                    // A has nothing to change, switch main array from A to B
                    return medianLocate(targetPos, anotherPos, B, startB, posB, A, startA, posA);
                }
                return medianLocate(targetPos, anotherPos, A, startA, posA, B, startB, posB);
            } else {
               if (endA - posA <= 1) {
                    return medianLocate(targetPos, anotherPos, B, posB, endB, A, posA, endA);
                }
                // jump to right
                return medianLocate(targetPos, anotherPos, A, posA, endA, B, posB, endB);
            }
        }
    }

    public double findMedianSortedArrays(int A[], int B[]) {
        double median = 0;
        int m = A.length, n = B.length, left, right;

        if (((m + n) & 1) == 0) {
            left = (m + n) / 2 - 1;
            right = left + 1;
        } else {
            left = (m + n) / 2;
            right = left;
        }

        // 1. A is not overlapped with B
        if (m == 0 || n == 0) {
            return  m == 0 ? ((double)B[left] / 2 + (double)B[right] / 2) : ((double)A[left] / 2 + (double)A[right] / 2);
        } else if (A[m - 1] <= B[0]) {
            // {A} {B}
            if (left < m) {
                median = (double)A[left] / 2 + (double)(right < m ? A[right] : B[right - m]) / 2;
            } else {
                median = (double)B[left - m] / 2 + (double)B[right - m] / 2;
            }
        } else if (B[n - 1] <= A[0]) {
            // {B} {A}
            if (left < n) {
                median = (double)B[left] / 2 + (double)(right < n ? B[right] : A[right - n]) / 2;
            } else {
                median = (double)A[left - n] / 2 + (double)A[right - n] / 2;
            }
        // 2. A is overlapped with B
        } else {
            median = medianLocate(left, right, A, 0, m, B, 0, n);
        }

        return median;
    }

    public static void main (String args[]) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(
                new int[] {2, 3, 4}, new int[] {1}));
    }
}
