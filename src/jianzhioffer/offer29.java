package jianzhioffer;

import java.util.ArrayList;

public class offer29 {
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = matrix.length;//行数
        if(n == 0) return result;
        int m = matrix[0].length;//列数
        if(m == 0) return result;

        //每次都是打印矩阵的外围一圈，起点都是(start,start)
        int loopNum = (Math.min(n,m) - 1)/2 + 1;//这个是start的取值
        for(int start = 0; start < loopNum; start++){
            int endX = m - start - 1;
            int endY = n - start - 1;
            //从左至右打印一行
            for(int i = start;i <= endX;i++){
                int num = matrix[start][i];
                result.add(num);
            }
            //从上到下打印一列，要考虑没有这一列的情况
            if(start < endY){
                for(int i = start + 1;i <= endY;i++){
                    int num = matrix[i][endX];
                    result.add(num);
                }
            }

            //从右到左，至少需要两行两列
            if(start < endX && start < endY){
                for(int i = endX - 1;i >= start;i--){
                    int num = matrix[endY][i];
                    result.add(num);
                }
            }

            //从下到上，至少是三行两列
            if(start < endX && start < endY - 1){
                for(int k = endY - 1;k >= start + 1;k--){
                    int num = matrix[k][start];
                    result.add(num);
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr = new int[4][4];
        int count = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                count++;
                arr[i][j] = count;
            }
        }
        ArrayList<Integer> result = printMatrix(arr);
        for(int i : result){
            System.out.print(i + " ");
        }
    }
}
