package practice;

import java.util.List;

public class WordSearch {
    /**
     * 79. Word Search
     *
     * https://leetcode.com/problems/word-search/
     *
     * Given a 2D board and a word, find if the word exists in the grid.
     *
     * The word can be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     *
     * The same letter cell may not be used more than once.
     *
     * For example,
     * Given board =
     * [
     *      ["ABCE"],
     *      ["SFCS"],
     *      ["ADEE"]
     * ]
     *
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     *
     * 此题是求连 横线和竖线 可否出现该字母序列，不可以使用 斜线
     *
     * Created by mukui on 3/13/15.
     */
    public boolean exist(char[][] board, String word) {
        int x, y, i = 0, l = word.length();

        for (x = 0; x < board.length;) {
            for (y = 0; y < board[x].length;) {
                if (!exist(board, x, y, word, i, l)) {
                    // 没有找到，继续迁移y
                    y++;
                } else {
                    return true;
                }
            }
            x++;
        }

        return false;
    }

    public boolean exist(char[][] board, int x, int y, String word, int i, int l) {
        char letter = word.charAt(i);
        boolean isExist = false;
        if (board[x][y] != 'x' && board[x][y] == letter) {
            // 在板上找到了当前的letter，标记它、以[x, y]继续下一个letter的检测
            board[x][y] = 'x';

            if (i == l - 1) {
                return true;
            }

            isExist = x >= 1 && exist(board, x - 1, y, word, i + 1, l);

            if (!isExist) {
                isExist = x < board.length - 1 && exist(board, x + 1, y, word, i + 1, l);
            }
            if (!isExist) {
                isExist = y >= 1 && exist(board, x, y - 1, word, i + 1, l);
            }
            if (!isExist) {
                isExist = y < board[0].length - 1 && exist(board, x, y + 1, word, i + 1, l);
            }

            if (!isExist) {
                // 该节点不行，重置回来
                board[x][y] = letter;
            }
        }

        return isExist;
    }



    /**
     * 212. Word Search II
     *
     * https://leetcode.com/problems/word-search-ii/
     *
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     * Each word must be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once in a word.
     *
     * For example,
     * Given words = ["oath","pea","eat","rain"] and board =
     * [
     *   ['o','a','a','n'],
     *   ['e','t','a','e'],
     *   ['i','h','k','r'],
     *   ['i','f','l','v']
     * ]
     * Return ["eat","oath"].
     *
     * Note. You may assume that all inputs are consist of lowercase letters a-z.
     */
    public List<String> findWords(char[][] board, String[] words) {
        return null;

    }

    public static void main (String args[]) {
        char[][] board = new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "CES";
        System.out.println(new WordSearch().exist(board, word));

        for (int x = 0; x < board.length; x ++) {
            for (int y = 0; y < board[x].length; y ++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }
}
