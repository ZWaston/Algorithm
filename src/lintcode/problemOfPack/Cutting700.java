package lintcode.problemOfPack;

public class Cutting700 {
    public static class Solution {
        /**
         * @param prices: the prices
         * @param n: the length of rod
         * @return: the max value
         */
        public int cutting(int[] prices, int n) {
            // Write your code here
            int[] dp = new int[n + 1];
            //初始化
            for(int i = 1; i <= n; i++) {
                dp[i] = prices[i - 1];
            }

            //n/2是为了加快速度，因为裁剪是对称的
            for(int i = 0; i < n/2; i++) {
                //必须正序，因为dp[j]要用到之前的dp[i]和dp[j - i]，这些必须是已经算出来的
                for(int j = i; j <= n; j++) {
                    dp[j] = Math.max(dp[j], dp[j - i] + dp[i]);
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {3,5,8,9,10,17,17,20};
        int result = solution.cutting(arr, 8);
        System.out.println(result);
    }
}
