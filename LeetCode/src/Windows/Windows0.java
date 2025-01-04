package Windows;

import javax.sound.midi.Soundbank;
import java.util.LinkedList;

public class Windows0 {
    public int[] GetMax(int[] arr ,int w){
        if (arr == null || arr.length == 0||w<1){
            return null;
        }
        int[] res = new int[arr.length - w +1];
        int ResIndex = 0;
        int L = 0;
        int R = w - 1;
        while (R < arr.length){
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max,arr[i]);
            }
            res[ResIndex++] = max;
            L++;
            R++;
        }
        return res;
    }
    public int[] GetWindowsMax(int[] arr ,int w){
        if (arr == null || arr.length == 0||w<1){
            return null;
        }
        int[] res = new int[arr.length - w +1];
        int ResIndex = 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        for (int R = 0; R < arr.length; R++) {
                while (!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[R]){
                    qmax.pollLast();
                }
                qmax.addLast(R);
                if (qmax.peekFirst() == R - w){
                    qmax.pollFirst();
                }
                //现在是否形成一个正常窗口，形成了的话给res赋值
                if (R>=w-1) {
                    res[ResIndex++] = arr[qmax.peekFirst()];
                }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        Windows0 w = new Windows0();
        int[] ints = w.GetMax(arr, 3);
        for (int anInt : ints) {
            System.out.print(anInt + ",");
        }
        int[] ints1 = w.GetWindowsMax(arr, 3);
        for (int i : ints1) {
            System.out.println(i);
        }

    }
}
