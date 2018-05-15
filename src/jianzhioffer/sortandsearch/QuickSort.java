package jianzhioffer.sortandsearch;

/**
 * 是一个整型数组的快速排序的简单实现，未优化的地方有：
 * 1、未对输入数组shuffle，且选取数组第一个作为主元，因此没有消除输入的依赖性
 * 2、左侧扫描 是遇到 大于等于 切分元素值的元素停下，这样避免“处理只有若干种元素值的数组时的运行时间是平方级别的”，但是会
 * 造成不必要的相同元素值进行交换，如：所有元素值都是5
 * 博客地址：https://blog.csdn.net/u013885699/article/details/80239399
 */
public class QuickSort{

    /**
     * 此函数用于交换数组任意两个元素的位置
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        //健壮性判断
        if(arr == null || arr.length <= 0) {
            System.out.println("数组为空");
            return;
        }
        //交换下标分别为i和j的元素值
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 此函数是快排中的划分算法，实现了一趟快速排序，以第一个元素为主元，
     * 本函数运行结束后使得主元左侧元素均小于主元，主元右侧元素大于主元。
     * @param arr   待排序的数组
     * @param start
     * @param end
     * @return 返回一趟排序后主元的下标
     */
    private static int partition(int[] arr, int start, int end) {
        int i = start,j = end + 1;
        //选择第一个元素为主元
        int key = arr[start];

        while(true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while(arr[++i] < key) if(i == end) break;      // 还需要检查指针i是否越界,less是a[++i] < v，即遇到 大于等于 切分元素值的元素时停下
            while(key < arr[--j]) if(j == start) break;    // 同样需要检查指针j是否越界
            if(i >= j) break;               // 循环终止条件
            swap(arr, i, j);                // 交换下标分别i、j的元素位置
        }
        swap(arr, start, j);            // 将v = a[j]放入正确的位置，注意是与j交换而不是i
        System.out.println("某一趟排序结果:"+printArray(arr));
        return j;                       // a[lo .. j-1] <= a[j] <= a[j+1 .. hi] 达成

    }

    /**
     * 快速排序的递归函数
     * @param arr       待排序的数组
     * @param start     数组起始下标
     * @param end       数组结束下标
     */
    private static void QuickSort(int[] arr, int start, int end) {
        if(start >= end) return;
        int j = partition(arr, start, end);        // 切分
        QuickSort(arr, start, j - 1);        // 对左半部分a[start .. j-1]排序
        QuickSort(arr, j + 1, end);         // 对右半部分a[j+1 .. end]排序
    }

    /**
     * 此函数为快排的入口函数
     * @param arr
     */
    public static void QuickSort(int[] arr) {
        // 健壮性判断
        if(arr == null || arr.length <= 0) {
            System.out.println("数组为空");
            return;
        }
        // 通过递归进行快排
        QuickSort(arr, 0, arr.length -  1);
    }

    public static String printArray(int[] arr) {
        // 健壮性判断
        if(arr == null) {
            System.out.println("数组为空");
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i] + " ");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        //测试案例
        int[] arr = {2,12,34,34,56,623,21};
        System.out.println("before sort：" +  printArray(arr));
        QuickSort(arr);
        System.out.println("after sort：" +  printArray(arr));

    }

}
