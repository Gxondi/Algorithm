package A002排序.A004随机选择;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class FindKthLargest {
    public static int[] array = {3,2,1,5,6,4};
    public static int first, last;

    public static void main(String[] args) {
        int k = 2;
        int kthLargest = findKthLargest(array, array.length - k);
        System.out.println(kthLargest);
    }

    public static int findKthLargest(int[] array, int k) {
        int ans = 0;
        for (int l = 0, r = array.length - 1; l <= r; ) {
            partition(l, r, array, array[(int) (l + Math.random() * (r - l + 1))]);
            //排好序，左边界，右边界的找
            if(k < first){
                r = first-1;
            } else if (k > last) {
                l = last + 1;
            }else {
                ans = array[k];
                break;
            }
        }
        return ans;
    }

    public static void partition(int l, int r, int[] array, int x) {
        first = l;
        last = r;
        int i = l;
        while (i <= last) {
            if (array[i] == x) {
                i++;
            } else if (array[i] < x) {
                swap(i++, first++);
            } else {
                swap(i, last--);
            }
        }
    }

    public static void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
