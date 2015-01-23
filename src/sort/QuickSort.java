package sort;

/**
 * Created by mukui on 1/15/15.
 */
class QuickSort {
    /**
     * split data to 2 parts, all numbers in left part are smaller than the right part
     * @param num
     * @param start
     * @param end
     * @return
     */
    public static void quickSort (int[] num, int start, int end) {
        if (end <= start) {
            return;
        }
        // 1. randomly pick one pivot, e.g. num[start]
        int pivotPoint = (int)(Math.random() * (end - start) + start),
                pivot = num[pivotPoint],
                left = start, right = end - 1;

        // 2. compare from one direction first, e.g. start to end
        for (; left <= right; left += 1) {
            if (num[left] > pivot) {
                // if this number is bigger than pivot, reverse direction, e.g. end to start
                for (; right > left; right -= 1) {
                    if (num[right] < pivot) {
                        // switch left value and right value
                        int swap = num[left];
                        num[left] = num[right];
                        num[right] = swap;
                        // back to left, continue compare
                        break;
                    }
                }
                if (left > right) {
                    break;
                }
            }
        }

        // [ ..., right, left, ...], check which one is the better pivot point
        if (pivotPoint < right && num[right] > pivot) {
            right -= 1;
        }
        num[pivotPoint] = num[right];
        num[right] = pivot;
        pivotPoint = right;


        // 3. pivot is fixed now, compared the left array and right array separately
        quickSort(num, start, pivotPoint);
        quickSort(num, pivotPoint + 1, end);
    }

    public static String quickSort(int[] num) {
        quickSort(num, 0, num.length);
        String result = " ";
        for (int n : num) {
            result += n + " ";
        }
        return "[" + result + "]";
    }

    public static void main (String args[]) {
        System.out.println(quickSort(new int[] {3, 30, 34, 5, 9, 111}));
        System.out.println(quickSort(new int[] {47, 40, 444, 50, 19, 29}));
    }
}
