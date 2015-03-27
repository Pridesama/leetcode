package practice;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * Created by mukui on 3/16/15.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String common = "";
        if (strs.length == 0) return common;
        for (int i = 0; ; ) {
            for (int j = 0; j < strs.length; ) {
                if (i >= strs[j].length()) {
                    return common;
                }
                if (j == 0 || strs[0].charAt(i) == strs[j].charAt(i)) {
                    j ++;
                } else {
                    return common;
                }
            }
            common += strs[0].charAt(i);
            i ++;
        }
    }

    public static void main (String args[]) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[] {"hello", "hellobaby", "helloworld", "hel"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[] {}));
    }
}
