package jianzhioffer;

//链表中倒数第k个节点
//利用两个指针，一个指针先走k步，注意鲁棒性

import java.lang.reflect.Field;

public class offer22 {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public static ListNode FindKthToTail(ListNode head,int k) {
        //k从1开始计数，即尾节点是倒数第一个节点
        if(head == null || k == 0)
            return null;
        //指针p1先跑k步
        ListNode p1 = head;
        for(int i = 0; i < k - 1; i++){
            //还需要判断链表长度是否够k
            if(p1.next != null)
                p1 = p1.next;
            else
                return null;
        }
        //指针p2指向head
        ListNode p2 = head;
        //p1和p2指针同时跑，直到p1跑向链表末尾
        while(p1.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(3);
        node1.next = node2;
        ListNode node3 = new ListNode(4);
        node2.next = node3;

        ListNode result = FindKthToTail(head,2);
        System.out.println(result.val);
    }
}
