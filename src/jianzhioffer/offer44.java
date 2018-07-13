package jianzhioffer;

/**
 * 题目描述：求数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从0开始计数）是5，第13位是1，第19位是4，
 * 等等。请写一个函数，求任意第n位对应的数字。
 */
public class offer44 {
    /**
     * 解决思路：
     * 以序列1001举例子：
     * 序列前10位是0-9，跳过，再从后面找991位；
     * 序列前90*2位是10-99，跳过，再从后面找811位；
     * 序列前900*3位是100-999，由于811<2700，所以第811位在100-999之间，
     * 811 = 270 * 3 + 1，所以是100+270=370的第1位，即3
     *
     * 注意：若index=190，二位的时候，190-10 == 180，180 % 2 == 0,此时说明是180的前一个数字
     * 179的最后一位。
     * @param index
     * @return
     */
    public int digitAtIndex(int index){
        if(index < 0)
            return -1;
        int digits = 1; //代表index位是在几位数字中，初始表示0-9中，一位数字中
        while(true){
            int numbers = countOfIntegers(digits);//numbers是如3位数字的个数
            if(index <= numbers * digits)
                return digitAtIndex(index,digits);
            index -= digits * numbers;
            digits++;
        }
    }

    /**
     * 例如10-99一共有10*9位
     * @param digits
     * @return
     */
    private int countOfIntegers(int digits){
        if(digits == 1)
            return 10;
        int count = (int)Math.pow(10,digits - 1);
        return 9 * count;
    }

    /**
     * 找到index位于m位数中哪一个数字
     * @param index
     * @param digits
     * @return
     */
    private int digitAtIndex(int index,int digits){
        int number = beginNumber(digits) + index / digits;//找出那位数字
        int indexOfNumber = index % digits;//是那位数字的第几位
        if(indexOfNumber == 0){
            //说明是number前一个数number-1的最后一位
            return (number - 1)%10;
        }
        for(int i = 0; i < digits - indexOfNumber;i++){
            number /= 10;
        }
        return number;
    }
    private int beginNumber(int digits){
        if(digits == 1)
            return 0;
        return (int)Math.pow(10,digits - 1);
    }

    public static void main(String[] args) {
        offer44 test = new offer44();
        int result = test.digitAtIndex(189);
        System.out.println(result);
    }
}
