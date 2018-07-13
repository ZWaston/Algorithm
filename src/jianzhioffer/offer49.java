package jianzhioffer;

/**
 * 题目：我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求从小到大的顺序的第 1500个丑数。
 * 举例说明：
 * 例如 6、8 都是丑数，但 14 不是，它包含因子 7。习惯上我们把 1 当做第一个丑数。
 *
 */
public class offer49 {
    /**
     * 基本思路：先判断一个数是不是丑数，然后按顺序判断第1500个丑数。直观不够高效
     * @param index
     * @return
     */
    public int GetUglyNumber(int index){
        if(index <= 0)
            return 0;
        int number = 0;//从0开始判断是不是丑数
        int uglyFound = 0;//已经发现的丑数个数
        while(uglyFound < index){
            ++number;

            if(IsUgly(number)){
                uglyFound++;
            }
        }
        return number;
    }
    private boolean IsUgly(int number){
        while(number%2 == 0)
            number /= 2;
        while(number%3 == 0)
            number /= 3;
        while(number%5 == 0)
            number /= 5;
        return (number == 1) ? true : false;
    }

    /**
     * 用数组保存得到的丑数，关键是怎么使数组中的丑数排好序
     * 假设数组已经有排好序的丑数，分析如何生成下一个丑数：
     * 该丑数肯定是前面的丑数乘以2或者3或者5的结果，最简单的思路每次生成都乘以2、3、5，取最小。
     * 优化：例如乘以2的情况，每次保存一个索引，该索引的值*2比当前最大丑数大。
     * 同理，对于3和5，那么每次生成的丑数从这三个数最小的取，注意生成丑数后，这些索引需要向后移动。
     * @param index
     * @return
     */
    public int GetUglyNumber2(int index){
        if(index <= 0)
            return 0;
        int[] uglyArray = new int[index];
        uglyArray[0] = 1;
        int nextUglyIndex = 1;//表示的是下一个生成的丑数是第几个丑数
        int m2 = 0,m3 = 0,m5 = 0;
        while(nextUglyIndex < index){
            int min = Math.min(Math.min(uglyArray[m2]*2,uglyArray[m3]*3),uglyArray[m5]*5);//取三者最小的作为最新的丑数
            uglyArray[nextUglyIndex] = min;

            //注意还需要移动m2 m3 m5的下标
            while(uglyArray[m2] * 2 <= uglyArray[nextUglyIndex])
                m2++;
            while(uglyArray[m3] * 3 <= uglyArray[nextUglyIndex])
                m3++;
            while(uglyArray[m5] * 5 <= uglyArray[nextUglyIndex])
                m5++;

            nextUglyIndex++;
        }

        return uglyArray[nextUglyIndex - 1];

    }

    public static void main(String[] args) {
        offer49 test = new offer49();
        int result = test.GetUglyNumber2(1500);//859963392
        System.out.println(result);
    }
}
