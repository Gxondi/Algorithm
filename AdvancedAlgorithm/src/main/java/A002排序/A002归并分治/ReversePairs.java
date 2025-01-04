package A002排序.A002归并分治;

public class ReversePairs {
    public static int[] array = {1, 3, 2, 3, 1};
    public static int[] help = new int[0];

    public static void main(String[] args) {
        int n = array.length;
        help = new int[n];
        int process = process(0, n - 1);
        System.out.println(process);
    }

    private static int process(int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (r + l) / 2;
        return process(l, mid) + process(mid + 1, r) + merge(l, mid, r);
    }

    private static int merge(int l, int mid, int r) {
        int count = 0;
        for (int i = l, j = mid + 1; i <= mid; i++) {
            while (j <= r && array[i] > 2 * array[j]) {
                j++;
            }
            //j移动几个，就有几个答案加上
            count += j - mid - 1;
        }
        //归并排序
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
        return count;
    }
}
