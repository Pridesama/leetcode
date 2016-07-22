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
     * 2、3、5为因子的丑陋数序列
     * 1×2, 1x3, 2×2, 1x5, 2x3, 2x4, 3x3, 2x5, 4x3…
     *
     * 每一个新丑陋数 都是 已有的丑陋数x某个因子
     *
     * @param n
     */
    public int nthUglyNumber(int n) {
        if (n <= 6) return n;

        int[] uglyNum = new int[n];
        uglyNum[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < n; i ++) {
            int  sum2 = uglyNum[i2] * 2,
                    sum3 = uglyNum[i3] * 3,
                    sum5 = uglyNum[i5] * 5,
                    smallest = Math.min(sum2, Math.min(sum3, sum5));

            if (sum2 == smallest) ++i2;
            if (sum3 == smallest) ++i3;
            if (sum5 == smallest) ++i5;

            uglyNum[i] = smallest;
        }

        return uglyNum[n - 1];
    }


    /**
     * https://leetcode.com/problems/super-ugly-number/
     *
     * Write a program to find the nth super ugly number.
     * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
     * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
     *
     * Note:
     * (1) 1 is a super ugly number for any given primes.
     * (2) The given numbers in primes are in ascending order.
     * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
     */
    // 从升序列表中，找到第n个质数
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNum = new int[n],
                primeIndex = new int[primes.length],
                sum = new int[primes.length];
        uglyNum[0] = 1;
        for (int j = 0; j < primeIndex.length; j ++) {
            primeIndex[j] = 0;
        }

        for (int i = 1; i < n; i ++) {
            int smallest = 0;

            for (int j = 0; j < primes.length; j ++) {
                sum[j] = uglyNum[primeIndex[j]] * primes[j];

                if (smallest == 0) {
                    smallest = sum[j];
                } else {
                    smallest = Math.min(sum[j], smallest);
                }
            }

            for (int j = 0; j < primes.length; j ++) {
                if (sum[j] == smallest) primeIndex[j] = ++ primeIndex[j];
            }

            uglyNum[i] = smallest;
        }

        return uglyNum[n - 1];
    }

    // 在一般领域，对正整数n，如果用2到 根号n 之间的所有整数去除，均无法整除，则n为质数。
    // 约数都是成对出现的，两个数相乘得出num，则一个必然<=根号num，另外一个>=根号num
    private boolean isPrime(int num) {
        if (num <= 3) return num > 1;

        int sqrtN = (int) Math.sqrt(num);

        for (int i = 2; i <= sqrtN; i ++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    public static void main (String args[]) {
        UglyNumber solution = new UglyNumber();
        int num = 905391974;
        System.out.println(num + (solution.isUgly(num) ? " is ugly" : " is not ugly"));

        int nthUgly = 10;
        System.out.println("the " + nthUgly + " th ugly number is " + solution.nthUglyNumber(nthUgly));

        int nthSuperUgly = 8;
        System.out.println(solution.nthSuperUglyNumber(nthSuperUgly, new int[] {2, 3}));
    }
}
