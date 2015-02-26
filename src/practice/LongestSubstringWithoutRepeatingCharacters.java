package practice;

import java.util.HashMap;

/**
 * https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 *
 * Created by mukui on 1/23/15.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0, start = 0, len = s.length();
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int index = 0; index < len; index += 1) {
            String ch = s.substring(index, index + 1);
            if (!map.containsKey(ch) || map.get(ch).intValue() < start) {
                // find a non-dup char
                map.put(ch, new Integer(index));
                if (index == len - 1) {
                    maxLen = Math.max(maxLen, len - start);
                }
            } else {
                // duplicated, record max length
                maxLen = Math.max(maxLen, index - start);
                // move start pos
                start = map.get(ch).intValue() + 1;
                // if the left string is less than maxLen, done
                if (len - start < maxLen) {
                    break;
                }
                map.put(ch, index);
            }
        }

        return maxLen;
    }

    public static void main (String args[]) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbb"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcabcd"));

        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("ieblgmgiyhhxygivtwvfzvtgmikwndryisjqeradzhczpmujirqjojpbuzxhdohnjqdpkdulnykekgnszddnpsojsnsxeaknspec"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("gsmsxybfdckdnusslswvkwycpyeaeqhklt"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco"));
    }
}
