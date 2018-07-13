package jianzhioffer;

/**
 * 1~n整数中数字1出现的次数
 */
public class offer43 {
    /**
     * 简单思路：对每一个数进行1的个数统计，再累计
     * 时间复杂度O(n*lgn)
     * 时间复杂度过高
     * @param n
     * @return
     */
    public static int NumberOf1Between1AndN_Solution(int n) {
        if(n <= 0)
            return 0;
        int sum = 0;
        for(int i = 1; i <= n; i++){
            sum += NumberOf1(i);
        }
        return sum;
    }
    private static int NumberOf1(int n){
        int number = 0;
        while(n != 0){
            if(n % 10 == 1)
                number++;
            n = n/10;
        }
        return number;
    }

    /**
     * 利用数字的特征
     * 如：534
     * 先看个位数1出现的次数：_ _ 1，那么0~530的个位数为1的次数一共有53（0~52一共有53个），由于4 > 0，所以还有一个531
     * 所以个位数1出现的次数 = 54；
     * 再看十位数1出现的次数：_ 1 _，先看0~419，一共有5*10个，又因为3 > 0，所以51_也符合，还有10个，一共有5*10+10=60个；
     * 最后看百位数1出现的次数:1 _ _，0*100 + 100 = 100.
     * 所以534 = 54 + 60 + 100 = 214；
     * 同理。504 = （50+1）+（5*10+0） + （0*100 + 100）=201.
     * 514 = 54 + 55 + 100 = 209
     * 记某一位的权值base（如个位的base=1，十位为10），该位的值为weight，该位之前的数字为former，之后的数字为behind，则：
     * 1）若weight = 0，则1在该位出现的次数为：former*base
     * 2)若weight > 1，则1在该位出现的次数为：former*base + base
     * 3)若weight = 1，则1在该位出现的次数为：former*base + behind + 1
     * 如514中，求十位的1的出现次数=5 * 10 + 4 + 1 = 55
     * @param n
     * @return
     */
    public static int NumberOf1Between1AndN_Solution2(int n){
        if(n < 1)
            return 0;
        int result = 0;//保存最终结果
        int base = 1;//从个位数开始
        int former = n;
        while(former > 0){
            int weight = former%10;
            former/=10;
            result += former*base;
            if (weight == 1)
                result += (n % base) + 1;
            else if(weight > 1)
                result += base;
            base*=10;

        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN_Solution(514));
        System.out.println(NumberOf1Between1AndN_Solution2(514));
    }
}
