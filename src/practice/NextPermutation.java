package practice;

public class NextPermutation {
    /**
     * 31. Next Permutation
     *
     * https://leetcode.com/problems/next-permutation/
     *
     * 重新排列数组中的数，使得到的新数组的数字序列是恰好大于原数组，且不比其他可能序列大。 如果不存在这样的排列，就将原数组从小到大排序。
     *
     * The replacement must be in-place
     *
     1,2,3 → 1,3,2
     3,2,1 → 1,2,3
     1,1,5 → 1,5,1

     * Created by yangmukui on 6/28/17.
     */
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) return;
        int i, j;

        // 找到一个上升的相邻组
        for (i = nums.length - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) break;
        }
        // 找到后从其后的序列中找到最后一个可以替换的数
        for (j = nums.length - 1; i >= 0 && j > i; --j) {
            if (nums[i] < nums[j]) break;
        }
        if (i >= 0) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
        for (int l = i + 1, r  = nums.length - 1; l < r; l ++, r --) {
            nums[l] ^= nums[r];
            nums[r] ^= nums[l];
            nums[l] ^= nums[r];
        }
    }

    public static void main (String args[]) {
        int[] nums = new int[]{1, 3, 2};
        for (int i = 0; i < nums.length; i ++) {
            System.out.print(nums[i] + (i == nums.length - 1 ? " -> " : ", "));
        }
        new NextPermutation().nextPermutation(nums);
        for (int i = 0; i < nums.length; i ++) {
            System.out.print(nums[i] + (i == nums.length - 1 ? "\n" : ", "));
        }
    }
}
