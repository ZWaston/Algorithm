package jianzhioffer;

import java.util.ArrayList;

public class offer40 {
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if(input == null || input.length == 0 || k <= 0 || k > input.length)
            return result;
        int index = Partition(input,0,input.length - 1);
        while(index != k - 1){
            if(index > k - 1){
                index = Partition(input,0,index - 1);
            }else{
                index = Partition(input,index + 1,input.length - 1);
            }
        }
        for (int i = 0; i < k;i++){
            result.add(input[i]);
        }
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
        int[] input = {4,5,1,6,2,7,3,8};
        System.out.println(GetLeastNumbers_Solution(input,8).toString());
    }
}
