package jianzhioffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class offer38extend {
    /**
     * 扩展题1，求字符的所有组合
     * 如：abc的全部组合为 a、b、c、ab、ac、bc、abc
     * 注意：ab和ba是2个排列，但是是一个组合
     * 解题思路：假设我们想在长度为n的字符串中求m个字符的组合。
     * 我们先从头扫描字符串的第一个字符。针对第一个字符，我们有两种选择：
     * 第一是把这个字符放到组合中去，接下来我们需要在剩下的n-1个字符中选取m-1个字符；
     * 第二是不把这个字符放到组合中去，接下来我们需要在剩下的n-1个字符中选择m个字符。
     * 这两种选择都很容易用递归实现。
     * @param str
     * @return
     */
    public ArrayList<String> extend1(String str){
        List<String> result = new ArrayList<>();
        if(str != null && str.length() != 0){
            for(int m = 1; m <= str.length(); m++){
                StringBuilder sb = new StringBuilder();
                //从字符串str中选择m个字符
                extend1Core(str.toCharArray(),0,m,sb,result);
            }
            //Collections.sort(result);//最终结果按字典序输出
        }
        return (ArrayList<String>) result;
    }


    private void extend1Core(char[] str,int index,int number,StringBuilder sb,List<String> result){
        if (number == 0) {
            char[] tmp = sb.toString().toCharArray();
            Arrays.sort(tmp);//将符合条件的字符串按照字典序排序，以便于剔除重复的
            String val = String.valueOf(tmp);
            if(!result.contains(val))
                result.add(val);
            return;
        }
        if (index == str.length)
            //说明字符串遍历完了，直接返回
            return;
        sb.append(str[index]);  //向StringBuilder中添加元素
        extend1Core(str, index + 1, number - 1, sb,result);
        sb.deleteCharAt(sb.length() - 1);  //重置，在StringBuilder中删除元素
        extend1Core(str, index + 1, number, sb,result);

    }

    public static void main(String[] args) {
        offer38extend test = new offer38extend();
        ArrayList<String> ans = test.extend1("abc");
        System.out.println(ans.toString());
    }

}
