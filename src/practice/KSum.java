package practice;

import java.util.*;

public class KSum {
    /**
     * https://oj.leetcode.com/problems/two-sum/
     *
     * Given an array of integers, find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target,
     * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution.
     *
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     *
     * Created by mukui on 1/16/15.
     */
    public int[] twoSum(int[] numbers, int target) {
        int index1 = -1, index2 = -1;

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int cur = 1; cur <= numbers.length; cur ++) {
            int num = numbers[cur - 1], partner = target - num;
            if (map.containsKey(num)) {
                if (map.get(num) == -1) {
                    // someone already asked me to be his partner
                    index1 = map.get(partner);
                    index2 = cur;
                    break;
                } else if (partner == num) {
                    // duplicate number? skip unless my partner is myself
                    index1 = map.get(num);
                    index2 = cur;
                    break;
                }
            } else {
                // asking partner
                map.put(num, cur);
                if (num != partner) {
                    map.put(partner, -1);
                }
            }
        }

        return new int[] {index1, index2};
    }

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

    /**
     * 18. 4Sum
     *
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
    public List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> list = new ArrayList<List<Integer>>();

        for (int low = 0, high = num.length - 1; high - low >= 3;) {
            int sum1 = num[low] + num[high];
            for (int i = low + 1, j = high - 1; i < j; ) {
                int sum2 = num[i] + num[j];
                if (target - sum1 == sum2) {
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
            while (high > 0 && high > low & num[high] == num[high - 1]) high --;
            high --;
            if (high - low < 3) {
                high = num.length - 1;
                while (high > low && num[low] == num[low + 1]) low ++;
                low ++;
            }
        }

        return list;
    }

    public static void main (String args[]) {
        // List<List<Integer>> list = new FourSum().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        // List<List<Integer>> list = new FourSum().fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0);
//        List<List<Integer>> list = new Sum().fourSum(new int[]{0,4,-5,2,-2,4,2,-1,4}, 12);
        List<List<Integer>> list = new KSum().fourSum(new int[]{1,-5,-1,1,-6,-7,-5,-1,-1,2,-5,6,4,5,-8,1,3,-1,9}, 0);

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
