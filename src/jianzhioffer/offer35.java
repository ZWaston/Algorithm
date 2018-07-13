package jianzhioffer;

import java.util.HashMap;

public class offer35 {
    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 第一种思路：借助一个HashMap，将复制节点与原始节点一一对应
     * 目的是为了以O(1)的时间找到random，但耗费了O(n)的空间
     * 此思路的时间开销也为O(n)
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null)
            return null;
        RandomListNode copyRandomListNode = new RandomListNode(pHead.label);
        //第一步：复制节点，并将对应信息用hash表保存
        HashMap<RandomListNode,RandomListNode> map = new HashMap<>();
        map.put(pHead,copyRandomListNode);
        RandomListNode p1,p2;//p1是访问原始链表的指针，p2是访问复制链表的指针
        p1 = pHead.next;
        p2 = copyRandomListNode;
        while(p1 != null){
            p2.next = new RandomListNode(p1.label);
            map.put(p1,p2.next);
            p1 = p1.next;
            p2 = p2.next;
        }
        //第二步：根据hash表设置每个节点的random
        p1 = pHead;
        while(p1 != null){
            if(p1.random != null){
                map.get(p1).random = map.get(p1.random);
                System.out.println(p1.label + ":" + map.get(p1).label);
            }
            p1 = p1.next;
        }
        return copyRandomListNode;
    }

    /**
     * 不使用辅助空间，假设初始复杂链表为 A->B->C ，其中A--->C（即A.random = C），步骤如下：
     * 1、在原始链表中增加复制节点，即变成A->A'->B->B'->C->C'；
     * 2、遍历链表，A'.random = A.random.next；
     * 3、拆分链表，奇数位的是我们初始链表，偶数位的是我们的复制链表
     * @param pHead
     * @return
     */
    public RandomListNode Clone1(RandomListNode pHead){
        if(pHead == null)
            return null;
        RandomListNode p1 = pHead;//用来遍历链表的指针
        //复制next，A->B->C 变成 A->A'->B->B'->C->C'
        while(p1 != null){
            RandomListNode copyNode = new RandomListNode(p1.label);
            copyNode.next = p1.next;
            p1.next = copyNode;
            p1 = copyNode.next;//移动指针p1
        }

        //复制random
        p1 = pHead;
        while(p1 != null){
            if(p1.random != null)
                p1.next.random = p1.random.next;
            p1 = p1.next.next;//注意p1移动2位
        }

        //拆分链表
        RandomListNode head = pHead.next;
        RandomListNode cur = head;//复制的链表为偶数位,原始链表为奇数位，cur代表的是复制链表遍历的指针
        p1 = pHead;
        while(p1 != null){
            p1.next = p1.next.next;
            if(p1.next != null)
                cur.next = cur.next.next;
            cur = cur.next;
            p1 = p1.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RandomListNode A = new RandomListNode(1);
        RandomListNode B = new RandomListNode(2);
        RandomListNode C = new RandomListNode(3);
        RandomListNode D = new RandomListNode(4);
        RandomListNode E = new RandomListNode(5);
        A.next = B;
        A.random = C;
        B.next = C;
        B.random = E;
        C.next = D;
        D.next = E;
        D.random = B;
        offer35 test = new offer35();
        RandomListNode result = test.Clone1(A);
        System.out.println(result.label + ":" + result.next.label + " " +result.random.label);
    }
}
