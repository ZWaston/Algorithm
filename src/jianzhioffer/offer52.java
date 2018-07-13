package jianzhioffer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 两个链表的第一个公共节点
 */
public class offer52 {
     static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 从尾部开始比较两个链表的节点是否相同，用栈这个数据结构
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null | pHead2 == null)
            return null;
        Deque<ListNode> stack1 = new ArrayDeque<ListNode>();//推荐使用ArrayDeque作为栈
        Deque<ListNode> stack2 = new ArrayDeque<ListNode>();
        ListNode p1 = pHead1,p2 = pHead2;
        while(p1 != null){
            stack1.offerFirst(p1);
            p1 = p1.next;
        }
        while(p2 != null){
            stack2.offerFirst(p2);
            p2 = p2.next;
        }
        ListNode result = null;
        p1 = stack1.pollFirst();
        p2 = stack2.pollFirst();
        while(p1 == p2 && p1 != null){
            result = p1;
            p1 = stack1.pollFirst();
            p2 = stack2.pollFirst();
        }
        return result;
    }

    /**
     * 不使用栈，先获取链表的长度，然后用两个指针去跳
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2){
        if(pHead1 == null || pHead2 == null)
            return null;
        int len1 = 0,len2 = 0;
        ListNode p1 = pHead1,p2 = pHead2;
        while(p1 != null){
            p1 = p1.next;
            len1++;
        }
        while(p2 != null){
            p2 = p2.next;
            len2++;
        }
        //长度长的指针先跳
        ListNode longList = pHead1;
        ListNode shortList = pHead2;
        int lenDiff = len1 - len2;
        if(lenDiff < 0){
            longList = pHead2;
            shortList = pHead1;
        }
        //现在长链表先走几步，然后同时在两个链表上遍历
        for(int i = 0; i < lenDiff; i++)
            longList = longList.next;
        while(longList != null && shortList != null && longList != shortList){
            longList = longList.next;
            shortList = shortList.next;
        }
        return longList;
    }

    public static void main(String[] args) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);
        ListNode p6 = new ListNode(6);
        ListNode p7 = new ListNode(7);
        p1.next = p2;
        p2.next = p3;
        p3.next = p6;
        p6.next = p7;
        p4.next = p5;
        p5.next = p6;
        ListNode result = FindFirstCommonNode2(p1,p4);
        System.out.println(result.val);
    }

}
