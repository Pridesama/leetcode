package practice;

import java.util.ArrayList;
import java.util.List;

public class Parentheses {
    /**
     * 20. Valid Parentheses
     * <p>
     * https://leetcode.com/problems/valid-parentheses
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * <p>
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     */
    public boolean isValid(String s) {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
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
     * <p>
     * https://leetcode.com/problems/generate-parentheses
     * https://leetcode-cn.com/problems/generate-parentheses/
     * <p>
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * <p>
     * For example, given n = 3, a solution set is:
     * <p>
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     */
    public List<String> generateParenthesis(int n) {
//        List<String> lists = new ArrayList<>();
//        generateParenthesis_backtrack(lists, "", 0, 0, n);
//        return lists;

        /*
        闭合数 / 动态规划
         f(n) = "(" + f(q) + ")" + f(n-1-q) (0 <= q <= n-1)
         说一下为什么要ans.add("(" + left + ")" + right); 而不是ans.add(left+right)呢,
          应该是因为有一种特殊的情况 如果left right(n=4) 全是()() 这种分隔的小括号
          则当(left,right)为(1,3)或者(2,2)或者(3,1) 就会出现()()()()重复出现多次的情况
         而上面那种做法可以将()()()()的情况放到left=0,rigint=3的情况中,且只会出现一次
         */
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left : generateParenthesis(c)) {
                    for (String right : generateParenthesis(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        return ans;
    }

    // 回溯
    private void generateParenthesis_backtrack(List<String> lists, String result, int left, int right, int n) {
        if (left > n || right > n || right > left) {
            return;
        }
        if (left == n && right == n) {
            lists.add(result);
            return;
        }
        generateParenthesis_backtrack(lists, result + "(", left + 1, right, n);
        generateParenthesis_backtrack(lists, result + ")", left, right + 1, n);
    }

    public static void main(String args[]) {
        Parentheses solution = new Parentheses();
        System.out.println(solution.isValid("[]"));

        solution.generateParenthesis(3).forEach(System.out::println);
    }
}
