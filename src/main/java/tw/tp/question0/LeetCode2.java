package tw.tp.question0;

public class LeetCode2 {
    public static void main(String[] args) {
        ListNode test = createListNode(5);
        ListNode test2 = createListNode(5);
        ListNode result = add(test, test2, false);
        System.out.println(result);
    }

    public static ListNode add(ListNode l1, ListNode l2, Boolean carry) {
        boolean needCarry = false;
        if (l1 == null) {
            l1 = new ListNode(0);
        }
        if (l2 == null) {
            l2 = new ListNode(0);
        }
        int sum = l1.val + l2.val;
        if (carry) {
            sum++;
        }
        if (sum > 9) {
            sum = sum % 10;
            needCarry = true;
        }
        if (l1.next != null || l2.next != null) {
            ListNode nextListNode = add(l1.next, l2.next, needCarry);
            return new ListNode(sum, nextListNode);
        } else {
            if (needCarry) {
                return new ListNode(sum, new ListNode(1));
            } else {
                return new ListNode(sum);
            }
        }
    }

    public static ListNode createListNode(int... a) {
        ListNode result = new ListNode();
        ListNode currentNode = result;
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                currentNode.val = a[i];
            } else {
                currentNode.next = new ListNode(a[i]);
                currentNode = currentNode.next;
            }
        }
        return result;
    }

}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        if (this.next != null) {
            return String.valueOf(this.val) + this.next;
        } else {
            return String.valueOf(this.val);
        }
    }

}
