package practice;

import java.util.Arrays;
import java.util.HashMap;
import javafx.util.Pair;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？  
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class LC34_FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {

        return null;
    }

    public static void main(String[] args) {
        LC34_FindFirstAndLastPositionOfElementInSortedArray solution = new LC34_FindFirstAndLastPositionOfElementInSortedArray();

        new HashMap<Pair<int[], Integer>, int[]>() {{
            // 输入：nums = [5,7,7,8,8,10], target = 8; 输出：[3,4]
            put(new Pair<>(new int[]{5, 7, 7, 8, 8, 10}, 8), new int[]{3, 4});
            //输入：nums = [5,7,7,8,8,10], target = 6; 输出：[-1,-1]
            put(new Pair<>(new int[]{5, 7, 7, 8, 8, 10}, 6), new int[]{-1, -1});
            // 输入：nums = [], target = 0; 输出：[-1,-1]
            put(new Pair<>(new int[]{}, 0), new int[]{-1, -1});
        }}.forEach((input, expect) -> {
            int[] nums = input.getKey();
            int target = input.getValue();
            int[] actual = solution.searchRange(nums, target);

            (Arrays.equals(actual, expect) ? System.out : System.err).println(
                String.format("searchRange(%s, %d), expect %s, actual %s",
                    Arrays.toString(nums), target, Arrays.toString(expect), Arrays.toString(actual)
                ));

        });
    }
}
