package practice;

/**
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
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        return false;
    }
    public static void main (String args[]) {
        char[][] board = new char[3][4];
        board[0][0] = 'A';
        String word = "B";
        System.out.println(new WordSearch().exist(board, word));
    }
}
