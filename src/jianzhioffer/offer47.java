package jianzhioffer;

/**
 * 在一个m*n的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于0）。
 * 从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，
 * 求拿到礼物的最大价值。例如，对于如下棋盘
 *
  1    10   3    8
 12   2    9    6
 5    7    4    11
 3    7    16   5
 * 礼物的最大价值为1+12+5+7+7+16+5=53。
 */
public class offer47 {
    /**
     * 思路1：动态规划
     * f(i,j) = max{f(i-1,j) , f(i,j-1)} + gift(i,j)
     * @param gift
     * @return
     */
    public static int getMaxValue1(int[][] gift){
        int rows = gift.length;
        if(gift == null || rows <= 0)
            return 0;
        int cols = gift[0].length;
        if(cols <= 0)
            return 0;
        int[][] maxValues = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int left = 0;
                int up = 0;
                if(i > 0)
                    up = maxValues[i - 1][j];
                if(j > 0)
                    left = maxValues[i][j - 1];
                maxValues[i][j] = Math.max(left,up) + gift[i][j];
            }
        }
         return maxValues[rows - 1][cols - 1];
    }

    /**
     * 进一步优化，将二维数组降维
     * @param gift
     * @return
     */
    public static int getMaxValue2(int[][] gift){
        return 0;
    }

    public static void main(String[] args) {
        int [][] gift = {{1,10,3,8},{12,2,9,6},{5,7,4,11},{3,7,16,5}};
        int result = getMaxValue1(gift);//53
        System.out.println(result);
    }
}
