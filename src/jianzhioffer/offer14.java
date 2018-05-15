package jianzhioffer;

/**
 * 剑指offer第14题，剪绳子。
 * 题目：给你一根长度为n的绳子，请把绳子剪成m段(m和n都是整数，n>1并且m>1)每段绳子的长度记为k[0],k[1],...,k[m].
 * 请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
 * 例如，当绳子的长度为8时，我们把它剪成长度分别为2,3,3的三段，此时得到的最大乘积是18.
 * f(n) = max(f(i) * f(n-i))，因此外部是个n循环，内部是个i的遍历
 * 动态规划 或者 贪心算法
 */
public class offer14 {
    /**
     * 常规的动态规划，时间复杂度为O(n^2)，空间复杂度为O(n)。
     * @param length    绳子的总长度
     * @return          剪绳子，返回得到的最大乘积
     */
    private static int maxProductAfterCuttingSolution1(int length) {
        if(length < 2) return 0;
        if(length == 2) return 1;
        if(length == 3) return 2;
        int[] arr = new int[length + 1];//java默认初始化为0
        arr[1] = 1; //需注意arr[1 .. 3]应初始化为1、2、3
        arr[2] = 2;
        arr[3] = 3;
        int max = 0;
        for(int i = 4; i <= length; i++) {
            max = 0;
            for(int j = 1; j <= i/2; j++) {
                int tmp = arr[i - j] * arr[j];
                if(max < tmp)
                    max = tmp;
                arr[i] = max;
            }
        }

        max = arr[length];
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProductAfterCuttingSolution1(8));
    }
}
