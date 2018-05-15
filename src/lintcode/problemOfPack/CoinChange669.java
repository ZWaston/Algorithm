package lintcode.problemOfPack;

/**
 * 给出不同面额的硬币以及一个总金额. 写一个方法来计算给出的总金额可以换取的最少的硬币数量.
 * 如果已有硬币的任意组合均无法与总金额面额相等, 那么返回 -1.
 * 你可以假设每种硬币均有无数个。
 * @author wangcheng zhang
 样例
 给出 coins = [1, 2, 5], amount = 11
 返回 3 (11 = 5 + 5 + 1)
 给出 coins = [2], amount = 3
 返回 -1

 lintcode链接：https://www.lintcode.com/problem/coin-change/description

 */
public class CoinChange669 {

    public static class Solution {
        /**
         * @param coins: a list of integer
         * @param amount: a total amount of money amount
         * @return: the fewest number of coins that you need to make up
         */
        public int coinChange(int[] coins, int amount) {
            // write your code here
            //用一维数组进行动态规划
            int[] dp = new int[amount + 1];
            //由于要求已有硬币的组合均无法与总金额相等，返回-1，因此要定义不正确状态，为INF
            //dp[0]全部为0，初始的dp[]数组代表的意思是没有任何硬币的情况下，金额为amount的组合最小值是多少
            //只有dp[0]为0，其余为INF，因为其余状态都无法组合成功
            int INF = 999999;
            for(int i = 1; i <= amount; i++) {
                dp[i] = INF;
            }

            for(int i = 0; i < coins.length; i++) {
                //正序，因为每个硬币的数量是无限的，完全背包问题
                for(int j = 0; j <= amount; j++) {
                    if(j - coins[i] >= 0)
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
            if(dp[amount] >= INF) return -1;
            return dp[amount];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr  = {1,2,5};
        int result = solution.coinChange(arr, 11);
        System.out.println(result);
    }
}
