package practice;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-number/
 *
 * Validate if a given string is numeric.
 *
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 *
 * 有点像StringToIntegerATOI LeetCode上 .3, 3., 001 都算是合法数字
 *
 * Created by mukui on 3/16/15.
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        // trim the prefix and suffix whitespaces
        int i = 0, j = s.length() - 1, k;
        while (j > i && s.charAt(i) == ' ') i ++;
        while (j > i && s.charAt(j) == ' ') j --;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') i ++;
        // check string to be match {+-}\d+{.}\d+(e{+-}\d+)   +-, \d, ., \d, e, +-, \d
        int FIRST_SIGNED_OPERATOR = 0, FIRST_DIGIT = 1, DOT = 2, SECOND_DIGIT = 3,
                SCIENCE_OPERATOR = 4, SECOND_SIGNED_OPERATOR = 5, THIRD_DIGIT = 6;
        int[] symbols = new int[7];
        Arrays.fill(symbols, -1);
        for (k = FIRST_SIGNED_OPERATOR; i <= j; i ++) {
            char ch = s.charAt(i);
            if ((k == FIRST_SIGNED_OPERATOR || k == SECOND_SIGNED_OPERATOR) && (ch == '+' || ch == '-')) {
                symbols[k ++] = ch;
            } else if (k > FIRST_DIGIT && k <= SCIENCE_OPERATOR && ch == 'e' &&
                    (symbols[FIRST_DIGIT] >= '0' || symbols[SECOND_DIGIT] >= '0')) {
                k = SCIENCE_OPERATOR;
                symbols[k++] = ch;
            } else if (k >= FIRST_SIGNED_OPERATOR && k <= DOT && ch == '.') {
                k = DOT;
                symbols[k++] = ch;
            } else if (ch >= '0' && ch <= '9') {
                if (k > THIRD_DIGIT) {
                    continue;
                } else if (k == FIRST_SIGNED_OPERATOR || k == SECOND_SIGNED_OPERATOR) {
                    symbols[k++] = '+';
                }
                if (k == FIRST_DIGIT || k == SECOND_DIGIT || k == THIRD_DIGIT) {
                    symbols[k++] = ch;
                }
            } else {
                return false;
            }
        }
        return (k >= FIRST_DIGIT && k <= SCIENCE_OPERATOR && (symbols[FIRST_DIGIT] >= '0' || symbols[SECOND_DIGIT] >= '0')) ||
                (k > THIRD_DIGIT);
    }
}
