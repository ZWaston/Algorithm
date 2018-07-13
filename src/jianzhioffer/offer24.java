package jianzhioffer;
/**
 * 反转链表
 */
public class offer24 {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null)
            //若head为null，或者只有一个节点时
            return head;
        ListNode p1 = head;
        ListNode p2 = head.next;
        p1.next = null;
        ListNode p3 = p2.next;
        //p2是正在遍历的点
        while(p3 != null){
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
        }
        p2.next = p1;
        return p2;
    }

    public static void main(String[] args) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);
        ListNode p6 = new ListNode(6);
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;

        ListNode result = ReverseList(p1);
        while(result!=null){
            System.out.println(result.val);
            result = result.next;
        }
    }
}
