package practice;

/**
 * Created by mukui on 16/7/18.
 */
public class PowerOfNumber {

    /**
     * 231. Power of Two
     * https://leetcode.com/problems/power-of-two/
     *
     * Given an integer, write a function to determine if it is a power of two.
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }

    /**
     * 326. Power of Three
     * https://leetcode.com/problems/power-of-three/
     *
     * 之所以不能用底数2是因为java精度计算带来的问题, log(3)返回的四舍五入值比真实值大
     *
     * log(243) = 5.493061443340548    log(3) = 1.0986122886681098
     *      ==> log(243)/log(3) = 4.999999999999999
     *
     * log10(243) = 2.385606273598312    log10(3) = 0.47712125471966244
     *      ==> log10(243)/log10(3) = 5.0
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        double logVal = Math.log10(n) / Math.log10(3);
        return n > 0 && (logVal - (int)logVal == 0);
    }

    /**
     * 342. Power of Four
     * https://leetcode.com/problems/power-of-four/
     *
     * 根据杨辉三角，pow(4, n) - 1 得到的展开式全部是3的幂次方的倍数
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        return (n - 1) % 3 == 0 && (n & (n - 1)) == 0;
    }


    public static void main (String args[]) {
        PowerOfNumber solution = new PowerOfNumber();
        System.out.println(solution.isPowerOfThree(243));
    }

}
