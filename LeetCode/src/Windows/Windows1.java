package Windows;

import java.util.LinkedList;

public class Windows1 {
    public int GetCount(int[] arr, int num){
        int count = 0;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max,arr[i]);
                    min = Math.min(min,arr[i]);
                }
                if (max - min<=num){
                    count++;
                }
            }
        }
        return count;
    }
    public int GetWindowsCount(int[] arr, int num){
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int count = 0;
        int R = 0;
        for (int L = 0; L < arr.length; L++) {
            while (R < arr.length){
                while (!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[R]){
                    qmax.pollLast();
                }
                qmax.addLast(R);
                while (!qmin.isEmpty()&&arr[qmin.peekLast()]>=arr[R]){
                    qmin.pollLast();
                }
                qmin.addLast(R);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] >= num){
                    break;
                }else {
                    R++;
                }
            }
            count+=R-L;
            if (qmax.peekFirst() == L){
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == L){
                qmin.pollFirst();
            }
        }
        return count;
    }
}
