package practice;

import java.util.Map;
import java.util.HashMap;

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
public class TwoSum {
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

        System.out.println(index1 + ", " + (index2));
        return new int[] {index1, index2};
    }

    public static void main (String args[]) {
        new TwoSum().twoSum(new int[] {0, 4, 3, 0}, 0);
        new TwoSum().twoSum(new int[] {2, 7, 11, 15}, 9);
        new TwoSum().twoSum(new int[] {47, 45, 24, 50, 60, 58, 94}, 74);
    }
}
