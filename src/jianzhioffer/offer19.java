package jianzhioffer;

import java.util.Scanner;

/**
 * 正则表达式匹配
 */
public class offer19 {
    public static boolean match(String input, String pattern) {
        if (input == null || pattern == null) {
            return false;
        }
        return matchCore(input,0, pattern,0);
    }
    private static boolean matchCore(String input,int i, String pattern,int p){
        //出口条件1:input 和 pattern都已经遍历到字符串末尾了
        if((input.length() == i) && (pattern.length() == p)){
            return true;
        }

        //出口条件2:input未到末尾，pattern到末尾
        if((input.length() != i) && (pattern.length() == p)){
            return false;
        }

       //出口条件3:input到末尾，pattern未到末尾，注意空和a*这种情况
        if((input.length() == i) && (pattern.length() != p)){
            //当模式pattern字符串的第二个字符是*时
            if((p + 1 < pattern.length()) && (pattern.charAt(p + 1) == '*')) {
                return matchCore(input,i,pattern,p + 2); //表示*代表的字母出现0次
            }
            //其余情况返回false
            return false;
        }

        //当模式pattern字符串的第二个字符是*时
        if((p + 1 < pattern.length()) && (pattern.charAt(p + 1) == '*')){
            //若首字母是匹配的，这里应该判断input是否到尾
            if((input.charAt(i) == pattern.charAt(p)) || (input.charAt(i)=='.')){
                return matchCore(input,i,pattern,p + 2) //表示*代表的字母出现0次
                        || matchCore(input,i + 1,pattern,p + 2)//表示*代表的字母出现1次
                        || matchCore(input,i + 1,pattern,p); //表示*代表的字母出现多次,pattern指针不变
            }else{
                //首字母不匹配，直接返回false
                return false;
            }
        }

        //当模式pattern字符串的第二个字符不是*时
        //若首字母匹配
        if((input.charAt(i) == pattern.charAt(p)) || (input.charAt(i)=='.')){
            return matchCore(input,i + 1,pattern,p + 1);
        }

        //其余情况均为不匹配
        return false;
    }

    //牛客网解法
    private static class Solution {
        public boolean match(char[] str, char[] pattern) {
            if (str == null || pattern == null) {
                return false;
            }
            int strIndex = 0;
            int patternIndex = 0;
            return matchCore(str, strIndex, pattern, patternIndex);
        }

        public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
            //有效性检验：str到尾，pattern到尾，匹配成功
            if (strIndex == str.length && patternIndex == pattern.length) {
                return true;
            }
            //pattern先到尾，匹配失败
            if (strIndex != str.length && patternIndex == pattern.length) {
                return false;
            }
            //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
            if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
                if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                    return matchCore(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
                            || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
                            || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
                } else {
                    return matchCore(str, strIndex, pattern, patternIndex + 2);
                }
            }
            //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
            }
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Solution solution = new Solution();
        while(true){
            String input = scanner.nextLine();
            String pattern = scanner.nextLine();
            System.out.println(solution.match(input.toCharArray(),pattern.toCharArray()));
        }
    }
}

