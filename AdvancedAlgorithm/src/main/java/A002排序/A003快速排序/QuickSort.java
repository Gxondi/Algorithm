package A002排序.A003快速排序;


public class QuickSort {
    public static int[] array = {1, 3, 2, 3, 1, 5, 6, 4, 8, 7, 9, 0, 11, 10, 12, 14, 13, 15, 17, 16};
    public static int first, last;

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(0,array.length-1);
        for (int i : array) {
            System.out.print(i+",");
        }
    }
    public void quickSort(int l, int r) {
        if (l >= r) {
            return;
        }
        int x = array[(int) (l + Math.random() * (r - l + 1))];
        partition(l,r,x);
        int left = first;
        int right = last;
        quickSort(l, left - 1);
        quickSort(right + 1, r);
    }

    private void partition(int l, int r, int x) {
        first = l;
        last = r;
        int i = l;
        while(i<=last){
            if(array[i]==x){
                i++;
            }else if(array[i] < x){
                swap(i++,first++);
            }else {
                swap(i,last--);
            }
        }

    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
