package practice;

/**
 * Created by yangmukui on 6/23/17.
 */
public class Pow {
    /**
     * 50. Pow(x, n)
     *
     * https://leetcode.com/problems/powx-n/
     *
     */
    public double myPow(double x, int n) {
        if (x == 1.0 || n == 0) return 1;
        else if (x == -1.0) return n % 2 == 0 ? 1 : -1;
        else if (n == 1) return x;
        else if (n == -1) return 1 / x;

        double val = 1;
        if (n > 1) {
            while (n-- > 0) {
                val *= x;
                if (val == 0.0) return val;
            }
        } else {
            while (n++ < 0) {
                val /= x;
                if (val == 0.0) return val;
            }
        }

        return val;
    }

    public static void main (String args[]) {
        double x = -1;
        int n = 2147483647;
        System.out.println(x * x * x);
        System.out.println(String.format("Math.pow(%f, %d) = %f / %f", x, n, Math.pow(x, n), new Pow().myPow(x, n)));
    }
}
