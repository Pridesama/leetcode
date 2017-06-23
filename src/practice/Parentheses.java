package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parentheses {
    /**
     *
     * 20. Valid Parentheses
     *
     * https://leetcode.com/problems/valid-parentheses
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     *
     */
    public boolean isValid(String s) {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i ++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                case '{':
                case '[':
                    chars.add(ch);
                    break;
                case ')':
                case '}':
                case ']':
                    if (chars.isEmpty()) return false;
                    char pop = chars.remove(chars.size() - 1);
                    int step = ch - pop;
                    if (step < 1 || step > 2) return false;
                    break;
            }

        }
        return chars.isEmpty();
    }


    /**
     * 22. Generate Parentheses
     *
     * https://leetcode.com/problems/generate-parentheses
     *
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     * For example, given n = 3, a solution set is:

     [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
     ]
     */
    public List<String> generateParenthesis(int n) {
        return null;
    }

    public static void main (String args[]) {
        System.out.println(new Parentheses().isValid("]"));
    }
}
