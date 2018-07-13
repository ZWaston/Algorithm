package jianzhioffer;

/**
 * O(n)时间复杂度求连续子数组的最大和
 */
public class offer42 {
    /**
     * 思路1：利用连续子数组最大和的性质
     * 若一个子连续和<0，则可以直接抛弃，因为后面连续和加上前面这个会变得更小
     * @param array
     * @return
     */
    public static int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length == 0)
            return -1;
        int resultSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < array.length; i++){
            if(curSum > 0){
                curSum += array[i];
            }else{
                curSum = array[i];
            }
            resultSum = Math.max(resultSum,curSum);
        }
        return resultSum;
    }

    /**
     * 思路2：利用动态规划的思想，其实和思想1差不多
     * f(i)表示以第i个数字结尾的子数组的最大和
     * 1、若f(i - 1) < 0 ，f(i) = array[i]
     * 2、否则，f(i) = f(i - 1) + array[i]
     * @param array
     * @return
     */
    public static int FindGreatestSumOfSubArray2(int[] array){
        int[] f = new int[array.length];
        f[0] = array[0];
        for(int i = 1; i < array.length; i++){
            if(f[i-1] < 0)
                f[i] = array[i];
            else{
                f[i] = f[i - 1] + array[i];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < f.length; i++){
            if(max < f[i])
                max = f[i];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,-2,3,10,-4,7,2,-5};
        int[] arr1 = {-11,-2,-3};
        System.out.println(FindGreatestSumOfSubArray2(arr1));
    }
}
