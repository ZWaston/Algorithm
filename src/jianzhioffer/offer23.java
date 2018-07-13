package jianzhioffer;

import java.util.List;

//寻找链表中环的入口节点
/*
步骤：
1、先判断是不是有环；
2、有环之后，如何找到环的入口。（需要用到链表中环的长度，变成如何知道环的长度）
 */
public class offer23 {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public static ListNode EntryNodeOfLoop(ListNode pHead)
    {
        ListNode meetingNode = MeetingNode(pHead);
        //System.out.println(meetingNode.val);
        if(meetingNode == null)
            return null;

        //从相遇顶点获得环的长度
        int nodeNumInLoop = 1;
        ListNode pNode = meetingNode;
        while(pNode.next != meetingNode){
            pNode = pNode.next;
            nodeNumInLoop++;
        }
        //System.out.println(nodeNumInLoop);

        //获得环的长度后，用一个指针先走nodeNumInLoop步，然后另一个从pHead开始走，获得入口节点
        ListNode p1 = pHead;
        //p1先跑nodeNumInLoop步
        for(int i = 0; i < nodeNumInLoop; i++){
            p1 = p1.next;
        }
        ListNode p2 = pHead;
        //同时跑p1和p2
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 用两个指针，一个快一个慢，
     * 一个一次跑一步，一个一次跑两步，若相遇表示一定有环
     * 若没有环则返回null，
     * 否则返回环中的一个Node
     * @return
     */
    private static ListNode MeetingNode(ListNode pHead){
        if(pHead == null)
            return null;
        ListNode pSlow = pHead;
        ListNode pFast = pSlow.next;
        if(pFast == null)
            return null;
        while(pFast != null){
            if(pFast == pSlow)
                return pFast;
            pSlow = pSlow.next;//pSlow每次走一步

            pFast = pFast.next;//pFast每次走两步
            if(pFast != null)
                pFast = pFast.next;
        }
        return null;
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
        p6.next = p3;
        ListNode result = EntryNodeOfLoop(p1);
        System.out.println(result.val);
    }
}
