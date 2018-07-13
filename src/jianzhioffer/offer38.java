package jianzhioffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 字符串排列
 * 输入字符串abc，输出排列abc,acb,bac,bca,cab,cba
 * 注意的是aaa只有一种全排列
 */
public class offer38 {
    public static void main(String[] args) {
        offer38 p = new offer38();
        System.out.println(p.Permutation("abc").toString());
    }

    /**
     * 递归的思路：将字符串看成两部分，第一部分：第一个字符，第二部分：剩余字符。
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        List<String> res = new ArrayList<>();
        if (str != null && str.length() > 0) {
            PermutationCore(str.toCharArray(), 0, res);
            Collections.sort(res);//最终结果按字典序输出
        }
        return (ArrayList)res;
    }

    /**
     *
     * @param str   输入的字符串数组
     * @param index 已经固定的下标
     * @param list  最终排列结果保存在list中
     */
    private void PermutationCore(char[] str,int index,List<String> list){
        if(index == str.length - 1){
            String val = String.valueOf(str);
            //如果位置全部固定，将str保存在list中
            if(!list.contains(val))
                list.add(val);
        }else {
            //否则，先确定第一个位置有多少种情况
            for(int i = index;i < str.length;i++){
                swap(str,i,index);
                PermutationCore(str,index + 1,list);
                swap(str,i,index);//注意要复位
            }
        }
    }

    private void swap(char[] str,int i,int j){
        if(str == null || i < 0 || i >= str.length || j < 0 || j >= str.length)
            return;
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

}
