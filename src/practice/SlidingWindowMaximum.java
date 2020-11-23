package practice;

import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * <p>
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * <p>
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 动态规划，将数组划成多个大小为k的连接区域, e.g. [a, b, c, d], k = 2 -> [[a, b],[c, d]]
        int n = nums.length;
        int[] left = new int[n], right = new int[n];

        left[0] = nums[0];
        right[n - 1] = nums[n - 1];
        // left维护 从每个区域的头到i的最大元素， right维护 从每个区域的尾到j的最大元素
        for (int i = 1; i < n; i++) {
            if (i % k == 0) {
                left[i] = nums[i];  // 区域头
            } else {
                left[i] = Math.max(left[i - 1], nums[i]); // 和区域中元素比较
            }
            int j = n - i - 1;// i从1开始是因为避免最后一个区域元素不足
            if ((j + 1) % k == 0) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }
        // 每个块[i, j]比较的就是 left[i]与right[j]


        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            output[i] = Math.max(left[i + k - 1], right[i]);
        }
        return output;
    }

    /**
     * 队列方法
     */
    private int[] maxSlidingWindow_queue(int[] nums, int k) {
        if(nums == null || nums.length < 2) {
            return nums;
        }
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList<>();
        // 结果数组
        int[] result = new int[nums.length-k+1];
        // 遍历nums数组
        for(int i = 0;i < nums.length;i++){
            // 保证从大到小 如果队尾数小则需要依次弹出，直至满足要求
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            if(queue.peek() <= i-k){
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if(i+1 >= k){
                result[i+1-k] = nums[queue.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<Pair<int[], Integer>, int[]> expects = new HashMap<>(2);
        expects.put(new Pair<>(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3), new int[]{3, 3, 5, 5, 6, 7});
        expects.put(new Pair<>(new int[]{7,2,4}, 2), new int[]{7, 4});

        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        expects.forEach((k, v) -> System.out.println(String.format("sliding k = %d, nums = %s;\n\t expected %s, actual %s",
                k.getValue(), Arrays.toString(k.getKey()),
                Arrays.toString(v),
                Arrays.toString(solution.maxSlidingWindow(k.getKey(), k.getValue())))));
    }
}
