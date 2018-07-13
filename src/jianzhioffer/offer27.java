package jianzhioffer;

public class offer27 {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void Mirror(TreeNode root) {
        if(root == null)
            return;
        TreeNode tmp;

        tmp = root.right;
        root.right = root.left;
        root.left = tmp;

        if(root.left != null)
            Mirror(root.left);
        if(root.right != null)
            Mirror(root.right);

    }
}
