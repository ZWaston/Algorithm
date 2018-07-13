package jianzhioffer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 找出数组中出现次数超过一半的数字
 */
public class offer39 {
    /**
     * 第一种思路：用HashMap保存对应次数信息
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution(int [] array) {
        if(array == null || array.length == 0)
            return 0;
        HashMap<Integer,Integer> map = new HashMap();
        for(int i = 0; i < array.length; i++){
            int count = 0;
            if(map.get(array[i]) != null ){
                count = map.get(array[i]);
            }
            map.put(array[i],++count);
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue()*2 > array.length){
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 利用数组的特性，数组中一个数字的个数超过数组长度一半，说明它出现的次数比
     * 其他所有数字出现次数之和还要多。遍历整个数组，记录两个值：正在遍历的值 和 次数。
     * 次数一旦发现下一个数字与保存的数字相同，+1；反之，-1；若次数等于0，将值设为下一
     * 个数字，次数重置为1。
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution2(int [] array){
        if(array == null || array.length == 0)
            return 0;
        int result = array[0];
        int times = 1;
        for(int i = 1; i < array.length; i++){
            if(times == 0){
                //如果times=0，需要保存下一个数字，并把次数设置为1
                result = array[i];
                times = 1;
            }else if(array[i] == result){
                //如果下一个数字与保存的result相同，次数加一
                times++;
            }else{
                //否则，次数减1
                times--;
            }
        }
        //此时得到result，但还需要判断数组中可能不存在超过一半这样的数字
        int num = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == result)
                num++;
        }
        if(num * 2 <= array.length)
            result = 0;

        return result;
    }

    /**
     * 利用快排的Partition思想，缺点：需要改变数组的顺序
     * 若有数字超过一半，那么排序后这个数字一定是中位数(中位数下标为：middle = n/2，n为数组长度)
     * 随机选一个数字，将比它小的放左边，比它大的放右边，如果这样利用快排的Partition思想之后得到的
     * 下标index 恰好等于 middle，说明这个数字就是我们要求的数字。
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution3(int [] array){
        if(array == null || array.length == 0)
            return 0;
        int middle = array.length / 2;
        int start = 0,end = array.length - 1;
        int index = Partition(array,start,end);
        while(index != middle){
            if(index > middle){
                //说明中位数在左边
                end = index - 1;
                index = Partition(array,start,end);
            }else {
                //说明中位数在右边
                start = index + 1;
                index = Partition(array,start,end);
            }
        }
        int result = array[index];
        //此时得到result，但还需要判断数组中可能不存在超过一半这样的数字
        int num = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == result)
                num++;
        }
        if(num * 2 <= array.length)
            result = 0;

        return result;
    }

    private static int Partition(int [] array, int start, int end){
        int i = start,j = end + 1;
        int key = array[start];//选取第一个元素作为主元
        while(true){
            if(i != end)
                while(array[++i] < key) if(i == end) break;//找出第一个大于等于key的下标，注意判断i是否会越界
            if(j != start)
                while(array[--j] > key) if(j == start) break;//找出第一个小于等于key的下标，注意判断i是否会越界
            if(i >= j)
                break;
            swap(array,i,j);//交换i，j的位置
        }
        swap(array,start,j);//交换j与start的位置
        return j;// a[lo .. j-1] <= a[j] <= a[j+1 .. hi] 达成
    }
    private static void swap(int [] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1};
        System.out.println(MoreThanHalfNum_Solution(arr));
        System.out.println(MoreThanHalfNum_Solution2(arr));
        System.out.println(MoreThanHalfNum_Solution3(arr));//会改变数组顺序
    }
}
