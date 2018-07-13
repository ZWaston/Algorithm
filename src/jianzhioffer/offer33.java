package jianzhioffer;

public class offer33 {
    /**
     * 判断数组是不是某个“二叉搜索树”的后序遍历结果
     * 思路：利用LRN的特点，最后一个节点肯定是根节点，左子树的值都比根节点小，右子树都比根节点大
     * 节点的value值都不相同
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        int len = sequence.length;
        if(sequence == null || len <= 0)
            return false;

        return VerifySquenceOfBSTCore(sequence, 0, len - 1);
    }
    private boolean VerifySquenceOfBSTCore(int [] sequence,int start,int end) {
        int root = sequence[end];
        //二叉搜索树中左子树的值都小于根节点
        int i = 0;  //i为左子树的下标
        for(; i < end;i++){
            if(sequence[i] > root)
                break;
        }

        //二叉搜索树中右子树的值都大于根节点
        int j = i;
        for(; j < end;j++){
            if(sequence[j] < root)
                //若发现右子树存在比root小的，异数组一定不能构成二叉搜索树
                return false;
        }

        //判断左子树是不是二叉搜索树
        boolean left = true;
        if(i > 0)
            left = VerifySquenceOfBSTCore(sequence, 0, i - 1);

        //判断右子树是不是二叉搜索树
        boolean right = true;
        if(i < end){
            right = VerifySquenceOfBSTCore(sequence, i, j - 1);
        }

        return (left&&right);
    }
}
