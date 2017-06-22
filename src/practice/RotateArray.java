package practice;

/**
 * https://leetcode.com/problems/rotate-array/
 *
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 *
 * Note: Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Related problem: Reverse Words in a String II
 *
 *
 * Created by mukui on 3/10/15.
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return;
        // confirm nums[k] to nums[n - 1]
        // rotate the remaining nums[0] to nums[k - 1] elements
        while ((k %= n) > 0 && n > 1) {
            int range = n - k;
            // swap [0, n - k - 1] with [k, n - 1]
            for (int i = 1; i <= range; i++) {
                nums[n - i] ^= nums[n - i - k];
                nums[n - i - k] ^= nums[n - i];
                nums[n - i] ^= nums[n - i - k];
            }
            n = k;
            k = n - (range % k);
        }
    }

    public static void main (String args[]) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        new RotateArray().rotate(nums, 3);

        for (int i = 0; i < nums.length; i ++) {
            System.out.print(nums[i] + (i == nums.length - 1 ? "\n" : " "));
        }
    }
}
