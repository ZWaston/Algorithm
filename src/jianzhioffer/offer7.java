package jianzhioffer;

/**
 * 重建二叉树
 * 通过前序遍历 和 中序遍历 来重建二叉树，假设遍历结果是不包含重复数字的。
 */
public class offer7 {

    public static class Solution {
        /**
         * 定义二叉树的结构，Java没有指针，对象是引用
         */
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         *重建二叉树的入口函数
         * @param pre       前序遍历的结果
         * @param in        中序遍历的结果
         * @return          返回根据中序和前序重建的二叉树
         */
        public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
            //健壮性判断
            if(pre == null || in == null || pre.length != in.length) {
                return null;
            }

            return reConstructCore(pre, 0, pre.length - 1, in, 0, in.length - 1);
        }

        /**
         * 重建二叉树的递归函数
         * @param pre               前序遍历的数组
         * @param preStart          前序遍历数组的起始位置
         * @param preEnd            前序遍历数组的终止位置
         * @param in                中序遍历的数组
         * @param inStart           中序遍历的数组的起始位置
         * @param inEnd             中序遍历的数组的终止位置
         * @return                   返回一个重建二叉树的root
         */
        public TreeNode reConstructCore(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {

            //确定递归终止条件
            if(preStart > preEnd || inStart > inEnd) {
                return null;
            }

            //前序遍历的第一个节点是根节点
            int rootValue = pre[preStart];
            //构建根节点
            TreeNode root = new TreeNode(rootValue);

            //在数组in遍历，找到与pre的第一个节点相同的数字
            for(int i = inStart; i <=inEnd; i++) {
                if(in[i] == pre[preStart]) {
                    //说明in[i]是中序遍历的根节点
                    root.left = reConstructCore(pre, preStart + 1, preStart + i - inStart,
                            in, inStart, i - 1);
                    root.right = reConstructCore(pre, preStart + i - inStart + 1, preEnd,
                            in, i + 1, inEnd);
                    break;
                }
            }
            return root;
        }

        /**
         * 前序遍历二叉树，递归实现
         * @param root
         */
        public void printTree(TreeNode root) {
            if(root != null) {
                System.out.print(root.val + " ");
                printTree(root.left);
                printTree(root.right);
            }
        }

    }
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        s.printTree(s.reConstructBinaryTree(pre, in));
    }
}
