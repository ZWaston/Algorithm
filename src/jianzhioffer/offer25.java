package jianzhioffer;

public class offer25 {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    //递归版本
    public static ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        if(list1.val <= list2.val){
            list1.next = Merge(list1.next,list2);
            return list1;
        }else{
            list2.next = Merge(list1,list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(3);
        ListNode p3 = new ListNode(5);
        ListNode p4 = new ListNode(7);
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;

        ListNode p5 = new ListNode(2);
        ListNode p6 = new ListNode(4);
        ListNode p7 = new ListNode(6);
        ListNode p8 = new ListNode(8);
        p5.next = p6;
        p6.next = p7;
        p7.next = p8;

        ListNode result = Merge(p1,p5);
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }


    }
}
