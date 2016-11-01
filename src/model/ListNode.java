package model;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static ListNode link (int... vals) {
        return link(vals, null);
    }

    public static ListNode link (int[] vals, ListNode head) {
        if (vals.length == 0) return null;
        ListNode fake = new ListNode(-1), cur = fake;
        for (int val : vals) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        cur.next = head;
        return fake.next;
    }

    public static ListNode link(ListNode headA, ListNode headB) {
        ListNode tailA = headA;

        while (null != tailA.next) {
            tailA = tailA.next;
            if (tailA.next == null) {
                tailA.next = headB;
                break;
            }
        }
        return headA;
    }

    public static void print(ListNode head) {
        while (null != head) {
            System.out.print(head.val);
            if (null != head.next) {
                System.out.print(" -> ");
                head = head.next;
            } else break;
        }
        System.out.println();
    }
}
