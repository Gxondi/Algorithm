package BaseAlgorithm;

import com.sun.org.apache.bcel.internal.generic.SWAP;

//选择排序
public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {1,8,7,9,7,8,6,4,7,5,10};
        int arr1[] = {123,8,37,93,7,8,46,4,7,55,110};
        int arr2[] = {8,4,1,3,6,45,7,5,9,4,6,9,87,10};
       //printArray(arr);
       //bubbleSort(arr);
       //selectsort(arr1);
       //printArray(arr);
        //printArray(arr1);
        InsertSort1(arr2);
        printArray(arr2);
    }

    private static void InsertSort(int[] arr2) {
        int n = arr2.length;
        for (int i = 1; i < n; i++) {
            int indexArr = i;
            while(indexArr-1 >= 0&& arr2[indexArr-1]>arr2[indexArr]){
                swap(arr2,indexArr,indexArr-1);
                indexArr--;
            }
        }
    }
    private static  void InsertSort1(int[] arr){
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int pre = i - 1; pre>=0&&arr[pre]>arr[pre+1]; pre--) {
                swap(arr,pre,pre+1);
            }
        }
    }

    private static void selectsort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) { //选择下标第0个数，然后从下标1的数往后遍历，小于0的替换数值，然后i++成为1 进入for循环j 等于 2
                min = arr[j]<arr[min]?j:min;
            }
            swap(arr,i,min);
        }
    }

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = n-1; i >=0 ; i--) {
            for (int j = 1; j < n; j++) {
                if (arr[j-1] > arr[j]){
                    swap(arr,j,j-1);
                }
            }
        }
    }


    private static void swap(int[] arr , int i , int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
