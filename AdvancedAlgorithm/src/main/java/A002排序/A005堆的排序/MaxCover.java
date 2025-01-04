package A002排序.A005堆的排序;

import java.util.Arrays;


/**
 * 计算N个线段的最大重合数
 */

public class MaxCover {
    public static int[][] line = {{1, 2}, {2, 3}, {1, 3}};
    public static int n = line.length;
    //存放线段尾值
    public static int[] heap = new int[100];
    public static int size;

    public static void main(String[] args) {
        int i = maxCover();
        System.out.println(i);
    }

    public static int maxCover() {
        size = 0;
        Arrays.sort(line, 0, n, (o1, o2) -> o1[0] - o2[0]);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (size > 0 && heap[0] <= line[i][0]) {
                pop();
            }
            add(line[i][1]);
            ans = Math.max(ans, size);
        }
        return ans;
    }

    private static void pop() {
        swap(0, --size);
        int i = 0;
        int l = 1;
        while (l < size) {
            int best = l + 1 < size && heap[l + 1] < heap[l] ? l + 1 : l;
            if (best == i) {
                break;
            }
            swap(best, i);
            i = best;
            l = i * 2 + 1;
        }
    }

    private static void add(int i) {
        heap[size] = i;
        int x = size++;
        while (heap[x] < heap[(i - 1) / 2]) {
            swap(x, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}
