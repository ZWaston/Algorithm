package jianzhioffer;

//对称的二叉树
public class offer28 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null)
            return true;
        boolean result = isSymmetrical(pRoot.left,pRoot.right);
        return result;
    }

    private boolean isSymmetrical(TreeNode tree1,TreeNode tree2){
        if(tree1 == null && tree2 == null)
            return true;
        if(tree1 == null || tree2 == null)
            return false;
        if(tree1.val != tree2.val)
            return false;
        return isSymmetrical(tree1.right,tree2.left) && isSymmetrical(tree1.left,tree2.right);
    }
}
