package practice;

public class SortedArray {

    /**
     * remove duplicates from sorted array if element shows out {times} times
     *
     * e.g. times = 1, given input array A = [1, 1, 2], output [1, 2] with new length 2
     * e.g. times = 2, given input array A = [1, 1, 2, 2, 2, 3], output [1, 1, 2, 2, 3] with new length 5
     */
    public static int removeDuplicates (int A[], int times) {
        if (A.length < times) return A.length;
        int len = times;
        // 游标len指向当前最后一个待插入元素
        // 游标i在数组间逐个移动
        for (int i = len; i < A.length; i ++) {
            if (A[i] != A[len - times]) {
                A[len++] = A[i];
            }
        }
        // print it
        for (int i = 0; i < len; i ++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
        return len;
    }

    public static void main (String args[]) {
        int[] A = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 5, 19, 19, 30, 41, 41, 45, 45, 45};
        System.out.println("appear once: " + removeDuplicates(A.clone(), 1));
        System.out.println("appear twice: " + removeDuplicates(A.clone(), 2));
    }
}
