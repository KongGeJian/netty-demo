package com.zz;

public class SolutionTest {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 2 -> 4 -> 3
        ListNode l1 = new ListNode(9);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(4);
//        l1.next.next.next.next = new ListNode(9);
        // 5 -> 6 -> 4
        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        long t1 = System.currentTimeMillis();
        ListNode r1 = solution.addTwoNumbers1(l1, l2);
        System.out.println(System.currentTimeMillis() - t1);
        System.out.println(r1);
        System.out.println("------------------");
        long t2 = System.currentTimeMillis();
        ListNode r2 = solution.addTwoNumbers1(l1, l2);
        System.out.println(System.currentTimeMillis() - t2);
        System.out.println(r2);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + " -> " + next;
    }
}

class Solution {

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
//        ListNode dummyHead = new ListNode(0);
//        ListNode p = l1, q = l2, curr = dummyHead;
//        int carry = 0;
//        while (p != null || q != null) {
//            int x = (p != null) ? p.val : 0;
//            int y = (q != null) ? q.val : 0;
//            int sum = carry + x + y;
//            carry = sum / 10;
//            curr.next = new ListNode(sum % 10);
//            curr = curr.next;
//            if (p != null) p = p.next;
//            if (q != null) q = q.next;
//        }
//        if (carry > 0) {
//            curr.next = new ListNode(carry);
//        }
//        return dummyHead.next;
        ListNode resultList = new ListNode(0);
        int cache = 0;

        ListNode l3 = resultList;
        while (l1 != null || l2 != null || cache > 0){
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int l3Val = l1Val + l2Val + cache;
            cache = 0;

            // 判断是否大于 9 大于9 进一位
            if (l3Val >  9){
                cache = 1;
                l3Val = l3Val - 10;
            }

            l3.next = new ListNode(l3Val);

            l3 = l3.next;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }

        return resultList.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        boolean isF = false;
        while ((p != null && q != null) || ((isF = true) && carry > 0)) {
            if (isF) {
                if (p == null && q == null) {
                    curr.next = new ListNode(carry);
                    carry = 0;
                } else if (p != null) {
                    int sum = carry + p.val;
                    carry = sum / 10;
                    curr.next = new ListNode(sum % 10);
                    curr = curr.next;
                    p = p.next;
                } else {
                    int sum = carry + q.val;
                    carry = sum / 10;
                    curr.next = new ListNode(sum % 10);
                    curr = curr.next;
                    q = q.next;
                }
                continue;
            }
            int sum = carry + p.val + q.val;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            p = p.next;
            q = q.next;
        }
        if (p != null) {
            curr.next = p;
        }
        if (q != null) {
            curr.next = q;
        }
        return dummyHead.next;
    }

}
