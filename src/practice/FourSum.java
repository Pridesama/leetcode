package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/4sum/
 *
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain duplicate quadruplets.
 * For example, given array S = {1, 0, -1, 0, -2, 2}, and target = 0.
 *
 * A solution set is:
 * (-1,  0, 0, 1)
 * (-2, -1, 1, 2)
 * (-2,  0, 0, 2)
 *
 * Created by mukui on 3/16/15.
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> list = new ArrayList<List<Integer>>();

        for (int low = 0, high = num.length - 1; high - low >= 3;) {
            int sum1 = num[low] + num[high];
            for (int i = low + 1, j = high - 1; i < j; ) {
                int sum2 = num[i] + num[j];
                if (target - sum1 == sum2) {
                    System.out.println("add num[" + low + "]=" + num[low] + ", num[" + i + "]=" + num[i] +
                            ", num[" + j + "]=" + num[j] + ", num[" + high + "]=" + num[high]);
                    list.add(Arrays.asList(num[low], num[i], num[j], num[high]));
                    while (i < j && num[j] == num[j - 1]) j --;
                    while (i < j && num[i] == num[i + 1]) i ++;
                    j --;
                    i ++;
                } else if (sum2 > target - sum1) {
                    while (i < j && num[j] == num[j - 1]) j --;
                    j --;
                } else {
                    while (i < j && num[i] == num[i + 1]) i ++;
                    i ++;
                }
            }
            while (high > low & num[high] == num[high - 1]) high --;
            high --;
            if (high - low < 3) {
                while (high > low && num[low] == num[low + 1]) low ++;
                low ++;
                high = num.length - 1;
            }
        }

        return list;
    }

    public static void main (String args[]) {
        // List<List<Integer>> list = new FourSum().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        // List<List<Integer>> list = new FourSum().fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0);
        List<List<Integer>> list = new FourSum().fourSum(new int[]{0,4,-5,2,-2,4,2,-1,4}, 12);

        System.out.println("--------------------");
        for (List<Integer> subList : list) {
            for (Integer integer : subList) {
                System.out.print(" " + integer);
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }
}
