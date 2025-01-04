package A002排序.A001归并排序;

public class MergeSort {
    static int[] array = new int[]{1, 3, 23, 2, 2, 5, 8, 2, 1, 0};
    static int[] array1 = new int[]{1, 3, 23, 2, 2, 5, 8, 2, 1, 0};
    static int[] help = new int[array.length];

    public static void main(String[] args) {
        int length = array.length - 1;
        process1(0, length);
        for (int i : array) {
            System.out.println(i);
        }
        System.out.println("###################################");
        //非递归版本-step跳跃，步长每次*2
        process2();
        for (int i : array1) {
            System.out.println(i);
        }
    }

    private static void process1(int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        process1(l, mid);
        process1(mid + 1, r);
        merge(l, mid, r);
    }

    //非递归版本
    private static void process2() {
        int n = array1.length;
        for (int l, m, r, step = 1; step < n; step *= 2) {
            l = 0;
            while (l < n) {
                m = l + step - 1;
                //右边界没有值
                if (m + 1 >= n) {
                    break;
                }
                r = Math.min(n - 1, l + 2 * step - 1);
                merge2(l, m, r);
                l = r + 1;
            }
        }
    }

    private static void merge(int l, int mid, int r) {
        int index = l;
        int a = l;
        int b = mid + 1;
        while (a <= mid && b <= r) {
            help[index++] = array[a] <= array[b] ? array[a++] : array[b++];
        }
        while (a <= mid) {
            help[index++] = array[a++];
        }
        while (b <= r) {
            help[index++] = array[b++];
        }
        for (int i = l; i <= r; i++) {
            array[i] = help[i];
        }
    }

    private static void merge2(int l, int mid, int r) {
        int index = l;
        int a = l;
        int b = mid + 1;
        while (a <= mid && b <= r) {
            help[index++] = array1[a] <= array1[b] ? array1[a++] : array1[b++];
        }
        while (a <= mid) {
            help[index++] = array1[a++];
        }
        while (b <= r) {
            help[index++] = array1[b++];
        }
        for (int i = l; i <= r; i++) {
            array1[i] = help[i];
        }
    }
}
