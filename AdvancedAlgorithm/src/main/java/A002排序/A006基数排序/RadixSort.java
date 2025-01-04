package A002排序.A006基数排序;

import java.util.Arrays;

public class RadixSort {
    public static final int BASE = 10;
    public static int[] help = new int[100];
    public static int[] cnts = new int[BASE];

    public static void main(String[] args) {
        int[] arr = {1234,123132};
        sort(arr);
    }
    public static void sort(int[] arr){
        int n = arr.length;
        int min = arr[0];
        //转化负数
        for (int i = 0; i < n; i++) {
            min = Math.min(min,arr[i]);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] -= min;
            max = Math.max(max,arr[i]);
        }
        if(max != 0){
            radixSort(arr, n, bits(max));
        }
        for (int i = 0; i < n; i++) {
            arr[i] += min;
        }
    }

    private static int bits(int number) {
        if (number < 1 ){
            return 1;
        }
        int count = 0;
        while (number > 0){
            count++;
            number/=10;
        }
        System.out.println(count);
        return count;
    }

    /**
     * offset 偏移，取出每个位
     *
     * @param arr 数组整数
     * @param n 数组长度
     * @param bits
     */
    public static void radixSort(int[] arr, int n, int bits) {
        for (int offset = 1; bits > 0; offset *= BASE, bits--) {
            Arrays.fill(arr, 0);
            for (int i = 0; i < n; i++) {
                // 数字提取某一位
                //出现一次累加一次
                cnts[(arr[i] / offset) % BASE]++;
            }
            //处理成前缀累加次数
            for (int i = 1; i < BASE; i++) {
                cnts[i] = cnts[i] + cnts[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                //排序,基数出现次数作为下标就行排序，填入一个就--
                help[--cnts[(arr[i] / offset) % BASE]] = arr[i];
            }
            for (int i = 0; i < n; i++) {
                arr[i] = help[i];
            }
        }
    }
}
