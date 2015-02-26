package practice;

import com.sun.tools.javac.util.Assert;


/**
 *
 * https://oj.leetcode.com/problems/regular-expression-matching/
 *
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 *      isMatch("aa","a") → false
 *      isMatch("aa","aa") → true
 *      isMatch("aaa","aa") → false
 *      isMatch("aa", "a*") → true
 *      isMatch("aa", ".*") → true
 *      isMatch("ab", ".*") → true
 *      isMatch("aab", "c*a*b") → true
 *
 * Created by mukui on 2/28/15.
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        return dynamicMatch(s, p);
    }

    public boolean dynamicMatch (String s, String p) {
        // match[i + 1][j + 1] means s[0 - i] match p[0 - j]
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        for (int j = 0; j < p.length(); j ++) {
            if (p.charAt(j) != '*') {
                // single char match, [i+1][j+1] is match when [i][j] is match
                for (int i = 0; i < s.length(); i ++) {
                    match[i + 1][j + 1] = match[i][j] &&
                            (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.');
                }
            } else {
                if (j > 0) {
                    match[0][j + 1] = match[0][j + 1] || match[0][j - 1];
                }
                for (int i = 0; i < s.length(); i++) {
                    // a matches a* or ab* or .* or a.* or .*a
                    match[i + 1][j + 1] = match[i + 1][j] || (j > 0 && match[i + 1][j - 1]) ||
                            (i > 0 && j > 0  && (match[i][j] || match[i][j + 1]) &&
                                    (s.charAt(i - 1) == s.charAt(i) && s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.'));
                }
            }
        }
        return match[s.length()][p.length()];
    }

    public boolean bruteForceMatch(String s, String p, int i, int j){
        // pattern and string matched over
        if(j >= p.length()){
            return i >= s.length();
        }
        // last single char should be matched
        // next pattern char is not *, it's a single pattern char
        if(j == p.length() - 1  || p.charAt(j+1) != '*'){
            // string is over or string char is not match
            if(i == s.length() || !(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')){
                return false;
            }
            return false;
        }

        // next pattern char is * and single char match
        while(i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j)=='.')){
            if(bruteForceMatch(s, p, i, j + 2)){
                return true;
            }
            i++;
        }

        // 1. next pattern char is * and current pattern char is not equals to string char
        // 2. compared all duplicated string
        return bruteForceMatch(s, p, i, j + 2);
    }

    public static void main (String args[]) {
        RegularExpressionMatching solution = new RegularExpressionMatching();
        Assert.check(!solution.isMatch("aaa", "ab*a"));
        Assert.check(solution.isMatch("aaa", ".*"));
    }
}
