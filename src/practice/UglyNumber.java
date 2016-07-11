package practice;

/**
 * https://leetcode.com/problems/ugly-number/
 * https://leetcode.com/problems/ugly-number-ii/
 *
 * Created by mukui on 16/7/11.
 */
public class UglyNumber {
    /**
     * https://leetcode.com/problems/ugly-number/
     *
     * Write a program to check whether a given number is an ugly number.
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
     *
     * Note that 1 is typically treated as an ugly number.
     *
     * num需要且只能被2, 3, 5整除
     *
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        while (num >= 2) {
            if (num % 2 == 0) num /= 2;
            else if (num % 3 == 0) num /= 3;
            else if (num % 5 == 0) num /= 5;
            else return false;
        }
        return num == 1;
    }

    /**
     * https://leetcode.com/problems/ugly-number-ii/
     *
     * Write a program to find the n-th ugly number.
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
     *
     * Note that 1 is typically treated as an ugly number.
     *
     * 动态规划的方式解决
     *
     * 丑陋数序列可以拆分为3个子列表
     * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     *
     * @param n
     */
    public int nthUglyNumber(int n) {
        if (n <= 6) return n;

        int[] uglyNum = new int[n];
        uglyNum[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < n;) {
            int last = uglyNum[i - 1],
                    sum2 = uglyNum[i2] * 2,
                    sum3 = uglyNum[i3] * 3,
                    sum5 = uglyNum[i5] * 5,
                    smallest = Math.min(sum2, Math.min(sum3, sum5));

            if (sum2 == smallest) ++i2;
            else if (sum3 == smallest) ++i3;
            else if (sum5 == smallest) ++i5;

            if (smallest != last) {
                uglyNum[i] = smallest;
                i ++;
            }
        }

        return uglyNum[n - 1];
    }

    public static void main (String args[]) {
        UglyNumber solution = new UglyNumber();
        int num = 905391974;
        System.out.println(num + (solution.isUgly(num) ? " is ugly" : " is not ugly"));
        int n = 10;
        System.out.println("the " + n + " th ugly number is " + solution.nthUglyNumber(n));
    }
}
