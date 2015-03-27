package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 * For example, given array S = {-1 0 1 2 -1 -4},
 *
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 *
 * Created by mukui on 3/16/15.
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);

        List<List<Integer>> list = new ArrayList<List<Integer>>();

        for (int i = 0; i < num.length - 2; i ++) {
            if (i == 0 ||  num[i] != num[i-1]) {
                for (int low = i + 1, high = num.length - 1; high > low; ) {
                    int sum = -num[i];
                    if (num[low] + num[high] == sum) {
                        list.add(Arrays.asList(num[i], num[low], num[high]));
                        while (high > low && num[high - 1] == num[high]) high--;
                        while (high > low && num[low + 1] == num[low]) low++;
                        high--;
                        low++;
                    } else if (num[low] + num[high] < sum) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }

        return list;
    }
}
