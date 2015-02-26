package practice;

/**
 * https://oj.leetcode.com/problems/palindrome-number/
 *
 * Determine whether an integer is a palindrome. Do this without extra space.
 *
 *
 * Could negative integers be palindromes? (ie, -1)
 * If you are thinking of converting the integer to string, note the restriction of using extra spaces
 * You could also try reversing an integer.
 * However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow.
 *
 *
 * Created by mukui on 2/26/15.
 */
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x >= 10) {
            int l_times = 0, r_times = 0, y = x;
            while (y / 10 > 0) {
                l_times += 1;
                y = y / 10;
            }
            do {
                int r_num = x % ((int) Math.pow(10, r_times + 1)) / ((int)Math.pow(10, r_times)),
                    l_num = (x / ((int) Math.pow(10, l_times))) % 10;
                if (r_num != l_num) return false;
            } while ((l_times -= 1) >= (r_times += 1));
        } else if (x < 0) {
            return false;
        }
        return true;
    }

    public static void main (String args[]) {
        System.out.println(new PalindromeNumber().isPalindrome(21));
    }
}
