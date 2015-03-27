package practice;

/**
 * https://leetcode.com/problems/sort-colors/
 *
 * Given an array with n objects colored red, white or blue
 * sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Created by mukui on 3/12/15.
 */
public class SortColors {
    public void sortColors(int[] A) {
        int k = 3;
        int p[] = new int[k];
        for(int i = 0; i < A.length; i++){
            int t = A[i];
            for(int j = k-1; j >= t;j--) {
                A[p[j]++] = j;
            }
        }
    }
    public void sortColors_1 (int[] A) {
        for (int i = 0, j = 0; i < A.length && j < 3; j ++) {
            for (int k = i; k < A.length; ) {
                if (A[k] == j) {
                    if (i != k) {
                        A[i] ^= A[k];
                        A[k] ^= A[i];
                        A[i] ^= A[k];
                    }
                    i++;
                    k++;
                } else {
                    k ++;
                }
            }
        }

    }

    public static void main (String args[]) {
        int[] A = new int[] {2, 1, 2, 2, 2, 1, 0, 2, 0, 2, 1, 1, 0};
        new SortColors().sortColors(A);
        for (int a : A) {
            System.out.print(" " + a + " ");
        }
        System.out.println();
    }
}
