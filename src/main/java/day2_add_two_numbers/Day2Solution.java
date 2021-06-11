package day2_add_two_numbers;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + val + ((next != null) ? "," + next : "");
    }
}

public class Day2Solution {

    public static void main(String[] args) {
        ListNode num1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode num2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        System.out.println(addTwoNumbers(num1, num2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1It = l1;
        ListNode l2It = l2;
        ListNode sentinel = new ListNode(0);
        sentinel.next = sentinel;
        int carry = 0;
        ListNode result = new ListNode(0);
        ListNode resultLast = result;
        while (l1It != sentinel || l2It != sentinel) {
            var sum = l1It.val + l2It.val + carry;
            carry = sum / 10;
            l1It = (l1It.next != null) ? l1It.next : sentinel;
            l2It = (l2It.next != null) ? l2It.next : sentinel;
            resultLast.next = new ListNode(sum % 10);
            resultLast = resultLast.next;
        }
        if (carry != 0) {
            resultLast.next = new ListNode(carry);
        }
        return result.next;
    }


}
