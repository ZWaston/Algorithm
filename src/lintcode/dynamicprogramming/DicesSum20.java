package lintcode.dynamicprogramming;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 题目描述：
 * 扔 n 个骰子，向上面的数字之和为 S。给定 Given n，请列出所有可能的 S 值及其相应的概率。
 * 样例:
 给定 n = 1，返回 [ [1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]]。

 基本思路：
 假设有n个骰子，总的点数和为sum，那么在前面n-1个骰子的情况下，一共有sum - 1、sum - 2、sum - 3、
 sum - 4、sum - 5、sum - 6六种情况。
 所以n个骰子投出sum的情况，是上述六种情况的总和。
 定义初始值：当n = 1时，
 f(1,1) = f(1,2) = f(1,3) = f(1,4) = f(1,5) = f(1,6) =1
 那么当n = 2时，
 sum = 1 ~ 12,则f(2,2) = f(1,1) = 1。
 f(2,6) = f(1,5) + f(1,4) + f(1,3) + f(1,2) + f(1,1) = 5。

 其实这就是一个动态规划的过程。
 */
public class DicesSum20 {
    public static class Solution {
        /**
         * @param n an integer
         * @return a list of Map.Entry<sum, probability>
         */
        public List<Map.Entry<Integer, Double>> dicesSum(int n) {
            // Write your code here
            // Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
            // to create the pair
            //用long，测试用例骰子较多时，可能int会溢出
            long[][] dp = new long[n + 1][6 * n + 1];
            //骰子和点数都是从1开始，初始化骰子个数为1的情况
            for(int i = 1; i <= 6; i++) {
                dp[1][i] = 1;               //其余的情况都是为0
            }
            //从第二个骰子开始遍历
            for(int i = 2; i <= n; i++) {
                //j从i开始，是为了增加速度，因为每个骰子点数至少为1
                for(int j = i; j <= 6 * i; j++) {
                    for(int k = 1; k <= 6; k++) {
                        if(j - k > 0) {
                            dp[i][j] += dp[i - 1][j - k];
                        }
                    }
                }
            }

            //将结果保存在List中
            List<Map.Entry<Integer, Double>> result = new ArrayList<Map.Entry<Integer, Double>>();
            //一共有6 ^ n次种情况
            for(int i = n; i <= 6 * n; i++) {
                Map.Entry<Integer, Double> entry = new AbstractMap.SimpleEntry<Integer, Double>(i,
                        dp[n][i]/Math.pow(6, n));
                result.add(entry);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.dicesSum(1));
    }
}
