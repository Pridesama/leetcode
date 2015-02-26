package practice;

import com.sun.tools.javac.util.Assert;

/**
 * https://oj.leetcode.com/problems/wildcard-matching/
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 *  The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 *
 * Created by mukui on 3/3/15.
 */
public class WildcardMatching {
    /**
     * match the whole string s with pattern p
     *
     * for p[j]
     * 1. it's ? or same character with s[i], then match[i+1] depends on match[i] (if [0, i - 1] match then [0, i] match)
     * 2. if it's *, match[i...] depends on match[i] (if [0, i - 1] match, then [0, i], [0, i + 1], ... match)
     */
    public boolean isMatch(String s, String p) {
        // compare length first
        if (p.length() == 0) {
            return s.length() == 0;
        } else if (p.length() != s.length() && !p.contains("*")) {
            return false;
        } else if (p.length() + (p.charAt(p.length() - 1) == '*' ? 0 : 1) - p.split("\\*").length > s.length()) {
            return false;
        }
        // match[i + 1] means s[0 - i] match p[0 - j]
        boolean[] match = new boolean[s.length() + 1];
        match[0] = true;
        // k is the first true in match[], t is the last
        for (int j = 0, k = 0, t = 0; j < p.length(); j ++) {
            if (p.charAt(j) != '*') {
                int firstTrue = - 1, lastTrue = -1;
                // match[t + 1, s.length() - 1] & match[0, k - 1] are false, no need to reassign
                for (int i = t ; i >= k; i--) {
                    match[i + 1] = match[i] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
                    if (match[i + 1]) {
                        firstTrue = i;
                        if (lastTrue == -1) {
                            lastTrue = Math.min(s.length() - 1, i + 1);
                        }
                    }
                }
                // there exists non * char, therefore no char in string shall not pass the pattern
                match[0] = false;
                if (firstTrue >= 0 && lastTrue >= 0) {
                    k = firstTrue;
                    t = lastTrue;
                } else {
                    // no true yet, impossible for the later pattern
                    return false;
                }
            } else {
                for (int i = k; i < s.length(); i++) {
                    match[i + 1] = true;
                }
                t = s.length() - 1;
            }
        }
        return match[s.length()];
    }

    public static void main (String args[]) {

        WildcardMatching solution = new WildcardMatching();

        System.out.println(solution.isMatch("aab", "c*a*b"));


        System.out.println("c*a*b".split("\\*").length);
        System.out.println("c*a*b*".split("\\*+").length);
    }
}
