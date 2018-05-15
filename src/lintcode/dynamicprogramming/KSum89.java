package lintcode.dynamicprogramming;

/**
 * 待思考
 */
public class KSum89 {
    public static class Solution {
        /**
         * @param A: An integer array
         * @param k: A positive integer (k <= length(A))
         * @param target: An integer
         * @return: An integer
         */
        public int kSum(int[] A, int k, int target) {
            // write your code here

            int[][] dp = new int[k+1][target+1];

            dp[0][0] = 1;

            for(int i = 0; i < A.length; ++i) {
                for(int j = k; j >= 1; --j) {
                    for(int s = target; s >= A[i]; --s) {
                        dp[j][s] = dp[j-1][s-A[i]] + dp[j][s];
                    }
                }
            }

            for(int i = 0; i <= k; i++) {
                for(int j = 0; j <= target; j++){
                    System.out.print(dp[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            return dp[k][target];
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int [] arr = {1,2,1,2};
        int result = solution.kSum(arr, 2, 3);
        System.out.println(result);
    }
}
