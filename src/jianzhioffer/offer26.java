package jianzhioffer;

public class offer26 {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 判断root2是不是root2的子结构
     * 约定：空树不是任意一个树的子结构
     * @param root1
     * @param root2
     * @return
     */
    public static boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null)
            return false;
        boolean result = false;
        if(root1.val == root2.val){
            //以这个根节点为为起点判断是否包含Tree2
            result = doesTree1HasTree2(root1,root2);
        }

        //如果找不到，那么就再去root1的左儿子当作起点，去判断是否包含Tree2
        if(!result){
            result = HasSubtree(root1.left,root2);
        }

        //如果还是找不到，那么就再去root1的右儿子当作起点，去判断是否包含Tree2
        if(!result){
            result = HasSubtree(root1.right,root2);
        }

        return result;
    }

    private static boolean doesTree1HasTree2(TreeNode root1,TreeNode root2){
        //如果Tree2遍历完还能对的上，返回true
        if(root2 == null)
            return true;
        //如果Tree1遍历完，Tree2还有，返回false
        if(root1 == null)
            return false;
        //一旦有一个点对应不上返回false
        if(root1.val != root2.val)
            return false;
        return doesTree1HasTree2(root1.left,root2.left) && doesTree1HasTree2(root1.right,root2.right);
    }
}
