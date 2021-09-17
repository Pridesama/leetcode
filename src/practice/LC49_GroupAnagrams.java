package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/group-anagrams/
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [ ["ate","eat","tea"], ["nat","tan"], ["bat"]]
 * <p>
 * 所有输入均为小写字母。 不考虑答案输出的顺序。
 */
public class LC49_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        int[] mark = new int[26];

        for (String str : strs) {
            for (int i = 0, l = str.length(); i < l; i ++) {
                
            }
        }

        return null;
    }

    public static void main(String[] args) {
        LC49_GroupAnagrams solution = new LC49_GroupAnagrams();
        new HashMap<String[], List<List<String>>>() {{
            put(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                new ArrayList<List<String>>() {{
                    add(Arrays.asList("ate", "eat", "tea"));
                    add(Arrays.asList("nat", "tan"));
                    add(Arrays.asList("bat"));
                }});
            put(new String[]{"reed", "deer", "dear", "red"},
                new ArrayList<List<String>>() {{
                    add(Arrays.asList("reed", "deer"));
                    add(Arrays.asList("dear"));
                    add(Arrays.asList("red"));
                }});
        }}.forEach((input, expect) -> {
            List<List<String>> actual = solution.groupAnagrams(input);

//            (Arrays.equals(actual, expect) ? System.out : System.err).println(
//                String.format("searchRange(%s, %d), expect %s, actual %s",
//                    Arrays.toString(nums), target, Arrays.toString(expect), Arrays.toString(actual)
//                ));
        });
    }
}
