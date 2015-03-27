package practice;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-closest/
 *
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 *
 * Created by mukui on 3/16/15.
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int threeSum = num[0] + num[1] + num[num.length - 1];

        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || num[i] != num[i - 1]) {
                for (int low = i + 1, high = num.length - 1; high > low; ) {
                    int sum = num[i] + num[low] + num[high];
                    if (sum == target) return target;
                    threeSum = Math.abs(sum - target) < Math.abs(threeSum - target) ? sum : threeSum;

                    if (sum > target) {
                        while (high - 1 > low && num[high - 1] == num[high]) high--;
                        high--;
                    } else {
                        while (high > low + 1 && num[low + 1] == num[low]) low ++;
                        low++;
                    }
                }
            }
        }
        return threeSum;

    }
}
