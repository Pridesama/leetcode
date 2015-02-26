package practice;

import java.util.Arrays;
import java.util.List;

/**
 *  Roman Numerals: I, II, III, IV, V, VI, VII, VIII, IX, X
 * Arabic Numerals: 1,  2,   3,  4, 5,  6,   7,    8,  9, 10
 *
 * (1)相同的数字连写，所表示的数等于这些数字相加得到的数，如：Ⅲ = 3；
 * (2)小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数， 如：Ⅷ = 8；Ⅻ = 12；
 * (3)小的数字（只限I, X, C）在大的数字的左边，所表示的数等于大数减小数得到的数，如：Ⅳ= 4；Ⅸ= 9；
 * (4)相加连写的数字重复不得超过三次。（唯一例外，IV是朱庇特IVPITER的首字母，因此有时候也用IIII代替）
 * (5)左减的数字只能为1位（8不能写作 IIX），且不可跨越位数（99不能写作 IC 只能写作 XCIX 即 [100-10][10-1]）
 * (6)在一个数的上面画一条横线，表示这个数增值1000 倍
 *
 * 罗马数字没有0，与进位无关，只做记数
 *
 * Created by mukui on 2/27/15.
 */
public class RomanInteger {
    enum Roman {
        I (1), V (5), X (10), L (50), C (100), D (500), M(1000);
        int arabic;
        Roman(int v) {
            this.arabic = v;
        }
    }
    /**
     * Given a roman numeral, convert it to an integer.
     * Input is guaranteed to be within the range from 1 to 3999.
     *
     * https://oj.leetcode.com/problems/roman-to-integer/
     *
     */

    public int romanToInt(String s) {
        int arabic = 0;
        List<Roman> leftRoman = Arrays.asList(new Roman[]{Roman.I, Roman.X, Roman.C});
        for (int i = 0; i < s.length() - 1; i += 1) {
            Roman l_roman = Roman.valueOf(s.substring(i, i + 1)),
                  r_roman = Roman.valueOf(s.substring(i + 1, i + 2));
            if (l_roman.arabic < r_roman.arabic && leftRoman.contains(l_roman)) {
                arabic -= l_roman.arabic;
            } else {
                arabic += l_roman.arabic;
            }
        }
        arabic += Roman.valueOf(s.substring(s.length() - 1)).arabic;
        return arabic;
    }

    /**
     * Given an integer, convert it to a roman numeral.
     * Input is guaranteed to be within the range from 1 to 3999.
     *
     * https://oj.leetcode.com/problems/integer-to-roman/
     */
    public String intToRoman(int num) {
        String roman = "";
        Roman[] numerals = new Roman[]{Roman.I, Roman.V, Roman.X, Roman.L, Roman.C, Roman.D , Roman.M };
        for (int i = numerals.length - 1; i >= 0; i -= 1) {
            int times = num / numerals[i].arabic;
            if (times > 0) {
                int smaller = i % 2 == 0 ? i : (i - 1);
                if (i < numerals.length - 1 && (numerals[i + 1].arabic - num) <= numerals[smaller].arabic) {
                    roman += numerals[smaller].name() + numerals[i + 1].name();
                    num -= numerals[i + 1].arabic - numerals[smaller].arabic;
                } else {
                    num = num - numerals[i].arabic * times;
                    while (times-- > 0) {
                        roman += numerals[i].name();
                    }
                }
            }
            if (num <= 0) {
                break;
            }
        }
        return roman;
    }

    public static void main (String args[]) {
        RomanInteger solution = new RomanInteger();
        System.out.println(solution.intToRoman(31));
        System.out.println(solution.romanToInt("XL") + " = " + solution.intToRoman(solution.romanToInt("XL")));
        System.out.println(solution.romanToInt("I") + " = " + solution.intToRoman(solution.romanToInt("I")));
        System.out.println(solution.romanToInt("XL") + " = " + solution.intToRoman(solution.romanToInt("XL")));
        System.out.println(solution.romanToInt("IIII") + " = " + solution.intToRoman(solution.romanToInt("IIII")));
        System.out.println(solution.romanToInt("XCIX") + " = " + solution.intToRoman(solution.romanToInt("XCIX")));
        System.out.println(solution.romanToInt("CMXCIX") + " = " + solution.intToRoman(solution.romanToInt("CMXCIX")));
        System.out.println(solution.romanToInt("XCIII") + " = " + solution.intToRoman(solution.romanToInt("XCIII")));
        System.out.println(solution.romanToInt("XCVIII") + " = " + solution.intToRoman(solution.romanToInt("XCVIII")));
    }
}
