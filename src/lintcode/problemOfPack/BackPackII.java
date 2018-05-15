package lintcode.problemOfPack;

/**
 描述:
 给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大？
 A[i], V[i], n, m均为整数。你不能将物品进行切分。你所挑选的物品总体积需要小于等于给定的m。
 样例:
 对于物品体积[2, 3, 5, 7]和对应的价值[1, 5, 2, 4], 假设背包大小为10的话，最大能够装入的价值为9。
 挑战:
 O(n x m) memory is acceptable, can you do it in O(m) memory?

 * @author wangcheng zhang
 * lintcode链接：https://www.lintcode.com/problem/backpack-ii/description
 */
public class BackPackII {

    /**
     * 问题分析，与backPack背包问题本质是一样的，唯一的区别就是把backPack背包问题状态转移方程的A[i]换成V[i]
     */
    public static class Solution {
        /**
         * @param m: An integer m denotes the size of a backpack
         * @param A: Given n items with size A[i]
         * @param V: Given n items with value V[i]
         * @return: The maximum value
         */
        public int backPackII(int m, int[] A, int[] V) {
            // write your code here
            //创建一维数组，初始状态表示没有任何物品时，背包载重为i的情况下，能装多满，故初始化为0
            int[] dp = new int[m + 1];    //java已经默认初始化为0
            for(int i = 0; i < A.length; i++) {
                //降序，保证第i次循环每一次求dp[j]用的都是上一次i-1循环的dp数组，而不是已经更改过的数组
                for(int j = m; j >= 0; j--) {
                    if(j - A[i] >= 0)
                        dp[j] = Math.max(dp[j - A[i]] + V[i],dp[j]);
                }
            }
            return dp[m];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {2,3,5,7};
        int[] V = {1,5,2,4};
        int result = solution.backPackII(10, A, V);
        System.out.println(result);
    }
}
