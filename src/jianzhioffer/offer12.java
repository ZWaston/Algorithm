package jianzhioffer;

/**
 * 牛客刷题链接：https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc?tpId=13&tqId=11218&tPage=4&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class offer12 {
    static class Solution {
        /**
         * 入口函数
         *
         * @param matrix    输入的一维字符串矩阵
         * @param rows      矩阵的行数
         * @param cols      矩阵的列数
         * @param str       要搜索的字符串
         * @return          是否找到，找到返回true，否则返回false
         */
        public static boolean hasPath(char[] matrix, int rows, int cols, char[] str)
        {
            //先对参数进行校验
            if(matrix == null || matrix.length != rows * cols || str == null || str.length < 1) {
                return false;
            }
            boolean[] visited = new boolean[matrix.length];//初始化都为false，默认

            //用一个只有一个元素的数组记录已经处理的str的个数
            //因为java没有指针，所以只能使用数组
            //初始设置为0，表示初始处理str的个数为0
            int[] pathLength= {0};

            //对matrix数组的每一个点为起始进行搜索
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if(hasPathCore(matrix, rows, cols, visited, str, i, j, pathLength)) {
                        return true;
                    }
                }
            }

            return false;
        }

        /**
         * 回溯搜索算法
         *
         * @param matrix        输入的一维字符串矩阵
         * @param rows          矩阵的行数
         * @param cols          矩阵的列数
         * @param visited       访问标记数组，表示当前数组的字符是否已经被访问
         * @param str           要搜索的字符串
         * @param row           当前处理的起始字符所在行号
         * @param col           当前处理的起始字符所在列号
         * @param pathLength    字符串str已经处理过的字符个数
         * @return               是否找到
         */
        public static boolean hasPathCore(char[] matrix, int rows, int cols,boolean[] visited,
                                   char[] str, int row, int col, int[] pathLength) {
            //递归终止条件
            if(pathLength[0] == str.length) {
                //说明当前str的字符串已经处理完毕，返回true
                return true;
            }

            //设置标志位，表示从matrix搜索是否与str有路径匹配
            boolean hasPath = false;

            //满足位置合法，当前matrix的起始字符串等于str当前要处理的字符且该起始点未被访问过，
            //因为不允许重复进入矩阵的格子.
            if(row>=0 && row<rows && col>=0 && col<cols &&
                    matrix[row * cols + col] == str[pathLength[0]] && !visited[row * cols + col]) {
                //说明当前matrix的字符与str的当前字符匹配
                pathLength[0]++;
                visited[row * cols + col] = true;

                //按照左上右下进行回溯
                hasPath = hasPathCore(matrix, rows, cols, visited, str, row , col - 1, pathLength)
                        || hasPathCore(matrix, rows, cols, visited, str, row - 1, col, pathLength)
                        || hasPathCore(matrix, rows, cols, visited, str, row , col + 1, pathLength)
                        || hasPathCore(matrix, rows, cols, visited, str, row + 1 , col, pathLength);

                //如果没有匹配的路径，说明需要回溯到前一个字符，并把访问标志改成false，因为回溯之后，可能还需要访问这个
                if(!hasPath) {
                    pathLength[0]--;
                    visited[row * cols + col] = false;
                }

            }

            return hasPath;
        }

        public static void main(String[] args) {
            System.out.println(hasPath("abtgcfcsjdeh".toCharArray(), 3, 4,
                    "bfce".toCharArray()) );
        }

    }
}
