package jianzhioffer;

/**
 * 给定一个数字，按照如下规则翻译成字符串：0翻译成“a”，1翻译成“b”...25翻译成“z”。
 * 一个数字有多种翻译可能，
 * 例如12258一共有5种，分别是bccfi，bwfi，bczi，mcfi，mzi。实
 * 现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */
public class offer46 {
    public static int getTranslationCount(int number){
        if(number < 0)
            return 0;
        return getTranslationCount(Integer.toString(number));
    }

    /**
     * 动态规划，如12258
     * f(i) = f(i+1) + (0或1)*f(i+2)
     * @param number
     * @return
     */
    private static int getTranslationCount(String number){
        char[] str = number.toCharArray();
        //从后往前算
        int f1 = 0,f2 = 1,g = 0;
        int temp;
        for(int i = str.length - 2;i >= 0; i--){
            //判断g是0还是1，若f(i)和f(i+1)能翻译，则为1，否则为0
            if(Integer.parseInt(str[i]+""+str[i+1]) < 26)
                g = 1;
            else
                g = 0;
            temp = f2;
            f2 = f2 + g*f1;
            f1 = temp;
        }
        return f2;
    }

    public static void main(String[] args) {
        System.out.println(getTranslationCount(-10));  //0
        System.out.println(getTranslationCount(1234));  //3
        System.out.println(getTranslationCount(12258)); //5
    }
}
