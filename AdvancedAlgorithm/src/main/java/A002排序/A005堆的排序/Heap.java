package A002排序.A005堆的排序;


public class Heap {
    public static void main(String[] args) {

    }

    void heapSort(int arr[]) {
        int n = arr.length;
        //先进行向上大根堆排序
        for (int i = 0; i < n; i++) {
            heapInsert(arr, i);
        }
        //大根堆最后的值最大，减掉，不进入循环，从新开始向下调整大根堆
        int size = n;
        while (size > 1) {
            swap(arr, 0, size--);
            heapify(arr, 0, size);
        }
    }

    //向上调整大根堆
    void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    //向下调整大根堆
    void heapify(int[] arr, int i, int size) {
        int l = (i * 2) + 1;
        while (l < size) {
            int best = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;
            if (best == i) {
                break;
            }
            swap(arr, best, i);
            i = best;
            l = (i * 2) + 1;
        }
    }

    private void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
