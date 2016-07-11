package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukui on 16/5/19.
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * {
 *     1: [],
 *     2: ['a', 'b', 'c'],
 *     3: ['d', 'e', 'f'],
 *     ...
 * }
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsOfAPhoneNumber {

    // 递归
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.length() == 0) return result;
        int digit = ((int)digits.charAt(0)) - 48;

        List<String> initialCombinations = letterCombinations(digit),
                subCombinations = digits.length() <= 1 ? null : letterCombinations(digits.substring(1));

        if (null != subCombinations) {
            for (String initialStr : initialCombinations) {
                for (String subStr : subCombinations) {
                    result.add(initialStr + subStr);
                }
            }
        } else {
            result = initialCombinations;
        }

        return result;
    }

    private List<String> letterCombinations(int digit) {
        char a = (char) 97;
        List<String> result = new ArrayList<>();
        if (digit >= 1 && digit <= 9) {
            char from = (char)(a + (digit - 2) * 3 + (digit > 7 ? 1 : 0)),
                    to = (char)(a + (digit - 1) * 3 - (digit < 7 ? 1 : (digit == 9 ? -1 : 0)));

            for (char letter = from; letter <= to; letter ++) {
                result.add("" + letter);
            }
        }
        return result;
    }
}
