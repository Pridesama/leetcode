package practice;


/**
 * https://oj.leetcode.com/problems/zigzag-conversion/
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 *
 * Created by mukui on 2/26/15.
 */
public class ZigZagConversation {
    public String convert(String s, int nRows) {
        // row 1: 0 + 2 * (n - 1)
        // row n: (n - 1) + 2 * (n - 1)
        // row i: (i - 1) + 2 * (n - i) or (i - 1) + 2 * (i - 1)

        if (nRows == 1) {
            return s;
        }
        String converted = "";
        for (int row = 1; row <= nRows; row++) {
            if (row == 1 || row == nRows) {
                for (int i = row - 1; i < s.length(); i += 2 * (nRows - 1)) {
                    converted += s.charAt(i);
                }
            } else {
                boolean odd = true;
                for (int i = row - 1; i > 0 && i < s.length(); ) {
                    converted += s.charAt(i);
                    i += 2 * (odd ? (nRows - row) : (row - 1));
                    odd = !odd;
                }
            }
        }
        return converted;
    }

    public static void main (String args[]) {
        System.out.println(new ZigZagConversation().convert("PAYPALISHIRING", 3));
        System.out.println(new ZigZagConversation().convert("PAYPALISHIRING", 1));
    }
}
