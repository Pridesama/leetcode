package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

/**
 * https://leetcode-cn.com/problems/minimum-size-subnums-sum/
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * 如果存在，则返回最短子数组长度；如果不存在返回0。 例如：nums = 1, 3, 4, 3, 9,
 * 1, n = 12,那么子数组3, 9满足条件且长度最短为2
 */
public class LC209_FindMinSubArrayLength {

    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int sum = nums[0], way = 1, minLen = Integer.MAX_VALUE;
        for (int i = 0, j = i + 1; i < len && j < len; ) {
            if (nums[i] >= s) {
                return 1;
            }
            sum = way < 0 ? (sum/*s[i - 1][j]*/ - nums[i - 1]) : (sum/*s[i][j - 1]*/ + nums[j]);

            if (sum >= s) {
                minLen = Math.min(minLen, j - i + 1);
                // 且现在找到长度为2, 只用判断剩下有没有1个
                if (minLen == 2) {
                    for (++j; j < len; j++) {
                        if (nums[j] >= s) {
                            return 1;
                        }
                    }
                    return minLen;
                } else {
                    // 找到一组，这组的头后移找其他的可能子数组
                    i ++;
                    way = -1;
                }
            } else {
                if (j < len - 1 ) {
                    // 和不够，加尾
                    j ++;
                } else if (i < len - 1) {
                    // 加到尾巴外了，但是头还没动
                    i = i + 1;
                    j ++;
                    sum = sum - nums[i];

                }
                way = 1;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        if(s < 0 )
            return 0;
        if(nums.length < 1)
            return 0;
        if(nums.length == 1){
            if(nums[0]>= s)
                return 1;
            else
                return 0;
        }

        int low = 0;
        int fast = 0;
        int length = nums.length;
        int curRes = 1;
        int subSum = nums[0];
        while(fast < length){
            while(subSum >= s){
                res = Math.min(res,curRes);
                subSum -= nums[low];
                low++;
                curRes--;
            }
            while(subSum <s){
                fast++;
                if(fast >= length)
                    return res;
                else{
                    subSum += nums[fast];
                    curRes++;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        LC209_FindMinSubArrayLength solution = new LC209_FindMinSubArrayLength();

        Map<Pair<int[], Integer> /* input */, Integer /* output */> tests = new HashMap<Pair<int[], Integer>, Integer>() {{
            put(new Pair<>(new int[]{1, 3, 4, 3, 8, 1, 3, 9, 2}, 12), 2);
            put(new Pair<>(new int[]{2, 3, 1, 2, 4, 3}, 7), 2);
            put(new Pair<>(new int[]{1, 3, 4, 3, 9, 1}, 12), 2);
            put(new Pair<>(new int[]{2, 3, 1, 2, 4, 1, 3, 1, 3, 4}, 7), 2);
            put(new Pair<>(new int[]{}, 100), 0);
            put(new Pair<>(new int[]{1, 2, 3, 4, 5}, 11), 3);
        }};

//        tests.forEach((input, expect) -> {
//            int[] nums = input.getKey();
//            int s = input.getValue();
//            int actual = solution.minSubArrayLen2(s, nums);
//            (expect == actual ? System.out : System.err).println(
//                String.format("nums = %s, n = %d; expect = %d, actual = %d",
//                    String.join(", ", Arrays.toString(nums)),
//                    s, expect, actual));
//        });
    }
}
