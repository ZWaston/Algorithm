package jianzhioffer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class offer32 {

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 不分行从上到下打印二叉树，即层次遍历
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode first = queue.peek();//获取队首元素，但不删除
            queue.poll();//删除队首
            result.add(first.val);
            if(first.left != null)
                queue.offer(first.left);
            if(first.right != null)
                queue.offer(first.right);
        }
        return result;
    }

    /**
     * 分行从上到下打印二叉树,
     * 打印格式：
     * 第一行：第一层的节点
     * 第二行：第二行的节点
     * ...
     *
     * 分行的层次遍历结果为：
     8
     6 10
     5 7 9 11
     */
    /**
     * 那么需要额外两个参数记录：下一层节点的数量  和 当前层还有多少节点未打印（一旦剩余为0，换行）
     * @param root
     */
    public void PrintFromTopToBottom1(TreeNode root){
        if(root == null)
            return;
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> nodeNumInLevel = new ArrayList<>();//用来保存每一层的节点数量
        nodeNumInLevel.add(1);//第一层只有1个节点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int nextLevel = 0;//保存下一层节点的数量
        int toBePrinted = 1;
        while(!queue.isEmpty()){
            TreeNode first = queue.poll();//获取队首元素，并删除
            toBePrinted--;
            result.add(first.val);
            if(first.left != null){
                queue.offer(first.left);
                nextLevel++;
            }

            if(first.right != null){
                queue.offer(first.right);
                nextLevel++;
            }

            if(toBePrinted == 0){
                //如果当前层未打印的节点数量为0，说明当前层已经打印完毕
                if(nextLevel != 0)
                    nodeNumInLevel.add(nextLevel);//将下一层的节点数量保存
                toBePrinted = nextLevel;
                nextLevel = 0;
            }
        }

        //利用nodeNumInLevel和result分行打印
        for(int num : nodeNumInLevel){
            while(num != 0){
                System.out.print(result.remove(0) + " ");//移除第一个元素并返回
                num--;
            }
            System.out.println();
        }
    }

    /**
     * 之字形分行从上到下打印二叉树,
     * 打印格式：
     * 第一行：第一层的节点从左到右打印
     * 第二行：第二行的节点从右到左打印
     * ...
     *
     * 分行的层次遍历结果为：
     8
     10 6
     5 7 9 11
     */
    /**
     * 那么需要额外两个参数记录：下一层节点的数量  和 当前层还有多少节点未打印（一旦剩余为0，换行）
     * @param root
     */
    public void PrintFromTopToBottom2(TreeNode root){
        if(root == null)
            return;
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> nodeNumInLevel = new ArrayList<>();//用来保存每一层的节点数量
        nodeNumInLevel.add(1);//第一层只有1个节点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int nextLevel = 0;//保存下一层节点的数量
        int toBePrinted = 1;
        while(!queue.isEmpty()){
            TreeNode first = queue.poll();//获取队首元素，并删除
            toBePrinted--;
            result.add(first.val);
            if(first.left != null){
                queue.offer(first.left);
                nextLevel++;
            }

            if(first.right != null){
                queue.offer(first.right);
                nextLevel++;
            }

            if(toBePrinted == 0){
                //如果当前层未打印的节点数量为0，说明当前层已经打印完毕
                if(nextLevel != 0)
                    nodeNumInLevel.add(nextLevel);//将下一层的节点数量保存
                toBePrinted = nextLevel;
                nextLevel = 0;
            }
        }

        //利用nodeNumInLevel和result分之字形行打印
        boolean flag = true;//true代表从左到右
        for(int num : nodeNumInLevel){
            if(flag){
                while(num != 0){
                    System.out.print(result.remove(0) + " ");//移除第一个元素并返回
                    num--;
                }
                flag = false;
            }else{
                ArrayDeque<Integer> tmp = new ArrayDeque<>();//tmp为栈
                while(num != 0){
                    tmp.offerFirst(result.remove(0));
                    num--;
                }
                while(!tmp.isEmpty()){
                    System.out.print(tmp.pollFirst() + " ");
                }
                flag = true;
            }
            System.out.println();
        }
    }

    //牛客网的格式
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot){
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<ArrayList<Integer> > ans = new ArrayList<>();
        if(pRoot == null)
            return ans;
        ArrayList<Integer> nodeNumInLevel = new ArrayList<>();//用来保存每一层的节点数量
        nodeNumInLevel.add(1);//第一层只有1个节点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(pRoot);
        int nextLevel = 0;//保存下一层节点的数量
        int toBePrinted = 1;
        while(!queue.isEmpty()){
            TreeNode first = queue.poll();//获取队首元素，并删除
            toBePrinted--;
            result.add(first.val);
            if(first.left != null){
                queue.offer(first.left);
                nextLevel++;
            }

            if(first.right != null){
                queue.offer(first.right);
                nextLevel++;
            }

            if(toBePrinted == 0){
                //如果当前层未打印的节点数量为0，说明当前层已经打印完毕
                if(nextLevel != 0)
                    nodeNumInLevel.add(nextLevel);//将下一层的节点数量保存
                toBePrinted = nextLevel;
                nextLevel = 0;
            }
        }

        //利用nodeNumInLevel和result分之字形行打印
        ArrayList<Integer> arrayList;
        boolean flag = true;//true代表从左到右
        for(int num : nodeNumInLevel) {
            arrayList = new ArrayList<>();
            if (flag) {
                while (num != 0) {
                    arrayList.add(result.remove(0));
                    num--;
                }
                flag = false;
            } else {
                ArrayDeque<Integer> tmp = new ArrayDeque<>();//tmp为栈
                while (num != 0) {
                    tmp.offerFirst(result.remove(0));
                    num--;
                }
                while (!tmp.isEmpty()) {
                    arrayList.add(tmp.pollFirst());
                }
                flag = true;
            }
            ans.add(arrayList);
        }
        return ans;
    }

    public static void main(String[] args) {
        offer32 test = new offer32();
        TreeNode root = new TreeNode(8);
        TreeNode p1 = new TreeNode(6);
        TreeNode p2 = new TreeNode(10);
        TreeNode p3 = new TreeNode(5);
        TreeNode p4 = new TreeNode(7);
        TreeNode p5 = new TreeNode(9);
        TreeNode p6 = new TreeNode(11);

        root.left = p1;
        root.right = p2;
        p1.left = p3;
        p1.right = p4;
        p2.left = p5;
        p2.right = p6;

        ArrayList<Integer> result = test.PrintFromTopToBottom(root);
        System.out.println("不分行的层次遍历结果为：" + result.toString());
        System.out.println("分行的层次遍历结果为：");
        test.PrintFromTopToBottom1(root);
        System.out.println("之字分行的层次遍历结果为：");
        test.PrintFromTopToBottom2(root);
        ArrayList<ArrayList<Integer> > ans = test.Print(root);
        System.out.println("之字牛客网格式结果：" + ans.toString());
    }
}
