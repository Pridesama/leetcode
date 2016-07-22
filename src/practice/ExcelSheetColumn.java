package practice;

/**
 * Created by mukui on 16/7/18.
 */
public class ExcelSheetColumn {

    /**
     * 168. Excel Sheet Column Title
     * https://leetcode.com/problems/excel-sheet-column-title/
     *
     * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
     *
     *      1 -> A
     *      2 -> B
     *      3 -> C
     *      ...
     *      26 -> Z
     *      27 -> AA
     *      28 -> AB
     *
     * 10进制转没有0的26进制
     *
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        String title = "";
        while(n > 0) {
            title = (char)((n - 1) % 26 + 'A') + title;
            n = (n - 1) / 26;
        }
        return title;
    }

    /**
     * 171. Excel Sheet Column Number
     * https://leetcode.com/problems/excel-sheet-column-number/
     *
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     *
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int num = 0;

        for (int i = 0; i < s.length(); i ++) {
            num = num * 26 + (s.charAt(i) - 'A' + 1);
        }

        return num;
    }


    public static void main (String args[]) {
        ExcelSheetColumn solution = new ExcelSheetColumn();

        int num = 1028;
        String title = solution.convertToTitle(num);
        System.out.println(num + " => " + title);
        System.out.println(title + " => " + solution.titleToNumber(title));
    }
}
