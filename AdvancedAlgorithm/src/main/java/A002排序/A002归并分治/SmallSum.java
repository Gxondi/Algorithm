package A002排序.A002归并分治;

/**
 * 求小的数的和
 */
public class SmallSum {
    public static int[] array = {1,3,5,2,4,6};
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
        int mid = (l + r) / 2;
        return process(l, mid) + process(mid + 1, r) + merge(l, mid, r);
    }
    private static int merge(int l, int mid, int r) {
        //统计
        int res = 0;
        for (int i = l, j = mid + 1,sum = 0; j <= r; j++) {
            while (array[i]<=array[j]&&i<=mid) {
                sum += array[i++];
            }
            res += sum;
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
        return res;
    }

}
