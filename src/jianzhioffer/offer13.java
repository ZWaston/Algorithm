package jianzhioffer;

public class offer13 {
    static class Solution {
        /**
         * 入口函数
         * @param threshold     位数之和的阈值
         * @param rows          格子的行数
         * @param cols          格子的列数
         * @return              机器人能够到达格子的数量
         */
        static int movingCount(int threshold, int rows, int cols)
        {
            //对初始参数进行健壮性判断
            if(threshold < 0 || rows <= 0 || cols <= 0) {
                return 0;
            }

            //用于标识机器人已经到达过该格子
            boolean[] visited = new boolean[rows * cols];   //java默认初始化为false
            int count = movingCountCore(threshold, rows, cols,0, 0, visited);
            return count;
        }

        /**
         * 回溯搜索算法
         *
         * @param threshold     位数之和的阈值
         * @param rows          格子的行数
         * @param cols          格子的列数
         * @param row           将要访问的各自行号
         * @param col           将要访问的各自列号
         * @param visited       格子的访问标记
         * @return              返回从row、col格子出发，机器人一共能到达多少格子
         */
        static int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[] visited) {
            int count = 0;
            //判断机器人是否能够进入该格子
            if(check(threshold, rows, cols, row, col, visited)) {
                //如果该格子能够进入，则标记该格子已被访问
                visited[row * cols + col] = true;
                //从左上右下的顺序进行移动，count要加1，代表此时访问的格子
                count = 1 + movingCountCore(threshold, rows, cols, row, col - 1, visited)
                        + movingCountCore(threshold, rows, cols, row - 1, col, visited)
                        + movingCountCore(threshold, rows, cols, row, col + 1, visited)
                        + movingCountCore(threshold, rows, cols, row + 1, col, visited);
            }
            return count;
        }

        /**
         * 得到一个数字的数位之和
         * @param number        输入的数字
         * @return              返回number的数位之和
         */
        static int getDigitSum(int number) {
            int sum = 0;
            while(number > 0) {
                sum += number % 10;
                number /= 10;
            }
            return sum;
        }

        /**
         * 判断机器人是否能够访问该格子
         *
         * @param threshold     位数之和的阈值
         * @param rows          格子的行数
         * @param cols          格子的列数
         * @param row           将要访问的各自行号
         * @param col           将要访问的各自列号
         * @param visited       格子的访问标记
         * @return              返回机器人是否能够访问该格子
         */
        static boolean check(int threshold, int rows, int cols, int row, int col, boolean[] visited) {
            if(row >= 0 && row < rows && col >= 0 && col < cols && getDigitSum(row) + getDigitSum(col) <= threshold
                    && !visited[row * cols + col]) {
                return true;
            }
            return false;
        }

        public static void main(String[] args) {
            System.out.println(movingCount(100, 3 , 4));
        }
    }
}
