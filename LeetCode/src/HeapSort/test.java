package HeapSort;

import javax.servlet.http.HttpServlet;

public class test {
    private static void swap(int[] nums, int j, int left) {
        int temp = nums[left];
        nums[left] = nums[j];
        nums[j] = temp;
    }
    /*
    * 排序思路：
    *
    * 1.首先数组一个一个传值进入
    * 2.HeapInsert方法会进行大根堆排序
    * 3.大根堆之后需要把0位置和最末尾位置的数置换，然后切割这个数与堆的联系
    * 4.然后重新把堆进行大根堆排序！因为从末尾位置置换到0位置的数不知道大小
    * 所以需要Heapify方法进行大根堆排序
    * */
    private static void HeapSort(int[] nums){
        if(nums == null || nums.length<2){
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            HeapInsert(nums,i);//大根堆
        }
        int HeapSize = nums.length;
        swap(nums,0,--HeapSize);//置换，切割
        while(HeapSize>0){
            Heapify(nums,0,HeapSize);
            swap(nums,0,--HeapSize);
        }
    }
    private static int findLargest(int[] nums , int k){
        for (int i = 0; i < nums.length; i++) {
            HeapInsert(nums,i);//大根堆
        }
        int heapSize = nums.length;
        for (int i = nums.length-1; i >=nums.length-k+1; i--) {
            swap(nums,0,i);
            --heapSize;
            Heapify(nums,0,heapSize);
        }
        return nums[0];
    }
    private static void HeapInsert(int[] nums, int index) {
        while(nums[index] > nums[(index-1)/2]){
            swap(nums,index,(index-1)/2);
            index = (index-1)/2;
        }
    }
    private static void Heapify(int[] nums, int index , int HeapSize){
        int left = 2*index+1;
        while(left<HeapSize){
            int largest = left+1<HeapSize && nums[left + 1] > nums[left]?left+1:left;//把左右树中最大值下标给它
            largest = nums[largest]>nums[index]?largest:index;//如果下标为largest的数大于index为下标的数那么赋值
            if(largest == index){
                break;//如果当前下标都相等说明，两个数的大小也相等，结束循环
            }
            swap(nums,largest,index);
            index = largest;
            left = index*2+1;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,3,2,4,6,5,9,8,7};
        HeapSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        int[] b = {3,2,3,1,2,4,5,5,6};
        System.out.println(findLargest(b,4));
    }
}
