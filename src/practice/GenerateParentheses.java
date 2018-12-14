package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate(list, "", n, n);
        return list;
    }

    private void generate(List<String> list, String str, int left, int right) {
        if (left == 0 && right == 0) {
            System.out.println("str = " + str);
            list.add(str);
        } else {
            System.out.println("left = " + left + ", right = " + right + ", str = " + str);
            if (left > 0) {
                generate(list, str + '(', left - 1, right);
            }
            if (right > left) {
                generate(list, str + ')', left, right - 1);
            }
        }
    }

    public static void main (String args[]) {

        GenerateParentheses solution = new GenerateParentheses();

        List<String> result = solution.generateParenthesis(3);
        result.forEach(t -> System.out.println(t));
    }
}
