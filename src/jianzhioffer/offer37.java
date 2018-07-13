package jianzhioffer;

/**
 * 序列化与反序列化二叉树
 * NLR和LNR可以确定一棵二叉树，但是有2个问题：
 * 1、不允许有重复数值的节点
 * 2、反序列化效率低，因为必须全部取去两个数组，才能开始反序列化
 */
public class offer37 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    //序列化成1,2,4,$,$,$,3,5,$,$,6,$,$的格式
    private StringBuilder result = new StringBuilder();
    String Serialize(TreeNode root) {
        StringBuilder ans = SerializeCore(root);
        ans.deleteCharAt(result.length() - 1);//删除最后一个","
        return ans.toString();
    }
    private StringBuilder SerializeCore(TreeNode root){
        if(root == null){
            result.append("$,");
            return result;
        }
        result.append(root.val + ",");
        SerializeCore(root.left);
        SerializeCore(root.right);
        return result;
    }
    private int index  = -1;
    TreeNode Deserialize(String str) {
        index++;
        int len = str.length();
        if(index >= len){
            return null;
        }
        String[] strr = str.split(",");
        TreeNode node = null;
        if(!strr[index].equals("$")){
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;

        offer37 test = new offer37();
        System.out.println(test.Serialize(node1));
    }
}
