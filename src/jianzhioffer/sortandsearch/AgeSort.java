package jianzhioffer.sortandsearch;

/**
 * 不同排序算法适用的场景不尽相同，而且在一些特定的情况下复杂度可能会变成O(n^2)。
 * 如果要求实现一个排序算法，一定要看清约束条件：
 * 1、应用的环境是什么？
 * 2、约束条件是什么？
 *
 * 如此题：公司员工年龄有一个范围，允许的范围是0~99，而员工有几千万人，
 * 如果用O(n)的复杂度进行排序，允许使用辅助内存空间，但不得超过O(n)。
 * 剑指offer的P82。
 *
 * 思路：我们可以使用一个辅助数组timesOfAges来统计每个年龄出现的次数，某个年龄
 * 出现了多少次，就在数组ages里设置几次该年龄，这就相当于给数组ages排序了。
 * 这样仅仅只用了100的整数数组的辅助空间换来了O(n)的时间效率，非常有价值。
 */
public class AgeSort {
    private static void SortAges(int[] ages) {
        if(ages == null || ages.length <= 0) {
            return;
        }
        int maxAges = 99;
        int[] timesOfAges = new int[maxAges + 1];

        //使用一个辅助数组timesOfAges来统计每个年龄出现的次数
        for(int i = 0; i < ages.length; i++) {
            //健壮性判断
            if(ages[i] < 0 || ages[i] > 99) {
                System.out.println("年龄超出范围");
            }
            timesOfAges[ages[i]] ++;
        }
        int index = 0;
        //利用数组timesOfAges对ages进行排序
        for(int i = 0; i <= maxAges; i++) {
            //年龄为i的员工一共出现了多少次
            for(int j = 0; j < timesOfAges[i]; j++) {
                ages[index] = i;
                index++;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {2,6,5,4,7,6,4,6};
        SortAges(arr);
        for(int i:arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
