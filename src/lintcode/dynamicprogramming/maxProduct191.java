package lintcode.dynamicprogramming;

/**
 * 描述:
 找出一个序列中乘积最大的连续子序列（至少包含一个数）。
 样例:
 比如, 序列 [2,3,-2,4] 中乘积最大的子序列为 [2,3] ，其乘积为6。

 @author wangcheng zhang
 lintcode链接：https://www.lintcode.com/problem/maximum-product-subarray/description
 */
public class maxProduct191 {
    /** [2,3,-2,4]中乘积最大的子序列为[2,3]，其乘积为6.
     * 思路分析：访问每个点时，以该点为子序列的末尾的乘积最大子序列，
     * 要么是该点本身，要么是该点乘以前一点为末尾的最大子序列乘积。
     * 因为负负得正，因此要保存每个点为末尾时，其乘积最大和最小的子序列乘积值。
     */
    public static class Solution {
        /**
         * @param nums: An array of integers
         * @return: An integer
         */
        public int maxProduct(int[] nums) {
            // write your code here
            int[] dp_min = new int[nums.length];
            int[] dp_max = new int[nums.length];
            dp_min[0] = nums[0];
            dp_max[0] = nums[0];
            for(int i = 1; i < nums.length; i++) {
                //每次以i为末尾子序列的最大乘积要么是nums[i]，要么是前面的最大乘以nums[i]，要么是前面的最小乘以最小nums[i]
                dp_max[i] = Math.max(nums[i],
                        Math.max(dp_max[i - 1]*nums[i], dp_min[i - 1]*nums[i] ));
                dp_min[i] = Math.min(nums[i],
                        Math.min(dp_max[i - 1]*nums[i], dp_min[i - 1]*nums[i] ));
            }

            int result = dp_max[0];
            //注意我们要求的结果不是dp_max[nums.length - 1]，而是dp_max数组中最大的那个
            for(int i = 1; i < dp_max.length; i++) {
                if(result < dp_max[i]) result = dp_max[i];
            }
            return result;

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {2,3,-2,4};
        int result = solution.maxProduct(arr);
        System.out.println(result);
    }
}
