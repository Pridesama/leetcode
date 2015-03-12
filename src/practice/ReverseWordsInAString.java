package practice;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 *
 *
 * Clarification:
 *      What constitutes a word?
 *          A sequence of non-space characters constitutes a word.
 *      Could the input string contain leading or trailing spaces?
 *          Yes. However, your reversed string should not contain leading or trailing spaces.
 *      How about multiple spaces between two words?
 *          Reduce them to a single space in the reversed string.
 *
 * Created by mukui on 3/12/15.
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1, j = 0;
        while(i >= 0){
            if (s.charAt(i) != ' ') {
                sb.insert(j, s.charAt(i --));
            } else {
                // met spaces
                while (-- i >= 0 && s.charAt(i) == ' ');
                if (sb.length() > 0 && i >= 0) {
                    sb.append(" ");
                }
                j = sb.length();
            }
        }
        return sb.toString();
    }

    public String reverseWords_1(String s) {
        String[] words = s.split("\\s+");
        s = "";
        for (String word : words) {
            if (word.trim().length() > 0) {
                s = word + " " + s;
            }
        }
        return s.length() > 0 ? s.substring(0, s.length() - 1) : s;
    }

    public static void main (String args[]) {
        System.out.println("{" + new ReverseWordsInAString().reverseWords("b a") + "}");

    }
}
