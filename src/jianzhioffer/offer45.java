package jianzhioffer;

import java.util.Arrays;
import java.util.Comparator;

/**
 *输入一个正整数数组，把数组里所有数字拼接起来拍成一个树，
 * 打印能拼接出的所有数字中最小的一个。例如输入数组｛3,32,321｝，
 * 则打印出这 3 个数字能排成的最小数字 321323.
 */
public class offer45 {
    /**
     * 这道题有陷阱：隐含的大数问题，若拼接之后的数字超过int，就会溢出
     * 思路很简单：两个数字m、n，比较mn 和 nm的大小，若nm<mn，则说明n应该排在m的前面，但注意隐含的大数问题
     * @param numbers
     * @return
     */
    public static String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length == 0)
            return "";
        //将整型数组转换成字符串数组
        String[] strNumbers = new String[numbers.length];
        for(int i = 0; i < numbers.length;i++){
            strNumbers[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(strNumbers, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s1.compareTo(s2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String s : strNumbers){
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] numbers = {3,32,321};
        String s = PrintMinNumber(numbers);
        System.out.println(s);
    }
}
