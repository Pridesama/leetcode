package practice;

/**
 * https://oj.leetcode.com/problems/largest-number/
 *
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 *
 * Created by mukui on 1/15/15.
 */
public class LargestNumber {
    static class Solution_LinkWithSort {
        // check if a > b, compare after they complete digits with same length
        // abcd & abc <=> abcd & abca
        private boolean compare(int a, int b) {
            if (a == 0) {
                return false;
            } else if (b == 0) {
                return true;
            }
            String aStr = a + "", bStr = b + "";
            int aLen = aStr.length(), bLen = bStr.length(), offset = aLen - bLen,
                    oaL = aLen, obL = bLen;
            if (offset == 0) {
                return a > b;
            }
            int k = 0;
            while (aLen - bLen < 0) {
                aStr += bStr.charAt(k++ % bStr.length());
                ++aLen;
            }
            k = 0;
            while (aLen - bLen > 0) {
                bStr += aStr.charAt(k++ % aStr.length());
                ++bLen;
            }
            // aLen == bLen, at most 10 now
            int maxLen = Math.min(("" + Integer.MAX_VALUE).length() - 1, aLen),
                    aPrefix = Integer.parseInt(aStr.substring(0, maxLen)),
                    bPrefix = Integer.parseInt(bStr.substring(0, maxLen));
            // compare the prefix
            if (aPrefix == bPrefix) {
                if (aLen > maxLen) {
                    // compare suffix
                    return aStr.charAt(aLen - 1) > bStr.charAt(bLen - 1);
                } else {
                    // abc...g & abc...ga, check if g > a
                    return aStr.charAt(oaL - 1) > bStr.charAt(obL - 1);
                }
            } else {
                return aPrefix > bPrefix;
            }

        }

        public void quickSort(int[] num, int start, int end) {
            if (end <= start) {
                return;
            }
            int pivotPoint = (int) (Math.random() * (end - start) + start),
                    pivot = num[pivotPoint],
                    left = start, right = end - 1;

            for (; left <= right; left += 1) {
                if (compare(num[left], pivot)) {
                    for (; right > left; right -= 1) {
                        if (compare(pivot, num[right])) {
                            int swap = num[left];
                            num[left] = num[right];
                            num[right] = swap;
                            break;
                        }
                    }
                    if (left > right) {
                        break;
                    }
                }
            }

            // [ ..., right, left, ...], check which one is the better pivot point
            if (pivotPoint < left && compare(num[right], pivot)) {
                right -= 1;
            }
            num[pivotPoint] = num[right];
            num[right] = pivot;
            pivotPoint = right;

            // 3. pivot is fixed now, compared the left array and right array separately
            quickSort(num, start, pivotPoint);
            quickSort(num, pivotPoint + 1, end);
        }

        public String largestNumber(int[] num) {
            String largest = "";

            quickSort(num, 0, num.length);
            if (num[num.length - 1] > 0) {
                for (int n : num) {
                    largest = n + largest;
                }
            }
            return "".equals(largest) ? "0" : largest;
        }
    }

    static class Solution_LinkedList {

        public int getDigit (int cm, int pos) {
            // abc, if try to get more digits from this string, treat as  abcabc..abc..
            String str = (cm + "");
            if (str.length() > pos) return str.charAt(pos) - 48;
            return str.charAt((pos - str.length()) % str.length()) - 48;
        }

        public void linkSort (int[] num, java.util.LinkedList<Integer> list, int pos, int maxPos,
                          int start, int end, boolean isInit) {
            if (end - start <= 1) {
                if (isInit) {
                    list.add(num[start]);
                }
                return;
            }
            // get sub list
            java.util.LinkedList<Integer> subList = isInit ? list : new java.util.LinkedList<Integer>();
            int[] cursor = new int[10]; // 0 - 9
            for (int index = start; index < end; index += 1) {
                int cm = isInit ? num[index] : list.get(index),
                        ch = getDigit(cm, pos);
                maxPos = isInit ? Math.max((cm + "").length(), maxPos) : maxPos;

                subList.add(cursor[ch], cm);

                for (int ic = 0; ic < cursor.length; ic++) {
                    if (ic >= ch) {
                        ++cursor[ic];
                    }
                }
            }
            // merge subList back to list
            if (!isInit) {
                int k = 0;
                for (int val : subList) {
                    list.set(start + k ++, val);
                }
            }
            // break into the next pos
            if (++ pos > maxPos) return;
            for (int l = -1, r = l + 1; r < cursor.length; l++, r++) {
                int left = start + (l < 0 ? 0 : cursor[l]), right = start + cursor[r];
                if (left == right) continue;
                linkSort(num, list, pos, maxPos, left, right, false);
            }
        }

        public String largestNumber (int[] num) {
            String largest = "";

            java.util.LinkedList<Integer> list = new java.util.LinkedList<Integer>();
            linkSort(num, list, 0, 0, 0, num.length, true);
            if (list.get(list.size() - 1) == 0) return "0";

            for (int index = list.size() - 1; index >= 0; index -= 1) {
                largest += list.get(index) + "";
            }
            return largest;
        }
    }

    public static void main (String args[]) {
        System.out.println(new Solution_LinkedList().largestNumber(new int[] {830, 8308, 830}));
        System.out.println(new Solution_LinkedList().largestNumber(new int[] {12, 121}));
        System.out.println(new Solution_LinkWithSort().largestNumber(new int[] {1231231, 123, 1231, 12312}));
    }
}
