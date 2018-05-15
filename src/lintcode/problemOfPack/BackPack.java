package lintcode.problemOfPack;

/**
 * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]。
 * 物体只能选择放与不放。
 * @author wangcheng zhang
 *
样例：
如果有4个物品[2, 3, 5, 7]
如果背包的大小为11，可以选择[2, 3, 5]装入背包，最多可以装满10的空间。
如果背包的大小为12，可以选择[2, 3, 7]装入背包，最多可以装满12的空间。
函数需要返回最多能装满的空间大小。
挑战：
O(n x m) time and O(m) memory.
O(n x m) memory is also acceptable if you do not know how to optimize memory.

 lintcode链接：https://www.lintcode.com/problem/backpack/description
 */
public class BackPack {
    /**
     * 问题分析：是典型的01背包问题，由于空间复杂度要求为O(m)，所以使用一维数组，
     * 注意内部循环的顺序
     */
    public static class Solution {
        /**
         * @param m: An integer m denotes the size of a backpack
         * @param A: Given n items with size A[i]
         * @return: The maximum size
         */
        public int backPack(int m, int[] A) {
            // write your code here
            //创建一维数组，初始状态表示没有任何物品时，背包载重为i的情况下，能装多满，故初始化为0
            int[] dp = new int[m + 1];    //java已经默认初始化为0
            for(int i = 0; i < A.length; i++) {
                //降序，保证第i次循环每一次求dp[j]用的都是上一次i-1循环的dp数组，而不是已经更改过的数组
                for(int j = m; j >= 0; j--) {
                    if(j - A[i] >= 0)
                         dp[j] = Math.max(dp[j - A[i]] + A[i],dp[j]);
                }
            }
            return dp[m];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {2,3,5};
        int result = solution.backPack(11, arr);
        System.out.println(result);
    }
}
