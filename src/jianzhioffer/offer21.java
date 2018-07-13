package jianzhioffer;

/*
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
使得所有的奇数位于数组的前半部分，所有的偶数位于位于数
组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class offer21 {
    //未保证数组中相对顺序不变，时间复杂度为O(n)
    public void reOrderArray1(int [] array) {
        int p1 = 0;
        int p2 = array.length - 1;
        while(p1 < p2){
            if(array[p1] % 2 == 0){
                if(array[p2] % 2 == 1){
                    //p1为偶数,p2为奇数，交换
                    int tmp = array[p1];
                    array[p1] = array[p2];
                    array[p2] = tmp;
                }else {
                    //p1为偶数,p2为偶数
                    p2--;
                }

            }else {
                if(array[p2] % 2 == 0) {
                    //p1为奇数，p2为偶数
                    p1++;
                    p2--;
                }else {
                    //p1为奇数，p2为奇数
                    p1++;
                }
            }
        }
    }
    //若要保证相对顺序，时间复杂度就没办法保证为O(n)

    /**
     * 步骤：
     * 1、先找到第一个偶数的位置i
     * 2、从i开始，再找到第一个奇数的位置j
     * 3、将[i .. j-1]的数字全部后移一位，然后将奇数放在下标为i的位置
     * 4、将i++，此时偶数的位置为i。循环进行上述操作
     * 5、终止条件：找不到奇数的位置j
     * @param array
     */
    public void reOrderArray(int [] array){
        if(array == null || array.length < 1)
            return;
        int i = 0,j;
        while(true){
            //先找到第一个偶数的位置i
            while(i < array.length && isEven(array[i]))
                i++;
            j = i + 1;
            //从i开始，再找到第一个奇数的位置j
            while(j < array.length && !isEven(array[j]))
                j++;
            if(j < array.length){
                //如果j查找成功
                //将[i .. j-1]的数字全部后移一位，然后将奇数放在下标为i的位置,将i++
                int tmp = array[j];
                for(int k = j - 1;k >= i;k--){
                    array[k + 1] = array[k];
                }
                array[i++] = tmp;
            }else {
                //查找失败
                return;
            }
        }
    }
    private boolean isEven(int num){
        if(num % 2 == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        offer21 solution = new offer21();
        int[] array = {2,2,5,2};
        solution.reOrderArray(array);
        for (int i = 0;i < array.length;i++){
            System.out.println(array[i]);
        }
    }
}
