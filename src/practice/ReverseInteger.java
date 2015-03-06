package practice;

/**
 * https://oj.leetcode.com/problems/reverse-integer/
 *
 * Reverse digits of an integer.
 *
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 *
 * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 * Did you notice that the reversed integer might overflow?
 * Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 *
 * Created by mukui on 2/26/15.
 */
public class ReverseInteger {
    public int pow (int digit, int times) {
        return (int)Math.pow(digit, times);
    }
    public int reverse(int x) {
        int symbol = x >> 31, cursor = 0, y = 0, z = 0;
        int[] digits = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        if (symbol < 0) {
            x *= symbol;
        }
        while (x / 10 != 0 || x % 10 != 0) {
            digits[cursor ++] = x % 10;
            x = x /10;
        }
        if (cursor == digits.length && digits[0] >= 3) return 0;
        for (int index = 1; index < digits.length && digits[index] >= 0; ++ index) {
            if (digits[index] > 0) {
                y += digits[index] * pow(10, cursor - index - 1);
            }
        }
        if (digits[0] > 0) {
            z = digits[0] * pow(10, cursor - 1);
        }
        if (cursor == digits.length && digits[0] == 2 && (Integer.MAX_VALUE - y - symbol) <= z) return 0;
        return (symbol == -1) ? (symbol * (y + z)) : (y + z);
    }

}
