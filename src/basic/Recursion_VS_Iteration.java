package basic;

/**
 * Created by mukui on 12/7/14.
 */
public class Recursion_VS_Iteration {
    // recursion
    public static int f_recursion (int n) {
        if (n <= 2) {
            return n;
        }
        return f_recursion(n - 1) + f_recursion(n - 2);
    }

    public static int f_iteration (int n) {
        if (n <= 2) {
            return n;
        }
        int first = 1, second = 2, third = 0;
        for (int i = 3; i <= n; i ++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
    public static void main (String args[]) {
        System.out.println(f_recursion(1));
        System.out.println(f_recursion(2));
        System.out.println(f_recursion(3));
        System.out.println(f_recursion(30));

        System.out.println(f_iteration(1));
        System.out.println(f_iteration(2));
        System.out.println(f_iteration(3));
        System.out.println(f_iteration(30));
    }
}
