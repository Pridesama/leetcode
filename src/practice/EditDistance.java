package practice;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 * <p>
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class EditDistance {
    /**
     * d[i][j], word1的0-i 比对 word2的0-j
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m * n == 0) {
            return m + n;
        }
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i ++) {
            d[i][0] = i;
        }
        for (int j = 0; j < n + 1; j ++) {
            d[0][j] = j;
        }
        for (int i = 1; i < m + 1; i ++) {
            for (int j = 1; j < n + 1; j ++) {
                d[i][j] = Math.min(d[i - 1][j - 1] + (word1.charAt(i - 1) != word2.charAt(j - 1) ? 1 : 0),
                        Math.min(d[i - 1][j], d[i][j - 1]) + 1);
            }
        }
        return d[m][n];
    }

    public static void main(String[] args) {
        EditDistance solution = new EditDistance();

        System.out.println(String.format("%d == %d", solution.minDistance("horse", "ros"), 3));
        System.out.println(String.format("%d == %d", solution.minDistance("intention", "execution"), 5));

        System.out.println(Thread.holdsLock(solution));
    }
}
