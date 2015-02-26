package practice;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * https://oj.leetcode.com/problems/longest-palindromic-substring/
 *
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 *
 * Created by mukui on 2/11/15.
 */
public class LongestPalindromicSubstring {


    // Manacher
    // e.g. abcd => #a#b#c#d#e# so it will always be even case
    public String longestPalindrome(String s) {
        char delimiter = "?".charAt(0);
        int maxId = 0;
        char[] str = new char[1 + 2 * s.length()];
        for (int i = 0; i < s.length(); i += 1) {
            str[2 * i] = delimiter;
            str[2 * i + 1] = s.charAt(i);
            if (i == s.length() - 1) {
                str[2 * i + 2] = delimiter;
            }
        }
        // record every element's radius
        int[] radius = new int[str.length];
        Arrays.fill(radius, 1);
        for (int i = 1; i < str.length; ) {
            int left = i - radius[i], right = i + radius[i];
            while (left >= 0 && right < str.length && str[left] == str[right]) {
                radius[i]++;
                left = i - radius[i];
                right = i + radius[i];
            }
            int k = 1;
            for (; k < radius[i]; k++) {
                if (radius[i - k] != radius[i] - k) {
                    radius[i + k] = Math.min(radius[i - k], radius[i] - k);
                } else {
                    // left boundary overlapped, inherit the radius and should continue check from boundary
                    radius[i + k] = radius[i - k];
                    break;
                }
            }
            i = i + k;
        }
        for (int j = radius.length - 1; j >= 0; j -= 1) {
            if (radius[j] > radius[maxId]) {
                maxId = j;
            }
        }
        return s.substring(((maxId + 1 - radius[maxId]) / 2), ((maxId + radius[maxId]) / 2));
    }

    public static void main (String args[]) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("abb"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("acd11111111111dcllklf"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("abaabababccb"));
    }
}
