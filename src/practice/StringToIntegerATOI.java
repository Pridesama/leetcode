package practice;

/**
 * https://oj.leetcode.com/problems/string-to-integer-atoi/
 *
 * Implement atoi to convert a string to an integer.
 *
 * Carefully consider all possible input cases. It is intended for this problem to be specified vaguely.
 *
 * Created by mukui on 3/6/15.
 */
public class StringToIntegerATOI {
    public int pow (int digit, int times) {
        return (int)Math.pow(digit, times);
    }
    public int atoi(String str) {
        if (str.length() == 0) return 0;
        int symbol = 0, cursor = -1, y = 0, z;
        int[] digits = new int[str.length() + symbol];
        for (int i = 0; i < str.length(); i ++) {
            char sch = str.charAt(i);
            // no a number
            if (sch < '0' || sch > '9') {
                // already got symbol
                if (symbol != 0) break;
                // not any accepted icon
                if (sch != ' ' && sch != '+' && sch != '-') return 0;
            }
            if (sch == ' ') continue;
            if (symbol == 0) {
                if (sch == '+') {
                    symbol = 1;
                } else if (sch == '-') {
                    symbol = -1;
                }
                // identify symbol
                continue;
            }
            int number = sch - '0';
            if (number >= 0 && number <= 9) {
                digits[++cursor] = number;
                symbol = (symbol != 0) ? symbol : 1;
            }
        }
        if (cursor < 9 || (cursor == 9 && digits[0] < 3)) {
            for (int index = 1; index <= cursor; ++index) {
                if (digits[index] > 0) {
                    y += digits[index] * pow(10, cursor - index);
                }
            }
            z = digits[0] > 0 ? digits[0] * pow(10, cursor) : 0;
            if (cursor < 9 || (Integer.MAX_VALUE - y - symbol) > z) {
                return symbol == 0 ? (y + z) : symbol * (y + z);
            }
        }
        return cursor < 0 ? 0 : (symbol >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE);
    }
}
