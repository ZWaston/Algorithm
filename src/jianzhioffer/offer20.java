package jianzhioffer;

import java.util.Scanner;

//表示数值的字符串
/*
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
*/
public class offer20 {
    private int index = 0; //用一个全局变量index表示正在遍历的位置
    //格式可以用A[.[B]][e|EC]] 或者 .B[e|EC]表示，
    //其中A和C都是整数（可以有正负号，也可以没有），B是无符号整数
    public boolean isNumeric(char[] str) {
        if(str == null || str.length < 1) return false;
        boolean flag = scanInteger(str);   //注意A是可以不存在的，当A不存在时，小数点部分不能为空
        if(index < str.length && str[index] == '.'){
            index++;
            //注意scanUnsignedInteger(str)一定要放在前面，必须先执行index++，否则flag为true会不执行，或者用|代替||
            flag = scanUnsignedInteger(str) || flag;
        }
        if(index < str.length && (str[index] == 'e' || str[index] == 'E')){
            index++;
            flag = scanInteger(str) && flag;
        }
        //index == str.length是为了保证遍历了整个str字符串
        return flag && index == str.length;
    }
    //扫描有符号整数，正负号可能存在也可能不存在
    private boolean scanInteger(char[] str){
        if(index < str.length && (str[index] == '+' || str[index] == '-')){
            index++;
        }
        return scanUnsignedInteger(str);
    }
    //用来扫描无符号整数123.45e+6
    private boolean scanUnsignedInteger(char[] str){
        int start = index;
        //index表示正在遍历的位置
        while (index < str.length && str[index] >= '0' && str[index] <= '9'){
            index++;
        }
        return start < index; //是否存在整数，当str第一个字符就不是0-9时，返回的是false
    }

    public static void main(String[] args) {
        offer20 solution = new offer20();
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            System.out.println(solution.isNumeric(input.toCharArray()));
        }
    }
}

