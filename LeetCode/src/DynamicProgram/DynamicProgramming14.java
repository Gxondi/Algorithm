package DynamicProgram;

import Tree.IsFullAndCBTree;

public class DynamicProgramming14 {
    public int right(int[] arr){
        if (arr == null|| arr.length <2){
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum+=i;
        }
        if (arr.length%2 == 0){
            return process(arr,0,sum>>1,arr.length/2);
        }else {
            return Math.max(process(arr,0,sum>>1,arr.length/2),process(arr,0,sum>>1,arr.length/2+1));
        }
    }
    private int process(int[] arr, int index, int rest,int picks) {
        if (index == arr.length){
            return picks == 0?0:-1;
        }
        int p1 = process(arr,index+1,rest,picks-1);
        int next = -1;
        int p2 = -1;
        if (arr[index] >= rest){
            next = process(arr,index+1,rest-arr[index],rest-1);
        }
        if (next!=-1){
            p2 = arr[index] + next;
        }
        return Math.max(p1,p2);
    }
}
