package jianzhioffer;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class offer34 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }



    private ArrayList<ArrayList<Integer>> pathAll = new ArrayList<ArrayList<Integer>>();//保存所有path的结果
    private ArrayList<Integer> path = new ArrayList<Integer>();//正在遍历的path
    /**
     * 二叉树中和为某一值的路径，根节点->叶子节点的路径
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if(root == null)
            return pathAll;
        //先序遍历，NLR
        path.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null){
            //如果为子节点并且target等于0，保存路径
            pathAll.add(new ArrayList<Integer>(path));//注意要new一个对象，否则后面操作会更改这个
        }
        //如果当前节点不是叶子节点，继续访问它的子节点
        FindPath(root.left,target);
        FindPath(root.right,target);
        //当节点访问结束后，需要回退到父节点
        path.remove(path.size() - 1);
        return pathAll;

    }

    public static void main(String[] args) {
        offer34 test = new offer34();
        TreeNode root = new TreeNode(10);
        TreeNode p1 = new TreeNode(5);
        TreeNode p2 = new TreeNode(12);
        TreeNode p3 = new TreeNode(4);
        TreeNode p4 = new TreeNode(7);
        root.left = p1;
        root.right = p2;
        p1.left = p3;
        p1.right = p4;
        ArrayList<ArrayList<Integer>> result = test.FindPath(root,22);
        System.out.println(result.toString());

    }
}
