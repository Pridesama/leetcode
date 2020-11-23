package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/remove-duplicate-letters/
 *
 * 给定一个仅包含小写字母的字符串，去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 输入: "bcabc"         输出: "abc"
 * 输入: "cbacdcbc"      输出: "acdb"
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        LinkedList<Character> characters = new LinkedList<>();
        Map<Character, Boolean> visited = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (!visited.containsKey(ch)) {
                visited.put(ch, true);
                characters.add(ch);
            } else if (characters.peekFirst() == ch) {
                characters.removeFirst();
                characters.add(ch);
            }
        }

        String result = "";
        for (Character ch : characters) {
            result += ch;
        }
        return result;
    }

    public static void main (String[] args) {
        RemoveDuplicateLetters solution = new RemoveDuplicateLetters();

        new HashMap<String, String>(){{
            put("bcabc", "abc");
            put("cbacdcbc", "acdb");
        }}.forEach((in, out) -> {
            String result = solution.removeDuplicateLetters(in);
            System.out.println(String.format("input %s, expect %s, actual %s. %s",
                    in, out, result, out.equals(result) ? "[YES]" : "[NO]"));
        });
    }
}
